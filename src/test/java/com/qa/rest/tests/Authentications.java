package com.qa.rest.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Authentications {

    @Test
    public void testBasicAuth(){
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }
}
