package TestCases;

import APIs.A02Products;
import APIs.A03Carts;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T03Carts {
    A03Carts a03CartsObject =new A03Carts();

    @Test(priority = 1)
    public void GetAllCartsTest(){
        Response response = a03CartsObject.GetAllCarts();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);

        //Hard Assertion Of Cart Id 1
        double ID_1 = jsonPath.getDouble("[0].id");
        String ProductQuantity_1 = jsonPath.getString("[0].products[0].quantity");
        Assert.assertEquals(ID_1, Double.parseDouble("1"));
        Assert.assertEquals(ProductQuantity_1, "4");
    }

    @Test(priority = 2)
    public void GetCartNo5(){
        Response response = a03CartsObject.GetCartNo5();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);

        //Hard Assertion Of Card Id
        double Id = jsonPath.getDouble("id");
        String FirstProductId = jsonPath.getString("products[0].productId");
        Assert.assertEquals(Id, Double.parseDouble("5"));
        Assert.assertEquals(FirstProductId, "7");
    }

    @Test(priority = 3)
    public void GetOnly3Product() {
        Response response = a03CartsObject.GetOnly5Carts();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String jsonResponse = response.getBody().asString();

        // Parse the JSON response to a JSONArray
        JSONArray jsonArray = new JSONArray(jsonResponse);
        // System.out.println(jsonArray);

        // Assert that the JSONArray contains exactly 5 Carts
        Assert.assertEquals(jsonArray.length(), 5, "Response contains exactly 5 Carts");
    }
    @Test(priority = 4)
    public void GetAllDescCartsTest() {
        Response response = a03CartsObject.GetAllDescCarts();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String jsonResponse = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(jsonResponse);
        JSONArray jsonArray = new JSONArray(jsonResponse);
        int ArrayLength = jsonArray.length();
        double FirstID = jsonPath.getDouble("[0].id");
        double LastID = jsonPath.getDouble("[-1].id");
        Assert.assertEquals(FirstID,(ArrayLength));
        Assert.assertEquals(LastID,1);
    }

    @Test(priority = 5)
    public void GetAllCartsBetween12_10_2019and10_10_2020Test() {
        Response response = a03CartsObject.GetAllCartsbetween10_12_2019and10_10_2020();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getTime() < 3000, "Response time exceeds 3 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String jsonResponse = response.getBody().asString();
        JSONArray jsonArray = new JSONArray(jsonResponse);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse("2019-12-10");
            endDate = dateFormat.parse("2020-10-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            String dateString = data.getString("date");
            Date currentDate = null;
            try {
                currentDate = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(currentDate != null && startDate != null && endDate != null);
            Assert.assertTrue(currentDate.after(startDate) && currentDate.before(endDate) || currentDate.equals(startDate) || currentDate.equals(endDate),
                    "Date " + dateString + " is not between 10-12-2019 and 10-10-2020");

        }
    }
    @Test(priority = 6)
    public void GetCartOfUserNo2(){
        Response response = a03CartsObject.GetCartOfUserNo2();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);

        String UserId = jsonPath.getString("[0].userId");

        Assert.assertEquals(UserId, "2");
    }

    @Test(priority = 7)
    public void PutCartNo7(){
        Response response = a03CartsObject.PutCartNo7(9,18,1);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime()<2000,"Response time exceeds 2 Seconds");
        System.out.println("Response time: " + response.getTime() + " milliseconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);
        Assert.assertEquals(jsonPath.getString("userId"),"9");
        Assert.assertEquals(jsonPath.getString("products[0].productId"),"18");
        Assert.assertEquals(jsonPath.getString("products[0].quantity"),"1");

    }
    @Test(priority = 8)
    public void DeleteCartNo6() {
        Response response = a03CartsObject.DeleteCartNo6();

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
