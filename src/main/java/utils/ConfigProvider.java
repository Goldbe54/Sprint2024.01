package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConf();

    static Config readConf() {
        return ConfigFactory.load("aplication.conf");
    }

    String API_KEY = readConf().getString("apiKey");
    String API_TOKEN = readConf().getString("apiToken");
    String EMAIL = readConf().getString("email");
    String PASSWORD = readConf().getString("password");
}
