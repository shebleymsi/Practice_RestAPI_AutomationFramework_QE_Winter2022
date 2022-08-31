package twitterAPI;

import base.RestBase;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Tweet2 extends RestBase {

    // https://developer.twitter.com/en/docs/twitter-api/tweets/timelines/api-reference/get-users-id-tweets#tab0
    // https://api.twitter.com/2/users/:id/tweets



    public RequestSpecification access = given().contentType("application/jason").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).when();

    public static final String SINGLE_USER_TWEET_ENDPOINT = "/users/:id/tweets";

    public ValidatableResponse getSingleUserTweet(Long id) {
        return given().contentType("application/jason").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).auth().oauth2(this.bearerToken).when()
                .get(this.baseUrl + "/users/:" + id + "/tweet" + this.SINGLE_USER_TWEET_ENDPOINT  ).then();
    }

    public String get_USER_TWEET_ENDPOINT_METHOD(Long id) {
        String endPoint = "/users/" + id + "/tweets";
        System.out.println("End Point : ===> " + endPoint);
        String fullUrl = this.baseUrl + endPoint;
        System.out.println("Base URL : ===> " + fullUrl);
        return endPoint;
    }

    public ValidatableResponse getTweetTimeLine(Long id) {
        return access.get(this.baseUrl + this.get_USER_TWEET_ENDPOINT_METHOD(id)).then();

    }

    public String get_USER_TWEET_ENDPOINT_FOLLOWING(Long useId) {
        String endPointFollowing = "/users/" + useId + "/following";
        return endPointFollowing;
    }

    public ValidatableResponse getTweetTimelineWithFollowing(Long useId) {
        return access.get(this.baseUrl + this.get_USER_TWEET_ENDPOINT_FOLLOWING(useId)).then();
    }

}
