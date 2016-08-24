package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.*;
import com.base.BaseTest;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginPageTest extends BaseTest {
	
	LoginPage lp;
	HomePage hp;
	
	@BeforeClass
	public void intialiaze(){
		
		hp = new HomePage(driver);
		hp.clickLogin();
		lp = new LoginPage(driver);
	}
	
	@DataProvider(name = "user-data")
	public Object[][] getUserData(){
		Object[][] data = {{"geeta", "geeta"},{"nilam", "nilam"}};
		return data;
	}
	
	@Test(dataProvider ="user-data" )
	public void testInvalidLogin(String userName, String pwd){
		String actual = lp.invalidLogin(userName, pwd);
		
		Assert.assertEquals("Something went wrong...Please try again.",actual);
		
	}
	

}
