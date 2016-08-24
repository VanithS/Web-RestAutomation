package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.base.BaseTest;
import com.pages.HomePage;

public class HomePageTest extends BaseTest {
	
	HomePage hp;
	
	@BeforeClass
	public void intialize(){
		hp = new HomePage(driver);
	}
	
	@Test
	public void testCountSocialIcons(){
		
		int actual =  hp.countSocialIcons();
		
		Assert.assertEquals(4, actual);
		
	}
	
	@Test
	public void testClickLogin(){
		
		String actual = hp.clickLogin();
		String expected = "http://www.whiteboxqa.com/login.php";
		
		Assert.assertEquals(expected, actual);
	}
	@AfterSuite
	public void quit(){
		driver.quit();
	}
}
