package typicodeTest;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import typicode.PostPoJo;

import typicode.PostsAPIClient;

public class TestTypiCode {

    private PostsAPIClient postsAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.postsAPIClient = new PostsAPIClient();
    }

    //01
    @Test
    public void testGetAllPosts() {
        ValidatableResponse response = this.postsAPIClient.getAllPosts();
        response.log();
        response.statusCode(200);
        response.statusCode(HttpStatus.SC_OK);  // public interface HttpStatus { int SC_OK = 200; }  [v 58 4.35.20]
        System.out.println(response.extract().body().asPrettyString());
    }

    //02
    @Test
    public void testCreatePost() {
        PostPoJo obj = new PostPoJo(11, 101, "QA Engineer", "Test body"); // using constructor make an object

        ValidatableResponse response = this.postsAPIClient.createPost(obj);
        // response.log().all();
        response.statusCode(201);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());
    }

    //03
    @Test
    public void testUserCanCreateAPostSuccessfully() {

        //  PostPoJo obj=new PostPoJo(11,101,"QA Engineer","Test body");
        // v 58 4.50.24

        int userId = 20;
        String title = "Dr";
        String body = "Patient descriptions";
        String patientName = "james";
        String contact = "779879879";

        // Create json payload
        JSONObject json = new JSONObject();  //  import org.json.simple.JSONObject; [ v 58 4.57.00 ]
        json.put("userId", userId);          // put method help as like a key : value pair
        json.put("title", title);
        json.put("body", body);
        json.put("patientName", patientName);
        json.put("contact", contact);

        ValidatableResponse response = this.postsAPIClient.createPost(json);
        // response.log().all();
        response.statusCode(201);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());

        // Verify property
        int actualId = response.extract().body().path("userId");
        System.out.println("userId = " + actualId);  // SHEBLEY : by me
        Assert.assertEquals(actualId, userId, "user id value does not match");

    }

}
