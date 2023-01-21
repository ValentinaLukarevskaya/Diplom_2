package com.valya.tests.user;

import com.valya.api.models.User;
import com.valya.api.generatingdata.GeneratingDataOfUser;
import com.valya.steps.UserSteps;
import com.valya.utils.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static com.valya.helpers.Assertions.*;
import static com.valya.helpers.StatusCodes.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static com.valya.helpers.MessageOfResponse.*;

@DisplayName("Обновление данных пользователя")
@Epic("Обновление данных пользователя")
public class UpdateUserDataTest extends BaseTest {

    private String expectedEmail;
    private String expectedName;

    @Override
    @Before
    public void setUp() throws InterruptedException {
        user = GeneratingDataOfUser.createNewUser();

        response = UserSteps.createUser(user);
        Thread.sleep(2000);

        loginResponse = UserSteps.loginUser((new User(user.getEmail(), user.getPassword(), null)));
        Thread.sleep(2000);
    }

    @Feature("Изменение 'email' пользователя с авторизацией")
    @Test
    @DisplayName("Изменение 'email' пользователя с авторизацией")
    @Description("Ожидаемый код ответа: 200")
    public void updateUserEmailWithAuth() throws InterruptedException {
        accessToken = UserSteps.getAccessToken(user);
        updateDataUser = GeneratingDataOfUser.editEmailOfUser();
        expectedEmail = updateDataUser.getEmail();

        updateDataOfUser = UserSteps.updateDataOfUser(updateDataUser, accessToken);
        updateDataOfUser
                .statusCode(OK_STATUS)
                .and()
                .body(ASSERT_USER_EMAIL, equalTo(expectedEmail));
    }

    @Feature("Изменение 'name' пользователя с авторизацией")
    @Test
    @DisplayName("Изменение 'name' пользователя с авторизацией")
    @Description("Ожидаемый код ответа: 200")
    public void updateUserNameWithAuth() throws InterruptedException {
        accessToken = UserSteps.getAccessToken(user);
        updateDataUser = GeneratingDataOfUser.editNameOfUser();
        expectedName = updateDataUser.getName();

        updateDataOfUser = UserSteps.updateDataOfUser(updateDataUser, accessToken);
        updateDataOfUser
                .statusCode(OK_STATUS)
                .and()
                .body(ASSERT_USER_NAME, equalTo(expectedName));
    }

    @Feature("Изменение 'password' пользователя с авторизацией")
    @Test
    @DisplayName("Изменение 'password' пользователя с авторизацией")
    @Description("Ожидаемый код ответа: 200")
    public void updateUserPasswordWithAuth() throws InterruptedException {
        accessToken = UserSteps.getAccessToken(user);
        updateDataUser = GeneratingDataOfUser.editPasswordOfUser();

        updateDataOfUser = UserSteps.updateDataOfUser(updateDataUser, accessToken);
        updateDataOfUser
                .statusCode(OK_STATUS)
                .and()
                .body(ASSERT_SUCCESS, equalTo(true));
        Thread.sleep(2000);

        user.setPassword(updateDataUser.getPassword());

        loginResponse.assertThat()
                .statusCode(OK_STATUS)
                .and()
                .body(ASSERT_SUCCESS, equalTo(true));
    }

    @Feature("Изменение данных пользователя без авторизации")
    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    @Description("Ожидаемый код ответа: 401")
    public void updateUserDataWithoutAuth() throws InterruptedException {
        updateDataUser = GeneratingDataOfUser.editNameOfUser();

        updateDataOfUser = UserSteps.updateWithoutAuth(updateDataUser);
        updateDataOfUser.assertThat()
                .statusCode(UNAUTHORIZED_STATUS)
                .and()
                .body(ASSERT_MESSAGE, equalTo(AUTH_ERROR_401));
    }
}
