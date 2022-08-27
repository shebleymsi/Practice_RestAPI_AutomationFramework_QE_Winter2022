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
    protected Properties properties;
    protected static InputStream inputStream;
    protected String baseUrl;
    protected String apiVersion = "1.1";
    protected String apiVersion2 = "2";



    public RestBase() {
        // https://api.twitter.com/1.1
        this.baseUrl = "https://api.twitter.com/" + this.apiVersion;
        this.properties = new Properties();

        try {
            this.properties = readProperty();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.apiKey = this.properties.getProperty("apiKey");
        this.apiSecretKey = this.properties.getProperty("apiSecretKey");
        this.accessToken = this.properties.getProperty("accessToken");
        this.accessTokenSecret = this.properties.getProperty("accessTokenSecret");
    }


}
