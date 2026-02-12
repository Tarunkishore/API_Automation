package com.qa.rest.tests;

import com.qa.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.util.Asserts;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiAutomation {
    public static void main(String args[]) throws IOException {
//        ApiAutomation.getMethod();
//        ApiAutomation.postMethod();
//        ApiAutomation.putMethod();
//        ApiAutomation.deleteMethod();
        ApiAutomation.getMethod1();
    }

    public static void getMethod() {
        System.out.println("****************** GET Method Start ******************");
        Response response = RestAssured
                .given().auth().basic(ConfigReader.getProperty("agileCRM_Username"),ConfigReader.getProperty("agileCRM_Password"))
                .accept("application/json")
                .get("https://tarunkishore.agilecrm.com/dev/api/contacts");
        String strResp = response.getBody().asPrettyString();
        System.out.println(strResp);
        System.out.println("Status Code of getMethod: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Response Time of getMethod: " + response.getTime());

        JSONArray resJsonArray = new JSONArray(strResp);
        JSONObject resJsonObjectBody = resJsonArray.getJSONObject(0);
        Number resArrayObjectId = resJsonObjectBody.getNumber("id");
        System.out.println("resArrayObjectId: " + resArrayObjectId);

        for (int i = 0; i < resJsonArray.length(); i++) {
            JSONObject jsonObj = resJsonArray.getJSONObject(i);
            System.out.println(i + " : id : " + jsonObj.getNumber("id"));
        }


        String resArrayObjectType = resJsonObjectBody.getString("type");
        System.out.println("resArrayObjectType: " + resArrayObjectType);

        System.out.println("created_time: " + resJsonObjectBody.getNumber("created_time"));
        System.out.println("updated_time: " + resJsonObjectBody.getNumber("updated_time"));
        System.out.println("last_contacted: " + resJsonObjectBody.getNumber("last_contacted"));
        System.out.println("last_emailed: " + resJsonObjectBody.getNumber("last_emailed"));
        System.out.println("last_campaign_emaild: " + resJsonObjectBody.getNumber("last_campaign_emaild"));
        System.out.println("last_called: " + resJsonObjectBody.getNumber("last_called"));
        System.out.println("viewed_time: " + resJsonObjectBody.getNumber("viewed_time"));

        JSONObject resJsonObjectBody_BodyViewed = resJsonObjectBody.getJSONObject("viewed");
        Number resJsonObjectBody_BodyViewed_ViewesTime = resJsonObjectBody_BodyViewed.getNumber("viewed_time");
        System.out.println("resJsonObjectBody_BodyViewed_ViewesTime: " + resJsonObjectBody_BodyViewed_ViewesTime);

        System.out.println("star_value: " + resJsonObjectBody.getNumber("star_value"));
        System.out.println("lead_score: " + resJsonObjectBody.getNumber("lead_score"));
        System.out.println("klout_score: " + resJsonObjectBody.getString("klout_score"));

        JSONArray resTagsArray = resJsonObjectBody.getJSONArray("tags");
        String tagsStr0 = resTagsArray.getString(0);
        String tagsStr1 = resTagsArray.getString(1);
        System.out.println("tagsStr0: " + tagsStr0);
        System.out.println("tagsStr1: " + tagsStr1);

        for (int i = 0; i < resTagsArray.length(); i++) {
            String str = resTagsArray.getString(i);
            System.out.println("for Loop: " + str);
        }


        System.out.println("****************** GET Method End ******************");
    }

    public static void deleteMethod() throws IOException {
        System.out.println("****************** DELETE Method Start ******************");
        Response response = RestAssured.given()
                .auth().basic("tarunkishore@qa.com", "112233")
                .accept("application/json")
//                .pathParam("id", "5857405906386944")
                .delete("https://tarunkishore.agilecrm.com/dev/api/contacts/4653106203394048");
        System.out.println("Status Code: "+response.getStatusCode());

        System.out.println("****************** DELETE Method End ******************");
    }


    public static void postMethod() throws IOException {
        System.out.println("****************** POST Method Start ******************");
        byte[] reqJsonDatabtarray = Files.readAllBytes(Paths.get("/Users/tarunkishore/eclipse-workspace/RestAPI/src/test/resources/CreatContact.json"));
        String reqJsonBody = new String(reqJsonDatabtarray);

        JSONObject reqJsonObjStarValue = new JSONObject(reqJsonBody);
        Number reqStarValue = reqJsonObjStarValue.getNumber("star_value");
        System.out.println("reqStarValue: " + reqStarValue);

        Response response = RestAssured
                .given().auth().basic("tarunkishore@qa.com", "112233")
                .contentType("application/json").accept("application/json")
                .body(reqJsonBody)
                .post("https://tarunkishore.agilecrm.com/dev/api/contacts");
        String strResp = response.getBody().asPrettyString();
        System.out.println(strResp);
        System.out.println("Status Code of postMethod: " + response.getStatusCode());
        System.out.println("Response Time of postMethod: " + response.getTime());

        JSONObject jsonResponseRoot = new JSONObject(strResp);
        int contactId = jsonResponseRoot.getInt("id");
        System.out.println("contactId : " + contactId);

        String actualType = jsonResponseRoot.getString("type");
        if (actualType.equalsIgnoreCase("person")) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        JSONObject jsoViewed = jsonResponseRoot.getJSONObject("viewed");
        Number viewed_time = jsoViewed.getNumber("viewed_time");
        System.out.println("viewed_time: " + viewed_time);

        JSONArray jsonTagArray = jsonResponseRoot.getJSONArray("tags");
        String firstTag = jsonTagArray.getString(0);
        System.out.println("firstTag: " + firstTag);
        String secondTag = jsonTagArray.getString(1);
        System.out.println("secondTag: " + secondTag);

        for (int i = 0; i < jsonTagArray.length(); i++) {
            String x = jsonTagArray.getString(i);
            System.out.println("Using for lopp: " + x);
        }

        JSONArray jsonPropArray = jsonResponseRoot.getJSONArray("properties");
        JSONObject jsoFirstName = jsonPropArray.getJSONObject(0);
        String fName = jsoFirstName.getString("value");
        System.out.println("FirstName: " + fName);

        Number repStarValue = jsonResponseRoot.getNumber("star_value");
        System.out.println("repStarValue: " + repStarValue);

        Assert.assertEquals(reqStarValue, repStarValue);

        System.out.println("****************** POST Method End ******************");
    }

    public static void getMethod1() {
        System.out.println("****************** GET Method-1 Start ******************");
        Response response = RestAssured
        .given()
                .auth().basic(ConfigReader.getProperty("agileCRM_Username"),ConfigReader.getProperty("agileCRM_Password"))
                .accept("application/json")
                .get("https://tarunkishore.agilecrm.com/dev/api/contacts");
        String strResp = response.getBody().asPrettyString();
//        System.out.println(strResp);
        System.out.println("Status Code of getMethod: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Response Time of getMethod: " + response.getTime());

        JSONArray resJsonArray = new JSONArray(strResp);
        JSONObject resJsonObjectBody = resJsonArray.getJSONObject(0);
        Number resArrayObjectId = resJsonObjectBody.getNumber("id");
        System.out.println("resArrayObjectId: " + resArrayObjectId);

        for (int i = 0; i < resJsonArray.length(); i++) {
            JSONObject jsonObj = resJsonArray.getJSONObject(i);
            System.out.println(i + " : id : " + jsonObj.getNumber("id"));
        }


        String resArrayObjectType = resJsonObjectBody.getString("type");
        System.out.println("resArrayObjectType: " + resArrayObjectType);




        System.out.println("****************** GET Method-1 End ******************");
    }

}


