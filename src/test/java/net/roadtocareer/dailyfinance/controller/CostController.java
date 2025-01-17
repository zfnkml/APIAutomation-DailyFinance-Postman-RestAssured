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

    public Response addItem(ItemData itemData) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .body(itemData)
                .post();
    }

    public Response getItemById(String itemId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .get("/" + itemId);
    }

    public Response updateItemById(String itemId, ItemData itemData) {
        Response getByItemById = getItemById(itemId);
        if(getByItemById.statusCode() == 404)
            return getByItemById;

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .body(itemData)
                .put("/" + itemId);
    }

    public Response deleteItemById(String itemId) {
        Response getByItemById = getItemById(itemId);
        if(getByItemById.statusCode() == 404)
            return getByItemById;

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + properties.getProperty("userToken"))
                .delete("/" + itemId);
    }
}
