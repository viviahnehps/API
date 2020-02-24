package rest;

import static io.restassured.RestAssured.given;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.github.fge.jsonschema.messages.JsonSchemaValidationBundle;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaTest {

	
	@Test
	public void testaXSD() {// compara um xsd para ver se ta no formato correto de xml
			
		 given()
	     .log().all()
   	   .when()
			 .get("http://restapi.wcaquino.me/usersXML") // 
		 .then()
			.log().all() 
			.statusCode(200) //mais é 400 api fora do padrão 
		    .body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"))//mostra o arquivo que deve ser comparado		
					
		;
		
		}

	
	@Test(expected = SAXException.class)
	public void testaXSDInvalido() {// compara um xsd para ver se ta no formato correto de xml
			
		 given()
	     .log().all()
   	   .when()
			 .get("http://restapi.wcaquino.me/invalidUsersXML") // 
		 .then()
			.log().all() 
			.statusCode(200) //mais é 400 api fora do padrão 
		    .body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"))//mostra o arquivo que deve ser comparado		
					
		;
		
		}
	

	@Test
	public void testaJsn() {// compara um xsd para ver se ta no formato correto de xml
			
		 given()
	     .log().all()
   	   .when()
			 .get("http://restapi.wcaquino.me/users") // 
		 .then()
			.log().all() 
			.statusCode(200)  
		    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"))//mostra o arquivo que deve ser comparado		
					
		;
		
		}


}
