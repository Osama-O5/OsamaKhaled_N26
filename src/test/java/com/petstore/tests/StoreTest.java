package com.petstore.tests;

import DataCreationFromModel.StoreDataCreator;
import com.jayway.jsonpath.JsonPath;
import com.petstore.requestApis.StoreRequests;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreTest extends TestBase {
    public static StoreRequests storeRequests = new StoreRequests();
    public static StoreDataCreator storeDataCreator = new StoreDataCreator();
    public static int createdOrderID;
    public static String createdOrderIDString;

    StoreTest() {
        super.TestBase("/store");
    }

    @Feature("Stores requests")
    @Story("Fetching all orders from inventory by given status")
    @Test
    @DisplayName("Fetch the inventory status")
    public void getInventoryStatus() {
        response = storeRequests.inventoryStatus();
        response.prettyPrint();

        validatableResponse =
                response
                        .then()
                        .statusCode(200);
    }

    @Feature("Stores requests")
    @Story("Place a new order")
    @Test
    @Order(1)
    @DisplayName("Place a new order")
    public void placeAnOrder() {
        super.TestBase("/store/order");

        response = storeRequests.newOrder(storeDataCreator.NewOrderData());
        response.prettyPrint();

        validatableResponse =
                response
                        .then()
                        .statusCode(200);

        String responseString = response.asString();

        createdOrderID = JsonPath.read(responseString, "$.id");
        createdOrderIDString = "/" + createdOrderID;
    }

    @Feature("Stores requests")
    @Story("Fetching the order by given ID")
    @Test
    @Order(2)
    @DisplayName("Fetch the given order by ID")
    public void getOrderByID() {
        super.TestBase("/store/order");

        Response response = storeRequests.orderById(createdOrderIDString);
        response.prettyPrint();

        validatableResponse =
                response
                        .then()
                        .statusCode(200);
    }

    @Feature("Stores requests")
    @Story("Delete the order created as part of previous test")
    @Test
    @Order(3)
    @DisplayName("Delete an newly created order")
    public void DeleteTheCreatedOrder() {
        super.TestBase("/store/order");

        response = storeRequests.deleteOrder(createdOrderIDString);
        response.prettyPrint();

        validatableResponse =
                response
                        .then()
                        .statusCode(200);
    }
}
