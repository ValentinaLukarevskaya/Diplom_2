package com.valya.steps;

import com.valya.api.models.Order;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static com.valya.config.EndPoints.*;
import static com.valya.specifications.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @DisplayName("Получение заказов пользователя без авторизации")
    @Step("Получение заказов пользователя без авторизации")
    public static ValidatableResponse getOrdersWithoutAuth() {
        return given()
                .spec(requestSpecification())
                .when()
                .get(ORDERS)
                .then()
                .log().ifError();
    }

    @DisplayName("Запрос списка ингредиентов")
    @Step("Запрос списка ингредиентов")
    public static List<String> getIngredients() {
        return given()
                .spec(requestSpecification())
                .when()
                .get(INGREDIENTS)
                .then()
                .log().ifError()
                .extract().path("data._id");
    }

    @DisplayName("Создание заказа с авторизацией")
    @Step("Создание заказа с авторизацией")
    public static ValidatableResponse createOrderWithAuth(Order order, String accessToken) {
        return given()
                .spec(requestSpecification())
                .header("Authorization", accessToken)
                .body(order)
                .when()
                .post(ORDERS)
                .then()
                .log().ifError();
    }

    @DisplayName("Получение заказа с авторизацией")
    @Step("Получение заказа с авторизацией")
    public static ValidatableResponse getOrder(String accessToken) {
        return given()
                .spec(requestSpecification())
                .header("Authorization", accessToken)
                .when()
                .get(ORDERS)
                .then()
                .log().ifError();
    }

    @DisplayName("Создание заказа без авторизации")
    @Step("Создание заказа без авторизации")
    public static ValidatableResponse createOrderWithoutAuth(Order order) {
        return given()
                .spec(requestSpecification())
                .body(order)
                .when()
                .post(ORDERS)
                .then()
                .log().ifError();
    }
}
