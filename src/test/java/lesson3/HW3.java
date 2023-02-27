package lesson3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HW3 {
    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
    @Test
    void getComplexSearch() {
        given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getComplexSearchBurger() {
        JsonPath response = given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .queryParam("query", "burger")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("totalResults"), equalTo(54));

    }

    @Test
    void getComplexSearchSugar() {
        JsonPath response = given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .queryParam("maxSugar", "100")
                .queryParam("number", "5")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("number"), equalTo(5));
    }

    @Test
    void getComplexSearchVitaminB12() {
        given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .queryParam("maxVitaminB12", "100")
                .expect()
                .body("results[0].nutrition.nutrients[0].name", equalTo("Vitamin B12"))
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200);
    }
    @Test
    void getComplexSearchCaffeine() {
        given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .queryParam("minCaffeine", "0")
                .expect()
                .body("results[0].nutrition.nutrients[0].name", equalTo("Caffeine"))
                .body("results[0].nutrition.nutrients[0].amount", equalTo(0.0F))
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200);
    }
    @Test
    void postPorkRoastWithGreenBeans() {
        given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .expect()
                .body("cuisine", equalTo("Mediterranean"))
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200);
    }
    @Test
    void postCauliflowerBrownRiceAndVegetableFriedRice() {
        given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .expect()
                .body("cuisine", equalTo("Chinese"))
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200);
    }
    @Test
    void postSpanishStyleSalmonFillets() {
        given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Spanish style salmon fillets")
                .expect()
                .body("cuisine", equalTo("European"))
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200);
    }
    @Test
    void postAfricanChickenPeanutStew() {
        given()
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","African Chicken Peanut Stew")
                .expect()
                .body("cuisine", equalTo("African"))
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200);
    }
    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .expect()
                .body("status", equalTo("success"))
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "d9d0aa735f1749828f52c178dc26e42c")
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);
    }
}
