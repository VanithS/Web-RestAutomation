package com.wbl.restapi.twitterapitest.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;

/**
 * It is a wrapper class for performing Authorization and logic 
 * for invoking GET and POST Twitter REST API requests.
 * @author vanith.sutrave
 *
 */
public class TwitterApiWrapper 
{
	//we are using a third party library for Oauth authentication in case of OAuthConsumer here
	private OAuthConsumer oAuthConsumer;
	
	/**
	 * Constructor will setup the oAuth consumerKey, consumerSecret, accessToken and accessTokenSecret for oAuthConsumer instance
	 * @param consumerKey
	 * @param consumerSecret
	 * @param accessToken
	 * @param accessTokenSecret
	 */
	public TwitterApiWrapper(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		this.oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        oAuthConsumer.setTokenWithSecret(accessToken, accessTokenSecret);
        oAuthConsumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());
	}
	
	
	/**
	 * Perform oAuth authorization for the current API request
	 * @throws OAuthCommunicationException 
	 * @throws OAuthExpectationFailedException 
	 * @throws OAuthMessageSignerException 
	 */
    private void doAuthorization(HttpRequestBase httpRequest) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
         oAuthConsumer.sign(httpRequest);
    }

    /**
     * Method to send any Twitter REST API GET request 
     * @param url REST API URL
     * @return
     * @throws OAuthCommunicationException 
     * @throws OAuthExpectationFailedException 
     * @throws OAuthMessageSignerException 
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public Response processGETRequest(String url) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException {
 
    	HttpClient http = HttpClientBuilder.create().build();
		
		HttpGet req = new HttpGet(url);
		//req.addHeader("content-type", "application/json");
		
		doAuthorization(req);
		HttpResponse response = http.execute(req);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("The status code is: "+statusCode);
    	
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println("Response is below: ");
		System.out.println(result.toString());
	
		Response res = new Response();
		res.setStatusCode(statusCode);
		res.setJsonResponse(result.toString());
		
		return res;
    }
    
    //************************************************************************************************************************************
    
 
    
    /**
     * Method to send any Twitter REST API POST request
     * @param url REST API URL
     * @param params HashMap of name value pairs of parameters
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     * @throws OAuthCommunicationException 
     * @throws OAuthExpectationFailedException 
     * @throws OAuthMessageSignerException 
     */
    public Response processPOSTRequest(String url, List<NameValuePair> params) throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpPost post = new HttpPost(url);

   
    	post.setEntity(new UrlEncodedFormEntity(params));
    	
    	doAuthorization(post);
    	
    	HttpResponse response = client.execute(post);
    	int statusCode = response.getStatusLine().getStatusCode();
    	System.out.println("Response Code : " + statusCode);

    	BufferedReader rd = new BufferedReader(
    	        new InputStreamReader(response.getEntity().getContent()));

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}
    	System.out.println("Response is below: ");
		System.out.println(result.toString());
    	
    	
    	Response res = new Response();
		res.setStatusCode(statusCode);
		res.setJsonResponse(result.toString());
		
		return res;
    }
    //************************************************************************************************************************************
 

    
  
}
