package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class _10_JsonToJavaCollections {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("springularURL");
    }

    // De-Serialization  --> Json to Java Collection like List, Set, Map

    @Test
    public void versionToMap(){
        /*
        {
          "version": "1.0.0",
          "major": 1,
          "minor": 0,
          "patch": 0
        }
         */
        Response response = given().header("Authorization",accessToken)
                           .when().get("/version");

        assertEquals(response.statusCode(),200);

        // we will convert json response to java map
        Map<String,Object> jsonDataMap = response.body().as(Map.class);
        System.out.println(jsonDataMap);

        String version = (String) jsonDataMap.get("version");
        double major = (double) jsonDataMap.get("major");
        double minor = (double) jsonDataMap.get("minor");
        double patch = (double) jsonDataMap.get("patch");

        System.out.println("version = "+version);
        System.out.println("major = " + major);
        System.out.println("minor = " + minor);
        System.out.println("patch = " + patch);

        assertEquals(version,"1.0.0");
        assertEquals(major,1.0);
        assertEquals(minor,0.0);
        assertEquals(patch,0.0);
    }

    /*
    @Test
    public void allEmployeeListOfMap(){
        Response response = given().header("Authorization",accessToken)
                .when().get("/api/employees");

        assertEquals(response.statusCode(),200);

        // we need to de-serialize Json response to List of Maps

        JsonPath jsonPath = response.jsonPath();
        //jsonPath.prettyPrint();

        //String items = jsonPath.getString("items");
        //System.out.println("items = " + items);

        List<Map<String,Object>> allEmployeesList = response.body().as(List.class);
        System.out.println("allEmployeesList = " + allEmployeesList);
        // Response'umuz bu yönteme uygun degil. Array'le baslamıyor bir obje ile baslıyor.
    }
     */

    @Test
    public void allBookingIdsToListOfMap(){
        Response response = given().get(ConfigurationReader.get("herokuURL")+"/booking");
        assertEquals(response.statusCode(),200);

        // we need to de-serialize Json response to List of Maps
        List<Map<String,Object>> allBookingsIdList = response.body().as(List.class);
        System.out.println("allBookingsIdList = " + allBookingsIdList);

        System.out.println("1.Elementin value'su : "+allBookingsIdList.get(0).get("bookingid"));
        System.out.println("7.Elementin value'su : "+allBookingsIdList.get(6).get("bookingid"));

    }

}
