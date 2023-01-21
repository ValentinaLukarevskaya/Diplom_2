package com.valya.utils;

import com.valya.api.generatingdata.GeneratingDataOfUser;
import com.valya.api.models.User;
import com.valya.steps.UserSteps;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    protected String accessToken;
    protected User user;
    protected User userModified;
    protected User updateDataUser;
    protected ValidatableResponse response;
    protected ValidatableResponse loginResponse;
    protected ValidatableResponse updateDataOfUser;
    protected ValidatableResponse errorResponse;

    @Before
    @Description("Конфигурация перед началом выполнения теста")
    public void setUp() throws InterruptedException {
        user = GeneratingDataOfUser.createNewUser();
    }

    @After
    @Description("Конфигурация после окончания теста")
    public void deleteDataOfUser() throws InterruptedException {
        if (response.extract().body().path("success").equals(true)) {
            accessToken = UserSteps.getAccessToken(response);
            user.setAccessToken(accessToken);
            UserSteps.deleteUser(user);
        }
        Thread.sleep(2000);
    }
}
