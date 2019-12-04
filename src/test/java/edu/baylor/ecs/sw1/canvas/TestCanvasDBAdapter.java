package edu.baylor.ecs.sw1.canvas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestCanvasDBAdapter {
		
	private CanvasAgentKey canvas = new CanvasAgentKey();
	private CanvasDBAdapter db = new CanvasDBAdapter(canvas,"strafford");
	
	@Test
	public void courseIDExtraction() {
		if(!db.syncStudentCanvas("strafford")) {
			Assert.fail("Canvas Key Invalid, Failed to Sync");
		}
	}
}