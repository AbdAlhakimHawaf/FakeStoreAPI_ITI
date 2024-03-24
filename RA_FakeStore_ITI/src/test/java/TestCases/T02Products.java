package TestCases;

import APIs.A01Login;
import APIs.A02Products;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class T02Products {

    A02Products a02ProductsObject = new A02Products();

    @Test(priority = 1)
    public void GetAllProductsTest(){
        Response response = a02ProductsObject.GetAllProducts();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);

        //Hard Assertion Of Product Id 1
        double ID_1 = jsonPath.getDouble("[0].id");
        String Title_1 = jsonPath.getString("[0].title");
        Assert.assertEquals(ID_1, Double.parseDouble("1"));
        Assert.assertEquals(Title_1, "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");

        //Hard Assertion Of Product Id 3
        double ID_3 = jsonPath.getDouble("[2].id");
        String Description_3 = jsonPath.getString("[2].description");
        Assert.assertEquals(ID_3, Double.parseDouble("3"));
        Assert.assertEquals(Description_3, "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.");
    }

    @Test(priority = 2)
    public void GetProductNo20Test(){
        Response response = a02ProductsObject.GetProductNo20();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);


        double ID_20 = jsonPath.getDouble("id");
        double Rating_Rate_20 = jsonPath.getDouble("rating.rate");
        Assert.assertEquals(ID_20, Double.parseDouble("20"));
        Assert.assertEquals(Rating_Rate_20, Double.parseDouble("3.6"));
    }

    @Test(priority = 3)
    public void GetOnly3Product() {
        Response response = a02ProductsObject.GetOnly3Results();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String jsonResponse = response.getBody().asString();

        // Parse the JSON response to a JSONArray
        JSONArray jsonArray = new JSONArray(jsonResponse);
       // System.out.println(jsonArray);

        // Assert that the JSONArray contains exactly 3 items
        Assert.assertEquals(jsonArray.length(), 3, "Response contains exactly 3 items");
    }

    @Test(priority = 4)
    public void GetAllCategories() {
        Response response = a02ProductsObject.GetAllCategories();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String jsonResponse = response.getBody().asString();

        // Parse the JSON response to a JSONArray
        JSONArray jsonArray = new JSONArray(jsonResponse);
        // System.out.println(jsonArray);

        // Assert that the JSONArray contains exactly 5 Categories Only
        Assert.assertEquals(jsonArray.length(), 4, "Response contains exactly 3 items");

        Assert.assertTrue(jsonResponse.contains("men's clothing"),"Not Containing that Category");
    }

    @Test(priority = 5)
    public void GetJeweleryCategoryTest() {
        Response response = a02ProductsObject.GetJeweleryCategorie();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getTime() < 3000, "Response time exceeds 3 seconds");

        String jsonResponse = response.getBody().asString();
        JSONArray jsonArray = new JSONArray(jsonResponse);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String categoryType = jsonObject.getString("category");
            Assert.assertEquals(categoryType, "jewelery");
        }
    }

    @Test(priority = 6)
    public void PostAddNewProductTest() {
        String Title = "test product";
        String Price = "13.5";
        String Description = "lorem ipsum set";
        String Image = "https://i.pravatar.cc";
        String Category = "electronic";

        Response response = a02ProductsObject.PostAddNewProduct(Title, Price,Description,Image,Category);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);

        String  ResponseId = jsonPath.getString("id");
        String  ResponseTitle = jsonPath.getString("title");
        String  ResponsePrice = jsonPath.getString("price");
        String  ResponseDescription = jsonPath.getString("description");
        String  ResponseImage = jsonPath.getString("image");
        String  ResponseCategory = jsonPath.getString("category");

        Assert.assertEquals(ResponseId, "21");
        Assert.assertEquals(ResponseTitle,Title);
        Assert.assertEquals(ResponsePrice, Price);
        Assert.assertEquals(ResponseDescription, Description);
        Assert.assertEquals(ResponseImage, Image);
        Assert.assertEquals(ResponseCategory, Category);


    }

    @Test(priority = 7)
    public void PatchProductNo7Test() {
        String Title = "test product";
        String Price = "13.5";
        String Description = "lorem ipsum set";
        String Image = "https://i.pravatar.cc";
        String Category = "electronic";

        Response response = a02ProductsObject.PatchProductNo7(Title, Price,Description,Image,Category);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);

        String  ResponseId = jsonPath.getString("id");
        String  ResponseTitle = jsonPath.getString("title");
        String  ResponsePrice = jsonPath.getString("price");
        String  ResponseDescription = jsonPath.getString("description");
        String  ResponseImage = jsonPath.getString("image");
        String  ResponseCategory = jsonPath.getString("category");

        Assert.assertEquals(ResponseId, "7");
        Assert.assertEquals(ResponseTitle,Title);
        Assert.assertEquals(ResponsePrice, Price);
        Assert.assertEquals(ResponseDescription, Description);
        Assert.assertEquals(ResponseImage, Image);
        Assert.assertEquals(ResponseCategory, Category);


    }

    @Test(priority = 8)
    public void DeleteProductNo6() {
        Response response = a02ProductsObject.DeleteProductNo6();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);

        //Hard Assertion Of Product Id 1
        double DeletedID = jsonPath.getDouble("id");
        Assert.assertEquals(DeletedID, Double.parseDouble("6"));
    }

    }



