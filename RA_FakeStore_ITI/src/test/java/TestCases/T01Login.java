package TestCases;

import APIs.A01Login;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class T01Login {

    A01Login a01LoginObject = new A01Login();
    String Token;

    @Test
    public void PostLoginAuthorizedUserTest() {
        String username = "mor_2314";
        String password = "83r5^_";

        A01Login a01LoginObject = new A01Login();
        Response response = a01LoginObject.LoginAuthorizedUser(username, password);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.getTime() < 2000, "Response time exceeds 2 Seconds");
        String JsonResponse = response.andReturn().asString();
        JsonPath jsonPath = new JsonPath(JsonResponse);


        Token = jsonPath.getString("token");
        System.out.println("Token: " + Token);

    }
    }

