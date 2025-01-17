package net.roadtocareer.dailyfinance.runner;

import io.restassured.response.Response;
import net.roadtocareer.dailyfinance.controller.AuthController;
import net.roadtocareer.dailyfinance.controller.CostController;
import net.roadtocareer.dailyfinance.controller.UserController;
import net.roadtocareer.dailyfinance.model.UserData;
import net.roadtocareer.dailyfinance.setup.Setup;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/*
 ** 2025, January 14, Tuesday, 11:46 AM
 */
public class TestRunner extends Setup {
    @org.junit.jupiter.api.Test
//    @Test(priority = 1, description = "Register a new user")
    public void testRegistration_withRandomData_successful() throws ConfigurationException, IOException, ParseException {
        AuthController authController = new AuthController(properties);
        UserData userData = new UserData().generator();

        Response response = authController.register(userData);
        Assert.assertEquals(response.statusCode(), 201);

        setProperties("_id", response.jsonPath().get("_id"));
        setRegistrationUserDataToProperties(userData);
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 2, description = "Register same user")
    public void testRegistration_withExistingData_failed() throws ConfigurationException, IOException, ParseException {
        UserData userData = getRegistrationUserDataFromProperties();
        AuthController authController = new AuthController(properties);

        Response response = authController.register(userData);
        Assert.assertEquals(response.statusCode(), 400);
    }

    @org.junit.jupiter.api.Test
    //    @Test(priority = 3, description = "Login by admin")
    public void testLoginByAdmin_withCred_successful() throws ConfigurationException {
        AuthController authController = new AuthController(properties);

        Response response = authController.login("admin@test.com", "admin123");
        Assert.assertTrue(response.jsonPath().get("_id").toString().contains("admin_id"));

        setProperties("adminToken", response.jsonPath().get("token"));
    }

    @org.junit.jupiter.api.Test
    //    @Test(priority = 3, description = "Login by admin")
    public void testLoginByAdmin_withWrongCred_fail() throws ConfigurationException {
        AuthController authController = new AuthController(properties);

        Response response = authController.login("admin@test.com", "admin1235");
        Assert.assertEquals(response.statusCode(), 401);
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 3, description = "Get user list")
    public void testUserList() {
        UserController userController = new UserController(properties);

        Response response = userController.getAllUser();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 4, description = "Search the new user by user id")
    public void testGetUserById_withValidId_successful() {
        UserController userController = new UserController(properties);
        String userId = properties.getProperty("_id");

        Response response = userController.getUserById(userId);
        Assert.assertTrue(response.jsonPath().get("_id").toString().contains(userId));
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 4, description = "Search the new user by user id")
    public void testGetUserById_withInvalidId_fail(){
        UserController userController = new UserController(properties);

        Response response = userController.getUserById("InvalidId");
        Assert.assertEquals(response.statusCode(), 404);

    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 5, description = "Edit the user info (e.g. firstname, phonenumber)")
    public void testUpdateUserFirstName() throws IOException, ConfigurationException {
        UserData userData = getRegistrationUserDataFromProperties();
        String userId = properties.getProperty("_id");
        userData.set_id(userId);
        userData.setFirstName(properties.getProperty("firstName") + "_updated");

        UserController userController = new UserController(properties);
        Response response = userController.updateUserById(userId, userData);
        Assert.assertEquals(response.statusCode(), 200);

        setProperties("firstName", response.jsonPath().get("firstName"));
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 5, description = "Edit the user info (e.g. firstname, phonenumber)")
    public void testUpdateUserPhoneNumber() throws IOException, ConfigurationException {
        UserData userData = getRegistrationUserDataFromProperties();
        String userId = properties.getProperty("_id");
        userData.set_id(userId);
        userData.setPhoneNumber("01234567890");

        UserController userController = new UserController(properties);
        Response response = userController.updateUserById(userId, userData);
        Assert.assertEquals(response.statusCode(), 200);

        setProperties("phoneNumber", response.jsonPath().get("phoneNumber"));
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 5, description = "Edit the user info (e.g. firstname, phonenumber)")
    public void testUpdateUser_invalidUserId_failed() throws IOException, ConfigurationException {
        UserData userData = getRegistrationUserDataFromProperties();
        String userId = "3508ba09-dfc6-4889-a891-bac1288ee3d-invalid";
        userData.set_id(userId);
        userData.setPhoneNumber("01234567890");

        UserController userController = new UserController(properties);
        Response response = userController.updateUserById(userId, userData);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 6, description = "Login by any user")
    public void testLoginByUser_withCred_successful() throws ConfigurationException {
        AuthController authController = new AuthController(properties);

        Response response = authController.login(
                properties.getProperty("email"),
                properties.getProperty("password")
        );
        Assert.assertEquals(response.jsonPath().get("_id"), properties.getProperty("_id"));

        setProperties("userToken", response.jsonPath().get("token"));
    }

    @org.junit.jupiter.api.Test
//    @Test(priority = 6, description = "Login by any user")
    public void testLoginByUser_withInvalidCred_failed() throws ConfigurationException {
        AuthController authController = new AuthController(properties);
        Response response = authController.login(
                "invalid.cred@gmail.com",
                "invalidPassword"
        );
        Assert.assertEquals(response.statusCode(), 401);
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

    private UserData getRegistrationUserDataFromProperties() {
        UserData userData = new UserData();
        userData.setFirstName(properties.getProperty("firstName"));
        userData.setLastName(properties.getProperty("lastName"));
        userData.setEmail(properties.getProperty("email"));
        userData.setPassword(properties.getProperty("password"));
        userData.setPhoneNumber(properties.getProperty("phoneNumber"));
        userData.setAddress(properties.getProperty("address"));
        userData.setGender(properties.getProperty("gender"));
        userData.setTermsAccepted(true);
        return userData;
    }

    private void setRegistrationUserDataToProperties(UserData userData) throws ConfigurationException {
        setProperties("firstName", userData.getFirstName());
        setProperties("lastName", userData.getLastName());
        setProperties("email", userData.getEmail());
        setProperties("password", userData.getPassword());
        setProperties("phoneNumber", userData.getPhoneNumber());
        setProperties("address", userData.getAddress());
        setProperties("gender", userData.getGender());
    }
}
