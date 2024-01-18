package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.CheckitemBuilder;
import api.pojo.requests.ChecklistBuilder;
import api.pojo.responses.CheckItemsResponse;
import api.pojo.responses.ChecklistResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class ApiChecklistClient extends BaseRestTestClient {
    public ApiChecklistClient(String url) {
        super(url);
    }

    @Step("Create checklist on card with id: {idCard}. Expected status code {expectedStatusCode} ")
    public ChecklistResponse createNewChecklist(ChecklistBuilder checklistBody, String idCard, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("idCard", idCard)
                .when()
                .body(checklistBody)
                .post("/1/checklists")
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(ChecklistResponse.class);
    }

    @Step("Create checkitem on checklist with id: {idChecklist}. Expected status code {expectedStatusCode} ")
    public CheckItemsResponse createNewCheckitem(CheckitemBuilder checkitemBuilder, String idChecklist, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("idCard", idChecklist)
                .when()
                .body(checkitemBuilder)
                .post("/1/checklists/{id}/checkItems", idChecklist)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CheckItemsResponse.class);
    }

    @Step("Delete checklist with id: {checklistsId}. Expected status code{expectedStatusCode}")
    public ChecklistResponse deleteChecklist(String IdChecklist, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/checklists/{id}", IdChecklist)
                .then()
                .statusCode(expectedStatusCode)
                .log().body()
                .extract().as(ChecklistResponse.class);
    }
    @Step("Update existing checklist with id: {checklistId}. Expected status code: {expectedStatusCode}")
    public ChecklistResponse updateChecklist(String checklistId, String checklistName, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("name", checklistName)
                .put("/1/checklists/{id}", checklistId)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(ChecklistResponse.class);
    }

}