package com.valya.steps;

import api.models.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import static com.valya.config.EndPoints.*;
import static com.valya.specifications.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class UserSteps{

    @DisplayName("Создание пользователя")
    @Step("Создание пользователя")
    public static ValidatableResponse createUser(User body) {
        return given()
                .spec(requestSpecification())
                .body(body)
                .when()
                .post(AUTH + REGISTER)
                .then().log().ifError();
    }

    @DisplayName("Авторизация пользователя")
    @Step("Авторизация пользователя")
    public static ValidatableResponse loginUser(User user) {
        return given()
                .spec(requestSpecification())
                .body(user)
                .when()
                .post(AUTH + LOGIN)
                .then()
                .log().ifError();

    }

    @DisplayName("Получение access token пользователя")
    @Step("Получение access token пользователя")
    public static String getAccessToken(User user) {
        return loginUser(user)
                .extract()
                .path("accessToken");
    }

    @DisplayName("Изменение данных пользователя без авторизации")
    @Step("Изменение данных пользователя без авторизации")
    public static ValidatableResponse updateWithoutAuth(User user) {
        return given()
                .spec(requestSpecification())
                .body(user)
                .when()
                .patch(AUTH + USER)
                .then()
                .log().ifError();
    }

    @DisplayName("Изменение данных пользователя с авторизацией")
    @Step("Изменение данных пользователя с авторизацией")
    public static ValidatableResponse updateDataOfUser(User user, String accessToken) {
        return given()
                .spec(requestSpecification())
                .header("Authorization", accessToken)
                .body(user)
                .when()
                .patch(AUTH +USER)
                .then()
                .log().ifError();
    }

    @DisplayName("Удаление пользователя")
    @Step("Удаление пользователя")
    public static void deleteUser(User user) {
        given()
                .spec(requestSpecification())
                .auth().oauth2(user.getAccessToken())
                .body(user)
                .when()
                .delete(AUTH +USER)
                .then()
                .statusCode(202)
                .body("message", equalTo("User successfully removed"))
                .log().ifError();
    }

    @DisplayName("Получение access token пользователя")
    @Step("Получение access token пользователя")
    public static String getAccessToken(ValidatableResponse validatableResponse) {
        return validatableResponse
                .extract().path("accessToken").toString().substring(7);
    }
}