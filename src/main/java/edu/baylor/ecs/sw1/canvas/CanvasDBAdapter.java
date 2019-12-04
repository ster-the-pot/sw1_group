package edu.baylor.ecs.sw1.canvas;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import edu.baylor.ecs.sw1.auth.AuthService;

/**
 * CanvasDB Adapter that adapts external event's to our internal db format
 * 
 * @author strafford
 *
 */
public class CanvasDBAdapter implements CanvasAgent {
	private CanvasAgentKey cAgent;
	private DatabaseConnector db = new DatabaseConnector("java","userdata","cerny");
	private AuthService auth = AuthService.getAuthService();

	public CanvasDBAdapter(CanvasAgentKey agent, String username) {
		cAgent = agent;
		String userKey = auth.getCanvasToken(username);	
		cAgent.setKey(userKey);
		
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

	/**
	 * Workflow of syncing all events from Canvas into User's db
	 * @param studentID
	 */
	public Boolean syncStudentCanvas(String username) {
		if(this.cAgent.getKey() == null) {
			return false;
		}
		Map<String, String> courses = this.getCourses(username);
		if(courses==null) {
			return false;
		}
		Map<String,List<Map<String, Object>>> courseAssignments = new HashMap<>();
		Map<String,String> revCoursesName = new HashMap<>();
		for(Map.Entry<String, String> entry: courses.entrySet()) {
			revCoursesName.put(entry.getValue(), entry.getKey());
		}
		courses.forEach((k, v) -> {

			courseAssignments.put(v,this.getAssignments(username, v));
		});
		
		courseAssignments.forEach((course,json) -> {
			//System.out.println("COURSE: " + course + "\n-------------");
			json.forEach(e->{
				e.put("course",revCoursesName.get(course));
				db.addUserEvent(username, e);
			});
		});

		return true;
	}

	
	
		

}
