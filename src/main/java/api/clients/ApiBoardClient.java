package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class ApiBoardClient extends BaseRestTestClient {
    public ApiBoardClient(String url) {
        super(url);
    }

    public BoardResponse createNewBoard(BoardBuilder boardBody) {
        return given()
                .spec(requestSpec)
                .when()
                .body(boardBody)
                .post("/1/boards/")
                .then()
                .log()
                .body()
                .extract()
                .as(BoardResponse.class);
    }
    public ValidatableResponse deleteExistingBoard(String boardId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", boardId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }
}
