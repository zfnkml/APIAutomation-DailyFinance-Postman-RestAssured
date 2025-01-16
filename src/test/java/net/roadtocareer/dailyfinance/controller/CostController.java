package net.roadtocareer.dailyfinance.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.roadtocareer.dailyfinance.model.ItemData;

import java.util.Properties;

/*
 ** 2025, January 14, Tuesday, 4:46 PM
 */
public class CostController {
    Properties properties;

    public CostController(Properties properties) {
        this.properties = properties;
        RestAssured.baseURI = "https://dailyfinanceapi.roadtocareer.net";
        RestAssured.basePath = "/api/costs";
    }


    public Response getAllItem() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .get();
    }

    public Response addItem() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .body(new ItemData().generate())
                .post();
    }

    private Response getItemById(String itemId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .get("/" + itemId);
    }

    public Response updateItemById(String itemId) {
        JsonPath jsonPath = getItemById(itemId).jsonPath();
        ItemData itemData = new ItemData(
                jsonPath.get("itemName").toString() + "_updated",
                Integer.parseInt(jsonPath.get("quantity").toString()) + 1,
                jsonPath.get("amount").toString(),
                jsonPath.get("purchaseDate").toString(),
                jsonPath.get("month").toString(),
                jsonPath.get("remarks").toString()
        );

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .body(itemData)
                .put("/" + itemId);
    }

    public Response deleteItemById(String itemId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .delete("/" + itemId);
    }
}
