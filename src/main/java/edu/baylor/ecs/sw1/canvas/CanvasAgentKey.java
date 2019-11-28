package edu.baylor.ecs.sw1.canvas;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.PagedList;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

/**
 * Canvas Facade that User events interact with to
 * 
 * @author strafford
 *
 */
public class CanvasAgentKey implements CanvasAgent {

	static Logger log = Logger.getLogger(CanvasAgentKey.class.getName());
	private String host = "https://canvas.instructure.com/api/v1/courses/";
	private String key;

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Responsible for determining if paginated list has a next link
	 * 
	 * @param links
	 * @return
	 */
	private String getNextPage(List<String> links) {
		if (links != null && links.size() != 0) {
			links = Arrays.asList(links.get(0).split(","));
			links = links.stream().filter(s -> s.contains("rel=\"next")).collect(Collectors.toList());
			if (links.size() == 1) {
				String next;
				next = links.get(0);
				next = next.split(";")[0];
				next = next.replace("<", "");
				next = next.replace(">", "");
				return next;
			} else {
				return "";
			}
		}
		return "";

	}

	/**
	 * Given a student key, retrieves a list of all classes for said student
	 * 
	 * @param studentID
	 */
	public Map<String, String> getCourses(String studentID) {
		if (studentID == null || this.key == null) {
			return null;
		}
		Map<String, String> courseList = new HashMap<>();
		;
		Set<String> studentCourses = new HashSet<String>();
		// System.out.println(this.key);
		try {
			PagedList<JsonNode> response = Unirest.get(host).header("Authorization", "Bearer " + this.key)
					.asPaged(r -> r.asJson(), r -> getNextPage(r.getHeaders().get("Link")));
			// Base JSON that holds all information on courses
			for (HttpResponse<JsonNode> j : response) {
				List<JsonNode> list = response.getBodies();
				for (JsonNode c : list) {
					JSONArray course = c.getArray();

					for (int i = 0; i < course.length(); i++) {
						if (!course.get(i).toString().contains("access_restricted_by_date")) {
							courseList.put(course.getJSONObject(i).get("name").toString(),
									course.getJSONObject(i).get("id").toString());
						}
					}
				}
			}

//			courseList.forEach((k,v)->{
//				System.out.println(k + ": " + v);
//			});
			return courseList;
//			

		} catch (UnirestException err) {
			log.severe("getCourse failed: " + err.toString());
		}
		return null;

	}

	/**
	 * given a studentID and courseID, returns the courses for said student
	 * 
	 * @param courseID
	 * @return
	 */
	public List<Map<String, Object>> getQuizes(String studentID, String courseID) {
		return null;

	}

	/**
	 * Given a student/courseID, returns all assignments
	 * 
	 * @param studentID
	 * @param courseID
	 * @return
	 */
	public List<Map<String, Object>> getAssignments(String studentID, String courseID) {
		String hostloc = "https://canvas.instructure.com/api/v1/users/";
		if (studentID == null || this.key == null) {
			return null;
		}
		ArrayList<Map<String, Object>> assignmentList = new ArrayList<>();
		;
		Set<String> studentCourses = new HashSet<String>();
		// System.out.println(this.key);
		try {
			PagedList<JsonNode> response = Unirest.get(host + "/" + courseID + "/assignments")
					.header("Authorization", "Bearer " + this.key)
					.asPaged(r -> r.asJson(), r -> getNextPage(r.getHeaders().get("Link")));
			// Base JSON that holds all information on courses
			for (HttpResponse<JsonNode> j : response) {
				List<JsonNode> list = response.getBodies();
				for (JsonNode c : list) {
					JSONArray course = c.getArray();
					//conversion of pagination into individual assignment's
					for (int i = 0; i < course.length(); i++) {
						assignmentList.add(course.getJSONObject(i).toMap());
					}
				}
			}

			return assignmentList;

		} catch (UnirestException err) {
			log.severe("getCourse failed: " + err.toString());
		}
		return null;

	}

}
