package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void Test1() {
        List<Integer> listOfIds = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void Test2() {
        List<String> listOfName = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Names are : " + listOfName);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void Test3() {
        String name = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Name are : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4.Extract the names of all object whose status = inactive
    @Test
    public void Test4() {
        List<String> listName = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Names are : " + listName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void Test5() {
        List<String> listGender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Gender are : " + listGender);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void Test6() {
        List<String> listName = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Names are : " + listName);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void Test7() {
        List<String> listEmail = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Emails are : " + listEmail);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void Test8() {
        List<Integer> listid = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listid);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void Test9() {
        List<String> listStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Status are : " + listStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void Test10() {
        String email = response.extract().path("[4].email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get of email are : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5914047
    @Test
    public void Test11(){

        String gender = response.extract().path("[18].gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get of gender are : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
