package com.qa.rest.tests;
import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("base.url");

        request = RestAssured.given()
                .header("x-api-key", ConfigReader.getProperty("x.api.key"))
                .header("User-Agent", "RestAssured-Test")
                .header("Content-Type", "application/json"); // for POST/PUT
    }
}
