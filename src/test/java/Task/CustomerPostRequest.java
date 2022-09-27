package Task;

import Post.TokenPost;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CustomerPostRequest {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("springularURL");
    }

    /*
    {
        "address1": "Bahcelievler",
        "address2": "Cavuspasa",
        "city": "Istanbul",
        "company": "GuiderSoft",
        "country": "Turkey",
        "email": "at@gmail.com",
        "firstName": "Alkan",
        "id": 520,
        "lastName": "Tuncer",
        "phone": "05000000053",
        "postalCode": "34183",
        "state": "Galatasaray"
    }
     */

    @Test
    public void postCustomer(){
       CustomerPost customerPost = new CustomerPost("Bahcelievler","Cavuspasa","Istanbul","GuiderSoft",
               "Turkey","at@gmail.com","Alkan",53017,"Tuncer","905535175317","34183","GALATASARAY");

       Response response = given().header("Authorization",TokenGenerator("guidersoft","quality_hunter"))
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .and().body(customerPost)
               .when().post("/api/customers");

       response.prettyPrint();
       assertEquals(response.statusCode(),200);

    }



    public static String TokenGenerator(String username,String password){
        TokenPost tokenPost = new TokenPost(username,password);

        Response response = given().header("Accept","*/*")
                .header("Content-Type","application/json")
                .and().body(tokenPost)
                .when().post("/session");
        response.prettyPrint();

        String token = response.jsonPath().getString("item.token");
        return token;
    }

}