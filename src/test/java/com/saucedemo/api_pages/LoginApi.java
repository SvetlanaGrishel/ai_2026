package com.saucedemo.api_pages;

import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class LoginApi {

    public static String login(String username, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        return given()
            .contentType(ContentType.JSON)
            .body(credentials)
        .when()
            .post("/auth/login")
        .then()
            .statusCode(200)
            .extract().path("token");
    }
}
