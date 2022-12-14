package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _03_SprinGularGetRequest {

    String sprinGularURL = "http://142.93.110.12:9119";
    String accessToken = "Bearer" +
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjQwNDA0NTEsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.Hp0GVeOKU1eDNINkDxNoTeQsKyOu1PsxnnexQoevNB0";

    @Test
    public void test01(){
        Response response = given().header("Authorization",accessToken)
                .when().get(sprinGularURL+"/api/orders");

        Assert.assertEquals(response.statusCode(),200);
    }

    // orderID si 4003 olan order ı print ediniz.

    @Test
    public void test2() {

        Response response = given().header("Authorization", accessToken)
                .when().get(sprinGularURL + "/api/orders?orderid=4003");

        response.prettyPrint();
    }

    // orderID si 4500 olan order ı print ediniz.
    //status code 200 olduğunu test ediniz.

    @Test
    public void test3(){
        Response response= given().header("Authorization", accessToken)
                .when().get(sprinGularURL + "/api/orders?orderid=4500");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
    }

}
