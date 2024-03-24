package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class A02Products {

    public Response GetAllProducts (){
        return  RestAssured.get("https://fakestoreapi.com/products");
    }

    public Response GetProductNo20 (){
        return  RestAssured.get("https://fakestoreapi.com/products/20");
    }

    public Response GetOnly3Results (){
        return  RestAssured.get("https://fakestoreapi.com/products?limit=3");
    }
    public Response GetAllCategories (){
        return  RestAssured.get("https://fakestoreapi.com/products/categories");
    }

    public Response GetJeweleryCategorie (){
        return  RestAssured.get("https://fakestoreapi.com/products/category/jewelery");
    }

    public Response PostAddNewProduct(String Title, String Price, String Description, String Image, String Category) {
        String requestBody = "{\n" +
                "    \"title\": \"" + Title + "\",\n" +
                "    \"price\": \"" + Price + "\",\n" +  // Added comma here
                "    \"description\": \"" + Description + "\",\n" +  // Added comma here
                "    \"image\": \"" + Image + "\",\n" +  // Added comma here
                "    \"category\": \"" + Category + "\"\n" +
                "}";

        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post("https://fakestoreapi.com/products");
    }

    public Response PatchProductNo7(String Title, String Price, String Description, String Image, String Category) {
        String requestBody = "{\n" +
                "    \"title\": \"" + Title + "\",\n" +
                "    \"price\": \"" + Price + "\",\n" +
                "    \"description\": \"" + Description + "\",\n" +
                "    \"image\": \"" + Image + "\",\n" +
                "    \"category\": \"" + Category + "\"\n" +
                "}";

        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .patch("https://fakestoreapi.com/products/7");
    }

    public Response DeleteProductNo6 (){
        return  RestAssured.delete("https://fakestoreapi.com/products/6");
    }

}