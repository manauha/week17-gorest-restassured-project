package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest {

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

    //1. Extract the title
    @Test
    public void Test1() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get title: " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void Test2() {
        Integer totalNoOfRecords = response.extract().path("size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of records : " + totalNoOfRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 10th record
    @Test
    public void Test3() {
        String body = response.extract().path("[9].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the body : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void Test4() {
        List<Integer> userId = response.extract().path("findAll{it.id}.user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the ids : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void Test5() {
        List<String> title = response.extract().path("findAll{it.id}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the ids : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 93943
    @Test
    public void Test6() {
        String titleId = response.extract().path("find{it.id == 93943}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the title whose id is : " + titleId);
        System.out.println("------------------End of Test---------------------------");

    }

    //7. Extract the body of all records whose id = 39296
    @Test
    public void Test7() {
        String titleBody = response.extract().path("find{it.id == 93830}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the body of : " + titleBody);
        System.out.println("------------------End of Test---------------------------");

    }
}