package com.saucedemo.api_tests;

import com.saucedemo.api_pages.ApiConfig;
import com.saucedemo.api_pages.ProductApi;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

@Feature("API Products")
public class ApiProductTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URI;
    }

    @Test
    @Description("Test fetching products and sorting")
    public void testGetAndSortProducts() {
        Response response = ProductApi.getProducts("name_asc");
        response.then()
            .statusCode(200)
            .body("products", everyItem(notNullValue())); // Assuming a 'products' array in response
    }

    @Test
    @Description("Test fetching a single product by ID")
    public void testGetSingleProduct() {
        Response response = ProductApi.getProduct(1);
        response.then()
            .statusCode(200)
            .body("id", equalTo(1));
    }
}
