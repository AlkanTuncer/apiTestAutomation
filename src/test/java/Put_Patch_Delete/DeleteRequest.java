package Put_Patch_Delete;

import Post.TokenGenerator;
import Post.TokenPost;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class DeleteRequest {

    String accessToken;

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void springularDeleteRequest(){
        TokenGenerator tokenGenerator = new TokenGenerator();
        accessToken = tokenGenerator.TokenGenerator("guidersoft","quality_hunter");

        Random rn = new Random();
        int idDelete = rn.nextInt(51)+1401;  // 1401 dahil 1452'ye kadar rastgele bir id secicek

        System.out.println("idDelete = "+idDelete);

        given().header("Authorization",accessToken)
                .pathParam("id",idDelete)
                .when().delete("/api/customers/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }

}
