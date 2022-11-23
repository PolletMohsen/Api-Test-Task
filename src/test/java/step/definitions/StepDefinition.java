package step.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;

public class StepDefinition {

    public static String BASEURL= "https://620e3da1585fbc3359db4edf.mockapi.io/api/v1";
    private Response response;

    @Given("Get Users API is provided")
    public void getUsersAPIIsProvided() {
        RestAssured.baseURI  = BASEURL;
    }

    @When("User call users API")
    public void userCallUsersAPI() {
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.when().get("/users");

    }

    @Then("OK status code should be returned")
    public void okStatusCodeShouldBeReturned() {
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(statusCode,200);

        ResponseBody body = response.getBody();

        System.out.println(body.jsonPath().get("id").toString());
    }

    @Given("Have a create user API")
    public void haveACreateUserAPI() {
        RestAssured.baseURI  = BASEURL;
    }

    @When("Call create API")
    public void callCreateAPI() {
        HashMap<String, Object> mainObject = new HashMap<String, Object>();
        HashMap<String, String> profileObject = new HashMap<String, String>();

        mainObject.put("name", "TEST");
        mainObject.put("username", "testUser");
        profileObject.put("firstName", "pollet");
        profileObject.put("lastName", "Mohsen");
        profileObject.put("order", "[]");
        mainObject.put("profile", profileObject);

        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.when().body(mainObject).post("/users");


    }

    @Then("User will be create with OK status code")
    public void userWillBeCreateWithOKStatusCode() {
        int statusCode = response.then().extract().statusCode();
        // Note that for now it returns 400 as "Max number of elements reached for this resource!"
        Assert.assertEquals(statusCode,201);
    }

    @Given("Get Users By Id API is provided")
    public void getUsersByIdAPIIsProvided() {
        RestAssured.baseURI  = BASEURL;
    }

    @When("User call get users API with id")
    public void userCallGetUsersAPIWithId() {

        String userID = "10";
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.when().get("/users/{id}",userID);

    }

    @Then("user name should be returned")
    public void userNameShouldBeReturned() {
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(statusCode,200);

        ResponseBody body = response.getBody();
        String userName = body.jsonPath().get("profile.firstName").toString();
        Assert.assertNotNull(userName);
        Assert.assertEquals(userName,"Claire");

    }
}
