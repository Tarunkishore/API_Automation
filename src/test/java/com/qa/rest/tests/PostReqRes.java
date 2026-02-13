package com.qa.rest.tests;

import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class PostReqRes {
    public static void main(String[] args) throws IOException {
        PostReqRes.testPOSTMethod2();
        PostReqRes.testGETMethod3();
    }

    public static void testPOSTMethod(){
        System.out.println("******* testPOSTMethod START *******");
        // Read data directly
        String reqJsonBody = "{\n" +
                "  \"name\": \"abc\",\n" +
                "  \"job\": \"SDET\"\n" +
                "}";

        Response res = RestAssured.given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
                .contentType("application/json").body(reqJsonBody)
                .when().post("https://reqres.in/api/users");
        String responseJson=res.getBody().asPrettyString();
        System.out.println("Status Code: "+res.getStatusCode());
        System.out.println(responseJson);

        System.out.println("******* testPOSTMethod END *******");
    }

    public static void testPOSTMethod1(){
        System.out.println("******* testPOSTMethod-1 START *******");
        // Read data using Map
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "RestAssured");
        requestBody.put("job", "SDET");

        Response res = RestAssured.given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
                .contentType("application/json").body(requestBody)
                .when().post("https://reqres.in/api/users");
        String responseJson=res.getBody().asPrettyString();
        System.out.println("Status Code: "+res.getStatusCode());
        System.out.println(responseJson);

        System.out.println("******* testPOSTMethod-1 END *******");
    }

    public static void testPOSTMethod2() throws IOException {
        System.out.println("******* testPOSTMethod-2 START *******");
        // Read data from json file
        byte[] barr = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/test/resources/User13.json"));
        String requestBody = new String(barr);

        Response res = RestAssured.given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
                .contentType("application/json").body(requestBody)
                .when().post("https://reqres.in/api/users");
        String responseJson=res.getBody().asPrettyString();
        System.out.println("Status Code: "+res.getStatusCode());
        System.out.println(responseJson);

        System.out.println("******* testPOSTMethod-2 END *******");
    }

    public static void testGETMethod3() throws IOException {
        System.out.println("******* testGETMethod-3 START *******");
        Response res = RestAssured.given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
                .contentType("application/json")
                .when().get("https://reqres.in/api/users");
        String responseJson=res.getBody().asPrettyString();
        System.out.println("Status Code: "+res.getStatusCode());
        System.out.println(responseJson);

        System.out.println("******* testGETMethod-3 END *******");
    }

}
