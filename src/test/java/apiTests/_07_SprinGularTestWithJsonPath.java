package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class _07_SprinGularTestWithJsonPath {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("springularURL");
    }


    /*
       {
         "version": "1.0.0",
         "major": 1,
         "minor": 0,
         "patch": 0
       }
     */

    @Test
    public void test01(){
        Response response = given().header("Authorization",accessToken)
                           .when().get("/version");

        //response.prettyPrint();

        String version = response.path("version");
        int major = response.path("major");
        int minor = response.path("minor");
        int patch = response.path("patch");

        System.out.println("version = " + version);
        System.out.println("major = " + major);
        System.out.println("minor = " + minor);
        System.out.println("patch = " + patch);

        assertEquals(version,"1.0.0");
        assertEquals(major,1);
        assertEquals(minor,0);
        assertEquals(patch,0);


        JsonPath jsonPath = response.jsonPath();

        String versionJson = jsonPath.getString("version");
        int majorJson = jsonPath.getInt("major");
        int minorJson = jsonPath.getInt("minor");
        int patchJson = jsonPath.getInt("patch");

        System.out.println("versionJson = " + versionJson);
        System.out.println("majorJson = " + majorJson);
        System.out.println("minorJson = " + minorJson);
        System.out.println("patchJson = " + patchJson);
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
    public void test02(){
        Response response = given().header("Authorization",accessToken)
                .when().get("/user");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        String operationStatus = jsonPath.getString("operationStatus");
        String operationMessage = jsonPath.getString("operationMessage");
        String data = jsonPath.getString("data");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("data = " + data);
    }

    @Test
    public void JsonObject01(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",53);
        jsonObject.put("title","Dante");
        jsonObject.put("body","AlkanTuncer");

        System.out.println(jsonObject);

    }
/*
{
    "firstname": "Mark",
    "lastname": "Ericsson",
    "totalprice": 354,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2016-10-11",
        "checkout": "2017-08-23"
    }
}
 */

    @Test
    public void JsonObject02(){

        JSONObject jsonObjectInner = new JSONObject();
        jsonObjectInner.put("checkin","2016-10-11");
        jsonObjectInner.put("checkout","2017-08-23");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname","Mark");
        jsonObject.put("lastname","Ericsson");
        jsonObject.put("totalprice",354);
        jsonObject.put("depositpaid",false);
        jsonObject.put("bookingdates",jsonObjectInner);

        System.out.println(jsonObject);
    }

    /*
    {
    "operationStatus": "SUCCESS",
    "operationMessage": null,
    "data": {
        "userId": "guidersoft",
        "password": "quality_hunter",
        "company": "Abshire Inc",
        "firstName": "Mrinmoy",
        "lastName": "Majumdar",
        "email": "arivera2@joomla.org",
        "role": "USER",
        "fullName": "MrinmoyMajumdar"
    }
}
     */

    @Test
    public void JsonObject03(){
        JSONObject jsonObjectInner = new JSONObject();
        jsonObjectInner.put("userId", "guidersoft");
        jsonObjectInner.put("password", "quality_hunter");
        jsonObjectInner.put("company", "Abshire Inc");
        jsonObjectInner.put("firstName", "Mrinmoy");
        jsonObjectInner.put("lastName", "Majumdar");
        jsonObjectInner.put("email", "arivera2@joomla.org");
        jsonObjectInner.put("role", "USER");
        jsonObjectInner.put("fullName", "MrinmoyMajumdar");

        JSONObject jsonObjectBody = new JSONObject();
        jsonObjectBody.put("operationStatus", "SUCCESS");
        jsonObjectBody.put("operationMessage", "null");
        jsonObjectBody.put("data",jsonObjectInner);

        System.out.println(jsonObjectBody);
    }
}
