package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class A03Carts {
    public Response GetAllCarts (){
        return  RestAssured.get("https://fakestoreapi.com/carts");
    }

    public Response GetCartNo5 (){
        return  RestAssured.get("https://fakestoreapi.com/carts/5");
    }

    public Response GetOnly5Carts (){
        return  RestAssured.get("https://fakestoreapi.com/carts?limit=5");
    }
    public Response GetAllDescCarts (){
        return  RestAssured.get("https://fakestoreapi.com/carts?sort=desc");
    }
    public Response GetAllCartsbetween10_12_2019and10_10_2020 (){
        return  RestAssured.get("https://fakestoreapi.com/carts?startdate=2019-12-10&enddate=2020-10-10");
    }
    public Response GetCartOfUserNo2 (){
        return  RestAssured.get("https://fakestoreapi.com/carts/user/2");
    }

    public Response PutCartNo7(Integer userId, Integer productId, Integer quantity) {
       String requestBody = "{\n" +
                "    \"userId\": " + userId + ",\n" +
                "    \"products\": [\n"+
                "{ \n"+
                "    \"productId\": " + productId + ",\n" +
                "    \"quantity\": " + quantity + "\n" +
                "}\n"+
                "]\n" +
                "}";

        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .put("https://fakestoreapi.com/carts/7");
    }


    public Response DeleteCartNo6 (){
        return  RestAssured.delete("https://fakestoreapi.com/carts/6");
    }
}
