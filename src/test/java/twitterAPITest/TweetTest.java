package twitterAPITest;

import Utils.LearnRandomNumber;
import base.RestBase;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import twitterAPI.Tweet;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static twitterAPI.Tweet.GET_USER_TWEET_ENDPOINT;

public class TweetTest extends RestBase {
    private Tweet tweet;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweet = new Tweet();
    }

    //01

    @Test
    public void testGetUserTimeLineTweet() {
        Tweet tweet = new Tweet();
        ValidatableResponse response = tweet.getUserTimeLineTweet();
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        String expectedValue = "Welcome back Ismat773"; // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedName = "MD SHAHADUL ISLAM SHEBLEY";
        long expectedId = 1590580268682731520L;   // SHEBLEY : Changeable & run code then check always ( after run test case //04 )
        String actualText = response.extract().body().path("[0].text");
        long actualId = response.extract().body().path("[0].id");
        String actualName = response.extract().body().path("[0].user.name");
        Assert.assertEquals(actualText, expectedValue, "Text value does not match");
        Assert.assertEquals(actualId, expectedId, "ID value does not match");
        System.out.println(actualName);
        Assert.assertEquals(actualName, expectedName, "Name value does not match");


    }


    //02
    @Test
    public void testGetUserTimeLineTweetNew() {
        Tweet tweet = new Tweet();
        ValidatableResponse response = tweet.getUserTimeLineTweetNew();
        response.statusCode(200);
        // System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        String expectedValue = "Welcome back Ismat773"; // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedName = "MD SHAHADUL ISLAM SHEBLEY";
        long expectedId = 1590580268682731520L; // SHEBLEY : Changeable & run code then check always ( after run test case //04 )
        String actualText = response.extract().body().path("[0].text");
        long actualId = response.extract().body().path("[0].id");
        // long actualId= response.extract().body().path("x[0].entities.hashtags");
        String actualName = response.extract().body().path("[0].user.name");
        Assert.assertEquals(actualText, expectedValue, "Text value does not match");
        Assert.assertEquals(actualId, expectedId, "ID value does not match");
        System.out.println(actualName);
        Assert.assertEquals(actualName, expectedName, "Name value does not match");


    }


    //03
    @Test
    public void testGetHomeTimeLineTweet() {
        Tweet tweet = new Tweet();
        ValidatableResponse response = tweet.getHomeTimeLineTweet();
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        String expectedValue = "OPINION: What Americans really told Democrats and Republicans on election night 2022 https://t.co/vh1IcOgboM";  // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedName = "Fox News";
        long expectedId = 1590625331714531328L; // SHEBLEY : Changeable & run code then check always ( after run test case //04 )
        String actualText = response.extract().body().path("[0].text");
        long actualId = response.extract().body().path("[0].id");
        String actualName = response.extract().body().path("[0].user.name");
        Assert.assertEquals(actualText, expectedValue, "Text value does not match");
        Assert.assertEquals(actualId, expectedId, "ID value does not match");
        System.out.println(actualName);
        Assert.assertEquals(actualName, expectedName, "Name value does not match");

    }


    //04
    @Test
    public void verifyCreateTweet() {
        String tweetMessage = "Welcome back Ismat" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweet.createTweet(tweetMessage);
        response.statusCode(200);
        response.contentType("application/json");
        //  response.log().all();
        System.out.println(response.extract().body().asPrettyString());
        // Verify tweet value
        String actualTweet = response.extract().body().path("text");
        String actualScreenName = response.extract().body().path("user.screen_name");
        System.out.println(actualTweet);
        Assert.assertEquals(actualTweet, tweetMessage, "Tweet does not match");
        Assert.assertEquals(actualScreenName, "shebley_md", "Screen name does not match");


    }


    //05  "message": "Status is a duplicate."
    @Test
    public void verifyUserCanNotCreateSameTweetTwiceInARow() {
        String tweetMessage = "Welcome back Ismat";
        ValidatableResponse response = this.tweet.createTweet(tweetMessage);
        response.statusCode(403);
        response.contentType("application/json");
        //  response.log().all();
        System.out.println(response.extract().body().asPrettyString());


        // Verify tweet value
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, "Status is a duplicate.", "message does not match");
    }


    //06  SHEBLEY : run after test 04 then collect ID number : 1590634665819328512L FOR Welcome back Ismat49
    @Test
    public void verifyDeleteTweet() {
        ValidatableResponse response = this.tweet.deleteTweet(1590634665819328512L);
        //  response.statusCode(200);
        //  response.log().all();
        System.out.println(response.extract().body().asPrettyString());

    }

    //07  actualResponse <900, 900 is changeable so check manually from Postman API App
    @Test
    public void verifyResponseTime() {
        long actualResponse = this.tweet.responseTimeCheck(GET_USER_TWEET_ENDPOINT);
        //   Assert.assertEquals(actualResponse,actualResponse<700,"Response time does not match");
        Assert.assertTrue(actualResponse < 900, "Response time exit the default time does not match");


    }

    //08
    @Test
    public void verifyHeaderValue1() {
        this.tweet.headerValue1(GET_USER_TWEET_ENDPOINT);

    }

    //09
    @Test
    public void verifyHeaderValue() {
        Headers response = this.tweet.headerValue(GET_USER_TWEET_ENDPOINT);
        String actualHeaderValue = response.getValue("content-type");
        System.out.println(response.getValue("content-type"));
        Assert.assertEquals(actualHeaderValue, "application/json;charset=utf-8", "Header value does not match");
    }


    //10
    @Test
    public void testPropertyFromResponse() {
        // User sent a tweet
        String tweet = "We are learning Rest API Automation and Hashem is the team Lead" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweet.createTweet(tweet);
        //   System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("id"));
        // Verify that the tweet is successful
        response.statusCode(200);

        // this.tweetAPIClient.checkProperty(tweetAPIClient.CREATE_TWEET_ENDPOINT,"text");

        Response response1 = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT);
        // Response response2=  response;
        JsonPath pathEvaluator = response1.jsonPath();
        String property = pathEvaluator.get("[0].text");
        System.out.println("Property value : " + property);
        //   System.out.println(response1.extract().body().asPrettyString());
        System.out.println(response1.body().asPrettyString());

    }



}
