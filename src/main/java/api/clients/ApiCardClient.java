package api.clients;

import api.BaseRestTestClient;
import api.pojo.requests.AttachmentBuilder;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.CommentOnTheCardBuilder;
import api.pojo.responses.AttachmentResponse;
import api.pojo.responses.CardResponse;
import api.pojo.responses.CommentOnTheCardResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

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
                .post("/1/cards/{id}/actions/comments", idCard)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CommentOnTheCardResponse.class);
    }

    @Step("Create attachment on the card with id: {idCard}. Expected status code {expectedStatusCode}")
    public AttachmentResponse createAttachmentOnCard
            (AttachmentBuilder attachmentOnCardBuilder, String idCard, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .body(attachmentOnCardBuilder)
                .post("/1/cards/{id}/attachments", idCard)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(AttachmentResponse.class);
    }

    @Step("Move card with id: {cardId} to other list with id: {targetListId}. Expected status code {expectedStatusCode}")
    public CardResponse moveCardsToAnotherList(String cardId, String targetListId, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("idList", targetListId)
                .when()
                .put("/1/cards/{id}", cardId)
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract().as(CardResponse.class);
    }
}