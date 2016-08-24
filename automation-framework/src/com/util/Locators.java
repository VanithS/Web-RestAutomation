package com.util;

import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class Locators {
	
	public static HashMap <String, String> loc = new HashMap<>();
	
	public static void getElemnts(){
		
		Properties p = new Properties();
		try {
			FileReader reader = new FileReader("locators.properties");// Since file is residing in my java project we can give the name directly else give the whole path
			p.load(reader);   // loading it to the properties file
			
			Enumeration e = p.propertyNames();
			while(e.hasMoreElements()){
				String key=(String) e.nextElement();
				loc.put(key, p.getProperty(key));
			}
				
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}

}
