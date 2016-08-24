package com.base;

import org.openqa.selenium.WebDriver;

import com.util.PageDriver;

public abstract class BasePage {    // making and abstract class, it can have abstract or non abstract(no body) methods eg gear ex
	
	public PageDriver driver;    			 //creating a global variable
			
	public BasePage(PageDriver driver){ 
		this.driver = driver;
	}
	
		/*creating a constructor. Dependency Injection(Passing the dependency to the classes. Your Page Object PAttern and 
		 * Dependency go parallely, if there is Page object pattern there will be a dependency ) - imp- The BasePage 
		 * class is depending on the WebDriver.
		 * With a constructor a dependency injection is created by injecting through the instance of WebDriver (which is driver)*/
}
