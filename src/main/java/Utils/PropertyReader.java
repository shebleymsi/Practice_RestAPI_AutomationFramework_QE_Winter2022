package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    protected static Properties properties;
    protected static InputStream inputStream;

    public static Properties loadProperties(String path) {
        try {
            properties = new Properties();
            inputStream = new FileInputStream(path);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File not load properly");
        } finally {
            try {
                inputStream.close();
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println("File is not closed");
            }
        }
        return properties;
    }

    public static Properties readProperty() throws IOException {
        return loadProperties(System.getProperty("user.dir") + "./src/main/resources/configProperty/secret.properties");
    }

}
