package com.qa.rest.tests;

import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class PatchReqRes {
    public static void main(String... args) throws IOException {
        PatchReqRes prr = new PatchReqRes();

//        prr.testPATCHMethod();
        prr.testPOSTUser13();
        prr.testGETUser13Body();
    }

    public void testPATCHMethod(){
        System.out.println("******* testPOSTMethod START *******");
        String reqBody = "{\n" +
                "  \"id\": \"2\",\n" +
                "  \"email\": \"abc@xyz.com\",\n" +
                "  \"first_name\": \"abc\",\n" +
                "  \"last_name\": \"xyz\"\n" +
                "}";
        Response res = RestAssured
                .given().header("x-api-key",ConfigReader.getProperty("x_api_key"))
                .contentType("application/json").body(reqBody)
            .when().patch("https://reqres.in/api/users/2");
        System.out.println(res.body().asPrettyString());

        System.out.println("******* testPOSTMethod END *******");
    }

    public void testPOSTUser13() throws IOException{
        System.out.println("******* testPOSTUser-13 START *******");

        byte[] byteJsondata = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/test/resources/User13.json"));
        String user13 = new String(byteJsondata);

        Response res = RestAssured.given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
                .contentType("application/json").body(user13)
                .when().post("https://reqres.in/api/users/13");
        String resJsonbody = res.body().asPrettyString();
        System.out.println(resJsonbody);

        System.out.println("******* testPOSTUser-13 END *******");
    }

    public void testGETUser13Body(){
        System.out.println("******* testGETUser2Body START *******");
        String resUser2body = given().header("x-api-key", ConfigReader.getProperty("x_api_key"))
                .contentType("application/json")
                .when().get("https://reqres.in/api/users/13")
                .then().extract().body().asPrettyString();
        System.out.println(resUser2body);
        System.out.println("******* testGETUser2Body END *******");
    }
}
