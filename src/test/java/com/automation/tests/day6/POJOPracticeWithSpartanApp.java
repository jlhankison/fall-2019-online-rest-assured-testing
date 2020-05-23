package com.automation.tests.day6;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;



public class POJOPracticeWithSpartanApp {

    @BeforeAll
    public static void beforeAll(){

        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    }

    /**
     * {
     *   "gender": "Male",
     *   "name": "Nursultan",
     *   "phone": "123112312312"
     * }
     */

    @Test
    public void addSpartanTest(){
        Map<String, String> spartan = new HashMap<>();
        spartan.put("gender", "Male");
        spartan.put("name", "Nursultan");
        spartan.put("phone", "123112312312");

        RequestSpecification requestSpecification = given().
                                                    auth().basic("admin","admin").
                                                    contentType(ContentType.JSON).
                                                    accept(ContentType.JSON).
                                                    body(spartan);

        Response response =
                given().
                        auth().basic("admin","admin").
                        contentType(ContentType.JSON).
                        body(spartan).
                    when().
                        post("/spartans").prettyPeek();

        response.then().statusCode(201);
        response.then().body("success", is("A Spartan is Born!"));

        Spartan spartanResponse = response.jsonPath().getObject("data", Spartan.class);

        System.out.println(spartanResponse instanceof Spartan);


    }

    @Test
    @DisplayName("Retrieve existing user")
    public void updateSpartanTest(){
        int userToUpdate = 101;
        String name = "Nurs";

        Spartan spartan = new Spartan(name, "Male",123112312312L);

        Spartan spartanToUpdate = given().
                                    auth().basic("adimin", "admin").
                                    accept(ContentType.JSON).
                                when().
                                    get("/spartans/{id}", userToUpdate).as(Spartan.class);
        spartanToUpdate.setName(name);

        Response response = given().auth().basic("admin","admin").
                            contentType(ContentType.JSON).
                            body(spartanToUpdate).
                            when().
                            put("/spartans/{id}", userToUpdate).prettyPeek();

        response.then().statusCode(204);

        System.out.println("##############################################");


        given().auth().basic("admin","admin").
                when().
                    get("/spartans/{id}", userToUpdate).prettyPeek().
                then().
                    statusCode(200).body("name", is(name));



    }
}
