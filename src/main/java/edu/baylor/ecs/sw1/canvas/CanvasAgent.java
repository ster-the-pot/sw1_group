package edu.baylor.ecs.sw1.canvas;


import java.util.List;
import java.util.Map;

public interface CanvasAgent {
	
	public List<Map<String, Object>> getCourses(String studentID);
	public List<Map<String, Object>> getQuizes(String studentID, String courseID);
	public List<Map<String, Object>> getAssignments(String studentID, String courseID);
}
