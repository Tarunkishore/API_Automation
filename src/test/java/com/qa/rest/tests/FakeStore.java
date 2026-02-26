package com.qa.rest.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class FakeStore {

//    @Test
    public static void testGetAllProducts(){
        	System.out.println("******* test Get All Products Start *******");
            Response res = RestAssured.given()
                    .when().get("https://fakestoreapi.com/products")
                    .then().statusCode(200).extract().response();
            String body = res.asPrettyString();
            System.out.println(body);

            JSONArray root = new JSONArray(body);
            JSONObject obj =  root.getJSONObject(0);
            System.out.println("id: "+obj.getNumber("id"));
            System.out.println("title: "+obj.getString("title"));

        System.out.println("******* test Get All Products End *******");
    }

//    @Test
    public static void testGetAllProduct(){
        System.out.println("******* test Get All Product Start *******");
        Response res = RestAssured.given()
                .when().get("https://fakestoreapi.com/products/1")
                .then().statusCode(200).extract().response();
        String body = res.asPrettyString();

        JSONObject jsonRes = new JSONObject(body);
        System.out.println("id: "+jsonRes.getNumber("id"));
        System.out.println("title: "+jsonRes.getString("title"));

        System.out.println("******* test Get All Product End *******");
    }

//    @Test
    public static void testGetAllProductKeys(){
        System.out.println("******* test Get All Product Start *******");
        Response res = RestAssured.given()
                .when().get("https://fakestoreapi.com/products")
                .then().statusCode(200).extract().response();
        String body = res.asPrettyString();
        System.out.println(body);

        JSONArray root = new JSONArray(body);
        JSONObject jsonRes = root.getJSONObject(0);
        ArrayList<String> keys = new ArrayList<>(jsonRes.keySet());
        for(String key : keys){
            System.out.println(key);
        }

        System.out.println("******* test Get All Product End *******");
    }

    @Test
    public static void testGetAllProductKeyWithValue(){
        System.out.println("******* test Get All Product Key With Value Start *******");
        Response res = RestAssured.given()
                .when().get("https://fakestoreapi.com/products/1")
                .then().statusCode(200).extract().response();
        String body = res.asPrettyString();
        System.out.println(body);



        System.out.println("******* test Get All Product Key With Value End *******");
    }
}
