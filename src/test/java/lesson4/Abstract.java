package lesson4;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

public abstract class Abstract {
    protected static ResponseSpecification responseSpecification;
    protected static RequestSpecification requestSpecification;
    @BeforeAll
    static void initTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", "02f3d8ab85d54af69db9e18220c414ba")
                .addQueryParam("includeNutrition", "false")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

}
