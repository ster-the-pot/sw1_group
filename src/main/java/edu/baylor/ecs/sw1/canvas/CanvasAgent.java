package edu.baylor.ecs.sw1.canvas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.PagedList;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

public interface CanvasAgent {
	
	public JsonNode getCourses(String studentID);
	public Set<String> getQuizes(String studentID, String courseID);
	public Set<String> getAssignments(String studentID, String courseID);
	public Set<String> getExams(String studentID,String courseID);

}
