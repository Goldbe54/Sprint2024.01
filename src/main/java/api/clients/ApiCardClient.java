package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.CardBuilder;
import api.pojo.responses.CardResponse;

import static io.restassured.RestAssured.given;

public class ApiCardClient extends BaseRestTestClient {
    public ApiCardClient(String url) {
        super(url);
    }

    public CardResponse createNewCard(CardBuilder cardBody, String listId, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("idList", listId)
                .when()
                .body(cardBody)
                .post("/1/cards/")
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CardResponse.class);
    }
}
