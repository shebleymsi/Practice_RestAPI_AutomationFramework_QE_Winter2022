package twitterAPI;

import base.RestBase;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;


public class TweetProject extends RestBase {


    // POST statuses/update
    public static final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";

    // DELETE : POST  https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-destroy-id
    public final String DELETE_TWEET_ENDPOINT = "/statuses/destroy/";

    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    // GET statuses/user_timeline
    public static final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";

    //GET followers/list
    //https://developer.twitter.com/en/docs/twitter-api/v1/accounts-and-users/follow-search-get-users/api-reference/get-followers-list
    public static final String GET_FOLLOWER_LIST_ENDPOINT = "/followers/list.json";

    //Get following/List
    public static final String GET_FOLLOWING_LIST_ENDPOINT = "/followers/list.json?cursor=-1&screen_name=twitterdev&skip_status=true&include_user_entities=false";

    //POST favorites/create
    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-favorites-create
    public static final String CREATE_USER_FAVORITE_TWEET_ENDPOINT = "/favorites/create.json";

    //**GET favorites/list
    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/get-favorites-list
    public static final String GET_FAVORITES_ENDPOINT = "/favorites/list.json";

    //*POST direct_messages/events/new (message_create)
    //https://developer.twitter.com/en/docs/twitter-api/v1/direct-messages/sending-and-receiving/api-reference/new-event
    public static final String POST_DIRECT_MESSAGES_ENDPOINT = "/direct_messages/events/new.json";

    //**POST favorites/destroy
    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-favorites-destroy

    public static final String DELETE_USER_FAVORITE_TWEET_ENDPOINT = "/favorites/destroy.json";
    //***POST blocks/create
    //https://developer.twitter.com/en/docs/twitter-api/v1/accounts-and-users/mute-block-report-users/api-reference/post-blocks-create

    public static final String POST_BLOCK_TWEETS = "/blocks/create.json";
    public static final String GET_BLOCK_LISTS = "/blocks/create.json?stringify_ids=true&cursor=-1";

    //****GET mutes/users/ids
    //https://developer.twitter.com/en/docs/twitter-api/v1/accounts-and-users/mute-block-report-users/api-reference/get-mutes-users-ids
    public static final String GET_MUTE_TWEETS = "/mutes/users/ids.json";

    // https://api.twitter.com/1.1/statuses/home_timeline.json
    public RequestSpecification access = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret);






    //  Action method
    //Get All Tweet Information
    public ValidatableResponse getUserTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT).then();
    }

    public ValidatableResponse getUserTimeLineTweet2() {
        return access.when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT).then();
    }

    public ValidatableResponse createTweet(String tweet) {
        return access.param("status", tweet).when().post(this.baseUrl + CREATE_TWEET_ENDPOINT).then();
    }

    public ValidatableResponse deleteTweet(long tweetId) {
        return access.queryParam("id", tweetId).when().post(this.baseUrl + DELETE_TWEET_ENDPOINT).then();
    }

    public long responseTimeCheck(String endPoint) {
        return access.when().get(this.baseUrl + endPoint).timeIn(TimeUnit.MILLISECONDS);
    }

    public Headers headerValue(String endPoint) {
        return access.when().get(this.baseUrl + endPoint).then().extract().headers();
    }

    public void headerValue1(String endPoint) {
        System.out.println(access.when().get(this.baseUrl + endPoint).then().extract().headers());
    }

    public ValidatableResponse getFollowerList() {
        return access.when().get(this.baseUrl + GET_FOLLOWER_LIST_ENDPOINT).then();
    }

    public ValidatableResponse getFollowingList() {
        return access.when().get(this.baseUrl + GET_FOLLOWING_LIST_ENDPOINT).then();
    }

    public ValidatableResponse createFavoriteTweet(Long tweetId) {
        return access.queryParam("id", tweetId).when().post(this.baseUrl + CREATE_USER_FAVORITE_TWEET_ENDPOINT + "?" + tweetId).then();
    }

    public ValidatableResponse getFavoritesTweets() {
        return access.when().get(this.baseUrl + GET_FAVORITES_ENDPOINT).then();
    }

    public ValidatableResponse postDirectMessageToHUSNA() throws FileNotFoundException {
        FileInputStream jsonMessage = new FileInputStream("C:\\Users\\amatu\\IdeaProjects\\Project_REST_API_AutomationFramework_QE_Winter2022\\src\\main\\directMessage\\directMessageIrin.Json");
        return access.body(jsonMessage).when().post(this.baseUrl + POST_DIRECT_MESSAGES_ENDPOINT).then().log().all();
    }

    public ValidatableResponse deleteFavoriteTweet(Long tweetId) {
        return access.queryParam("id", tweetId).when().post(this.baseUrl + DELETE_USER_FAVORITE_TWEET_ENDPOINT + "?" + tweetId).then();
    }

    public ValidatableResponse blockId(Long tweetId) {
        return access.queryParam("id", tweetId).when().post(this.baseUrl + POST_BLOCK_TWEETS).then();
    }

    public ValidatableResponse muteId() {
        return access.when().get(this.baseUrl + GET_MUTE_TWEETS).then();
    }

    public ValidatableResponse getBlockIdName() {
        return access.when().post(this.baseUrl + GET_BLOCK_LISTS).then();
    }

    public ValidatableResponse postDirectMessageToJamal() throws FileNotFoundException {
        FileInputStream jsonMessage = new FileInputStream("src/main/directMessage/directMessageJamal.Json");
        return access.body(jsonMessage).when().post(this.baseUrl + POST_DIRECT_MESSAGES_ENDPOINT).then().log().all();
    }


}
