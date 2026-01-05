package com.saucedemo.api_tests;

import com.saucedemo.api_pages.ApiConfig;

import com.saucedemo.api_pages.LoginApi;

import io.qameta.allure.Description;

import io.qameta.allure.Feature;

import io.restassured.RestAssured;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;



import static org.testng.Assert.assertNotNull;



@Feature("API Authentication")

public class ApiLoginTest {



    @BeforeClass

    public void setup() {

        RestAssured.baseURI = ApiConfig.BASE_URI;

    }

    @Test
    @Description("Test API login with valid credentials")
    public void testLoginWithValidCredentials() {
        String token = LoginApi.login("standard_user", "secret_sauce");
        assertNotNull(token);
    }
}
