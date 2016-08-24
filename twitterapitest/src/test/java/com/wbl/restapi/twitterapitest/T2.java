package com.wbl.restapi.twitterapitest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wbl.restapi.twitterapiter.T1;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

	/**
	 * This is the testNg class to read data from csv file using Apache POI
	 * and then to create different URL and parameter combinations
	 * to invoke GET and POST API requests from the TwitterApiWrapper class
	 * @author vanith.sutrave
	 *
	 */	
	public class T2 {		
		
		@Test
		public void testGETFollowers() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
			int expected = 200;
			String url = "https://api.twitter.com/1.1/followers/list.json";
			T1 wrapper = new T1("1EhF25u3LLqdbNDCSGGUw10yP",
					"V9B2B0SsU9lmfNmJFZsn9x0bhiSh0EcH1KLMDi1C86xwLXejh3",
					"753559786960982016-KRPQ4Pao4ihZUCf1UYo5Yd2rfunOKm9",
					"X5kIj7v7iH4GdeBTYAC8btY9vWV9QLwOjsy99TU00hpKv");
			int actual = wrapper.processGETRequest(url);
			Assert.assertEquals(actual, expected);			
			
		}
		
		//**************************************************************************************************************
		
		@Test
		public void testPOSTTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
			int expected = 200;
			String url = "https://api.twitter.com/1.1/statuses/update.json";
			
			T1 wrapper = new T1("1EhF25u3LLqdbNDCSGGUw10yP",
					"V9B2B0SsU9lmfNmJFZsn9x0bhiSh0EcH1KLMDi1C86xwLXejh3",
					"753559786960982016-KRPQ4Pao4ihZUCf1UYo5Yd2rfunOKm9",
					"X5kIj7v7iH4GdeBTYAC8btY9vWV9QLwOjsy99TU00hpKv");
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("status", "WBL rest automation"));
			int actual = wrapper.processPOSTRequest(url,urlParameters );
			Assert.assertEquals(actual, expected);
			
		}
}
