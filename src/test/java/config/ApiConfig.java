package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:auth.properties"
})
public interface ApiConfig extends Config {

    @Key("BASE_URI")
    @DefaultValue("https://reqres.in")
    String baseUri();

    @Key("BASE_PATH")
    @DefaultValue("/api")
    String basePath();

    @Key("EMAIL")
    @DefaultValue("eve.holt@reqres.in")
    String email();

    @Key("PASSWORD")
    @DefaultValue("pistol")
    String password();

    @Key("API_KEY")
    String apiKey();
}
