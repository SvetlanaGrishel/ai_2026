import com.saucedemo.api_pages.ApiConfig;
import com.saucedemo.api_pages.CartApi;
import com.saucedemo.api_pages.LoginApi;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("API Cart Management")
public class ApiCartTest {
    private String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URI;
        authToken = LoginApi.login("standard_user", "secret_sauce");
    }

    @Test
    @Description("Test managing the cart: add, update, and remove a product")
    public void testCartManagement() {
        // Add product to cart
        Response addResponse = CartApi.addProduct(authToken, 1, 1);
        addResponse.then()
            .statusCode(200)
            .body("success", equalTo(true));

        // Update product in cart
        Response updateResponse = CartApi.updateProduct(authToken, 1, 2);
        updateResponse.then()
            .statusCode(200)
            .body("success", equalTo(true));

        // Remove product from cart
        Response removeResponse = CartApi.removeProduct(authToken, 1);
        removeResponse.then()
            .statusCode(200)
            .body("success", equalTo(true));
    }
}
