package com.saucedemo.api_pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CheckoutApi {

    public static Response checkout(String token, String firstName, String lastName, String postalCode) {
        Map<String, String> checkoutInfo = new HashMap<>();
        checkoutInfo.put("first_name", firstName);
        checkoutInfo.put("last_name", lastName);
        checkoutInfo.put("postal_code", postalCode);

        return given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(checkoutInfo)
        .when()
            .post("/checkout");
    }
}
