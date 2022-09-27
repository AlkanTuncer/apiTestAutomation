package Post;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class TokenPostRequest {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("springularURL");
    }

    /*
            {
               "username":"guidersoft",
               "password":"quality_hunter"
            }
     */

    @Test
    public void postForToken(){
        String jsonBody = "{\n" +
                "    \"username\":\"guidersoft\",\n" +
                "    \"password\":\"quality_hunter\"\n" +
                "}";

        Response response = given().log().all()
                                   .header("Accept","*/*")
                                   .header("Content-Type","application/json")
                                   .and().body(jsonBody)
                                   .when().post("/session");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);

        /*
        String operationStatus = response.path("operationStatus");
        String operationMessage = response.path("operationMessage");
        String item = response.path("item");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);
         */

        JsonPath jsonPath = response.jsonPath();
        String operationStatus = jsonPath.getString("operationStatus");
        String operationMessage = jsonPath.getString("operationMessage");
        String item = jsonPath.getString("item");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);

        assertEquals(operationStatus,"SUCCESS");
        assertEquals(operationMessage,"Login Success");
    }

    @Test
    public void postNewTokenPOJO(){
        TokenPost tokenPost = new TokenPost("guidersoft","quality_hunter");
        //tokenPost.setUsername("guidersoft");
        //tokenPost.setPassword("quality_hunter");

        Response response = given().header("Accept","*/*")
                                   .header("Content-Type","application/json")
                                   .and().body(tokenPost)
                                   .when().post("/session");
        response.prettyPrint();

        String operationStatus = response.jsonPath().getString("operationStatus");
        String operationMessage = response.jsonPath().getString("operationMessage");
        String item = response.jsonPath().getString("item");
        String token = response.jsonPath().getString("item.token");
        String userId = response.jsonPath().getString("item.userId");
        String firstName = response.jsonPath().getString("item.firstName");
        String lastName = response.jsonPath().getString("item.lastName");
        String email = response.jsonPath().getString("item.email");
        String roles = response.jsonPath().getString("item.roles");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);
        System.out.println("token = " + token);
        System.out.println("userId = " + userId);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("email = " + email);
        System.out.println("roles = " + roles);
    }

}
