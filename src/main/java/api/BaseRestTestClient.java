package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static utils.ConfigProvider.API_KEY;
import static utils.ConfigProvider.API_TOKEN;

public class BaseRestTestClient {

    protected final RequestSpecification requestSpec;

    public BaseRestTestClient(String url) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .log().all();
//                .filter(new AllureRestA);

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }
}
