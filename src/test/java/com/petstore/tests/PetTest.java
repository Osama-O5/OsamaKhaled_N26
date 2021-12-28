package com.petstore.tests;

import DataCreationFromModel.PetDataCreator;
import DataModel.PetPojo;
import com.jayway.jsonpath.JsonPath;
import com.petstore.requestApis.PetRequests;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetTest extends TestBase {
    PetTest() {
        super.TestBase("/pet");
    }

    public static PetPojo pet = new PetPojo();
    public static PetRequests petRequest = new PetRequests();
    public static int createdPetID;
    public static String createdPetIDString;

    public static PetDataCreator petDataCreator = new PetDataCreator();

    @Story("Fetching all pets by given status")
    @Feature("Pet requests")
    @Test
    @DisplayName("Fetch all pets by statuses")
    public void getPetsByStatus() {
        response = petRequest.getPetByStatus("available");
        response.prettyPrint();

        validatableResponse =
                response
                        .then()
                        .statusCode(200);
    }

    @Story("Creating a new pet")
    @Feature("Pet requests")
    @Test
    @Order(1)
    @DisplayName("Create a new pet")
    public void createPet() {
        response = petRequest.petCreation(petDataCreator.petCreationData());

        response.prettyPrint();
        validatableResponse =
                response
                        .then()
                        .statusCode(200);

        String responseString = response.asString();

        createdPetID = JsonPath.read(responseString, "$.id");
        createdPetIDString = "/" + createdPetID;
    }

    @Story("Finding pet by given id")
    @Feature("Pet requests")
    @Test
    @DisplayName("Finding pet by given id")
    public void findPetByID() {
        response = petRequest.petById(createdPetIDString);

        response.prettyPrint();

        validatableResponse =
                response
                        .then()
                        .statusCode(200);
    }

    @Story("update pet pet by given id")
    @Feature("Pet requests")
    @Test
    @DisplayName("updating pet by given id")
    public void updateExistingPet() {
        response = petRequest.updateGivenPet(petDataCreator.existingPetUpdateData(createdPetID));

        response.prettyPrint();

        validatableResponse =
                response
                        .then()
                        .statusCode(200);
    }

    @Story("Update pet in store with form data")
    @Feature("Pet requests")
    @Test
    @DisplayName("updating pet by form data")
    public void updateExistingPetWithFormData() {
        response = petRequest.updatePetWithFormData(petDataCreator.petDataforUpdate(createdPetID));

        validatableResponse =
                response
                        .then()
                        .statusCode(200);

        response.prettyPrint();
    }
}
