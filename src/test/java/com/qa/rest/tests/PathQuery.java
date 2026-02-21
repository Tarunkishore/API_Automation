package com.qa.rest.tests;

import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PathQuery {
//    @Test
    public void pathQueryParameter(){
        Response response = RestAssured
        .given().auth().basic(ConfigReader.getProperty("agileCRM_Username"),ConfigReader.getProperty("agileCRM_Password"))
                .accept("application/json")
        .when()
                .get("https://tarunkishore.agilecrm.com/dev/api/contacts");
        String res = new String(response.asPrettyString());
        System.out.println(res);
        System.out.println(response.getStatusCode());

    }

//    @Test
    public static void testcookie(){
        Response res = given().when().get("https://google.com");
//        System.out.println("AEC cookie: "+res.cookie("AEC"));
//        System.out.println("NID cookie: "+res.cookie("NID"));

        Map<String, String> cookies_key = res.cookies();
        for(String k:cookies_key.keySet()){
            System.out.println(k+" : "+res.getCookie(k));
        }
    }

    @Test
    public static void testHeaders(){
        Response res = given().when().get("https://google.com");
        Headers allHeaders = res.getHeaders();
        for(Header hd:allHeaders){
            System.out.println(hd.getName()+" : "+hd.getValue());
        }
    }
}
