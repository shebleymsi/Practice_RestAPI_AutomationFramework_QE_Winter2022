package twitterAPITest;

import Utils.LearnRandomNumber;
import base.RestBase;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import twitterAPI.Tweet;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static twitterAPI.Tweet.*;


public class TweetTest extends RestBase {

    /*
    Note :
    # check 1st the secret.properties
    # For Twitter API version 1.1
    # Project SHEBLEY (QE_WINTER2022_M$I_STANDALONA  v56 : 3.39.30 Authantication informations for Authorization (new) )
    # Lecture 56 and 57, for TweetTest Class
    */

    private Tweet tweet;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweet = new Tweet();
    }

    // 01
    @Test
    public void testGetUserTimeLineTweet() {
        Tweet tweet = new Tweet();
        ValidatableResponse response = tweet.getUserTimeLineTweet();
        response.statusCode(200);
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());  // SHEBLEY : code ok, BUT comment its FOR SMALL CONSOLE

        long expectedId = 1590650473698181121L;            // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedText = "Welcome back Ismat81";      // SHEBLEY : Changeable & run code then check always ( after run test case //04 )
        long expectedUserId = 1557786208402640898L;        // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedName = "MD SHAHADUL ISLAM SHEBLEY";


        long actualId = response.extract().body().path("[0].id");
        String actualText = response.extract().body().path("[0].text");
        long actualUserId = response.extract().body().path("[1].user.id");
        String actualName = response.extract().body().path("[1].user.name");

        System.out.println(actualId);
        Assert.assertEquals(actualId, expectedId, "ID value does not match");

        System.out.println(actualText);
        Assert.assertEquals(actualText, expectedText, "Text value does not match");

        System.out.println(actualUserId);
        Assert.assertEquals(actualUserId, expectedUserId, "ID value does not match");

        System.out.println(actualName);
        Assert.assertEquals(actualName, expectedName, "Name value does not match");

    }


    // 02
    @Test
    public void testGetUserTimeLineTweetNew() {
        Tweet tweet = new Tweet();
        ValidatableResponse response = tweet.getUserTimeLineTweetNew();
        response.statusCode(200);
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());  // SHEBLEY : code ok, BUT comment its FOR SMALL CONSOLE

        long expectedId = 1590650473698181121L;            // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedText = "Welcome back Ismat81";      // SHEBLEY : Changeable & run code then check always ( after run test case //04 )
        long expectedUserId = 1557786208402640898L;        // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedName = "MD SHAHADUL ISLAM SHEBLEY";


        long actualId = response.extract().body().path("[0].id");
        String actualText = response.extract().body().path("[0].text");
        long actualUserId = response.extract().body().path("[1].user.id");
        String actualName = response.extract().body().path("[1].user.name");

        System.out.println(actualId);
        Assert.assertEquals(actualId, expectedId, "ID value does not match");

        System.out.println(actualText);
        Assert.assertEquals(actualText, expectedText, "Text value does not match");

        System.out.println(actualUserId);
        Assert.assertEquals(actualUserId, expectedUserId, "ID value does not match");

        System.out.println(actualName);
        Assert.assertEquals(actualName, expectedName, "Name value does not match");

    }


    // 03
    @Test
    public void testGetHomeTimeLineTweet() {
        Tweet tweet = new Tweet();
        ValidatableResponse response = tweet.getHomeTimeLineTweet();
        response.statusCode(200);
        System.out.println(response.statusCode(200));
        //   System.out.println(response.extract().body().asPrettyString());    // SHEBLEY : code ok, BUT comment its FOR SMALL CONSOLE

        long expectedId = 1590792681352929282L;            // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedText = "Russia flew stolen Western cash and weapons to Iran: Report https://t.co/MdAaHIT8XN"; // SHEBLEY : Changeable & run code then check always ( after run test case //04 )
        int expectedUserId = 1367531;         // SHEBLEY : Changeable & Check always twitter ID and run code ( after run test case //04 )
        String expectedName = "Fox News";

        long actualId = response.extract().body().path("[0].id");
        String actualText = response.extract().body().path("[0].text");
        int actualUserId = response.extract().body().path("[0].user.id");
        String actualName = response.extract().body().path("[0].user.name");

        System.out.println(actualId);
        Assert.assertEquals(actualId, expectedId, "ID value does not match");

        System.out.println(actualText);
        Assert.assertEquals(actualText, expectedText, "Text value does not match");

        System.out.println(actualUserId);
        Assert.assertEquals(actualUserId, expectedUserId, "ID value does not match");

        System.out.println(actualName);
        Assert.assertEquals(actualName, expectedName, "Name value does not match");

    }


    // 04
    @Test
    public void verifyCreateTweet() {
        Tweet tweet = new Tweet();

        String tweetMessage = "Welcome back Ismat" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweet.createTweet(tweetMessage);
        response.statusCode(200);
        System.out.println(response.statusCode(200));

        response.contentType("application/json");
        // response.log().all();
        System.out.println(response.extract().body().asPrettyString());

        // Verify tweet value
        String actualTweet = response.extract().body().path("text");
        String actualName = response.extract().body().path("user.name");
        String actualScreenName = response.extract().body().path("user.screen_name");

        System.out.println(actualTweet);
        Assert.assertEquals(actualTweet, tweetMessage, "Tweet does not match");

        System.out.println(actualName);
        Assert.assertEquals(actualName, "MD SHAHADUL ISLAM SHEBLEY", "Screen name does not match");

        System.out.println(actualScreenName);
        Assert.assertEquals(actualScreenName, "shebley_md", "Screen name does not match");

    }

    // 05 "message": "Status is a duplicate."
    @Test
    public void verifyUserCanNotCreateSameTweetTwiceInARow() {
        String tweetMessage = "Welcome back Ismat81";
        ValidatableResponse response = this.tweet.createTweet(tweetMessage);
        response.statusCode(403);
        response.contentType("application/json");
        //  response.log().all();
        System.out.println(response.extract().body().asPrettyString());

        // Verify tweet value
        String actualMessage = response.extract().body().path("errors[0].message");

        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage, "Status is a duplicate.", "message does not match");

    }

    // 06  SHEBLEY : run after test 04 then collect ID number : 1590632803473186816L FOR Welcome back Ismat972
    @Test
    public void verifyDeleteTweet() {
        ValidatableResponse response = this.tweet.deleteTweet(1590632803473186816L);
//      response.statusCode(200);
//      response.log().all();
        System.out.println(response.extract().body().asPrettyString());

    }

    // 07  actualResponse <900, 900 is changeable so check manually from Postman API App
    @Test
    public void verifyResponseTime() {
        long actualResponse = this.tweet.responseTimeCheck(GET_USER_TWEET_ENDPOINT);

        //  Assert.assertEquals(actualResponse,actualResponse<700,"Response time does not match");
        /*If we do verification like above this way, it will not work, because time is increasing or decreasing.
        so i set up a time, my expectation is it is should not bre more than 900 mili-second,
        now it will verify that actual response should be less than <900. */

        Assert.assertTrue(actualResponse < 900, "Response time exit the default time does not match");

    }

    // 08
    @Test
    public void verifyHeaderValue1() {
        this.tweet.headerValue1(GET_USER_TWEET_ENDPOINT);

    }

    // 09
    @Test
    public void verifyHeaderValue() {
        Headers response = this.tweet.headerValue(GET_USER_TWEET_ENDPOINT);
        String actualHeaderValue = response.getValue("content-type");
        System.out.println(response.getValue("content-type"));
//      System.out.println(actualHeaderValue); // SHEBLEY : By me
        Assert.assertEquals(actualHeaderValue, "application/json;charset=utf-8", "Header value does not match");

    }

    // 10 SHEBLEY : see and learn more
    @Test
    public void testPropertyFromResponse() {


        // User sent a tweet
        String tweet = "We are learning Rest API Automation and KUTUB_BABOR is the team Lead" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweet.createTweet(tweet);
        //  System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("id"));

        // Verify that the tweet is successful
        response.statusCode(200);
        System.out.println(response.statusCode(200)); // SHEBLEY : By me

        Response response1 = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response1.jsonPath();
        String property = pathEvaluator.get("[0].text");

        System.out.println("Property value : " + property);
        //  System.out.println(response1.extract().body().asPrettyString()); // SHEBLEY : wrong code but just check its like b4
        System.out.println(response1.body().asPrettyString());


        /* 1. This the way you can extract any JSON value.
           2. how can you capture or check any value is present or not?
         */

    }


}
