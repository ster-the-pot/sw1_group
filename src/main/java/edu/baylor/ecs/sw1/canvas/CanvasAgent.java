package edu.baylor.ecs.sw1.canvas;

import java.util.List;
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
public class CanvasAgent {

	static Logger log = Logger.getLogger(CanvasAgent.class.getName());
	private String host = "https://canvas.instructure.com/api/v1/courses/";
	private String key;

	public CanvasAgent(String key) {
		this.key = key;
	}

	/**
	 * Method responsible for determining if paginated list has a next link
	 * 
	 * @param links
	 * @return
	 */
	private String getNextPage(List<String> links) {
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

	public void getCourses(String studentID) {
		Set<String> studentCourses = new HashSet<String>();
		try {
			PagedList<JsonNode> response = Unirest.get(host).header("Authorization", "Bearer " + this.key)
					.asPaged(r -> r.asJson(), r -> getNextPage(r.getHeaders().get("Link")));
			// Base JSON that holds all information on courses

			for (HttpResponse<JsonNode> j : response) {
				List<JsonNode> list = response.getBodies();
				for (JsonNode c : list) {
					JSONArray course = c.getArray();
					
					course.forEach(e -> {
						if(!e.toString().contains("access_restricted_by_date")) {
							studentCourses.add(e.toString());
						}
					});

				}
			}
			studentCourses.stream().forEach(e->System.out.println(e));

		} catch (UnirestException err) {
			log.severe("getCourse failed: " + err.toString());
		}

	}

	public Set<String> getQuizes(String courseID) {
		return null;

	}

	public Set<String> getAssignments(String courseID) {
		return null;

	}

	public Set<String> getExams(String courseID) {
		return null;

	}

}
