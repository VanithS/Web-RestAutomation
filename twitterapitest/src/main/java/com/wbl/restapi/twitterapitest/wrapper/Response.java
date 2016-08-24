package com.wbl.restapi.twitterapitest.wrapper;

/**
 * POJO class to hold the response containing statusCode and JSON returned.
 * @author vanith.sutrave
 *
 */
public class Response {
	private int statusCode;
	private String jsonResponse;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getJsonResponse() {
		return jsonResponse;
	}
	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
}
