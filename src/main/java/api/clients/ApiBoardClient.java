package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiBoardClient extends BaseRestTestClient {
    public ApiBoardClient(String url) {
        super(url);
    }

    @Step("Create new board. Expected status code {expectedStatusCode}")
    public BoardResponse createNewBoard(BoardBuilder boardBody, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .body(boardBody)
                .post("/1/boards/")
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(BoardResponse.class);
    }

    @Step("Delete existing board with id: {boardId}. Expected status code{expectedStatusCode}")
    public ValidatableResponse deleteExistingBoard(String boardId, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", boardId)
                .then()
                .statusCode(expectedStatusCode);
    }
}