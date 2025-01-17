package net.roadtocareer.dailyfinance.controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.roadtocareer.dailyfinance.model.UserData;

import java.io.IOException;
import java.util.Properties;

/*
 ** 2025, January 14, Tuesday, 11:17 AM
 */
public class UserController {
    Properties properties;

    public UserController(Properties properties) {
        this.properties = properties;
        RestAssured.baseURI = "https://dailyfinanceapi.roadtocareer.net";
        RestAssured.basePath = "/api/user";
    }

    public Response getAllUser() {
        return RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + properties.getProperty("adminToken"))
                .get("/users");
    }

    public Response getUserById(String userId) {
        return RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + properties.getProperty("adminToken"))
                .get("/" + userId);
    }


    public Response updateUserById(String userId, UserData userData) throws IOException {
        Response getUserById = getUserById(userId);
        if(getUserById.getStatusCode() == 404)
            return getUserById;

        JsonPath jsonPath = getUserById.jsonPath();
        userData.setPassword(jsonPath.getString("password"));

        userData.setRole(jsonPath.getString("role"));
        userData.setProfileImage(jsonPath.get("profileImage"));
        userData.setResetPasswordToken(jsonPath.get("resetPasswordToken"));
        userData.setResetPasswordExpire(jsonPath.get("resetPasswordExpire"));
        userData.setCreatedAt(jsonPath.getString("createdAt"));
        userData.setUpdatedAt(jsonPath.getString("updatedAt"));

        System.out.println(userData);
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + properties.getProperty("adminToken"))
                .body(userData)
                .put("/" + userId);

        return response;
    }
}
