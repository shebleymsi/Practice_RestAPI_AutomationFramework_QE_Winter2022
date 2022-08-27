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

    @Test
    public void testGetAllPosts(){
        ValidatableResponse response= this.postsAPIClient.getAllPosts();
        response.log();
       response.statusCode(200);
       response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testCreatePost(){
        PostPoJo obj=new PostPoJo(11,101,"QA Engineer","Test body");

        ValidatableResponse response= this.postsAPIClient.createPost(obj);
       // response.log().all();
        response.statusCode(201);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testUserCanCreateAPostSuccessfully(){
      //  PostPoJo obj=new PostPoJo(11,101,"QA Engineer","Test body");

        int userId=20;
        String title="Dr";
        String body="Patient descriptions";
        String patientName="james";
        String contact="779879879";

        // Create json payload
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        json.put("title",title);
        json.put("body",body);
        json.put("patientName",patientName);
        json.put("contact",contact);

        ValidatableResponse response= this.postsAPIClient.createPost(json);
        // response.log().all();
        response.statusCode(201);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());

        // Verify property
        int actualId=response.extract().body().path("userId");
        Assert.assertEquals(actualId,userId,"user id value does not match");

    }

}
