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
import twitterAPI.TweetProject;

import java.io.FileNotFoundException;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static twitterAPI.Tweet.GET_USER_TWEET_ENDPOINT;

public class TweetProjectTest extends RestBase {

    // Convert json to string: https://jsontostring.com/
    String jsonToString = "{\"event\":{\"type\":\"message_create\",\"message_create\":{\"target\":{\"recipient_id\":\"RECIPIENT_USER_ID\"},\"message_data\":{\"text\":\"HelloWorld!\"}}}}";

    @Test
    public void createJson() {
        System.out.println(jsonToString);
    }

    // *************************************** Start *******************************************


    private TweetProject tweetProject;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetProject = new TweetProject();
    }



    //01
    @Test
    public void testGetUserTimelineTweet() {
        ValidatableResponse response = tweetProject.getUserTimeLineTweet();
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        String expectedValue = "Good afternoon";
        int responseLength = response.extract().body().jsonPath().getList("$").size();
        System.out.println(responseLength);
        String actualValue = null;
        for (int i = 0; i < responseLength; i++) {
            actualValue = response.extract().body().path("[" + i + "].text");
            if (actualValue == expectedValue)
                Assert.assertEquals(actualValue, expectedValue, "not match");
            break;
        }
    }

    //02
    @Test
    public void testGetUserTimelineIDValue() {
        ValidatableResponse response = tweetProject.getUserTimeLineTweet();
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        long expectedId = 1586125192937578501l;
        int responseLength = response.extract().body().jsonPath().getList("$").size();
        System.out.println(responseLength);
        long actualId = 0;
        for (int i = 0; i < responseLength; i++) {
            actualId = response.extract().body().path("[" + i + "].id");
            if (actualId == expectedId)
                Assert.assertEquals(actualId, expectedId, "not match");
            break;
        }
    }

    //03
    @Test
    public void testGetUserName() {
        ValidatableResponse response = tweetProject.getUserTimeLineTweet();
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        String expectedName = "HBO Max";
        int responseLength = response.extract().body().jsonPath().getList("$").size();
        System.out.println(responseLength);
        String actualName = response.extract().body().path("[0].user.name");
        for (int i = 0; i < responseLength; i++) {
            actualName = response.extract().body().path("[" + i + "].name");
            if (actualName == expectedName)
                Assert.assertEquals(actualName, expectedName, "not match");
            break;
        }
    }

    //04
    @Test
    public void testGetUserScreenName() {
        ValidatableResponse response = tweetProject.getUserTimeLineTweet();
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        String expectedName = "CNN";
        int responseLength = response.extract().body().jsonPath().getList("$").size();
        System.out.println(responseLength);
        String actualName = response.extract().body().path("[1].user.name");

        for (int i = 0; i < responseLength; i++) {
            actualName = response.extract().body().path("[" + i + "].name");
            if (actualName == expectedName)
                Assert.assertEquals(actualName, expectedName, "not match");
            break;
        }
    }

    //05
    @Test
    public void testGetUserLocation() {
        ValidatableResponse response = tweetProject.getUserTimeLineTweet();
        System.out.println(response.statusCode(200));
        System.out.println(response.extract().body().asPrettyString());
        String expectedValue = "New York City";
        int responseLength = response.extract().body().jsonPath().getList("$").size();
        System.out.println(responseLength);
        String actualValue = response.extract().body().path("[0].user.name");

        for (int i = 0; i < responseLength; i++) {
            actualValue = response.extract().body().path("[" + i + "].name");
            if (actualValue == expectedValue)
                Assert.assertEquals(actualValue, expectedValue, "not match");
            break;
        }
    }

    //06
    @Test
    public void verifyCreateTweetStatusCode() {
        String tweetMessage = "Nothing is impossible" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        response.log().all();
    }

    //07
    @Test
    public void verifyCreateTweetContentType() {
        String tweetMessage = "Nothing is impossible" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        response.contentType("application/json");
        response.log().all();
    }

    //08
    @Test
    public void verifyCreateTweetTextValue() {
        String tweetMessage = "GoodNight" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        //  to print response
        System.out.println(response.extract().body().asPrettyString());
        // Verify tweet text value
        String actualTweet = response.extract().body().path("text");
        System.out.println(actualTweet);
        Assert.assertEquals(actualTweet, tweetMessage, "Tweet does not match");
    }

    //09
    @Test
    public void verifyCreateTweetScreenName() {
        String tweetMessage = "GoodNight" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        //  to print response
        System.out.println(response.extract().body().asPrettyString());
        // Verify tweet ScreenName
        String actualScreenName = response.extract().body().path("user.screen_name");
        System.out.println(actualScreenName);
        Assert.assertEquals(actualScreenName, "HusnaAmatullah", "Screen name does not match");
    }

    //10
    @Test
    public void verifyCreateTweetLocation() {
        String tweetMessage = "GoodNight" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        //   to print response
        System.out.println(response.extract().body().asPrettyString());
        // Verify tweet ScreenName
        String actualLocation = response.extract().body().path("user.location");
        System.out.println(actualLocation);
        Assert.assertEquals(actualLocation, "Houston, TX", "location does not match");
    }

    //11
    @Test
    public void verifyCreateTweetID() {
        String tweetMessage = "GoodNight" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        //  to print response
        System.out.println(response.extract().body().asPrettyString());
        //  Verify tweet ScreenName
        long actualID = response.extract().body().path("user.id");
        System.out.println(actualID);
        Assert.assertEquals(actualID, 1578448920107253776l, "ID Value does not match");
    }

    //12
    @Test
    public void verifyCreateTweetIDStr() {
        String tweetMessage = "GoodNight" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        //     to print response
        System.out.println(response.extract().body().asPrettyString());
        // Verify tweet ScreenName
        String actualIDStr = response.extract().body().path("user.id_str");
        System.out.println(actualIDStr);
        Assert.assertEquals(actualIDStr, "1578448920107253776", "ID Value does not match");
    }

    //13
    @Test
    public void verifyCreateTweetName() {
        String tweetMessage = "GoodNight" + LearnRandomNumber.randomNumberGenerate();
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(200);
        //      to print response
        System.out.println(response.extract().body().asPrettyString());
        // Verify tweet ScreenName
        String actualName = response.extract().body().path("user.name");
        System.out.println(actualName);
        Assert.assertEquals(actualName, "Husna Amatullah", "ID Value does not match");
    }

    //14
    @Test
    public void verifyUserCanNotCreateSameTweetTwiceInARow() {
        String tweetMessage = "I like API Testing";
        ValidatableResponse response = this.tweetProject.createTweet(tweetMessage);
        response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        // Verify
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, "Status is a duplicate.", "message does not match");
    }

    //15
    @Test
    public void verifyDeleteTweet1() {
        ValidatableResponse response = this.tweetProject.deleteTweet(1587327163493191682l);
        System.out.println(response.extract().body().asPrettyString());
    }

    //16
    @Test
    public void verifyResponseTime() {
        long actualResponse = this.tweetProject.responseTimeCheck(GET_USER_TWEET_ENDPOINT);
        Assert.assertTrue(actualResponse > 400, "Response time exit the default time does not match");
    }

    // 17
    @Test
    public void verifyHeaderValue1() {
        this.tweetProject.headerValue1(GET_USER_TWEET_ENDPOINT);
    }

    // 18
    @Test
    public void verifyHeaderValue() {
        Headers response = this.tweetProject.headerValue(GET_USER_TWEET_ENDPOINT);
        String actualHeaderValue = response.getValue("content-type");
        System.out.println(response.getValue("content-type"));
        Assert.assertEquals(actualHeaderValue, "application/json;charset=utf-8", "Header value does not match");
    }

    //  19
    @Test
    public void testPropertyFromResponseUsingUUID() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

    //20
    @Test
    public void testPropertyFromResponseIDKey() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("id"));
        // Verify that the tweet is successful
        response.statusCode(200);
    }

    //21
    @Test
    public void testPropertyFromResponseTextKey() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("text"));
        // Verify that the tweet is successful
        response.statusCode(200);
    }

    //22
    @Test
    public void testPropertyFromResponseEntitiesKey() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("entities"));
        // Verify that the tweet is successful
        response.statusCode(200);
    }

    //23
    @Test
    public void testPropertyFromResponseProtectedKey() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("protected"));
        // Verify that the tweet is successful
        response.statusCode(200);
    }

    //24
    @Test
    public void testPropertyFromResponseTruncatedKey() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("truncated"));
        // Verify that the tweet is successful
        response.statusCode(200);
    }

    //25
    @Test
    public void testJsonPropertyTextValueFromResponse() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(200);
        Response response1 = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response1.jsonPath();
        String property = pathEvaluator.get("[0].text");
        System.out.println("Property value : " + property);
        System.out.println(response1.body().asPrettyString());
    }

    //26
    @Test
    public void testJsonPropertyIdValueFromResponse() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(200);
        Response response1 = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response1.jsonPath();
        long property = pathEvaluator.get("[0].id");
        System.out.println("Property value : " + property);
        System.out.println(response1.body().asPrettyString());
    }

    //27
    @Test
    public void testJsonPropertyLocationValueFromResponse() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(200);
        Response response1 = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response1.jsonPath();
        String property = pathEvaluator.get("[1].location");
        System.out.println("Property value : " + property);
        System.out.println(response1.body().asPrettyString());
    }

    //28
    @Test
    public void testJsonPropertyScreenNameValueFromResponse() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(200);
        Response response1 = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response1.jsonPath();
        String property = pathEvaluator.get("[1].screen_name");
        System.out.println("Property value : " + property);
        System.out.println(response1.body().asPrettyString());
    }

    //29
    @Test
    public void testJsonPropertyNameValueFromResponse() {
        // User sent a tweet
        String tweet = "I am learning Rest API Automation and I am enjoying." + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetProject.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(200);
        Response response1 = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response1.jsonPath();
        String property = pathEvaluator.get("[1].name");
        System.out.println("Property value : " + property);
        System.out.println(response1.body().asPrettyString());
    }

    // 30
    @Test
    public void testMyFollowerListNameKey() {
        ValidatableResponse response = tweetProject.getFollowerList();
        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("name"));
    }

    // 31
    @Test
    public void testMyFollowerList() {
        ValidatableResponse response = tweetProject.getFollowerList();
        System.out.println(response.extract().body().asPrettyString());
    }

    //32
    @Test
    public void verifyMyFollowerListResponseStatusCode() {
        ValidatableResponse response = tweetProject.getFollowerList();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        System.out.println(response.statusCode(200));
    }

    //33
    @Test
    public void verifyMyFollowerListName() {
        ValidatableResponse response = tweetProject.getFollowerList();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        System.out.println(response.statusCode(200));
        // Verify follower's Name
        String actualName = response.extract().body().path("users[0].name");
        System.out.println(actualName);
        Assert.assertEquals(actualName, "irin", "Value does not match");
        String actualName2 = response.extract().body().path("users[1].name");
        System.out.println(actualName2);
        Assert.assertEquals(actualName2, "James Dickson", "Value does not match");
    }

    //34
    @Test
    public void verifyMyFollowerListIDValue() {
        ValidatableResponse response = tweetProject.getFollowerList();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        System.out.println(response.statusCode(200));
        // Verify follower's ID value
        long actualIDValue = response.extract().body().path("users[0].id");
        System.out.println(actualIDValue);
        Assert.assertEquals(actualIDValue, 1557365649118306304l, "ID Value does not match");
        long actualId2 = response.extract().body().path("users[1].id");
        System.out.println(actualId2);
        Assert.assertEquals(actualId2, 1570910006073073664l, "ID Value does not match");
    }

    //35
    @Test
    public void verifyMyFollowerListScreenName() {
        ValidatableResponse response = tweetProject.getFollowerList();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        System.out.println(response.statusCode(200));
        // Verify follower's ScreenName
        String actualName = response.extract().body().path("users[0].screen_name");
        System.out.println(actualName);
        Assert.assertEquals(actualName, "irin33918852", "Value does not match");
        String actualName2 = response.extract().body().path("users[1].screen_name");
        System.out.println(actualName2);
        Assert.assertEquals(actualName2, "JamesDi53175135", "Value does not match");
    }

    //36
    @Test
    public void testMyFollowingList() {
        ValidatableResponse response = tweetProject.getFollowingList();
        System.out.println(response.extract().body().asPrettyString());
    }

    //37
    @Test
    public void testCreateFavoriteTweet1List() {
        ValidatableResponse response = tweetProject.createFavoriteTweet(1587330108909666304L);
        System.out.println(response.extract().body().asPrettyString());
    }

    //38
    @Test
    public void postMessageToIrin() throws FileNotFoundException {
        ValidatableResponse response = tweetProject.postDirectMessageToHUSNA();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    //39
    @Test
    public void testCreateFavoriteTweet2List() {
        ValidatableResponse response = tweetProject.createFavoriteTweet(1587290940674347008L);
        System.out.println(response.extract().body().asPrettyString());
    }

    //40
    @Test
    public void testCreateFavoriteTweet3List() {
        ValidatableResponse response = tweetProject.createFavoriteTweet(1585873088809508867L);
        System.out.println(response.extract().body().asPrettyString());
    }

    // 41
    @Test
    public void verifyGetFavouriteTweetTextFromJsonIndex() {
        ValidatableResponse response = tweetProject.getFavoritesTweets();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualFavouriteText1 = response.extract().body().path("[0].text");
        System.out.println(actualFavouriteText1);
        Assert.assertEquals(actualFavouriteText1, "I like API Testing", "does not match");
        String actualFavouriteText2 = response.extract().body().path("[1].text");
        System.out.println(actualFavouriteText2);
        Assert.assertEquals(actualFavouriteText2, "Nothing is impossible497", "does not match");
        String actualFavouriteText3 = response.extract().body().path("[2].text");
        System.out.println(actualFavouriteText3);
        Assert.assertEquals(actualFavouriteText3, "helloEveryOne, I am Learning", "does not match");
    }

    //42
    @Test
    public void testDeleteFavoriteTweet1() {
        ValidatableResponse response = tweetProject.deleteFavoriteTweet(1587330108909666304L);
        System.out.println(response.extract().body().asPrettyString());
    }

    //43
    // first need to mute  any id then  can block
    @Test
    public void testMute() {
        ValidatableResponse response = tweetProject.muteId();
        System.out.println(response.extract().body().asPrettyString());
    }

    //44
    @Test
    public void testBlockId1() {
        ValidatableResponse response = tweetProject.blockId(308526298L);
        //response.log().all();
        System.out.println(response.extract().body().asPrettyString());
    }

    //45
    @Test
    public void testBlockId2() {
        ValidatableResponse response = tweetProject.blockId(50393960L);
        //response.log().all();
        System.out.println(response.extract().body().asPrettyString());
    }

    //46
    @Test
    public void testBlockId3() {
        ValidatableResponse response = tweetProject.blockId(308526298L);
        //response.log().all();
        System.out.println(response.extract().body().asPrettyString());
    }

    //47
    @Test
    public void verifyGetBlockIdList() {
        ValidatableResponse response = tweetProject.getBlockIdName();
        System.out.println(response.extract().body().asPrettyString());
    }

    //48
    @Test
    public void postMessageToJamal() throws FileNotFoundException {
        ValidatableResponse response = tweetProject.postDirectMessageToJamal();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    //49
    @Test
    public void verifyTextMessagePostToJamal() throws FileNotFoundException {
        ValidatableResponse response = tweetProject.postDirectMessageToJamal();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualFavouriteText1 = response.extract().body().path("event.message_create.message_data.text");
        System.out.println(actualFavouriteText1);
        Assert.assertEquals(actualFavouriteText1, "Please Vai, Study more:P", "does not match");
    }

    //50
    @Test
    public void verifyRecipientIdPostMessageToJamal() throws FileNotFoundException {
        ValidatableResponse response = tweetProject.postDirectMessageToJamal();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualRecipientId = response.extract().body().path("event.message_create.target.recipient_id");
        System.out.println(actualRecipientId);
        Assert.assertEquals(actualRecipientId, "1557365649118306304", "does not match");
    }

    //51
    @Test
    public void verifySenderIdPostMessageToJamal() throws FileNotFoundException {
        ValidatableResponse response = tweetProject.postDirectMessageToJamal();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualSenderIdValue = response.extract().body().path("event.message_create.sender_id");
        System.out.println(actualSenderIdValue);
        Assert.assertEquals(actualSenderIdValue, "1578448920107253776", "does not match");
    }


}

