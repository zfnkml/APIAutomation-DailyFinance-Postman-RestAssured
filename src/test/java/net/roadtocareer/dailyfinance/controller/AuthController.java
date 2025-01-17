package net.roadtocareer.dailyfinance.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.roadtocareer.dailyfinance.model.UserData;

import java.io.IOException;
import java.util.Properties;

/*
 ** 2025, January 14, Tuesday, 11:17 AM
 */
public class AuthController {
    Properties properties;

    public AuthController(Properties properties) {
        this.properties = properties;
        RestAssured.baseURI = "https://dailyfinanceapi.roadtocareer.net";
        RestAssured.basePath = "/api/auth";
    }

    public Response register(UserData userData) throws IOException {
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(userData)
                .post("/register");

//        if(response.getStatusCode() == 201)
//            userData.setToJson();

        return response;
    }

    public Response login(String email, String password) {
        return RestAssured
                .given()
                .contentType("application/json")
                .body(new UserData(email, password))
                .post("/login");
    }

    public Response getAllUser() {
        return RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + properties.getProperty("adminToken"))
                .get();
    }

}
