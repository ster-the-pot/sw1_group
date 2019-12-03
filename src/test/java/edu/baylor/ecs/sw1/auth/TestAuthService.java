package edu.baylor.ecs.sw1.auth;

import org.junit.Assert;
import org.junit.Test;

public class TestAuthService {
	private AuthService auth = AuthService.getAuthService();

	@Test
	public void testAccountExists() {
		if (auth.accountExists("strafford") != true) {
			Assert.fail();
		}
		if (auth.accountExists("buweawdawdh") != false) {
			Assert.fail();
		}
	}

	@Test
	public void testCreateDeleteAccount() {
		auth.createAccount("reneelovesu", "123456");
		auth.deleteAccount("reneelovesu");
	}

	@Test
	public void changePassword() {
		auth.changePassword("strafford", "reneesmith");
	}

	@Test
	public void authenticateUser() {
		auth.createAccount("reneelovesu", "123456");
		Boolean pass = auth.authenticate("reneelovesu", "123456");
		auth.deleteAccount("reneelovesu");
		if (!pass) {
			Assert.fail();
		}
	}
	
	@Test
	public void canvasTokenGetSet() {
		auth.createAccount("reneelovesu", "123456");
		auth.setCanvasToken("reneelovesu", "testTokenWooHoo!");
		if(!auth.getCanvasToken("reneelovesu").equals("testTokenWooHoo!")) {
			Assert.fail();
		}
		auth.deleteAccount("reneelovesu");
	}
}
