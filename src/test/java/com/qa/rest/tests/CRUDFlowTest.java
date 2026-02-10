package com.qa.rest.tests;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class CRUDFlowTest extends BaseTest {

    private static String userId; // to store created user ID

    // 1️⃣ POST - Create User
    @Test(priority = 1)
    public void createUser() {

        String requestBody = "{ \"name\": \"John\", \"job\": \"QA Engineer\" }";

        userId = request
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("John"))
                .body("job", equalTo("QA Engineer"))
                .extract()
                .path("id"); // store the created user ID

        System.out.println("Created user ID: " + userId);
    }

    // 2️⃣ PUT - Update User
    @Test(priority = 2, dependsOnMethods = "createUser")
    public void updateUser() {

        String updateBody = "{ \"name\": \"John\", \"job\": \"Senior QA Engineer\" }";

        request
                .body(updateBody)
                .when()
                .put("/api/users/" + userId)
                .then()
                .statusCode(200)
                .body("job", equalTo("Senior QA Engineer"));
    }

    // 3️⃣ DELETE - Delete User
    @Test(priority = 3, dependsOnMethods = "updateUser")
    public void deleteUser() {

        request
                .when()
                .delete("/api/users/" + userId)
                .then()
                .statusCode(204); // No Content
    }
}
