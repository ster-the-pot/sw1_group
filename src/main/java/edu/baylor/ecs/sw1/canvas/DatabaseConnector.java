package edu.baylor.ecs.sw1.canvas;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import edu.baylor.ecs.sw1.event.Event;
import com.mongodb.MongoCredential;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

/** DESIGN PATTERN FACADE
 * The mongoDB database connector. Acts as a facade between the db and the java
 * application. This facade hides the MongoDB queries from the java application, handling the conversion
 * of internal events to mongoDB queries cleanly.
 * 
 * @author strafford
 *
 */
public class DatabaseConnector {

	static MongoClient client;
	static Logger log = Logger.getLogger(DatabaseConnector.class.getName());
	Integer count = 0;


	Document upsertOptions = new Document().append("upsert", Boolean.TRUE);
	MongoDatabase db;
	MongoCollection<Document> userdata;
	/**
	 * Constructor for Database Connector, accepts the MongoDB username, DB name, and password
	 * @param user
	 * @param db
	 * @param password
	 */
	public DatabaseConnector(String user, String db, String password) {

		MongoCredential credential = MongoCredential.createCredential(user, db, password.toCharArray());
		client = new MongoClient(new ServerAddress("18.224.202.17", 27017), Arrays.asList(credential));
		this.db = client.getDatabase("userdata");
		userdata = this.db.getCollection("userdata");
	}
	/**
	 * Closes the connection between the MongoDB server and the java application
	 */
	public void close() {
		client.close();
	}

	/**
	 * Get count of all events managed in the database
	 * 
	 * @param username
	 * @return
	 */
	
	public Integer getManagedEvents() {
		Integer count = 0;
		AggregateIterable<Document> result = userdata.aggregate(
				Arrays.asList(Aggregates.unwind("$events")));
		for(Document i : result) {
			count++;
		}
		return count;
	}
	/**
	 * Given a username, returns all non-ignored events for said user
	 * @param username
	 * @return
	 */
	public ArrayList<Document> getUserEvents(String username) {		
		AggregateIterable<Document> result = userdata.aggregate(
				Arrays.asList(Aggregates.match(Filters.eq("username", username)), Aggregates.unwind("$events"),
						Aggregates.match(Filters.not(new Document("events.ignore", true)))));

		ArrayList<Document> events = new ArrayList<>();
		MongoCursor<Document> curs = result.iterator();
		while (curs.hasNext()) {
			events.add((Document) curs.next().get("events"));
		}
		
		return events;
			
//		db.userdata.aggregate([
//		                       { $match: {username:"strafford"}},
//		                       {$unwind: '$events'},
//		                       {$match: {'events.ignore': {$ne: false}}},
//		                   ])


	}
	
	
	/**
	 * used to convert an event into a json-like object for placement into the database
	 * @param e
	 * @return
	 */
	public Map<String,Object> convertEventMap(Event e) {
		Map<String,Object> ret = new HashMap<>();
		ret.put("id", e.getEventID());
		ret.put("ignore",e.getIgnored());
		ret.put("completed",e.getCompleted());
		ret.put("name", e.getEventName());
		ret.put("description",e.getEventDescription());
		ret.put("due_at", e.getEndDateAsString());
		ret.put("course", e.getCourse());
		return ret;
	}
	/**
	 * Takes username and Event object and adds event into the database
	 * @param username
	 * @param event
	 */
	public void addUserEvent(String username, Event baseEvent) {
		
		Map<String,Object> event = this.convertEventMap(baseEvent);
		// Document to insert
				Document ev = new Document(event);
				// Document to Query
				// Document d = new Document().append("username", username).append("event", );

				
				// check if event currently exists
				Document checkInsertion = new Document("username", username).append("events",
						new Document("$elemMatch", new Document("id", event.get("id"))));

				Document check = userdata.find(checkInsertion).first();
				if (check == null) {
					// event does not exist, insert
					this.insertNonExist(username, event, userdata);
				} else {
					// event exists update/append operation
					insertExist(username, event, userdata);
				}
				// userdata.updateOne(, update, updateOptions)
				log.info("Added events for: "+ username);
				return;
	}

	/**
	 * takes username and adds given event into the database (json form)
	 * 
	 * @param username
	 * @param event
	 */
	
	void addUserEvent(String username, Map<String, Object> event) {
		// Document to insert
		Document ev = new Document(event);
		// Document to Query
		// Document d = new Document().append("username", username).append("event", );

		
		// check if event currently exists
		Document checkInsertion = new Document("username", username).append("events",
				new Document("$elemMatch", new Document("id", event.get("id"))));

		Document check = userdata.find(checkInsertion).first();
		if (check == null) {
			// event does not exist, insert
			this.insertNonExist(username, event, userdata);
		} else {
			// event exists update/append operation
			insertExistCanvas(username, event, userdata);
		}
		// userdata.updateOne(, update, updateOptions)
		log.info("Added events for: "+ username);

		return;
	}
	
	 
	/**
	 * Allows modification of a user's event details 
	 * @param username
	 * @param event
	 */
	public void changeEventDetails(String username, Event event) {
		//Map<String,Object> eventParse = new HashMap<String,Object>();
		this.insertExist(username, this.convertEventMap(event), userdata);
		log.info(username + "'s event id: " + event.getEventID() + "modified");

	}

	/**
	 * Returns the name of the database currently connected to
	 * @return
	 */
	public String getDBName() {
		MongoDatabase db = client.getDatabase("userdata");
		return db.getName();
	}


	private Document formatEventInsertionDoc(Map<String, Object> event, Document iVal) {
		if (event.containsKey("due_at")) {
			iVal.append("due_at", event.get("due_at"));
		}
		if (event.containsKey("type")) {
			iVal.append("type", event.get("type"));
		} else {
			iVal.append("type", "event");
		}
		return iVal;
	}

	/**
	 * insertion that preserves dependencies in MongoDB if event does not exists
	 */
	private void insertNonExist(String username, Map<String, Object> event, MongoCollection<Document> userdata) {
		// System.out.println("Event Does not Exists, Insert");
		// Apply correct db conditions (due_at $$ ignore and Assignment type)
		Document iVal = new Document("id", event.get("id")).append("ignore", Boolean.FALSE)
				.append("completed", Boolean.FALSE)
				.append("name", event.get("name")).append("course", event.get("course"));
		iVal = this.formatEventInsertionDoc(event, iVal);
		// insertion operation
		Document insert = new Document("username", username);
		Document eventPush = new Document("$push", new Document("events", iVal));
		UpdateResult result = userdata.updateOne(insert, eventPush);
		// System.out.println(result.toString());
	}

	
	private void insertExist(String username, Map<String, Object> event, MongoCollection<Document> userdata) {
		// query
		// System.out.println("Exists, Insert");
		// System.out.println(event.get("course"));
		Bson filter = Filters.and(Filters.eq("username", username), Filters.eq("events.id", event.get("id")));
		Bson setUpdate = Updates.combine(Updates.set("events.$.due_at", event.get("due_at")),
				Updates.set("events.$.name", event.get("name")), Updates.set("events.$.course", event.get("course")),
				Updates.set("events.$.ignore", event.get("ignore")),
				Updates.set("events.$.completed", event.get("completed")));
		UpdateResult result = userdata.updateOne(filter, setUpdate, new UpdateOptions().upsert(true));
		// System.out.println(result.getModifiedCount());

	}
	
	private void insertExistCanvas(String username, Map<String, Object> event, MongoCollection<Document> userdata) {
		// query
		// System.out.println("Exists, Insert");
		// System.out.println(event.get("course"));
		Bson filter = Filters.and(Filters.eq("username", username), Filters.eq("events.id", event.get("id")));
		Bson setUpdate = Updates.combine(Updates.set("events.$.due_at", event.get("due_at")),
				Updates.set("events.$.name", event.get("name")), Updates.set("events.$.course", event.get("course")));
		UpdateResult result = userdata.updateOne(filter, setUpdate, new UpdateOptions().upsert(true));
		// System.out.println(result.getModifiedCount());

	}

}
