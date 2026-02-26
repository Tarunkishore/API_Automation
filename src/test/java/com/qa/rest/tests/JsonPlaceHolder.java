package com.qa.rest.tests;

import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPlaceHolder {
    @Test
    public void testGetMenthod1(){
        System.out.println("***** test Get Menthod-1 Start *****");
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when().get("/posts");
        String resBody = response.asPrettyString();
        System.out.println(resBody);

        System.out.println("***** test Get Menthod-1 End *****");
    }

//    @Test
    public void testGetMenthod2(){
        System.out.println("***** test Get Menthod-2 Start *****");
        Response response = RestAssured
                .given()
                .when().get("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println(response.asPrettyString());
        System.out.println("Status Code: "+response.getStatusCode());

        JSONObject jsonObject = new JSONObject(response.asString());
        System.out.println("userId: "+jsonObject.getNumber("userId"));
        System.out.println("id: "+jsonObject.getNumber("id"));
        System.out.println("title: "+jsonObject.getString("title"));
        System.out.println("body: "+jsonObject.getString("body"));

        System.out.println("***** test Get Menthod-2 End *****");
    }

//    @Test
    public void testPostMethod1(){
        System.out.println("***** test Post Menthod-1 Start *****");
        String reqBody = "{\n" +
                "    \"title\": \"foo\",\n" +
                "    \"body\": \"bar\",\n" +
                "    \"userId\": 1\n" +
                "}";
        Response res = RestAssured.given().body(reqBody)
                .when().post("https://jsonplaceholder.typicode.com/posts");

        System.out.println("Status Code: "+res.getStatusCode());
        System.out.println(res.asPrettyString());

        System.out.println("***** test Post Menthod-1 End *****");
    }

//    @Test
    public void testPutMethod(){
        System.out.println("***** test Put Menthod Start *****");
        String requestBody = """
        {
          "id": 1,
          "title": "New Title",
          "body": "New Body",
          "userId": 1,
          "class": "UKG",
          "rollNo": 20
        }
        """;

        Response res = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1");
        System.out.println(res.getStatusCode());
        String resString = res.asString();
        System.out.println(res.asPrettyString());

        System.out.println("***** Verify above response *****");
        JSONObject resJson = new JSONObject(resString);
        for (String key : resJson.keySet()) {
            Object value = resJson.get(key);
            System.out.println(key + ": " + value);
        }

        System.out.println("***** test Put Menthod End *****");
    }
}
