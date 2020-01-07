package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers.*;
import org.junit.Test;
import io.restassured.RestAssured;



public class Usertest {

	
	@Test
    public void verificaNivel(){
		given()
		.when()
		 .get("http://restapi.wcaquino.me/users/1")
		 .then()
		 .statusCode(200)
		  .body("id", is(1))
		   .body(containsString(("Silva")))
		    .body("age",greaterThan(18));
		
	}

}


