package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();
    }

    //1. Verify that if the total record is 20
    @Test
    public void Test1() {
        response.body("id.size()", equalTo(20));
    }

    //2. Verify that if the name of id =  5914072 is equal to "Goswami Prajapat”
    @Test
    public void Test2() {
        response.body("[1].name", equalTo("Goswami Prajapat"));
    }

    //3. Check the single ‘Name’ in the Array list (Tara Panicker)
    @Test
    public void Test3() {
        response.body("[4].name", equalTo("Tara Panicker"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Somu Bhat, Ghanaanand Verma, Ramaa Banerjee)
    @Test
    public void Test4() {
        response
                .body("[0].name", equalTo("Somu Bhat"))
                .body("[5].name", equalTo("Ghanaanand Verma"))
                .body("[6].name", equalTo("Ramaa Banerjee"));
    }

    //5. Verify the emai of userid = 5914048 is equal “kaniyar_heema@oconnell.test”
    @Test
    public void Test5(){
        response.body("[17].email", equalTo("kaniyar_heema@oconnell.test"));
    }

    //6. Verify the status is “Active” of username is “Heema Kaniyar”
    @Test
    public void Test6(){
        response.body("[17].status", equalTo("active"));
    }

    //7. Verify the Gender = female of username is “Dwaipayan Trivedi”
    @Test
    public void Test7(){
        response.body("[16].gender", equalTo("female"));
    }

}