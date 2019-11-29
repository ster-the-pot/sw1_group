package edu.baylor.ecs.sw1.auth;

import org.junit.Assert;
import org.junit.Test;

public class TestAuthService {
	private AuthService auth = AuthService.getAuthService();
	
	@Test
	public void testAccountExists() {
		if(auth.accountExists("strafford")!= true) {
			Assert.fail();
		}
		if(auth.accountExists("buweawdawdh")!=false) {
			Assert.fail();
		}
	}
	
	@Test 
	public void testCreateDeleteAccount() {
		auth.createAccount("reneelovesu", "123456");
		auth.deleteAccount("reneelovesu");
	}
}
