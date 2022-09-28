package Put_Patch_Delete;

import Post.TokenPost;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PutRequest {

    String accessToken = ConfigurationReader.get("accessTokenHeroku");

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("herokuURL");
    }

    /*
    {
    "firstname" : "Alkan",
    "lastname" : "Tuncer",
    "totalprice" : 153,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "3018-03-04",
        "checkout" : "3019-04-03"
    },
    "additionalneeds" : "Breakfast"
}
     */

    @Test
    public void herokuPutTest(){
        // Create inner json body first -> innermap for the put request
        Map<String,Object> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","3018-03-04");
        bookingdatesMap.put("checkout","3019-03-04");

        Map<String,Object> jsonBodyMap = new HashMap<>();
        jsonBodyMap.put("firstname","Tuncay");
        jsonBodyMap.put("lastname","Tuncay");
        jsonBodyMap.put("totalprice",1753);
        jsonBodyMap.put("depositpaid",true);
        jsonBodyMap.put("bookingdates",bookingdatesMap);
        jsonBodyMap.put("additionalneeds","Coffee");

        given().log().all()
                .and()
                .header("Authorization",accessToken)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Cookie","token=e3bcc6b67d9ff39")
                .and()
                .body(jsonBodyMap)
                .when()
                .put("/booking/1066")
                .then().log().all()
                .assertThat().statusCode(200);
    }


}
