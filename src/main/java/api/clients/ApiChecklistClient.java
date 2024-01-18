package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.CheckitemBuilder;
import api.pojo.requests.ChecklistBuilder;
import api.pojo.responses.CheckitemsResponse;
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
    public CheckitemsResponse createNewCheckitem(CheckitemBuilder checkitemBuilder, String idChecklist, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("idCard", idChecklist)
                .when()
                .body(checkitemBuilder)
                .post("/1/checklists/{id}/checkItems",idChecklist)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CheckitemsResponse.class);
    }

    @Step("Edit checkitem on card with id: {idCard}. Expected status code {expectedStatusCode} ")
    public CheckitemsResponse editCheckitem(String idCard, String idCheckItem, CheckitemBuilder checkItemBody, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .body(checkItemBody)
                .put("/1/cards/{idCard}/checkItem/{idCheckItem}", idCard, idCheckItem)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CheckitemsResponse.class);
    }
}