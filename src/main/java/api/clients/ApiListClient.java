package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.ListBuilder;
import api.pojo.responses.BoardResponse;
import api.pojo.responses.ListResponse;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class ApiListClient extends BaseRestTestClient {
    public ApiListClient(String url) {
        super(url);
    }

    public ListResponse createNewList(ListBuilder listBody, String idBoard, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .body(listBody)
                .post(format("/1/boards/%s/lists/",idBoard))
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(ListResponse.class);
    }
}
