package edu.baylor.ecs.sw1.canvas;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;


import java.util.Arrays;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
/**
 * The mongoDB database connector. Acts as a facade between the db and the java application
 * @author strafford
 *
 */
public class DatabaseConnector {
	
	static MongoClient client;
	
	Document upsertOptions = new Document().append("upsert", Boolean.TRUE);
	
	
	public DatabaseConnector(String user, String db, char[] password) {
		
		MongoCredential credential =  MongoCredential.createCredential(user,db,password);
		client = new MongoClient(new ServerAddress("18.224.202.17",27017),Arrays.asList(credential));
	}
	
	public void close() {
		client.close();
	}
	/**
	 * given a username, returns all events as MongoCollection
	 * @param username
	 * @return
	 */
	
	/**
	 * Get all event for a user as a MongoCollection
	 * @param username
	 * @return
	 */
	MongoCollection<Document>getUserEvents(String username){
		return null;
	}
	/**
	 * takes username and adds given event into the database
	 * @param username
	 * @param event
	 */
	void addUserEvent(String username, Map<String,Object> event){
		// Document to insert
		Document ev = new Document(event);
		//Document to Query
		//Document d = new Document().append("username", username).append("event", );
		
		MongoDatabase db = client.getDatabase("userdata");
		MongoCollection<Document> userdata = db.getCollection("userdata");
		//check if event currently exists
		Document checkInsertion = new Document("username", username)
				.append("events", new Document("$elemMatch",new Document("id",event.get("id"))));
		
		Document check = userdata.find(checkInsertion).first();
		if(check == null) {
			//event does not exist, insert
			this.insertNonExist(username, event, userdata);
		}else {
			//event exists update/append operation
			insertExist(username,event,userdata);
		}
		//userdata.updateOne(, update, updateOptions)
		return;
	}
	
	
	public String getDBName() {
		MongoDatabase db = client.getDatabase("userdata");
		return db.getName();
	}
	/**
	 * Take HTTP Map<String,Object> json type and convert into relevant Document for insertion
	 * @return
	 */
	public Document prepareEventInsertion() {
		return null;
	}
	
	private Document formatEventInsertionDoc(Map<String,Object> event, Document iVal) {
		if(event.containsKey("due_at")) {
			iVal.append("due_at", event.get("due_at"));
		}
		if(event.containsKey("type")) {
			iVal.append("type", event.get("type"));
		}else {
			iVal.append("type", "event");
		}
		return iVal;
	}
	/**
	 * insertion that preserves dependencies in MongoDB if event does not exists
	 */
	private void insertNonExist(String username, Map<String,Object> event, MongoCollection<Document> userdata) {
		//System.out.println("Event Does not Exists, Insert");
		//Apply correct db conditions (due_at $$ ignore and Assignment type)
		Document iVal = new Document("id",event.get("id")).append("ignore", Boolean.FALSE)
				.append("name", event.get("name")).append("course", event.get("course"));
		iVal = this.formatEventInsertionDoc(event,iVal);
		//insertion operation
		Document insert = new Document("username", username);
		Document eventPush = new Document ("$push", new Document("events",iVal));
		UpdateResult result = userdata.updateOne(insert, eventPush);
		//System.out.println(result.toString());
	}
	
	private void insertExist(String username, Map<String,Object> event, MongoCollection<Document> userdata) {
		//query
		//System.out.println("Exists, Insert");
		//System.out.println(event.get("course"));
		Bson filter = Filters.and(Filters.eq("username",username),Filters.eq("events.id",event.get("id")));
		Bson setUpdate = Updates.combine(Updates.set("events.$.due_at",event.get("due_at"))
				,Updates.set("events.$.name", event.get("name")),Updates.set("events.$.course",event.get("course")));
		UpdateResult result = userdata.updateOne(filter, setUpdate, new UpdateOptions().upsert(true));
		//System.out.println(result.getModifiedCount());

	}
	
	
}

