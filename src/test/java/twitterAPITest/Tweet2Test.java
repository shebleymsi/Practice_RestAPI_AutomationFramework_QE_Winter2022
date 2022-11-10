package twitterAPITest;


import base.RestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import twitterAPI.Tweet2;

public class Tweet2Test extends RestBase {

    private Tweet2 tweet2;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweet2 = new Tweet2();
    }

   @Test
    public void testGetUserTimeLineTweet() {
        ValidatableResponse response =tweet2.getTweetTimeLine(1590650473698181121L);
        response.log().all();
        System.out.println(response.statusCode(404)); //200
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testGetUserTimeLineTweetFollowing() {
       // ValidatableResponse response =tweet2.getTweetTimelineWithFollowing(1557786208402640898L);
        ValidatableResponse response =tweet2.getTweetTimeLineWithFollowing(1557786208402640898L);
        response.log().all();
     //   response.statusCode(200);
      //  System.out.println(response.extract().body().asPrettyString());
    }

}
