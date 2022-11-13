package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static Utils.PropertyReader.readProperty;

public class RestBase {

    protected String apiKey;
    protected String apiSecretKey;
    protected String accessToken;
    protected String accessTokenSecret;
    protected String bearerToken; // lecture 58 (00.38.30)  Twitter API version 2
    protected Properties properties;

    protected static InputStream inputStream;
    protected String baseUrl;
    protected String baseUrl2;
    protected String apiVersion = "1.1";
    protected String apiVersion2 = "2";

    public RestBase() {

        // FOR TWITTER
        //      https://api.twitter.com/1.1
        //    this.baseUrl = "https://api.twitter.com/" + this.apiVersion2;
        this.baseUrl = "https://api.twitter.com/" + this.apiVersion;
        this.baseUrl2 = "https://api.twitter.com/" + this.apiVersion2;
        this.properties = new Properties();
        try {
            this.properties = readProperty();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.apiKey = this.properties.getProperty("apiKey");                        // 4m secret.properties
        this.apiSecretKey = this.properties.getProperty("apiSecretKey");            // 4m secret.properties
        this.accessToken = this.properties.getProperty("accessToken");              // 4m secret.properties
        this.accessTokenSecret = this.properties.getProperty("accessTokenSecret");  // 4m secret.properties
        this.bearerToken = this.properties.getProperty("bearerToken");              // lecture 58 (00.38.34)  Twitter API version 2 // 4m secret.properties

    }


}
