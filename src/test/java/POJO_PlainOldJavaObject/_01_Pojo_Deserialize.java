package POJO_PlainOldJavaObject;


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class _01_Pojo_Deserialize {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("springularURL");
    }

    /*
         - De Serialization -> Json to Java Collections

         - POJO -> Plain Old Java Object
                   Json to POJO(Custom Class)

             {
                "schoolName" : "guidersoft",
                "city" : "istanbul",
                "country" : "TR"
             }

             public class School{
                private String schoolName;
                private String city;
                private String country;

                constructor
                setter and getter methods

             }

             School school01 = response.body().as(School.class);

             assertEquals(school1.getCity(),"istanbul");
     */

    /*
   {
        "version": "1.0.0",
        "major": 1,
        "minor": 0,
        "patch": 0
    }
     */

    @Test
    public void versionPOJO(){

        Response response = given().header("Authorization",accessToken)
                .when().get("/version");
        assertEquals(response.statusCode(),200);

        // Json to POJO --> de-serialize to java custom class
        // Json to our version class

        Version version1 = response.body().as(Version.class);
        System.out.println(version1);

        System.out.println("version1.getVersion() = " + version1.getVersion());
        System.out.println("version1.getMajor() = " + version1.getMajor());
        System.out.println("version1.getMinor() = " + version1.getMinor());
        System.out.println("version1.getPatch() = " + version1.getPatch());

        assertEquals(version1.getVersion(),"1.0.0");
        assertEquals(version1.getMajor(),1.0);
        assertEquals(version1.getMinor(),0.0);
        assertEquals(version1.getPatch(),0.0);

    }

    /*
        {
    "firstname": "Dante",
    "lastname": "Jugking",
    "totalprice": 253,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-03-04",
        "checkout": "2021-04-04"
    },
    "additionalneeds": "Breakfast"
    }
     */

    @Test
    public void booking15ToPOJO(){

        Response response = given().accept("application/json").when().get(ConfigurationReader.get("herokuURL")+"/booking/1945");
        assertEquals(response.statusCode(),200);

        Booking1945 booking1945 = response.body().as(Booking1945.class);
        //System.out.println(booking1945);

        System.out.println("booking1945.getFirstname() = " + booking1945.getFirstname());
        System.out.println("booking1945.getLastname() = " + booking1945.getLastname());

    }

    @Test
    public void gson_example(){

        Gson gson = new Gson();

        // Json to Java Collections or POJO  --> de-serialization
        String ourJsonData = "   {\n" +
                "        \"version\": \"1.0.0\",\n" +
                "        \"major\": 1,\n" +
                "        \"minor\": 0,\n" +
                "        \"patch\": 0\n" +
                "    } ";

        Map<String,Object> map = gson.fromJson(ourJsonData,Map.class);
        System.out.println(map);

        Version version = gson.fromJson(ourJsonData,Version.class);
        System.out.println(version);

        // -------SERIALIZATION-------
        // Java Collections or POJO to Json

        Version versionNew = new Version("1.1.0",53,17,3);
        String anotherVersion = gson.toJson(versionNew);
        System.out.println(anotherVersion);

    }

}
