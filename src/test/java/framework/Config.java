package framework;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class Config {
    private static HashMap<String, String> data = new HashMap<>();

    public void LoadConfig() {
        Properties prop = new Properties();
        String sConfigFilePath = "src//test//resources//application.properties";

        try (InputStream input = Files.newInputStream(Paths.get(sConfigFilePath))) {
            prop.load(input);
            Set<Object> keys = prop.keySet();
            for (Object k : keys) {
                String key = (String) k;
                String value = prop.getProperty(key);
                data.put(key, value);
            }
        } catch (Exception e) {
            System.out.println("Error reading config file: " + e);
        }
    }

    public static String getProperty(String key) {
        return data.getOrDefault(key, "");
    }
}
