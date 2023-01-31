package com.valya.tests.user;

import com.github.javafaker.Faker;
import api.models.User;
import api.generatingdata.GeneratingDataOfUser;
import com.valya.utils.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import com.valya.steps.UserSteps;

import static com.valya.helpers.Assertions.*;
import static com.valya.helpers.StatusCodes.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static com.valya.helpers.MessageOfResponse.*;

@DisplayName("Логин пользователя")
@Epic("Логин пользователя")
public class LoginUserTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        user = GeneratingDataOfUser.createNewUser();

        response = UserSteps.createUser(user);
    }

    @Feature("Авторизация пользователя")
    @Test
    @DisplayName("Авторизация пользователя")
    @Description("Ожидаемый код ответа: 200")
    public void loginUserWithAllValidParams() {
        loginResponse = UserSteps.loginUser((new User(user.getEmail(), user.getPassword(), null)));
        loginResponse
                .statusCode(OK_STATUS)
                .and()
                .body(ASSERT_SUCCESS, equalTo(true));
    }

    @Feature("Авторизация несуществующего пользователя")
    @Test
    @DisplayName("Авторизация несуществующего пользователя")
    @Description("Ожидаемый код ответа: 401")
    public void loginNonExistentUser() {
        loginResponse = UserSteps.loginUser((new User(Faker.instance().internet().emailAddress(), Faker.instance().number().toString(), null)));
        loginResponse.assertThat()
                .statusCode(UNAUTHORIZED_STATUS)
                .and()
                .assertThat().body("message", equalTo(LOGIN_ERROR_UNAUTHORIZED_401));
    }
}
