package net.roadtocareer.dailyfinance.runner;

import io.restassured.response.Response;
import net.roadtocareer.dailyfinance.controller.AuthController;
import net.roadtocareer.dailyfinance.controller.CostController;
import net.roadtocareer.dailyfinance.controller.UserController;
import net.roadtocareer.dailyfinance.setup.Setup;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

/*
 ** 2025, January 14, Tuesday, 11:46 AM
 */
public class TestRunner extends Setup {
    @Test(priority = 1, description = "Register a new user")
    public void testRegistration() throws ConfigurationException, IOException, ParseException {

        AuthController authController = new AuthController(properties);
        Response response = authController.register();
        Assert.assertEquals(response.statusCode(), 201);

        setProperties("userId", response.jsonPath().get("_id"));

        setProperties("email", response.jsonPath().get("email"));
        setProperties("password", ((JSONObject) new JSONParser()
                .parse(new FileReader("./src/test/resources/user.json")))
                .get("password").toString());
    }

    @Test(priority = 2, description = "Login by admin")
    public void testLoginByAdmin() throws ConfigurationException {
        AuthController authController = new AuthController(properties);
        Response response = authController.login("admin@test.com", "admin123");
        Assert.assertTrue(response.jsonPath().get("_id").toString().contains("admin_id"));

        setProperties("adminToken", response.jsonPath().get("token"));
    }

    @Test(priority = 3, description = "Get user list")
    public void testUserList() {
        UserController userController = new UserController(properties);
        Response response = userController.getAllUser();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 4, description = "Search the new user by user id")
    public void testGetUserById() {
        UserController userController = new UserController(properties);
        Response response = userController.getUserById(properties.getProperty("userId"));
        Assert.assertTrue(response.jsonPath().get("_id").toString().contains(properties.getProperty("userId")));
    }

    @Test(priority = 5, description = "Edit the user info (e.g. firstname, phonenumber)")
    public void testUpdateUserById() throws IOException {
        UserController userController = new UserController(properties);
        Response response = userController.updateUserById(properties.getProperty("userId"));
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 6, description = "Login by any user")
    public void testLoginByUser() throws ConfigurationException {
        AuthController authController = new AuthController(properties);


        Response response = authController.login(
                properties.getProperty("email"),
                properties.getProperty("password")
        );


        Assert.assertEquals(response.jsonPath().get("_id"), properties.getProperty("userId"));

        setProperties("userToken", response.jsonPath().get("token"));
    }

    @Test(priority = 7, description = "Add any item")
    public void testAddCost() throws ConfigurationException {
        CostController costController = new CostController(properties);
        Response response = costController.addItem();
        Assert.assertEquals(response.statusCode(), 201);
        setProperties("itemId", response.jsonPath().get("_id"));
    }

    @Test(priority = 8, description = "Get item list")
    public void testGetAllCost() {
        CostController costController = new CostController(properties);
        Response response = costController.getAllItem();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 9, description = "Edit any item name")
    public void testUpdateCost()  {
        CostController costController = new CostController(properties);
        Response response = costController.updateItemById(properties.getProperty("itemId"));
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 10, description = "Delete any item from the item list")
    public void testDeleteCost() {
        CostController costController = new CostController(properties);
        Response response = costController.deleteItemById(properties.getProperty("itemId"));
        Assert.assertEquals(response.statusCode(), 200);
    }
}
