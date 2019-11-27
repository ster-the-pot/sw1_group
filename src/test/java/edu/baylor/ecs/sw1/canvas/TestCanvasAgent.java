package edu.baylor.ecs.sw1.canvas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestCanvasAgent {
	CanvasAgentKey canvas = new CanvasAgentKey();

	public TestCanvasAgent() {

	}

	@Test
	public void testGetCourseEmptyKey() {
		//canvas.getCourses("");
	}

	@Test
	public void testGetCourse() {
		String userKey;
		try {
			File keyF = new File("../canvas_api/key");
			//System.out.println(keyF.getAbsolutePath());
			BufferedReader key = new BufferedReader(new FileReader(keyF));
			try {
				userKey = key.readLine();
				//System.out.println(userKey);
				canvas.setKey(userKey);
				if(canvas.getCourses("") == null) {
					Assert.fail("Request Failed to Issue");
				}
				
			} catch (IOException e) {
				Assert.fail("File Failed to be read.");
			}
		}catch(FileNotFoundException err) {
			Assert.fail("File Not Found");
		}
	}
	
	
	
	


}
