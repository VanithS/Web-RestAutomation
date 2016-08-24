package com.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;   // concepts of Abstraction and Encapsulation
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
// if tommorrow your webdriver changes, then you will have to only make changes in Page Driver
// as we are accessing webDriver for PageDriver, similarily you can do for your WebElments too
// getting all the functions in PageDriver class.

public class PageDriver {

	WebDriver driver;
	Configuration configuration;
	
	public PageDriver(Configuration _config){
		this.configuration = _config;
		intializeBrowser();
		//this.driver = new FirefoxDriver();	
	}
	
	public void intializeBrowser(){
		String browser = configuration.Browse;
		
		switch (browser){
		case "Firefox":
				startFirefox();
				break;
		case "IE":
				startIE();
				break;
		case "Chrome":
				startChrome();
				break;
		/*case "Safari":
				startSafari();
				break;*/
		default:
				startHTML();
				break;
		}
		driver.get(configuration.URL);
	}
	
	public void startFirefox(){
		this.driver = new FirefoxDriver();
	}
	
	public void startChrome(){
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe"); // if the file is on your local you will provide
				// it like this, but if its external provide the entire path.
	this.driver = new ChromeDriver();		
	}
	
	public void startIE(){
		System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
		
		this.driver = new InternetExplorerDriver();		
	}
	
	public void startHTML(){
		this.driver = new HtmlUnitDriver();
	}
	
	
	public void get(String url){
		driver.get(url);
	}
	
	public WebElement findElement(By by){
		return driver.findElement(by);
	}
	
	public List<WebElement> findElements(By by){
		return driver.findElements(by);
	}
	
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	public void quit(){
		driver.quit();
	}
}
