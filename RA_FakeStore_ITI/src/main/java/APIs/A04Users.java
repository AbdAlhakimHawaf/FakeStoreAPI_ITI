package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class A04Users {
    public Response GetAllUsers (){
        return  RestAssured.get("https://fakestoreapi.com/users");
    }
    public Response GetUserNo1 (){
        return  RestAssured.get("https://fakestoreapi.com/users/1");
    }
    public Response GetOnly4Users (){
        return  RestAssured.get("https://fakestoreapi.com/users?limit=4");
    }
    public Response GetAllDescUsers (){
        return  RestAssured.get("https://fakestoreapi.com/users?sort=desc");
    }

    public Response PostNewUser(Integer userId, String UserName) {
        String requestBody = "{\n" +
                "    \"id\": " + userId + ",\n" +
                "    \"username\": \"" + UserName + "\"\n" +
                "}";

        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post("https://fakestoreapi.com/users");
    }

    public Response DeleteUserNo6 (){
        return  RestAssured.delete("https://fakestoreapi.com/users/6");
    }
}
