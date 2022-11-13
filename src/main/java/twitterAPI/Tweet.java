package twitterAPI;

import base.RestBase;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Tweet extends RestBase {


    // GET statuses/user_timeline
    // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    // https://api.twitter.com/1.1/statuses/home_timeline.json
    public static final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";


    // GET statuses/home_timeline
    // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-home_timeline
    // https://api.twitter.com/1.1/statuses/home_timeline.json
    public static final String GET_HOME_TWEET_ENDPOINT = "/statuses/home_timeline.json";


    // POST statuses/update
    // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-update
    public static final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";


    // DELETE : POST
    // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-destroy-id
    public final String DELETE_TWEET_ENDPOINT = "/statuses/destroy/";



    // create variable
    public  RequestSpecification access = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret);




    //  Action method
    // Get All Tweet information

    //01
    public ValidatableResponse getUserTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT).then();
    }

    //02
    public ValidatableResponse getUserTimeLineTweetNew() {
        return access.when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT).then();
    }

    //03
    public ValidatableResponse getHomeTimeLineTweet() {
        return access.when().get(this.baseUrl + GET_HOME_TWEET_ENDPOINT).then();
    }

    //04 and  //05  and //10
    public ValidatableResponse createTweet(String tweet) {
        return access.param("status", tweet).when().post(this.baseUrl + CREATE_TWEET_ENDPOINT).then();
    }

    //06
    public ValidatableResponse deleteTweet(Long tweetId) {
        return access.queryParam("id", tweetId).when().post(this.baseUrl + DELETE_TWEET_ENDPOINT + tweetId + ".json").then();
    }

    //07
    public long responseTimeCheck(String endPoint) {
        return access.when().get(this.baseUrl + endPoint).timeIn(TimeUnit.MILLISECONDS);
    }

    //08
    public void headerValue1(String endPoint) {
        System.out.println(access.when().get(this.baseUrl + endPoint).then().extract().headers());
    }

    //09
    public Headers headerValue(String endPoint) {
        return access.when().get(this.baseUrl + endPoint).then().extract().headers();
    }



}
