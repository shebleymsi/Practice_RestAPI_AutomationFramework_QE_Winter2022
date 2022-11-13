package typicode;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostsAPIClient extends TypiCodeAPIClient {

    // As like Tweet Class
    private final String POSTS_ENDPOINT = "/posts";


    //01
    public ValidatableResponse getAllPosts() {
        return when().get(this.baseUrl + POSTS_ENDPOINT).then();
    }

    //02 and //03
    //  POST
    public ValidatableResponse createPost(Object json) {
        return given().header("Content-type", "application/json").body(json).when().post(this.baseUrl + POSTS_ENDPOINT).then();
    }


}
