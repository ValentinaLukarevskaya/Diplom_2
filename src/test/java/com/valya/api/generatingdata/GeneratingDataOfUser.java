package com.valya.api.generatingdata;

import com.github.javafaker.Faker;
import com.valya.api.models.User;

public class GeneratingDataOfUser {
    public static User createNewUser() {
        return User.builder()
                .email(Faker.instance().internet().emailAddress())
                .password(Faker.instance().internet().password())
                .name(Faker.instance().name().firstName())
                .build();
    }

    public static User createNewUserWithoutEmail() {
        return User.builder()
                .email(null)
                .password(Faker.instance().internet().password())
                .name(Faker.instance().name().firstName())
                .build();
    }

    public static User createNewUserWithoutPassword() {
        return User.builder()
                .email(Faker.instance().internet().emailAddress())
                .password(null)
                .name(Faker.instance().name().firstName())
                .build();
    }

    public static User createNewUserWithoutName() {
        return User.builder()
                .email(Faker.instance().internet().emailAddress())
                .password(Faker.instance().internet().password())
                .name(null)
                .build();
    }

    public static User editEmailOfUser() {
        return User.builder()
                .email(Faker.instance().internet().emailAddress())
                .password(null)
                .name(null)
                .build();
    }

    public static User editNameOfUser() {
        return User.builder()
                .email(null)
                .password(null)
                .name(Faker.instance().name().firstName())
                .build();
    }

    public static User editPasswordOfUser() {
        return User.builder()
                .email(null)
                .password(Faker.instance().internet().password())
                .name(null)
                .build();
    }
}