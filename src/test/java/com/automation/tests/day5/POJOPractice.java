package com.automation.tests.day5;
import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class POJOPractice {

    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

    }

    @Test
    public void getUser(){
        Response response =   given().
                auth().
                basic("admin", "admin").
                when().
                get("/spartans/{id}", 393).prettyPeek();

        Spartan spartan = response.as(Spartan.class);

        System.out.println(spartan);
    }

    @Test
    public void addUser(){
        Spartan spartan = new Spartan("Jordan", "Male", 7153038637L);
        Gson gson = new Gson();
        String pojoAsJSON = gson.toJson(spartan);
        System.out.println(pojoAsJSON);

        Response response = given().
                                    auth().basic("admin","admin").
                                    contentType(ContentType.JSON).
                                    body(spartan).
                            when().
                                post("/spartans").prettyPeek();
    }
}
