package com.automation.tests.day2;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanTests {

    String BASE_URL = "http://3.90.112.152:8000";

    @Test
    @DisplayName("Get list of all spartans")
    public void getAllSpartans(){

        given().
                baseUri(BASE_URL).
                auth().
                basic("admin", "admin").
                when().
                get("/api/spartans").prettyPeek().
                then().
                statusCode(200);


    }

    @Test
    @DisplayName("Add new spartan")
    public void addSpartan(){

        Faker faker = new Faker();
        String name = faker.name().fullName();
        String phoneNumber = faker.phoneNumber().phoneNumber().toString().replace("-","");

        String body = "{\"gender\": \"Male\", \"name\": \""+name+"\", \"phone\": "+phoneNumber+"}";

        given().
                contentType(ContentType.JSON).
                auth().basic("admin", "admin").
                body(body).
                baseUri(BASE_URL).
            when().
                post("/api/spartans").prettyPeek().
            then().
                statusCode(201);
    }

    public void deleteSpartanTest(){
        given().
                auth().basic("admin", "admin").
                baseUri(BASE_URL).
            when().
                delete("/api/spartan/{id}", 672).
            then().
                statusCode(284);

    }
}
