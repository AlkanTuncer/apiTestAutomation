package POJO_PlainOldJavaObject;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


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

}
