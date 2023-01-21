package com.valya.specifications;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.valya.config.ConfigSingle.config;

public class Specifications {

    private static final AllureRestAssured FILTER = new AllureRestAssured()
            .setRequestTemplate("request.ftl")
            .setResponseTemplate("response.ftl");

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(config.getBaseUriProperties())
                .setBasePath(config.getBaseApiPathProperties())
                .setContentType("application/json")
                .addFilter(FILTER)
                .build();
    }
}
