package Task;

import Post.TokenGenerator;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CustomerPostWithFaker {

    String accessToken;

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void customerPostTest() {
        TokenGenerator tokenGenerator = new TokenGenerator();
        accessToken = tokenGenerator.TokenGenerator("guidersoft","quality_hunter");

        CustomerPost customerPost = new CustomerPost();

        Faker faker = new Faker();

        // id'si 9000 ile 9053 arasındaki customer'ları faker class'ı ile rastgele bilgilerle oluşturduk.

        int id = 9000;
        for (int i = id; i < 9054; i++) {

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = firstName+"."+lastName+"@guidersoft.com";
            String city = faker.address().city();
            String postalCode = faker.address().zipCode();

            customerPost.setId(i);
            customerPost.setFirstName(firstName);
            customerPost.setLastName(lastName);
            customerPost.setCompany("guidersoft");
            customerPost.setEmail(email);
            customerPost.setPhone("7");
            customerPost.setAddress1("Manchester");
            customerPost.setAddress2("United");
            customerPost.setPostalCode(postalCode);
            customerPost.setCity(city);
            customerPost.setState("TR");

            Response response = given().header("Authorization",accessToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .and().body(customerPost)
                    .when().post("/api/customers");
            response.prettyPrint();
            assertEquals(response.statusCode(), 200);

            String operationStatus = response.path("operationStatus");
            String operationMessage = response.path("operationMessage");

            System.out.println("operationStatus = " + operationStatus);
            System.out.println("operationMessage = " + operationMessage);

            assertEquals(operationStatus, "SUCCESS");
            assertEquals(operationMessage, "Customer Added");

        }
    }
}
