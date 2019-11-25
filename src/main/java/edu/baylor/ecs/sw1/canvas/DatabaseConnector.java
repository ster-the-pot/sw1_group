package edu.baylor.ecs.sw1.canvas;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;


import java.util.Arrays;

import org.bson.Document;
/**
 * The mongoDB database connector. Acts as a facade between the db and the java application
 * @author strafford
 *
 */
public class DatabaseConnector {
	
	static MongoClient client;
	
	public DatabaseConnector(String user, String db, char[] password) {
		
		MongoCredential credential =  MongoCredential.createCredential(user,db,password);
		client = new MongoClient(new ServerAddress("localhost",27017),Arrays.asList(credential));
	}
	/**
	 * given a username, returns all events as MongoCollection
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
	void addUserEvent(String username, Document event){
		return;
	}
	
	void addUserEvents(String username, Document[] events) {
		
	}
	
	
	
	
}

