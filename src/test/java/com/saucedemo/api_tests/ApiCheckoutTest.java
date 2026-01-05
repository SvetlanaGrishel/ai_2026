package com.saucedemo.api_tests;

import com.saucedemo.api_pages.ApiConfig;
import com.saucedemo.api_pages.CartApi;
import com.saucedemo.api_pages.CheckoutApi;
import com.saucedemo.api_pages.LoginApi;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("API Checkout")
public class ApiCheckoutTest {
    private String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URI;
        authToken = LoginApi.login("standard_user", "secret_sauce");
        CartApi.addProduct(authToken, 1, 1);
    }

    @Test
    @Description("Test the checkout process")
    public void testCheckout() {
        Response response = CheckoutApi.checkout(authToken, "John", "Doe", "12345");
        response.then()
            .statusCode(200)
            .body("success", equalTo(true))
            .body("order_id", notNullValue());
    }
}
