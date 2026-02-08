package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
//	1. GET
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		HttpGet httpGet = new HttpGet(url);		// http Get Request
		CloseableHttpResponse response = httpClient.execute(httpGet); 	// hit the GET URL
		
		int statusCode = response.getStatusLine().getStatusCode();	//a. statusCode
		System.out.println("Status Code : "+statusCode);
		
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");	//b. Jason String
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("Responce JSON from API : "+responsejson);
		
		Header[] headerArray = response.getAllHeaders();	//c. all Headers
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header:headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array : "+allHeaders);
	}

}
