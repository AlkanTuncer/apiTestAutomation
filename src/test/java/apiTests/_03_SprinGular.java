package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _03_SprinGular {

    String sprinGularURL = "http://142.93.110.12:9119";
    String accessToken = "Bearer" +
            " eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM3MDU4OTIsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZX" +
            "JJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.NA2d2-IEP6xRUkkDz5Su7anNDEctK4bAp68HVqfdt8g";

    @Test
    public void test01(){
        Response response = given().header("Authorization",accessToken)
                .when().get(sprinGularURL+"/api/orders");

        Assert.assertEquals(response.statusCode(),200);
    }

}
