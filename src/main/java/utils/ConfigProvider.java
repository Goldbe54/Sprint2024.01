package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    Config config = readConf();

    static Config readConf() {
        return ConfigFactory.load("aplication.conf");
    }

    String API_KEY = config.getString("apiKey");
    String API_TOKEN = config.getString("apiToken");
    String EMAIL = config.getString("email");
    String PASSWORD = config.getString("password");
}