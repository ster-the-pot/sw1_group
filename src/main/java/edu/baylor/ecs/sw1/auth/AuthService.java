package edu.baylor.ecs.sw1.auth;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

/**
 * Authorization Service used to add accounts, sign in, and check account
 * information also serves as a Singleton Design Pattern
 * 
 * @author strafford
 *
 */
public class AuthService {

	static MongoClient client;
	private static AuthService self;

	MongoDatabase db;
	MongoCollection<Document> userdata;

	private AuthService(String user, String db, char[] password) {

		MongoCredential credential = MongoCredential.createCredential(user, db, password);
		client = new MongoClient(new ServerAddress("18.224.202.17", 27017), Arrays.asList(credential));
		this.db = client.getDatabase("userdata");
		this.userdata = this.db.getCollection("userdata");
	}

	static public AuthService getAuthService() {
		if (self == null) {
			self = new AuthService("java", "userdata", "cerny".toCharArray());
			return self;
		} else {
			return self;
		}
	}

	public void close() {
		client.close();
	}
	
	public Boolean authenticate(String username, String password) {
		FindIterable<Document> result = userdata.find(new Document("username",username).append("password", password));
		if(result.first()!= null) {
			return true;
		}
		return false;
	}
	
	public void setCanvasToken(String username, String token) {
		userdata.findOneAndUpdate(new Document("username",username), new Document("$set",new Document("token",token)));
	}
	/**
	 * Returns for a user their canvas token, if null the string "" is returned
	 * @param username
	 * @return
	 */
	public String getCanvasToken(String username) {
		Document user = userdata.find(new Document("username",username)).first();
		if(user.containsKey("token")) {
			return user.getString("token");
		}else {
			return "";
		}
	}
	public Boolean accountExists(String username) {
		FindIterable<Document> result = userdata.find(new Document("username", username));
		if (result.first() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if password passes policy, false otherwise
	 * 
	 * @param password
	 * @return
	 */
	public Boolean passwordStrength(String password) {
		if (password.length() < 6) {
			return false;
		}
		return true;
	}

	public void createAccount(String username, String password) {
		if (accountExists(username) || !passwordStrength(password)) {
			return;
		}
		userdata.insertOne(new Document("username", username).append("password", password));
	}
	
	public void deleteAccount(String username) {
		userdata.deleteOne(new Document("username",username));
	}

	public Boolean changePassword(String username, String password) {
		if (passwordStrength(password) == false) {
			return false;
		}
		userdata.updateOne(Filters.eq("username", username),Updates.set("password", password));
		return true;
	}
	
	public Integer accountCount() {
		Integer count = 0;
		FindIterable<Document> result = userdata.find();
		for(Document i : result) {
			count++;
		}
		return count;
	}

}
