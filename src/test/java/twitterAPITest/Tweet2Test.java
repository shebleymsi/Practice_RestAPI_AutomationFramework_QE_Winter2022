package twitterAPITest;

import base.RestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import twitterAPI.Tweet;
import twitterAPI.Tweet2;

public class Tweet2Test extends RestBase {

    /*   Note :
           # check 1st secret.properties
           # For Twitter API version 2
           # Project SHEBLEY (API_1ST_PRACTICE_MSI  v58 : 02.02.51 Authantication informations for Authorization (new) )
           # Lecture 58, for TweetProjectTest Class
    */

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
