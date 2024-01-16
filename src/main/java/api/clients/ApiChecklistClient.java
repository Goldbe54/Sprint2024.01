package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.CheckitemBuilder;
import api.pojo.requests.ChecklistBuilder;
import api.pojo.responses.CardResponse;
import api.pojo.responses.CheckitemsResponse;
import api.pojo.responses.ChecklistResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

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
                .post(format("/1/checklists/%s/checkItems",idChecklist))
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CheckitemsResponse.class);
    }
}
