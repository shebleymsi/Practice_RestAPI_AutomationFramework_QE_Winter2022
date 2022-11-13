package twitterAPITest;

import base.RestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import twitterAPI.Tweet;
import twitterAPI.Tweet2;

public class Tweet2Test extends RestBase {
    private Tweet2 tweet2;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweet2 = new Tweet2();
    }

    // 01
    @Test
    public void testGetUserTimeLineTweet() {
        Tweet2 tweet2 = new Tweet2();
        ValidatableResponse response = tweet2.getTweetTimeLine(1557786208402640898L);
        response.log().all();
        System.out.println(response.statusCode(200)); //200
        System.out.println(response.extract().body().asPrettyString());

    }

    // 02
    @Test
    public void testGetUserTimeLineTweetFollowing() {
        ValidatableResponse response = this.tweet2.getTweetTimeLineWithFollowing(1557786208402640898L);
        response.log().all();
        System.out.println(response.extract().body().asPrettyString());

    }
}
