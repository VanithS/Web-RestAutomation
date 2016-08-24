package com.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// this file is created to read the config file
//.properties holds everything as key value pairs eg in config. file (key:browser, value:firefox)

public class Configuration {
	
	public String URL;
	public String Browse; // the constants that you create are named with capital
	
	public Configuration(){
		readProps();
	}
	
	public void readProps(){
		
		Properties p = new Properties(); // Properties is a class in java to read properties file
		
		try {
			FileReader reader = new FileReader("config.properties");// Since file is residing in my java project we can give the name directly else give the whole path
			p.load(reader);   // loading it to the properties file
			URL = p.getProperty("url");
			Browse = p.getProperty("browser");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	

}
