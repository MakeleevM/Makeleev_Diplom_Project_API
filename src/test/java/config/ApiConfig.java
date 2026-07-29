package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:auth.properties")
public interface ApiConfig extends Config {
    @Key("EMAIL")
    String email();

    @Key("PASSWORD")
    String password();

    @Key("API_KEY")
    String apiKey();
}

