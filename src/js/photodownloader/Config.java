package js.photodownloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String propFileName = "./config.properties";
    private Properties prop;

    public Config() throws ConfigException {
        prop = new Properties();

        try (InputStream inputStream = new FileInputStream(propFileName)) {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new ConfigException(e.getMessage());
        }
    }

    public void require(String key) throws ConfigException {
        if (get(key) == null) {
            throw new ConfigException("Key `" + key + "` can't be read from " + propFileName);
        }
    }

    public int getInt(String key) {
        Object result = prop.getOrDefault(key, null);
        if (result != null)
            return Integer.parseInt((String) result);

        return 0;
    }

    public boolean getBoolean(String key) {
        Object result = prop.getOrDefault(key, null);
        if (result != null)
            return Boolean.parseBoolean((String) result);

        return false;
    }

    public String get(String key) {
        Object result = prop.getOrDefault(key, null);
        if (result != null)
            return (String) result;

        return null;
    }
}
