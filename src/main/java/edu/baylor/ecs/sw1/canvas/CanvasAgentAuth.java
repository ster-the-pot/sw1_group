package edu.baylor.ecs.sw1.canvas;

import java.util.Set;

import kong.unirest.JsonNode;


/**
 * OAuth2 Access not given, future implementation of Auth2 would be placed here
 * @author strafford
 *
 */
public class CanvasAgentAuth implements CanvasAgent {

	@Override
	public JsonNode getCourses(String studentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getQuizes(String studentID, String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAssignments(String studentID, String courseID) {
		// TODO Auto-generated method stub
		return null;
	}


}
