package com.appiumtest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AppiumTest {
	private static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		
		//Set Device specific capabilities
		capabilities.setCapability("deviceName", "Vanith3");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		
		//Set Application specific capabilities. Copy and paste from Appium > Android Settings dialog
		capabilities.setCapability("appPackage", "io.selendroid.testapp");
		capabilities.setCapability("appActivity", ".HomeScreenActivity");
		
		//Initialize AndroidDriver
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		//Run Simple Tests
		
		//1. Locate a text field in the app and add "This is Vanith" text to it
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='my_text_fieldCD']")).sendKeys("This is Vanith");
		Thread.sleep(2000);
		
		//2. Locate a button and click it
		driver.findElement(By.id("io.selendroid.testapp:id/startUserRegistration")).click();
		Thread.sleep(3000);
		
		//Stop tests, quit driver
		driver.quit();
		
	}

}
