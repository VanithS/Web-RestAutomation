package com.wbl.restapi.twitterapitest.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wbl.restapi.twitterapitest.wrapper.Response;
import com.wbl.restapi.twitterapitest.wrapper.TwitterApiWrapper;

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
public class TwitterAPITest {
	
	
	
	public static final String CONSUMER_KEY="1EhF25u3LLqdbNDCSGGUw10yP";
	public static final String CONSUMER_SECRET="V9B2B0SsU9lmfNmJFZsn9x0bhiSh0EcH1KLMDi1C86xwLXejh3";
	
	//@Test
	public void testGETFollowers() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		int expected = 200;
		String url = "https://api.twitter.com/1.1/followers/list.json";
		TwitterApiWrapper wrapper = new TwitterApiWrapper(CONSUMER_KEY, CONSUMER_SECRET,
				"753559786960982016-KRPQ4Pao4ihZUCf1UYo5Yd2rfunOKm9",
				"X5kIj7v7iH4GdeBTYAC8btY9vWV9QLwOjsy99TU00hpKv");
		Response res = wrapper.processGETRequest(url);
		Assert.assertEquals(res.getStatusCode(), expected);
		String json = res.getJsonResponse();
		JSONTokener tokener = new JSONTokener(json);
		JSONArray tweetsArr = new JSONArray(tokener);
		if ( (tweetsArr != null) && tweetsArr.length() > 0) {
			for(int i=0; i < tweetsArr.length(); i++) {
				JSONObject tweet = tweetsArr.getJSONObject(i);
				System.out.println(tweet.get("text"));
			}//end for
			
			//Assert the latest tweet in the array
			JSONObject latestTweet = tweetsArr.getJSONObject(0);
			String expectedLatestTweet = "Hi, this is twitter";
			String actualText = latestTweet.getString("text");
			Assert.assertEquals(actualText, expectedLatestTweet);
	
		}
		
	}
	//*******************************************************************************************************************************************
	//@Test
	public void getTweetTest() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		int expected = 200;
		String url = "https://api.twitter.com/1.1/statuses/user_timeline.json";
		TwitterApiWrapper wrapper = new TwitterApiWrapper(CONSUMER_KEY, CONSUMER_SECRET,
				"753559786960982016-KRPQ4Pao4ihZUCf1UYo5Yd2rfunOKm9",
				"X5kIj7v7iH4GdeBTYAC8btY9vWV9QLwOjsy99TU00hpKv");
		Response res = wrapper.processGETRequest(url);
		Assert.assertEquals(res.getStatusCode(), expected);
		String json = res.getJsonResponse();
		JSONTokener tokener = new JSONTokener(json);
		JSONArray tweetsArr = new JSONArray(tokener);
		if ( (tweetsArr != null) && tweetsArr.length() > 0) {
			for(int i=0; i < tweetsArr.length(); i++) {
				JSONObject tweet = tweetsArr.getJSONObject(i);
				System.out.println(tweet.get("text"));
			}//end for
			
			//Assert the latest tweet in the array
			JSONObject latestTweet = tweetsArr.getJSONObject(0);
			String expectedLatestTweet = "Hi, this is twitter";
			String actualText = latestTweet.getString("text");
			Assert.assertEquals(actualText, expectedLatestTweet);
			
		}
	}
	//*******************************************************************************************************************************************

	@Test
	public void testPOSTTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		int expected = 200;
		String url = "https://api.twitter.com/1.1/statuses/update.json";
		TwitterApiWrapper wrapper = new TwitterApiWrapper(CONSUMER_KEY, CONSUMER_SECRET,
				"753559786960982016-KRPQ4Pao4ihZUCf1UYo5Yd2rfunOKm9",
				"X5kIj7v7iH4GdeBTYAC8btY9vWV9QLwOjsy99TU00hpKv");
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("status", "Pleasant evening everyday"));
		Response res = wrapper.processPOSTRequest(url,urlParameters);
		Assert.assertEquals(res.getStatusCode(), expected);
		System.out.println("Response is below:\n" + res);
		
		String json = res.getJsonResponse();
		JSONTokener tokener = new JSONTokener(json);
		JSONObject obj = new JSONObject(tokener);
		if (obj != null){
			String tweet = obj.getString("text");
			String expectedTweet = "Pleasant evening everyday";
			Assert.assertEquals(tweet, expectedTweet );
		
		}
		
		
	}
	//*******************************************************************************************************************************************
	
		//@Test
		public void testPostLocationRequest() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
			int expected = 200;
			String url = "https://api.twitter.com/1.1/account/update_profile.json";
			TwitterApiWrapper wrapper = new TwitterApiWrapper(CONSUMER_KEY, CONSUMER_SECRET,
					"753559786960982016-KRPQ4Pao4ihZUCf1UYo5Yd2rfunOKm9",
					"X5kIj7v7iH4GdeBTYAC8btY9vWV9QLwOjsy99TU00hpKv");
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("location", "San Jose, CA"));
			Response res = wrapper.processPOSTRequest(url,urlParameters);
			
			//Asserting Status Code
			Assert.assertEquals(res.getStatusCode(), expected);
			
			
			//Asserting on Response Object
			String json = res.getJsonResponse();
			JSONTokener tokener = new JSONTokener(json);
			JSONObject obj = new JSONObject(tokener);
			if (obj != null){
				String location = obj.getString("location");
				String expectedLocation = "San Jose, CA";
				Assert.assertEquals(location, expectedLocation );
			
			}
			
			//Check if "id" field exists
			boolean idExists = obj.has("id");
			Assert.assertEquals(idExists, true);
			
			//Check if id value is numeric
			try {
				long idVal = obj.getLong("id");
			} catch (Exception ex) {
				System.out.println("ERROR: id is not numeric**********");
			}
			
			//Check if "entities" exists
			boolean entitiesExists = obj.has("entities");
			Assert.assertEquals(true, entitiesExists);			
			
		}
}
