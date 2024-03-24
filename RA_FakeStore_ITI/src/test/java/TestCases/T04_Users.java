package TestCases;

import APIs.A04Users;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.module.ResolutionException;

import static org.testng.TestRunner.PriorityWeight.priority;

public class T04_Users {
    A04Users a04UsersObject = new A04Users();

    @Test(priority=1)
    public void GetAllUsersTest(){
        Response response = a04UsersObject.GetAllUsers();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime()<2000 ,"Response time exceeds 2 Seconds");
        String JsonResponse = response.getBody().asString();
       // JsonPath jsonPath = new JsonPath(JsonResponse);
        JSONArray JsonArray = new JSONArray(JsonResponse);
        for(int i=0; i<JsonArray.length();i++){
            JSONObject Data = JsonArray.getJSONObject(i);
            if(Data.getInt("id") == 8){
                Assert.assertEquals(Data.getString("email") , "william@gmail.com");
               Assert.assertEquals(Data.getJSONObject("name").getString("firstname"), "william");
            }
        }
    }
    @Test(priority = 2)
    public void GetUserNo1Test() {
        Response response = a04UsersObject.GetUserNo1();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime()<=2000,"Response time exceeds 2 Seconds");
        String JsonResponse = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);
        Assert.assertEquals(jsonPath.getDouble("id"),Double.parseDouble("1"));
        Assert.assertEquals(jsonPath.getString("name.firstname"),"john");
    }

    @Test(priority =3)
    public void GetOnly4UsersTest(){
        Response response = a04UsersObject.GetOnly4Users();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime()<=2000,"Response time exceeds 2 Seconds");
        String jsonResponse = response.getBody().asString();
        JSONArray jsonArray =new JSONArray(jsonResponse);
        Assert.assertTrue(jsonArray.length()==4 ,"Users are more than four");
    }
    @Test(priority=4)
    public void GetAllDescUsersTest(){
        Response response = a04UsersObject.GetAllDescUsers();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime()<=2000,"Response time exceeds 2 Seconds");
        String jsonResponse = response.getBody().asString();
        JSONArray jsonArray = new JSONArray(jsonResponse);
        JSONObject FirstData = jsonArray.getJSONObject(0);
        JSONObject LastData = jsonArray.getJSONObject(jsonArray.length()-1);
        Assert.assertEquals(FirstData.getInt("id"),jsonArray.length());
        Assert.assertEquals(LastData.getInt("id"),1);
    }

    @Test(priority =5)
    public void PostNewUser(){
        Response response = a04UsersObject.PostNewUser(1,"ABC");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime()<=2000,"Response time exceeds 2 Seconds");
        String jsonResponse = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(jsonResponse);
        Assert.assertEquals(jsonPath.getInt("id"),1);
     }

     @Test(priority = 6)
    public void DeleteUserNo6(){
         Response response = a04UsersObject.DeleteUserNo6();
         Assert.assertEquals(response.statusCode(),200);
         Assert.assertTrue(response.getTime()<=2000,"Response time exceeds 2 Seconds");
         String JsonResponse = response.getBody().asString();
         JsonPath jsonPath = new JsonPath(JsonResponse);
         Assert.assertEquals(jsonPath.getDouble("id"),Double.parseDouble("6"));
     }
}
