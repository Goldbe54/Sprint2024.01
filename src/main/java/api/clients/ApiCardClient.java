package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.CommentOnTheCardBuilder;
import api.pojo.responses.CardResponse;
import api.pojo.responses.CommentOnTheCardResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class ApiCardClient extends BaseRestTestClient {
    public ApiCardClient(String url) {
        super(url);
    }

    @Step("Create new card on the list with id: {listId}. Expected status code {expectedStatusCode}")
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

    @Step("Create new comment on the card with id: {idCard}. Expected status code {expectedStatusCode}")
    public CommentOnTheCardResponse createCommentOnTheCard(CommentOnTheCardBuilder commentOnTheCardBuilder, String idCard, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .body(commentOnTheCardBuilder)
                .post(format("/1/cards/%s/actions/comments", idCard))
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CommentOnTheCardResponse.class);
    }
}
