package com.qa.rest.tests;

import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CodeStudio {

    @Test
    public void test5(){
        RestAssured.baseURI = "https://reqres.in";

        given()
//                .header("x-api-key", "reqres_f5951af54ba24c5eaf2673a85ffbfd9d")
                .header("x-api-key", ConfigReader.getProperty("x_api_key"))
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .log().all();
    }

//    @Test
    void test1(){
        given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Activities")
                .then()
                .statusCode(200)
        ;
    }

//    @Test
    public void test2(){
//        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        Response response = get("https://fakerestapi.azurewebsites.net/api/v1/Activities");
        System.out.println(response.asString());
        System.out.println("Status code => "+response.getStatusCode());
        System.out.println("Response Body => "+response.getBody().asPrettyString());
        System.out.println("Response Time => "+response.getTime());
        System.out.println("Response Header => "+response.getHeader("Content-Type"));

        int actualStatusCode = 200;
        int expectedStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

//    @Test
    public void test4(){
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/1")
                .then().statusCode(200);
    }

//    @Test
    public void test3(){
        JSONObject jsonData = new JSONObject();
//        jsonData.put("id","0");
//        jsonData.put("title","SDET");
        jsonData.put("username","string");
        jsonData.put("password","string");
        baseURI="https://fakestoreapi.com/auth/login";
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).body(jsonData.toJSONString())
                .when().post()
                .then().statusCode(201).log().all();


    }
}
