package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class AutoTest {

	@Test
	public void AutenticaPublc() {
			
		 given()
	     .log().all()
   	   .when()
			 .get("https://swapi.co/api/1") //  query enviada solicitando uma resposta formato xml
		 .then()
			.log().all() 
			.statusCode(200) //mais é 400 api fora do padrão 
		    .body("error", is("Arquivo não enviado"))		
					
		;
		
		}

	
	
}
