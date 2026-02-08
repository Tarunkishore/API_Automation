package com.qa.rest.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;


public class GetCallBDD {
	
	@Test
	public void test1() {
	given()
	    .header("x-api-key", "reqres_1a7b6a0ec5c741f481ffe9e7a9e0c2dd")
	.when()
	    .get("https://reqres.in/api/users")
	.then()
	    .statusCode(200)
	    .log().all();
	}

	@Test
	public void test2() {
		given()
				.header("x-api-key", "reqres_1a7b6a0ec5c741f481ffe9e7a9e0c2dd")
				.when()
				.get("https://reqres.in/api/users")
				.then()
				.statusCode(200)
				.log().all();
	}
}
