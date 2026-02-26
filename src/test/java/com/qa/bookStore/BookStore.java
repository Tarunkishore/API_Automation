package com.qa.bookStore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.*;
import org.testng.annotations.*;

public class BookStore {
    @Test
    public void testGetBook(){
        Response response = RestAssured
           .given()
                .baseUri("https://bookstore.toolsqa.com")
           .when()
                .get("/BookStore/v1/Books");
        String res = response.getBody().asPrettyString();
        System.out.println(res);
        JSONObject rootJsonBody = new JSONObject(res);
        JSONArray books = rootJsonBody.getJSONArray("books");

    }
}
