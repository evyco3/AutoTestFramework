package com.evy.framework.config;

import com.evy.framework.driver.BrowserType;
import org.aeonbits.owner.Config;

/**
 * Configuration interface for accessing framework settings from the config.properties file.
 * This includes browser type, URL, timeouts, and login credentials.
 */
@Config.Sources("file:${user.dir}/src/main/resources/config.properties")
public interface FrameworkConfig extends Config{


    @Config.Key("browserType")
    @Config.DefaultValue("chrome")
    @ConverterClass(BrowserTypeConverter.class)
    BrowserType browserType();

    @Config.Key("url")
    @Config.DefaultValue("https://demo.sylius.com/en_US/")
    String url();

    @Config.Key("implicitTime")
    @Config.DefaultValue("10")
    int implicitTime();

    @Config.Key("pageLoadTime")
    @Config.DefaultValue("15")
    int pageLoadTime();

    @Config.Key("email")
    String email();

    @Config.Key("password")
    String password();
}
