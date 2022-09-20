package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _01_SimpleGetRequest {

    String herokuURL = "https://restful-booker.herokuapp.com/booking";

    @Test
    public void test01(){
        Response response = RestAssured.get(herokuURL);

        // print the status code
        System.out.println("StatusCode : "+response.statusCode());

        // print the content type
        System.out.println("ContentType = " + response.contentType());
    }

    @Test
    public void test02(){
        Response response = RestAssured.get(herokuURL);

        // print the json body
        response.prettyPrint();
    }

    @Test
    public void test03(){
        Response response = RestAssured.get(herokuURL);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }

    @Test
    public void test04(){
        given().accept(ContentType.JSON)
                .when().get(herokuURL)
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8");
    }

    @Test
    public void test05(){
        Response response = given().accept("application/json")
                .when().get(herokuURL + "/53");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
        System.out.println("StatusCode : "+response.statusCode());

        Assert.assertTrue(response.body().asString().contains("Jessica"),"Test FAILED - Name ");
    }

}
