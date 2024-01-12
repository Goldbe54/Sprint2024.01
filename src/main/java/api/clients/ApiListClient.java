package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.ListBuilder;
import api.pojo.responses.ListResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class ApiListClient extends BaseRestTestClient {
    public ApiListClient(String url) {
        super(url);
    }

    @Step("Create new list on board with id: {idBoard}. Expected status code {expectedStatusCode} ")
    public ListResponse createNewList(ListBuilder listBody, String idBoard, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .body(listBody)
                .post(format("/1/boards/%s/lists/", idBoard))
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(ListResponse.class);
    }

    @Step("Rename created list on board with id: {idList}. New name of list {listName}. Expected status code {expectedStatusCode}")
    public ListResponse renameList(String listId, String listName, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("name", listName)
                .put("/1/lists/{id}", listId)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(ListResponse.class);
    }

    @Step("Move cards from source list to target list")
    public ListResponse[] moveCardsToAnotherList(String idBoard, String idListSource, String idListTarget, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .queryParam("idBoard", idBoard)
                .queryParam("idList", idListTarget)
                .post(format("/1/lists/%s/moveAllCards", idListSource))
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(ListResponse[].class);
    }
}