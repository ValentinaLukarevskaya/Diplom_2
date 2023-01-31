package com.valya.specifications;


import com.valya.config.ConfigSingle;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    private static final AllureRestAssured FILTER = new AllureRestAssured()
            .setRequestTemplate("request.ftl")
            .setResponseTemplate("response.ftl");

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigSingle.config.getBaseUriProperties())
                .setBasePath(ConfigSingle.config.getBaseApiPathProperties())
                .setContentType("application/json")
                .addFilter(FILTER)
                .build();
    }
}
