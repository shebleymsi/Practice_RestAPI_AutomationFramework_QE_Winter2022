package twitterAPI;

import base.RestBase;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Tweet extends RestBase {

    // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-update

    // POST statuses/update
    public static final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";


    // DELETE : POST  https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-destroy-id
    public final String DELETE_TWEET_ENDPOINT = "/statuses/destroy/";

    //    https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    // GET statuses/user_timeline
    public static final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";

// https://api.twitter.com/1.1/statuses/home_timeline.json


    //  Action method
    public RequestSpecification access = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret);

    // Get All Tweet information
    public ValidatableResponse getUserTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT).then();
    }

    public ValidatableResponse getUserTimeLineTweetNew() {
        return access.when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT).then();
    }


    public ValidatableResponse createTweet(String tweet){
        return  access.param("status",tweet).when().post(this.baseUrl+CREATE_TWEET_ENDPOINT).then();
    }

  public ValidatableResponse deleteTweet(Long tweetId){
        return  access.queryParam("id",tweetId).when().post(this.baseUrl+DELETE_TWEET_ENDPOINT+tweetId+".json").then();
    }


    public long responseTimeCheck(String endPoint){
        return  access.when().get(this.baseUrl+endPoint).timeIn(TimeUnit.MILLISECONDS);
    }


    public Headers headerValue(String endPoint){
        return  access.when().get(this.baseUrl+endPoint).then().extract().headers();
    }
    public void headerValue1(String endPoint){
        System.out.println(access.when().get(this.baseUrl+endPoint).then().extract().headers());
    }


}
