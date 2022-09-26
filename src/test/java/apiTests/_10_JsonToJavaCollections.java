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
    }
     */

    @Test
    public void allBookingIdsToListOfMap(){
        Response response = given().get(ConfigurationReader.get("herokuURL")+"/booking");
        assertEquals(response.statusCode(),200);

        // we need to de-serialize Json response to List of Maps
        List<Map<String,Object>> allBookingsIdList = response.body().as(List.class);
        System.out.println("allBookingsIdList = " + allBookingsIdList);

        // print bookingids
        System.out.println("1.Elementin value'su : "+allBookingsIdList.get(0).get("bookingid"));
        System.out.println("7.Elementin value'su : "+allBookingsIdList.get(6).get("bookingid"));
        System.out.println("5.Elementin value'su : "+allBookingsIdList.get(4).get("bookingid"));

        // save the 5.bookingid in map
        Map<String,Object> bookingId_5 = allBookingsIdList.get(4);
        System.out.println("bookingId_5 = " + bookingId_5);

    }

    @Test
    public void productsToMap(){
        Response response = given().header("Authorization",accessToken)
                           .when().get("/api/products");
        assertEquals(response.statusCode(),200);

        // de-seralize JSON response to Java Map
        Map<String,Object> productMap = response.body().as(Map.class);
        System.out.println(productMap.get("operationStatus"));
        System.out.println(productMap.get("first"));
        System.out.println(productMap.get("pageSize"));
        System.out.println(productMap.get("totalPages"));
        System.out.println(productMap.get("totalItems"));
        System.out.println(productMap.get("sort"));
        System.out.println(productMap.get("items"));

        List<Map<String,Object>> itemsList = (List<Map<String, Object>>) productMap.get("items");

        System.out.println(itemsList.get(0).get("id"));
        System.out.println(itemsList.get(1).get("id"));
        System.out.println(itemsList.get(2).get("id"));
        System.out.println(itemsList.get(0).get("productCode"));

         assertEquals(itemsList.get(0).get("id"),601.0);
         assertEquals(itemsList.get(0).get("productCode"),"P1");
         assertEquals(itemsList.get(0).get("productName"),"Nikon D810");
         assertEquals(itemsList.get(0).get("description"),null);
         assertEquals(itemsList.get(0).get("standardCost"),1167.0);
         assertEquals(itemsList.get(0).get("listPrice"),1123.0);
         assertEquals(itemsList.get(0).get("targetLevel"),75.0);
         assertEquals(itemsList.get(0).get("reorderLevel"),10.0);
         assertEquals(itemsList.get(0).get("minimumReorderQuantity"),10.0);
         assertEquals(itemsList.get(0).get("quantityPerUnit"),"50");
         assertEquals(itemsList.get(0).get("discontinued"),1.0);
         assertEquals(itemsList.get(0).get("category"),"Camera");



    }

}