package lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HW4 extends Abstract {
    @Test
    void getComplexSearch() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getComplexSearchBurger() {
        given().spec(requestSpecification)
                .queryParam("apiKey", "02f3d8ab85d54af69db9e18220c414ba")
                .queryParam("query", "burger")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);

    }

    @Test
    void getComplexSearchSugar() {
        given().spec(requestSpecification)
                .queryParam("apiKey", "02f3d8ab85d54af69db9e18220c414ba")
                .queryParam("maxSugar", "100")
                .queryParam("number", "5")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getComplexSearchVitaminB12() {
        given().spec(requestSpecification)
                .queryParam("apiKey", "02f3d8ab85d54af69db9e18220c414ba")
                .queryParam("maxVitaminB12", "100")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getComplexSearchCaffeine() {
        given().spec(requestSpecification)
                .queryParam("apiKey", "02f3d8ab85d54af69db9e18220c414ba")
                .queryParam("minCaffeine", "0")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postPorkRoastWithGreenBeans() {
        given().spec(requestSpecification)
                .formParam("title","Pork roast with green beans")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postCauliflowerBrownRiceAndVegetableFriedRice() {
        given().spec(requestSpecification)
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postSpanishStyleSalmonFillets() {
        given().spec(requestSpecification)
                .formParam("title","Spanish style salmon fillets")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postAfricanChickenPeanutStew() {
        given().spec(requestSpecification)
                .formParam("title","African Chicken Peanut Stew")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postAddToShoppingList() {
        given().spec(requestSpecification)
                .queryParam("hash", "41d60715694df36f015061e7d9ddb2d089fb8893")
                .body("{\n" +
                        "\t\"item\": \"1 package baking powder\",\n" +
                        "\t\"aisle\": \"Baking\",\n" +
                        "\t\"parse\": true\n" +
                        "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/bandidog/shopping-list/items")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getShoppingList() {
        given().spec(requestSpecification)
                .queryParam("hash", "41d60715694df36f015061e7d9ddb2d089fb8893")
                .when()
                .get("https://api.spoonacular.com/mealplanner/bandidog/shopping-list")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }
    @Test
    void deleteFromShoppingList() {
        given().spec(requestSpecification)
                .queryParam("hash", "41d60715694df36f015061e7d9ddb2d089fb8893")
                .body("{\n" +
                        "    \"username\": \"bandidog\",\n" +
                        "    \"id\": 1272861,\n" +
                        "    \"hash\": \"41d60715694df36f015061e7d9ddb2d089fb8893\"\n" +
                        "}")
                .when()
                .delete("https://api.spoonacular.com/mealplanner/bandidog/shopping-list/items/1272861")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }
}

