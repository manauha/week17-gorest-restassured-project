package crudtest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserPoJo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.TestUtils;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.basePath = "/users";
    }

    static String name = "Neil";
    static String email = "nc"+TestUtils.getRandomValue()+"@test.com";
    static String gender = "male";
    static String status = "active";
    static int id = 5970694;
    String primaryToken = "df7e4e4edd522da2b682667dfbff70981cb0926ca7554f36f5a6b2e376db425a";

    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPoJo userPojo = new UserPoJo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response =
                given()
                        .header("Authorization", "Bearer "+primaryToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .post();
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void getUser() {

        String s1 = "findAll{it.firstName == '";
        String s2 = "'}.get(0)";

        Response response =
                given().log().all()
                        .header("Authorization", "Bearer "+primaryToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .get();
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    public void singleUser() {

        Response response =
                given().log().all()
                        .when()
                        .get("/" + id);
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void verifyUserUpdateSuccessfully() {

        UserPoJo userPojo = new UserPoJo();
        //setting all the values by passing parameters
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus("Inactive");

        Response response =
                given().log().all()
                        .header("Authorization", "Bearer " + primaryToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .put("/" + id);

        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void verifyUserDeletedSuccessfully() {
        Response response =
                given().log().all()
                        .header("Authorization", "Bearer " + primaryToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("/" + id);

        response.prettyPrint();
        response.then().statusCode(204);
    }

    

        //"id": 5970615,
        //"name": "Neil",
        //"email": "nc63187@test.com",
        //"gender": "male",
        //"status": "active"
}