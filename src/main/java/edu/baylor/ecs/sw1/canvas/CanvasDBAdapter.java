package edu.baylor.ecs.sw1.canvas;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import edu.baylor.ecs.sw1.auth.AuthService;

/** DESIGN PATTERN OBJECT ADAPTER
 * CanvasDB Adapter that adapts external event's to our internal db format
 * Operates as an object adapter to the CanvasAgentKey, allowing for internal services to make
 * queries to the Canvas Information
 * 
 * @author strafford
 *
 */
public class CanvasDBAdapter implements CanvasAgent {
	private CanvasAgentKey cAgent;
	private DatabaseConnector db = new DatabaseConnector("java","userdata","cerny");
	private AuthService auth = AuthService.getAuthService();
	static Logger log = Logger.getLogger(CanvasDBAdapter.class.getName());


	public CanvasDBAdapter(CanvasAgentKey agent, String username) {
		cAgent = agent;
		String userKey = auth.getCanvasToken(username);	
		cAgent.setKey(userKey);
		
	}
	/**
	 * Given a student ID, returns all courses that said student is currently enrolled in
	 */
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
		Map<String, String> courses = this.getCourses(username);
		Map<String,List<Map<String, Object>>> courseAssignments = new HashMap<>();
		Map<String,String> revCoursesName = new HashMap<>();
		if(courses == null) {
			return false;
		}
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
				System.out.println("Added event for course: "+ revCoursesName.get(course));
				db.addUserEvent(username, e);
			});
		});

		return true;
	}

	
}
