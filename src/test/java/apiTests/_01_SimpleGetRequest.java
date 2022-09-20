package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _01_SimpleGetRequest {

    String herokuURL = "https://restful-booker.herokuapp.com";

    @Test
    public void test01(){
        Response response = RestAssured.get(herokuURL+"/booking");

        // print the status code
        System.out.println("StatusCode : "+response.statusCode());

        // print the content type
        System.out.println("ContentType = " + response.contentType());
    }

    @Test
    public void test02(){
        Response response = RestAssured.get(herokuURL+"/booking");

        // print the json body
        response.prettyPrint();
    }

    @Test
    public void test03(){
        Response response = RestAssured.get(herokuURL+"/booking");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }

    @Test
    public void test04(){
        given().accept(ContentType.JSON)
                .when().get(herokuURL+"/booking")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8");
    }

    @Test
    public void test05(){
        Response response = given().accept("application/json")
                .when().get(herokuURL + "/booking/53");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
        System.out.println("StatusCode : "+response.statusCode());

        Assert.assertTrue(response.body().asString().contains("Jessica"),"Test FAILED - Name ");
    }

    /*
     * https://restful-booker.herokuapp.com/booking/100 url’ine bir GET request
     * gonderdigimizde donen Response’un,
     * status code’unun 200,
     * ve content type’inin application/json; charset=utf-8,
     * ve Server isimli Header’in degerinin Cowboy,
     * ve status Line’in HTTP/1.1 200 OK
     */

    @Test
    public void testTask(){
        Response response = RestAssured.get(herokuURL+"/booking/100");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),"Cowboy");
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");

        given().get(herokuURL+"/booking/100")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8")
                .and().assertThat().header("Server","Cowboy")
                .and().assertThat().statusLine("HTTP/1.1 200 OK");
    }

    /* TASK
    When users sends a get request to /booking/150
    Then status code should be 200
    And content type should be application/json; charset=UTF-8
    And json body should contain Howard
    */

    @Test
    public void testTask02(){
        Response response = RestAssured.get(herokuURL+"/booking/150");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.body().asString().contains("Howard"));

        given().get(herokuURL+"/booking/150")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8")
                .and().assertThat().body("firstname",Matchers.equalTo("Howard"));
    }

}
