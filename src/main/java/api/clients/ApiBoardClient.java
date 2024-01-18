package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;
import io.qameta.allure.Step;

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
    public BoardResponse deleteExistingBoard(String boardId, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", boardId)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(BoardResponse.class);
    }

    @Step("Update existing board with id: {boardId}. Expected status code: {expectedStatusCode}")
    public BoardResponse updateBoardName(String boardId, String boardName, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("name", boardName)
                .put("/1/boards/{id}", boardId)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(BoardResponse.class);
    }

    @Step("Open or close board with id: {boardId}. Expected status code: {expectedStatusCode}")
    public BoardResponse doOpenOrCloseExistBoard(String boardId, boolean openOrClose, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("closed", openOrClose)
                .put("/1/boards/{id}", boardId)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(BoardResponse.class);
    }
}