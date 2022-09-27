package POJO_PlainOldJavaObject;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class BookingPostRequest {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("herokuURL");
    }

    /*
    Given header type and Content-Type is application/json
    And request json body is:
    {
      "firstname":"GuiderSoft1",
      "lastname":"Hunters",
      "totalprice":"1250",
      "depositpaid":true,
      "bookingdates":{
                "checkin": "2022-01-01",
                "checkout": "2023-01-01"
                },
      "additionalneeds":"dinner"
   }
    When user sends POST request to '/booking'
    Then status code 200
    And json payload/response/body should contain:
    the same data what is posted
    */

    @Test
    public void postBookingHeroku(){

        Booking booking = new Booking();

        Bookingdates bookingdates = new Bookingdates("2022-01-01","2023-01-01");

        booking.setFirstname("GuiderSoft");
        booking.setLastname("Hunters");
        booking.setTotalprice(1250);
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("dinner");

        Response response = given().header("Accept","application/json")
                .header("Content-Type","application/json")
                .and().body(booking)
                .when().post("/booking");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);

        String firstName = response.jsonPath().getString("booking.firstname");
        String lastName = response.jsonPath().getString("booking.lastname");
        String totalPrice = response.jsonPath().getString("booking.totalprice");
        String depositPaid = response.jsonPath().getString("booking.depositpaid");
        String bookingDates = response.jsonPath().getString("booking.bookingdates");
        String additionalNeeds = response.jsonPath().getString("booking.additionalneeds");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("depositPaid = " + depositPaid);
        System.out.println("bookingDates = " + bookingDates);
        System.out.println("additionalNeeds = " + additionalNeeds);
    }

    @Test
    public void postBookingHerokuWithMap(){
        // Create a map to keep request json body information
        Map<String,Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin","2022-01-01");
        bookingDatesMap.put("checkout","2023-01-01");

        Map<String,Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("firstname","GuiderSoft53");
        requestBodyMap.put("lastname","Hunters");
        requestBodyMap.put("totalprice",5353);
        requestBodyMap.put("depositpaid",false);
        requestBodyMap.put("bookingdates",bookingDatesMap);
        requestBodyMap.put("additionalneeds","lunch");

        Response response = given().header("Content-Type","application/json")
                .and().body(requestBodyMap)
                .when().post("/booking");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);

        String checkIn = response.jsonPath().getString("booking.bookingdates.checkin");
        System.out.println("checkIn = " + checkIn);


    }

    @Test
    public void postNewBooking(){
        Bookingdates bookingdates = new Bookingdates("2022-01-01","2023-01-01");
        Booking booking = new Booking("Alkan","Tuncer",5300,true,bookingdates,"breakfast");

        Response response = given().header("Accept","application/json")
                .header("Content-Type","application/json")
                .and().body(booking)
                .when().post("/booking");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);

        String bookingId = response.jsonPath().getString("bookingid");
        String firstName = response.jsonPath().getString("booking.firstname");
        String lastName = response.jsonPath().getString("booking.lastname");
        String totalPrice = response.jsonPath().getString("booking.totalprice");
        String depositPaid = response.jsonPath().getString("booking.depositpaid");
        String bookingDates = response.jsonPath().getString("booking.bookingdates");
        String additionalNeeds = response.jsonPath().getString("booking.additionalneeds");

        System.out.println("bookingId = " + bookingId);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("depositPaid = " + depositPaid);
        System.out.println("bookingDates = " + bookingDates);
        System.out.println("additionalNeeds = " + additionalNeeds);


        Map<String,Object> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","1992-03-04");
        bookingdatesMap.put("checkout","1992-10-04");

        Map<String,Object> bookingMap = new HashMap<>();
        bookingMap.put("firstname","Dante");
        bookingMap.put("lastname","Jugking");
        bookingMap.put("totalprice",5353);
        bookingMap.put("depositpaid",false);
        bookingMap.put("bookingdates",bookingdatesMap);
        bookingMap.put("additionalneeds","dumbell&barbell");

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("bookingId",response.jsonPath().getString("bookingid"));
        responseMap.put("booking",bookingMap);

    }

}
