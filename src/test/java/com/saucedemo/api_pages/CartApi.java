package com.saucedemo.api_pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CartApi {

    public static Response addProduct(String token, int productId, int quantity) {
        Map<String, Object> product = new HashMap<>();
        product.put("product_id", productId);
        product.put("quantity", quantity);

        return given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(product)
        .when()
            .post("/cart/add");
    }

    public static Response updateProduct(String token, int productId, int quantity) {
        Map<String, Object> product = new HashMap<>();
        product.put("product_id", productId);
        product.put("quantity", quantity);

        return given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(product)
        .when()
            .post("/cart/update");
    }

    public static Response removeProduct(String token, int productId) {
        Map<String, Object> product = new HashMap<>();
        product.put("product_id", productId);

        return given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(product)
        .when()
            .post("/cart/remove");
    }
}
