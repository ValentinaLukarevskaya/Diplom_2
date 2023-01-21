package com.valya.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:default.properties"})
public interface ProjectConfig extends Config {

    @Key("base.uri")
    @DefaultValue("https://stellarburgers.nomoreparties.site")
    String getBaseUriProperties();

    @Key("base.api.path")
    @DefaultValue("/api")
    String getBaseApiPathProperties();
}
