package edu.baylor.ecs.sw1.canvas;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDatabaseConnector {

	DatabaseConnector db;
	
	@Before
	public void setUp() {
		db = new DatabaseConnector("java","userdata","cerny".toCharArray());
	}
	
	@After
	public void tearDown() {
		db.close();
		db = null;
	}
	
	@Test
	public void testConnect() {
		if(db.getDBName()!= "userdata") {
			Assert.fail("Connection to db Failed.");
		}
	}
	
	@Test
	public void testInsertion() {
		Map<String,Object> testEvent = new HashMap<>();
		testEvent.put("id", 270);
		testEvent.put("name","Software Lab Assignment 11 Modified 2.0");
		db.addUserEvent("carlos", testEvent);
	}
	
	@Test
	public void testRetrieval() {
		db.getUserEvents("strafford");
	}
}
