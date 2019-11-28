package edu.baylor.ecs.sw1.auth;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
/**
 * Authorization Service used to add accounts, sign in, and check account information
 * @author strafford
 *
 */
public class AuthService {

static MongoClient client;
	
	Document upsertOptions = new Document().append("upsert", Boolean.TRUE);
	
	
	public AuthService(String user, String db, char[] password) {
		
		MongoCredential credential =  MongoCredential.createCredential(user,db,password);
		client = new MongoClient(new ServerAddress("localhost",27017),Arrays.asList(credential));
	}
	
	public void close() {
		client.close();
	}
	
	
	public Boolean accountExists(String username) {
		return null;
	}
	
	public Boolean passwordStrength(String password) {
		return null;
	}
	
	public Boolean createAccount(String username, String password) {
		if(accountExists(username) || !passwordStrength(password)) {
			return false;
		}
		return null;
	}
	
	public Boolean changePassword(String username, String password) {
		if(passwordStrength(password) == false) {
			return false;
		}
		return null;
	}
	
}
