package com.saucedemo.api_pages;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ProductApi {

    public static Response getProducts(String sort) {
        return given()
            .queryParam("sort", sort)
        .when()
            .get("/products");
    }

    public static Response getProduct(int id) {
        return given()
            .pathParam("id", id)
        .when()
            .get("/products/{id}");
    }
}
