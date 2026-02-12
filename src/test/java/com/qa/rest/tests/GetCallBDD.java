package com.qa.rest.tests;

import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;


public class GetCallBDD {
	public static void main(String... args){
		GetCallBDD gcb = new GetCallBDD();
		gcb.testGETMethod();
		gcb.testGETMethod1();
		gcb.testGETMethod2();
	}

	public void testGETMethod() {
		System.out.println("******* testGETMethod Start *******");
		given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
				.when().get("https://reqres.in/api/users")
				.then().log().all();
		System.out.println("******* testGETMethod END *******");
	}

	public void testGETMethod1() {
		System.out.println("******* testGETMethod-1 Start *******");
		String responseBody = given()
				.header("x-api-key", ConfigReader.getProperty("x_api_key"))
				.when().get("https://reqres.in/api/users")
				.then().extract().body().asPrettyString();
		System.out.println(responseBody);

		System.out.println("******* Response validation below *******");
		JSONObject resJson = new JSONObject(responseBody);
		int page = resJson.getInt("page");
//		int per_page = resJson.getInt("per_page");
//		int total = resJson.getInt("total");
//		int total_pages = resJson.getInt("total_pages");
		System.out.println("page: "+page);
		System.out.println("per_page: "+resJson.getInt("per_page"));
		System.out.println("total :"+resJson.getInt("total"));
		System.out.println("total_pages: "+resJson.getInt("total_pages"));

		System.out.println("******* testGETMethod-1 END *******");
	}

	public void testGETMethod2() {
		System.out.println("******* testGETMethod-2 Start *******");
		Response response = RestAssured.given()
				.header("x-api-key", ConfigReader.getProperty("x_api_key"))
				.when()
				.get("https://reqres.in/api/users");
		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);

		System.out.println("******* Response validation below *******");
		JSONObject resJsonroot = new JSONObject(responseBody);
		Number page = resJsonroot.getNumber("page");
		System.out.println("page: "+page);
		System.out.println("per_page: "+resJsonroot.getInt("per_page"));
		System.out.println("total :"+resJsonroot.getInt("total"));
		System.out.println("total_pages: "+resJsonroot.getInt("total_pages"));

		JSONArray dataArray = resJsonroot.getJSONArray("data");
		for(int i=0; i<dataArray.length(); i++) {
			JSONObject arrObject = dataArray.getJSONObject(i);
			System.out.println("id: " + arrObject.getNumber("id"));
			System.out.println("email: " + arrObject.getString("email"));
			System.out.println("first_name: " + arrObject.getString("first_name"));
			System.out.println("last_name: " + arrObject.getString("last_name"));
			System.out.println("avatar: " + arrObject.getString("avatar"));
		}

		JSONObject supportObject = resJsonroot.getJSONObject("support");
		System.out.println("support_url: "+supportObject.getString("url"));
		System.out.println("support_text: "+supportObject.getString("text"));

		JSONObject metaObject = resJsonroot.getJSONObject("_meta");
		System.out.println("meta_powered_by: "+metaObject.getString("powered_by"));
		System.out.println("meta_docs_url: "+metaObject.getString("docs_url"));
		System.out.println("meta_upgrade_url: "+metaObject.getString("upgrade_url"));
		System.out.println("meta_example_url: "+metaObject.getString("example_url"));
		System.out.println("meta_variant: "+metaObject.getString("variant"));
		System.out.println("meta_message: "+metaObject.getString("message"));
		System.out.println("meta_context: "+metaObject.getString("context"));

		JSONObject ctaObject = metaObject.getJSONObject("cta");
		System.out.println("cta_label: "+ctaObject.getString("label"));
		System.out.println("cta_url: "+ctaObject.getString("url"));


		System.out.println("******* testGETMethod-2 END *******");
	}
}
