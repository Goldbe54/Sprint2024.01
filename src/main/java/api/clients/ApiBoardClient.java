package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;

import static io.restassured.RestAssured.given;

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
}
