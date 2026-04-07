package configs;

import constants.Constants;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:default.properties"
})
public interface TestPropertiesConfig extends Config {
    @Key("baseUrl")
    @DefaultValue(Constants.BASE_URL)
    String getBaseUrl();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

    @Key("timeoutWeryFast")
    Integer getTimeoutWeryFast();

    @Key("timeoutFast")
    Integer getTimeoutFast();

    @Key("timeoutMedium")
    Integer getTimeoutMedium();

    @Key("timeoutSlow")
    Integer getTimeoutSlow();
}
