package edu.baylor.ecs.sw1.canvas;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
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

	public CanvasAgentKey(String key) {
		System.out.println(key);
		this.key = key;
	}

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
		if (links != null && links.size()!=0) {
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
	public JsonNode getCourses(String studentID) {
		if (studentID == null || studentID.length() != 69) {
			return null;
		}
		Set<String> studentCourses = new HashSet<String>();
		System.out.println(this.key);
		try {
			PagedList<JsonNode> response = Unirest.get(host).header("Authorization", "Bearer " + this.key)
					.asPaged(r -> r.asJson(), r -> getNextPage(r.getHeaders().get("Link")));
			System.out.println(response.get(0).getStatusText());
			// Base JSON that holds all information on courses
			for (HttpResponse<JsonNode> j : response) {
				List<JsonNode> list = response.getBodies();
				for (JsonNode c : list) {
					JSONArray course = c.getArray();

					course.forEach(e -> {
						if (!e.toString().contains("access_restricted_by_date")) {
							studentCourses.add(e.toString());
						}
					});
					
					HashSet<Map<String,Object>> courseList = new HashSet<>();
					for(int i = 0; i < course.length();i++) {
						courseList.add(course.getJSONObject(i).toMap());
					}

				}
			}
			studentCourses.stream().forEach(e -> System.out.println(e));

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
	public Set<String> getQuizes(String studentID, String courseID) {
		return null;

	}

	/**
	 * Given a student/courseID, returns all assignments
	 * 
	 * @param studentID
	 * @param courseID
	 * @return
	 */
	public Set<String> getAssignments(String studentID, String courseID) {
		return null;

	}

}
