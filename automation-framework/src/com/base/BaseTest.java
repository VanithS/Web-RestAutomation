package com.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.util.Configuration;
import com.util.Locators;
import com.util.PageDriver;

public abstract class BaseTest {
	
	public static final Configuration _config;
	
	public PageDriver driver;
	
	static{
		_config = new Configuration();
		Locators.getElemnts();
	}
	
	@BeforeSuite
	public void setUp(){
		driver = new PageDriver(_config);
		driver.get("http://www.whiteboxqa.com/");
	}
	
	/*@AfterSuite
	public void quit(){
		driver.quit();
	}*/
}

/*If you double click on the blue bar you will get debug point
 * if you want to debug line by line then press F6, after finishing pressF8.
 * And then hover over the value (of the debug point)
 * 1:45mins intellij whitebox QA big framework.
 * learn log4j.
 * learn json.json is similar to properties, can use either.
 * to read from excel sheets you need ApachePOI.
 * */
