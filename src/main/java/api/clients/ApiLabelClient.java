package api.clients;

import api.BaseRestTestClient;
import api.pojo.responses.LabelResponse;

import static io.restassured.RestAssured.given;

public class ApiLabelClient extends BaseRestTestClient {
    public ApiLabelClient(String url){super(url);}

    public LabelResponse createNewLabel(String labelName, String labelColor, String idBoard, int expectedStatusCode){
        return given()
                .spec(requestSpec)
                .queryParam("name",labelName)
                .queryParam("color", labelColor)
                .queryParam("idBoard" ,idBoard)
                .when()
                .post("/1/labels")
                .then()
                .statusCode(expectedStatusCode)
                .log().body().extract().as(LabelResponse.class);
    }
}
