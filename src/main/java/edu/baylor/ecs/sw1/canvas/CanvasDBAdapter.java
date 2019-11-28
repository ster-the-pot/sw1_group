package edu.baylor.ecs.sw1.canvas;

import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.PagedList;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

/**
 * CanvasDB Adapter that adapts external event's to our internal db format
 * 
 * @author strafford
 *
 */
public class CanvasDBAdapter implements CanvasAgent {
	private CanvasAgentKey cAgent;

	public CanvasDBAdapter(CanvasAgentKey agent) {
		cAgent = agent;
		String userKey;
		try {
			File keyF = new File("../canvas_api/key");
			// System.out.println(keyF.getAbsolutePath());
			BufferedReader key = new BufferedReader(new FileReader(keyF));
			try {
				userKey = key.readLine();
				// System.out.println(userKey);
				cAgent.setKey(userKey);

			} catch (IOException e) {
				Assert.fail("File Failed to be read.");
			}
		} catch (FileNotFoundException err) {
			Assert.fail("File Not Found");
		}
	}

	public Map<String, String> getCourses(String studentID) {
		return cAgent.getCourses(studentID);

	}

	/**
	 * given a studentID and courseID, returns the courses for said student
	 * 
	 * @param courseID
	 * @return
	 */
	public List<Map<String, Object>> getQuizes(String studentID, String courseID) {
		return cAgent.getQuizes(studentID, courseID);

	}

	/**
	 * Given a student/courseID, returns all assignments
	 * 
	 * @param studentID
	 * @param courseID
	 * @return
	 * @return
	 */
	public List<Map<String, Object>> getAssignments(String studentID, String courseID) {
		return cAgent.getAssignments(studentID, courseID);

	}

	public void syncStudentCanvas(String studentID) {
		Map<String, String> courses = this.getCourses(studentID);
		Map<String,List<Map<String, Object>>> courseAssignments = new HashMap<>();
		courses.forEach((k, v) -> {
			courseAssignments.put(v,this.getAssignments(studentID, v));
		});
		
		courseAssignments.forEach((course,json) -> {
			System.out.println("COURSE: " + course + "\n-------------");
			System.out.println(json.size());
			json.forEach(e->{
				//System.out.println(e.toString());	
			});
		});

	}

	
	
		

}
