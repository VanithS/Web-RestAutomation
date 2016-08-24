package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.BasePage;
import com.util.Locators;
import com.util.PageDriver;

public class HomePage extends BasePage {

	public HomePage(PageDriver driver) //making a constructor of the current class since this child class extends parent class..
	{
		super(driver);   //.. using super keyword
		driver.get("http://whiteboxqa.com/");  //If you put this in the constructor as soon as your
	}										// object is created , your home page also will open
												//even better move it to base page
	
	/*public void openPage()
	{
		driver.get("http://whiteboxqa.com/");
	} */
	
	public int countSocialIcons()
	{
		int count = 0;
		List<WebElement> elements = driver.findElements(By.cssSelector(Locators.loc.get("header-social-icons")));
		
		if(!elements.isEmpty())
		{	count = elements.size();
		}
		return count;
	}
	
	public String clickLogin(){
		String url = null;
		driver.findElement(By.id(Locators.loc.get("header-login-link"))).click();
		url = driver.getCurrentUrl();
		return url;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
