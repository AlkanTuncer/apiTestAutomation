package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class _06_SprinGularTestWithPath {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("springularURL");
    }

        /*
    {
    "operationStatus": "SUCCESS",
    "operationMessage": null,
    "data": {
        "userId": "guidersoft",        data.userId
        "password": "quality_hunter",
        "company": "Abshire Inc",
        "firstName": "Mrinmoy",
        "lastName": "Majumdar",
        "email": "arivera2@joomla.org",
        "role": "USER",
        "fullName": "MrinmoyMajumdar",
        "test": {  -> test kısmını biz uydurduk!
            "test1" : "pass",   data.test.test1
            "test2" : "pass",   data.test.test2
            "test3" : "fail"    data.test.test3
                }
            }
    }
     */

    @Test
    public void testUserWithPath(){
        Response response = given().header("Authorization",accessToken)
                           .when().get("/user");

        //response.prettyPrint();

        String operationStatus = response.path("operationStatus");
        String operationMessage = response.path("operationMessage");
        //String data = response.path("data");
        String userId = response.path("data.userId");
        String password = response.path("data.password");
        String company = response.path("data.company");
        String firstName = response.path("data.firstName");
        String lastName = response.path("data.lastName");
        String email = response.path("data.email");
        String role = response.path("data.role");
        String fullName = response.path("data.fullName");

        /*
        System.out.println("operationStatus = "+operationStatus);
        System.out.println("operationMessage = "+operationMessage);
        //System.out.println("data = "+data);
        System.out.println("userId = "+userId);
        System.out.println("password = " + password);
        System.out.println("company = " + company);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("email = " + email);
        System.out.println("role = " + role);
        System.out.println("fullName = " + fullName);
         */

        assertEquals(operationStatus,"SUCCESS");
        assertNull(operationMessage);  //assertEquals(operationMessage,null);
        assertEquals(userId,"guidersoft");
        assertEquals(password,"quality_hunter");
        assertEquals(company,"Abshire Inc");
        assertEquals(firstName,"Mrinmoy");
        assertEquals(lastName,"Majumdar");
        assertEquals(email,"arivera2@joomla.org");
        assertEquals(role,"USER");
        assertEquals(fullName,"MrinmoyMajumdar");

    }

}
