package api.clients;

import api.BaseRestTestClient;
import api.pojo.responses.LabelResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiLabelClient extends BaseRestTestClient {
    public ApiLabelClient(String url) {
        super(url);
    }

    @Step("Create new label with name {labelName} and with id {idLabel}. Expected status code {expectedStatusCode}")
    public LabelResponse createNewLabel(String labelName, String labelColor, String idBoard, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("name", labelName)
                .queryParam("color", labelColor)
                .queryParam("idBoard", idBoard)
                .when()
                .post("/1/labels")
                .then()
                .statusCode(expectedStatusCode)
                .log()
                .body()
                .extract()
                .as(LabelResponse.class);
    }

    @Step("Add a Label with id: {idLabel} to Card with id: {idCard}. Expected status code {expectedStatusCode}")
    public Response addLabelToCard(String idCard, String idLabel, int expectedStatusCode) {
        return given()
                .spec(requestSpec)
                .queryParam("value", idLabel)
                .when()
                .post("/1/cards/{id}/idLabels", idCard)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }
    @Step("Delete a Label by cardId: {idCard} and labelId {idLabel}")
    public Response deleteLabel(String idCard, String idLabel, int expectedStatusCode){
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/cards/{idCard}/idLabels/{idLabel}", idCard, idLabel)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }
}