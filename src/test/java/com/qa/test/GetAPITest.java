package com.qa.test;

import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;

import com.qa.base.ConfigReader;
import com.qa.client.RestClient;

public class GetAPITest extends ConfigReader {

	ConfigReader configReader;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	
	@BeforeMethod
	public void setup() throws IOException {
		configReader = new ConfigReader();
		serviceURL = properties.getProperty("URL");
		apiURL = properties.getProperty("serviceURL");
		url = serviceURL + apiURL;
		
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		restClient.get(url);
	}
	

}
