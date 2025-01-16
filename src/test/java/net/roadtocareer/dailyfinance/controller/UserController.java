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


    public Response updateUserById(String userId) throws IOException {
        JsonPath jsonPath = getUserById(userId).jsonPath();
        UserData userData = new UserData(
                jsonPath.getString("_id"),
                jsonPath.getString("firstName") + "_update",
                jsonPath.getString("lastName"),
                jsonPath.getString("email"),
                jsonPath.getString("password"),
                jsonPath.getString("phoneNumber"),
                jsonPath.getString("address"),
                jsonPath.getString("gender"),
                true,
                jsonPath.getString("role"),
                jsonPath.getString("profileImage"),
                jsonPath.get("resetPasswordToken"),
                jsonPath.get("resetPasswordExpire"),
                jsonPath.getString("createdAt"),
                jsonPath.getString("updatedAt")
        );

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + properties.getProperty("adminToken"))
                .body(userData)
                .put("/" + userId);

        userData.storeAsJson();

        return response;
    }
}
