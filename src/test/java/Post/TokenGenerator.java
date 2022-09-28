package Post;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenGenerator {

    public String TokenGenerator(String username,String password){
        TokenPost tokenPost = new TokenPost(username,password);

        Response response = given().header("Accept","*/*")
                .header("Content-Type","application/json")
                .and().body(tokenPost)
                .when().post("/session");
        //response.prettyPrint();

        String token = response.jsonPath().getString("item.token");
        return token;
    }

}
