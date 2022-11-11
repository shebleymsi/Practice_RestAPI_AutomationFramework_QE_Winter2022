package twitterAPI;

import base.RestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Tweet2 extends RestBase {


    // https://developer.twitter.com/en/docs/twitter-api/tweets/timelines/api-reference/get-users-id-tweets#tab0
    // https://api.twitter.com/2/users/:id/tweets
    // https://api.twitter.com/2/users/1182463426272092160/tweets
    // https://tweeterid.com/     for converting twitter id
    public static final String SINGLE_USER_TWEET_ENDPOINT = "/users/:id/tweets";


    public RequestSpecification access = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret);


    public ValidatableResponse getSingleUserTweet(long id) {
        return given().contentType("application/json")
                .auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + "/users/:" + id + "/tweets").then();
    }

    public String get_USER_TWEET_ENDPOINT_METHOD(Long id) {
        String endPoint = "/users/" + id + "/tweets";
        System.out.println("End Point: ===> " + endPoint);
        String fullUrl = this.baseUrl + endPoint;
        System.out.println("Base URL: ===> " + fullUrl);
        return endPoint;
    }

    public ValidatableResponse getTweetTimeLine(Long id) {
        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.get_USER_TWEET_ENDPOINT_METHOD(id)).then();     //.log().all();

    }

    public static String GET_USER_TWEET_ENDPOINT_FOLLOWING(Long useId) {
        String endPointFollowing = "/users/" + useId + "/following";
        return endPointFollowing;
    }

    public ValidatableResponse getTweetTimeLineWithFollowing(Long useId) {
        // return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
        //.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json."))

        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                // .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT_FOLLOWING(useId)).then();    //.log().all();
    }


    //GET statuses/home_timeline // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-home_timeline
    //public final String GET_USER_TWEET_ENDPOINT = "/statuses/mentions_timeline.json";
    // public final String GET_USER_TWEET_ENDPOINT_2 = "/users/:id/mentions";
    public final String GET_USER_TWEET_ENDPOINT_2 = "/users/:id/tweets";
    public final String GET_USER_TWEET_ENDPOINT_SEARCH = "/tweets/search/recent";
    public final String GET_USER_TWEET_ENDPOINT_ID = "/users";
    public final String GET_USER_TWEET_ENDPOINT_TWEET = "/tweets";
    public final String GET_USER_TWEET_ENDPOINT = "statuses/user_timeline.json";


    // DELETE / Destroy : Delete or Post  // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-destroy-id
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";


    // Create: POST     // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-update
    public final String CREATE_TWEET_ENDPOINT = "/tweets";  // v2
    Long useId;
    public final String GET_USER_TWEET_ENDPOINT_FOLLOWING = "/users/" + useId + "/following";

    public static String GET_USER_TWEET_ENDPOINT_FOLLOWING1(Long useId) {
        String endPointFollowing = "/users/" + useId + "/following";
        return endPointFollowing;
    }

    public final String POST_USERS_ID_FOLLOWING = "/users/1488323722574544899/followed_lists";

    public ValidatableResponse postUsersIdFollowed(Object listId) {
        return access.contentType("application/json").body(listId).when().post(this.baseUrl + POST_USERS_ID_FOLLOWING).then();
    }


    // DELETE /2/users/:id/followed_lists/:list_id   //1488323722574544899
    long id;

    public static String DELETE_USERS_ID_FOLLOWING_1(String id) {
        String deleteFollower = "/users/" + id + "/followed_lists/:list_id";
        return deleteFollower;
    }

    //  public final String DELETE_USERS_ID_FOLLOWING="/users/"+ id +"/followed_lists/:list_id";
    public final String DELETE_USERS_ID_FOLLOWING = "/users/1488323722574544899/followed_lists/1431101898908585990";

    public ValidatableResponse deleteUsersIdFollowing() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                // .param("list_id","1468318534224781319")
                //  .param("id","1431101898908585990")
                .when().delete(this.baseUrl + DELETE_USERS_ID_FOLLOWING).then();
    }


    //DELETE /2/users/:id/retweets/:source_tweet_id
    public final String DELETE_USERS_ID_RETWEETS_SOURCE = "/users/1488323722574544899/retweets/813272392558645248";

    public ValidatableResponse deleteUserIdRetweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().delete(this.baseUrl + DELETE_USERS_ID_RETWEETS_SOURCE).then();
    }


    //DELETE /2/users/:id/likes/:tweet_id
    public final String DELETE_USERS_LIKE_TWEET = "/users/1488323722574544899/likes/1506695413814153216";

    public ValidatableResponse deleteUsersLikeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().delete(this.baseUrl + DELETE_USERS_LIKE_TWEET).then();
    }


    //GET /2/users/:id/blocking
    public final String USERS_ID_BLOCKING = "/users/1488323722574544899/blocking";

    public ValidatableResponse usersIdBlocking() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + USERS_ID_BLOCKING).then();
    }


    //GET /2/users/:id/followed_lists
    public final String GET_USERS_ID_FOLLOWING_LIST = "/users/1488323722574544899/followed_lists";

    public ValidatableResponse getUsersIdFollowingList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .contentType("application/json")
                .when().get(this.baseUrl + GET_USERS_ID_FOLLOWING_LIST).then();
    }


    //==================================================================================================================


    public final String GET_USER_TWEET_ENDPOINT_3 = "/users/" + id + "/tweets";

    public static String get_USER_TWEET_ENDPOINT_METHOD1(Long id) {
        String endPoint = "/users/" + id + "/tweets";
        System.out.println("End Point: ===> " + endPoint);
        return endPoint;
    }

    public final String GET_USER_TWEET_ENDPOINT_4 = "/users/tweets";
    //Action Methods
    //https://api.twitter.com/2/users/2244994945/tweets?max_results=100&exclude=retweets,replies
    // https://api.twitter.com/2/users/:id/tweets
    // https://api.twitter.com/2/users/2244994945/tweets?tweet.fields=attachments,author_id,created_at,entities,geo,id,in_reply_to_user_id,lang,possibly_sensitive,public_metrics,referenced_tweets,source,withheld&user.fields=created_at,description,entities,id,location,name,profile_image_url,protected,public_metrics,url,username,verified,withheld&expansions=author_id,referenced_tweets.id,referenced_tweets.id.author_id,entities.mentions.username,attachments.media_keys&media.fields=duration_ms,height,preview_image_url,public_metrics,url,width&max_results=100

    //==================================================================================================================

    public ValidatableResponse getTweetTimeLine1(Long id) {

        // return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
        //.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json."))
        // , OAuthSignature.valueOf(this.bearerToken)
        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                //.auth().oauth2(this.bearerToken)
                // .param(":id",tweets)
                //   .queryParam("id",id)
                //   .when().get(this.baseUrl+"/users/"+id+"/tweets"+"?tweet.fields=attachments,author_id,created_at,entities,geo,id,in_reply_to_user_id,lang,possibly_sensitive,public_metrics,referenced_tweets,source,withheld&user.fields=created_at,description,entities,id,location,name,profile_image_url,protected,public_metrics,url,username,verified,withheld&expansions=author_id,referenced_tweets.id,referenced_tweets.id.author_id,entities.mentions.username,attachments.media_keys&media.fields=duration_ms,height,preview_image_url,public_metrics,url,width&max_results=100").then();     //.log().all();
                //;    .when().get(this.baseUrl+"/users/"+id+"/tweets"+"?tweet.fields=author_id").then();     //.log().all();
                .when().get(this.baseUrl + this.get_USER_TWEET_ENDPOINT_METHOD(id)).then();     //.log().all();

    }

    //==================================================================================================================


    //https://developer.twitter.com/en/docs/twitter-api/tweets/likes/api-reference/post-users-id-likes
    //https://api.twitter.com/2/users/:id/likes

    //    public static String get_USER_TWEET_LIKE(String idUser) {
//        String endPoint = "/users/" + idUser + "/likes";
//        System.out.println("End Point: ===> " + endPoint);
//        return endPoint;
//
//
//    }
    public final String get_USER_TWEET_LIKE = "/users/1488323722574544899/likes";

    public ValidatableResponse postUsersTweetLike(Object TweetID) {
        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)

                // .auth().oauth2(this.bearerToken)
                .body(TweetID)
                .when().post(this.baseUrl + get_USER_TWEET_LIKE).then();
    }

    //==================================================================================================================


    //https://developer.twitter.com/en/docs/twitter-api/tweets/likes/migrate
    //Users who have liked a Tweet
    //    ///2/tweets/:id/liking_users
    public static String getuserLike(String id) {
        String endpointID = "/tweets/" + id + "/liking_users/";
        return endpointID;
    }

    public final String GET_TWEETS_LIKE_USERS = "/tweets/:id/liking_users";

    public ValidatableResponse getTweetsLikeUser(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                //.param("id",id)
                .when().get(this.baseUrl + this.getuserLike(id)).then();
    }


    //==================================================================================================================


    // POST /2/users/:id/retweets

    //    Long idTweet;
//    public static String GET_USER_RETWEETED_Id(Long idTweet){
//        String endPoint2="/users/"+ idTweet +"/retweets";
//        return endPoint2;
//    }

    public static String GET_USER_RETWEETED_Id(String UserId) {
        String endPoint2 = "/users/" + UserId + " /retweets";
        return endPoint2;
    }

    public final String POST_USERS_RETWEETS = "/users/1488323722574544899/retweets";

    public ValidatableResponse tweetsReTweet(Object tweet_id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .contentType("application/json")
                //   .auth().oauth2(this.bearerToken)

                .body(tweet_id)
                .when().post(this.baseUrl + POST_USERS_RETWEETS).then();
    }


    // https://api.twitter.com/2/tweets/:id/retweeted_by
    ///2/users/:id/retweeted_by


    public static String GET_TWEETS_ID_RETWEETED_BY(String idNum) {
        String endpoint = "/tweets/" + idNum + "/retweeted_by";
        return endpoint;
    }


    //public final String GET_TWEETS_ID_RETWEETED_BY="/tweets/:id/retweeted_by";
    public ValidatableResponse getReTweetBy(String idNum) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                //  .param("id",id)
                .when().get(this.baseUrl + GET_TWEETS_ID_RETWEETED_BY(idNum)).then();
    }


    public ValidatableResponse getTweetTimeLineWithFollowing1(Long useId) {
        // return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
        //.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json."))

        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                // .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT_FOLLOWING(useId)).then();    //.log().all();

    }


    //==================================================================================================================


    public ValidatableResponse getUserTimeLineTweetWithId(String tweetId) {

        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("ids", tweetId)
                .when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT_ID).then();
    }

    //==================================================================================================================


    public ValidatableResponse getUserTimeLineTweetWithTweet(String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                //.oauth2(this.bearerToken)
                // .auth().oauth2(this.bearerToken)
                .param("ids", tweetId)
                // .param("usernames",usernames)
                .when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT_TWEET).then();
    }

    //==================================================================================================================

    public ValidatableResponse TweetDuplication(Object tweet) {
        // return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
        //.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json."))

        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .body(tweet)
                .when().post(this.baseUrl + CREATE_TWEET_ENDPOINT).then();
    }


    // Create a tweet from user twitter
    public ValidatableResponse createTweet(Object tweet) {
        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .body(tweet)
                .when().post(this.baseUrl + CREATE_TWEET_ENDPOINT).then();
    }


    //    // get All tweet information
    public ValidatableResponse getUserTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                //.param(":id",tweetId)
                //.param("id",tweetId)
                //.queryParam("id:",tweetId)
                // .body(tweetId)
                .when().get(this.baseUrl + GET_USER_TWEET_ENDPOINT).then();


    }

    //==================================================================================================================

    //v1.1=  https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-destroy-
    //v2=     https://developer.twitter.com/en/docs/twitter-api/tweets/manage-tweets/migrate


//    public static String get_USER_TWEET_ENDPOINT_METHOD(Long id){
    //    String endPoint="/users/"+id+"/tweets";
//        System.out.println("End Point: ===> "+endPoint);
//        return endPoint;
//    }
//
//


    //String  deleteTweetId;
    //  public final String GET_TWEET_ENDPOINT_DELETE="/tweets/"+deleteTweetId;

    public static String Delete_USER_TWEET_ENDPOINT_DELETE(String deleteTweet) {
        String endPointDelete = "/tweets/" + deleteTweet;
        System.out.println("End Point: ===> " + endPointDelete);
        return endPointDelete;
    }

    //Delete tweet
    public ValidatableResponse deleteTweet(String deleteTweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                // .param("id",deleteTweetId)
                .when().delete(this.baseUrl + Delete_USER_TWEET_ENDPOINT_DELETE(deleteTweetId)).then();
    }


    public final String DELETE_TWEET_ENDPOINT_DELETE_2 = "/tweets/1502882205567225859";

    public ValidatableResponse deleteTweet2() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                //  .param("id",tweetId)
                .when().delete(this.baseUrl + DELETE_TWEET_ENDPOINT_DELETE_2).then();
    }


    //==================================================================================================================
    //https://developer.twitter.com/en/docs/twitter-api/tweets/search/api-reference/get-tweets-search-recent
    //https://api.twitter.com/2/tweets/search/recent

    public final String SEARCH_TWEET_ENDPOINT = "/tweets/search/recent";

    public ValidatableResponse searchTweetEndPoint(String searchTopic) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("query", searchTopic)
                .when().get(this.baseUrl + SEARCH_TWEET_ENDPOINT).then();
    }


    //=================================================================================================================


    //This endpoint is only available to those users who have been approved for Academic Research access.
    // https://developer.twitter.com/en/docs/twitter-api/tweets/search/api-reference/get-tweets-search-all
    //endpoint
    //https://api.twitter.com/2/tweets/search/all

    //crate variable for endpoint
    public final String TWEET_SEARCH_ALL_ENDPOINT = "/tweets/search/all";

    //create ValidDatableRespond method
    public ValidatableResponse tweetSearchAll(String searchAll) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("query", searchAll)
                .when().get(this.baseUrl + TWEET_SEARCH_ALL_ENDPOINT).then();
    }

    //curl https://api.twitter.com/2/tweets/search/recent?query=from%3Atwitterdev%20new%20-is%3Aretweet&max_results=10 -H "Authorization: Bearer $ACCESS_TOKEN"

    //curl "https://api.twitter.com/2/tweets/search/all?query=from%3Atwitterdev%20new%20-is%3Aretweet&max_results=10" -H "Authorization: Bearer $ACCESS_TOKEN"


    //==================================================================================================================


    //https://developer.twitter.com/en/docs/twitter-api/tweets/counts/migrate
    //https://api.twitter.com/2/tweets/counts/recent;

    public final String TWEETS_COUNTS_RECENT = "/tweets/counts/recent";

    public ValidatableResponse tweetsCountsRecent(String tweetCount) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("query", tweetCount)

                .when().get(this.baseUrl + TWEETS_COUNTS_RECENT).then();
    }

    //==================================================================================================================


    //This endpoint is only available to those users who have been approved for Academic Research access.
    //https://developer.twitter.com/en/docs/twitter-api/tweets/counts/api-reference/get-tweets-counts-all
    //GET /2/tweets/counts/all
    public final String TWEETS_COUNTS_RECENT_ALL = "/tweets/counts/all";

    public ValidatableResponse tweetsCountsRecentAll(String tweetCount) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("query", tweetCount)

                .when().get(this.baseUrl + TWEETS_COUNTS_RECENT_ALL).then();
    }

//      Long idTweet;
//    public static String GET_USER_RETWEETED_Id(Long idTweet){
//        String endPoint2="/users/"+idTweet+"/retweets";
//        return endPoint2;
//    }
//
//    public ValidatableResponse tweetsReTweet(Long id) {
//        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//                .auth().oauth2(this.bearerToken)
//                .param("id",id)
//                .when().get(this.baseUrl + GET_USER_RETWEETED_Id).then();
//    }


    //==================================================================================================================


    // Response Time Check
    public ValidatableResponse responseTimeCheck(String endPoint) {
        System.out.println(given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + endPoint).timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + endPoint).then();
    }

    //Header Value
    public void headerValue(String endPoint) {
        System.out.println(given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + endPoint).then().extract().headers());
    }

    // Check Property
    public void checkProperty(String endPoint, String prop) {
        Response response = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + endPoint);
        JsonPath pathEvaluator = response.jsonPath();
        String property = pathEvaluator.get(prop);
        System.out.println("Property value : " + property);
    }
    //https://developer.twitter.com/en/docs/twitter-api/tweets/likes/migrate
    //Users who have liked a Tweet
    ///2/tweets/:id/liking_users

    //public final String GET_USERS_LIKED="/tweets/1503533028127264772/liking_users";
    public final String GET_USERS_LIKED = "/tweets/1502823444064440322/liking_users";

    public ValidatableResponse getUserLikeTweetName() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + GET_USERS_LIKED).then();

    }
    //
    // get information about a user’s liked Tweets.
    //2/users/:id/liked_tweets


    public static String GET_USER_LIKED_TWEETS(String id) {
        String endpointLike = "/users/" + id + "/liked_tweets";
        System.out.println("id is: --->" + endpointLike);
        return endpointLike;
    }

    public ValidatableResponse getUserLikesTweets(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                //.param("id",id)
                .when().get(this.baseUrl + this.GET_USER_LIKED_TWEETS(id)).then();
    }


    //==================================================================================================================


    //https://api.twitter.com/2/users/by/username/:username

    public static String GET_USERS_BY_USERNAME_TWEETS(String userName) {
        String endpointLike = "/users/by/username/" + userName;
        System.out.println("id is: --->" + endpointLike);
        return endpointLike;
    }

    public final String GET_USERS_BY_USERNAME = "/users/by/username/:username";


    public ValidatableResponse getUserByUserName(String userName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                //  .param("username")
                .when().get(this.baseUrl + this.GET_USERS_BY_USERNAME_TWEETS(userName)).then();

    }
//Returns a variety of information about one or more users specified by the requested IDs.
//public static String GET_USERS_BY_IDS_TWEETS(String userTweetID){
//        String endpointTweetID= "/users/by/username/"+ userTweetID;
//        System.out.println("id is: --->"+endpointTweetID);
//        return endpointTweetID;
//    }

    public final String GET_USERS_BY_IDS_TWEETS = "/users";


    public ValidatableResponse getUserByUserTweetIDs(String userTweetID, String TweetField) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("ids", userTweetID)
                .queryParam("user.fields", TweetField)
                .when().get(this.baseUrl + this.GET_USERS_BY_IDS_TWEETS).then();

    }

    //==================================================================================================================


    public final String GET_USERS_BY_IDs = "/users/by";

    public ValidatableResponse getUserByUserByName(String usernames, String TweetField) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("usernames", usernames)
                .queryParam("tweet.fields", TweetField)
                .when().get(this.baseUrl + this.GET_USERS_BY_IDs).then();

    }

    //==================================================================================================================


    //GET /2/spaces/:id
    public static String GET_SPACES_ID_ENDPOINT(String userID) {
        String endpointUserId = "/spaces/" + userID;
        return endpointUserId;

    }


    public final String GET_SPACES_ID_ENDPOINT_1 = "/spaces/:id";

    public ValidatableResponse getSpacesUsersId(String userID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)

                .when().get(this.baseUrl + this.GET_SPACES_ID_ENDPOINT(userID)).then();
    }


    //==================================================================================================================


    //testCase 16) Return live or scheduled Spaces matching your specified search terms
    ///2/spaces/search
    public final String GET_SPACES_SEARCH_ENDPOINT_1 = "/spaces/search";

    public ValidatableResponse getSpacesUsersId2(String searchQuery) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .queryParam("query", searchQuery)
                .when().get(this.baseUrl + this.GET_SPACES_SEARCH_ENDPOINT_1).then();
    }

    //==================================================================================================================


    //testCase 17) get List lookup using id
    ///2/lists/:id

    public static String GET_LIST_ID_ENDPOINT(String likeId) {
        String endpointListId = "/lists/" + likeId;
        return endpointListId;
    }

    public ValidatableResponse getListsId(String likeId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + this.GET_LIST_ID_ENDPOINT(likeId)).then();
    }
    //==================================================================================================================


    ///2/users/:id/owned_lists
    public static String GET_USERS_OWNED_LIST(String ownerList) {
        String endpointListId = "/users/" + ownerList + "/owned_lists";
        return endpointListId;
    }

    public ValidatableResponse getUsersOwnerLists(String ownerList) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + this.GET_USERS_OWNED_LIST(ownerList)).then();
    }

    //==================================================================================================================


    ///2/lists/:id/tweets
    public static String GET_LISTS_ID_TWEET(String tweetList) {
        String endpointList = "/lists/" + tweetList + "/tweets";
        return endpointList;
    }

    public ValidatableResponse getListIdTweet(String tweetList) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + this.GET_LISTS_ID_TWEET(tweetList)).then();
    }


    //==================================================================================================================


    ///2/lists/:id/members
    //84839422
    //GET /2/lists/:id/members
    public static String GET_LISTS_ID_MEMBER_1(String memberList) {
        String endpointList = "/lists/" + memberList + "/members";
        return endpointList;
    }

    public final String GET_LISTS_ID_MEMBER = "/lists/1503930655515815939/members";

    public ValidatableResponse getListIdMember() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + this.GET_LISTS_ID_MEMBER).then();
    }


    ///2/users/:id/list_memberships

    public final String GET_USER_MEMBER_LIST = "/users/1488323722574544899/list_memberships";

    public ValidatableResponse getUserListMember() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + GET_USER_MEMBER_LIST).then();
    }

    //     /2/lists/:id/members
    //                                POST /2/lists/:id/members
    public final String POST_USER_MEMBER_LIST_1 = "/lists/1506619444004630537/members";
    public final String POST_USER_MEMBER_LIST = "/lists/1488323722574544899/members";

    public ValidatableResponse postUserListMember(Object userId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .contentType("application/json")
                //.auth().oauth2(this.bearerToken)
                .body(userId)
                .when().post(this.baseUrl + POST_USER_MEMBER_LIST_1).then();
    }

    //GET /2/users/:id/list_memberships
    public final String USERS_ID_LIST_MEMBERSHIPS = "/users/1488323722574544899/list_memberships";

    public ValidatableResponse getUsersIdListMemberships() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + USERS_ID_LIST_MEMBERSHIPS).then();
    }


    //https://api.twitter.com/2/tweets/search/stream/rules

    public final String TWEETS_SEARCH_STREAM_RULES = "/tweets/search/stream/rules";

    public ValidatableResponse getTweetSearchStreamRule() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + TWEETS_SEARCH_STREAM_RULES).then();
    }


    //https://api.twitter.com/2/users/:id/pinned_lists
    // public final String GET_USERS_ID_PINNED_LISTS="/users/1506619444004630537/pinned_lists";
    public final String GET_USERS_ID_PINNED_LISTS = "/users/1488323722574544899/pinned_lists";

    ///users/2244994945/pinned_lists?expansions=owner_id&list.fields=follower_count&user.fields=created_at
    public ValidatableResponse getUsersIdPinnedList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                //   .auth().oauth2(this.bearerToken)
                // .queryParam("expansions",ownerId)
                .when().get(this.baseUrl + GET_USERS_ID_PINNED_LISTS).then();
    }


    ///2/users/:id/muting
    public final String GET_USERS_ID_MUTING = "/users/1506695413814153216/muting";

    public static String GET_USERS_ID_MUTING_1(String ids) {
        String endpoint = "/users/" + ids + "/muting";
        return endpoint;

    }

    public ValidatableResponse getUsersIdMuting() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                //   .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + GET_USERS_ID_MUTING).then();
    }


    // public final String POST_USERS_ID_MUTING="1504157060497170438"
    public final String POST_USERS_ID_MUTING = "/users/1488323722574544899/muting";

    public static String POST_USERS_ID_MUTING_1(String ids) {
        ///2/users/:id/muting
        String endpoint = "/users/" + ids + "/muting";
        return endpoint;
    }

    public ValidatableResponse postUsersIdMuting(Object targetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .body(targetId)
                //   .auth().oauth2(this.bearerToken)
                .when().post(this.baseUrl + POST_USERS_ID_MUTING).then();
    }


    //PUT /2/lists/:id
    public final String PUT_LISTS_ID = "/lists/1506673567869673473";

    public ValidatableResponse putListsId(Object name) {
        return given().contentType("application/json").auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)

                .body(name)
                .when().put(this.baseUrl + PUT_LISTS_ID).then();
    }

    //POST/2/lists
    public final String POST_LISTS_ID = "/lists";

    public ValidatableResponse postListsId(Object name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .contentType("application/json")
                .body(name)
                .when().post(this.baseUrl + POST_LISTS_ID).then();
    }


    //==================================================================================================================


    ///2/tweets/sample/stream
    //public final String GET_TWEETS_SAMPLE_STREAM="/tweets/sample/stream";
    public final String GET_TWEETS_SAMPLE_STREAM = "/tweets/sample/stream";

    public ValidatableResponse getTweetsSampleStream(String tweetStream) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .queryParam("tweet.fields", tweetStream)
                .when().get(this.baseUrl + GET_TWEETS_SAMPLE_STREAM).then();
    }


    ///tweets/search/stream/rules
    public final String GET_TWEETS_SAMPLE_STREAM_RULES = "/tweets/search/stream/rules";

    public ValidatableResponse getTweetsSampleStreamRules(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .queryParam("ids", id)
                .when().get(this.baseUrl + GET_TWEETS_SAMPLE_STREAM_RULES).then();
    }

    ///tweets/search/stream/rules
    public final String GET_TWEETS_SAMPLE_STREAM_RULES_2 = "/tweets/search/stream/rules";

    public ValidatableResponse getTweetsSampleStreamRules2() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + GET_TWEETS_SAMPLE_STREAM_RULES_2).then();


    } ///tweets/search/stream/rules

    public final String GET_SEARCH_STREAM = "/tweets/search/stream";

    public ValidatableResponse getSearchStream() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + GET_SEARCH_STREAM).then();


    }

    public final String COVID19_STREAM = "/tweets/stream/covid19";
    public final String COVID19_STREAM_1 = "/labs/1/tweets/stream/covid19";

    public ValidatableResponse getAllCovid19Stream(int number) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                .auth().oauth2(this.bearerToken)
                .queryParam("partition", number)
                .when().get(this.baseUrl + COVID19_STREAM_1).then();

    }

    ///2/tweets/search/stream/rules
    //  public final String TWEET_SEARCH_


    ///users/me
    public final String GET_USERS_ME = "/users/me";

    public ValidatableResponse getUserMe() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessToken)
                //   .queryParam("expansions",pinned_tweet_id)
                //.auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + GET_USERS_ME).then();
    }

    //2/tweets?ids=1204084171334832128
    //GET /2/tweets/:id

    //  public final String GET_TWEET_ID_ENDPOINT="/tweets/:id";

    public static String getTweetIdEndPoint(String tweetId) {

        String endpoint = "/tweets/" + tweetId;
        return endpoint;
    }

    public ValidatableResponse getTweetsId(String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .when().get(this.baseUrl + getTweetIdEndPoint(tweetId)).then();
    }

    public final String GET_TWEET_ID_ENDPOINT = "/tweets/1503931500491952128";

    public ValidatableResponse getQueryParaTweet(String expansions) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .auth().oauth2(this.bearerToken)
                .queryParam("expansions", expansions)
                .when().get(this.baseUrl + GET_TWEET_ID_ENDPOINT).then();
    }


    //GET /2/users/:id/muting

    //https://api.twitter.com/2/users/:id/followed_lists
    public final String USERS_ID_FOLLOWED_LISTS = "/users/1488323722574544899/followed_lists";

    public ValidatableResponse postUsersIdFollowList(Object followId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .contentType("application/json")
                .body(followId)
                .when().post(this.baseUrl + USERS_ID_FOLLOWED_LISTS).then();

    }


}
