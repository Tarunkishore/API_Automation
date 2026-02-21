package com.qa.rest.tests;

import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class GetReqRes {
	public static void main(String... args) throws IOException {
		GetReqRes grr = new GetReqRes();
//		grr.testGETMethod();
//		grr.testGETMethod1();
//		grr.testGETMethod2();
		grr.testGETMethod3();
//		grr.readFromXLSFile();
	}


	public void testGETMethod3() {
		System.out.println("******* testGETMethod Start *******");
		Response res = RestAssured.given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
				.when().get("https://reqres.in/api/users");
//		System.out.println(res.asPrettyString());

		JsonPath jsonPath = res.jsonPath();
		assertThat(jsonPath.get("page"), equalTo(1));
		assertThat(jsonPath.get("per_page"), equalTo(6));
		assertThat(jsonPath.get("total"), equalTo(12));
		assertThat(jsonPath.get("total_pages"), equalTo(2));

		JSONObject rootObject = new JSONObject(res.asString());
		JSONArray dataArray = rootObject.getJSONArray("data");
		JSONObject dataArrayJSONObject = dataArray.getJSONObject(1);
		System.out.println("id: "+dataArrayJSONObject.getNumber("id"));
		System.out.println("email: "+dataArrayJSONObject.getString("email"));
		System.out.println("first_name: "+dataArrayJSONObject.getString("first_name"));
		System.out.println("last_name: "+dataArrayJSONObject.getString("last_name"));
		System.out.println("avatar: "+dataArrayJSONObject.getString("avatar"));

		System.out.println("StatusCode: "+res.getStatusCode());
		System.out.println("StatusLine: "+res.getStatusLine());
		System.out.println("Header: "+res.getHeader("Content-Type"));
		System.out.println("Date: "+res.header("Date"));
		System.out.println("Server: "+res.header("server"));

//		Headers allHeaders = res.getHeaders();
//		for(Header header:allHeaders){
//			System.out.println(header.getName()+" : "+header.getValue());
//		}



		System.out.println("******* testGETMethod END *******");
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

	public static void readFromXLSFile() throws IOException {
		String filePath = "/Users/tarunkishore/IdeaProjects/API_Automation/src/test/resources/DVUKVU.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		for(int i=0; i<rowCount; i++){
			System.out.println(sheet.getRow(i).getCell(0).getStringCellValue());
		}
	}

}
