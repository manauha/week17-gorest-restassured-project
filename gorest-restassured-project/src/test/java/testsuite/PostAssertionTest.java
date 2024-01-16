package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Verify that if the total record is 25
    @Test
    public void Test1() {
        response.body(("size()"), equalTo(25));
    }

    //2. Verify that if the title of id = 93825 is equal to "Carbo vilitas quia caritas itaque."
    @Test
    public void Test2() {
        response.body("findAll{it.id == 93825}.title", hasItem("Carbo vilitas quia caritas itaque."));
    }

    //3. Check the single user_id in the Array list (93825)
    @Test
    public void Test3() {
        response.body("findAll{it}.id", hasItem(93825));
    }

    //4. Check the multiple ids in the ArrayList (39686, 39663, 39653)
    @Test
    public void Test4() {
        response.body("findAll{it}.id", hasItems(93916, 93829, 93943));
    }

    //5. Verify the body of userid = 93829 is equal "Vero solutio aperte. Occaecati dolor apto. Pauper canis utilis. Antiquus corpus amet. Cado sollers anser. Vesper vivo officiis. Non deripio degusto. Delibero vulgo viriliter. Derideo deficio vitium. Arcus damnatio est. Coniuratio tepidus theologus. Triumphus et damno. Et surculus vesper."
    @Test
    public void Test5() {
        response.body("findAll{it.id == 93829}.body", hasItem("Vero solutio aperte. Occaecati dolor apto. Pauper canis utilis. Antiquus corpus amet. Cado sollers anser. Vesper vivo officiis. Non deripio degusto. Delibero vulgo viriliter. Derideo deficio vitium. Arcus damnatio est. Coniuratio tepidus theologus. Triumphus et damno. Et surculus vesper."));
    }
}