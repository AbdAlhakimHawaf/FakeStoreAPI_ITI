package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class A01Login {

    public Response LoginAuthorizedUser(String username, String password) {
        String requestBody = "{\n" +
                "    \"username\": \"" + username + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}";

        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post("https://fakestoreapi.com/auth/login");
    }
}
