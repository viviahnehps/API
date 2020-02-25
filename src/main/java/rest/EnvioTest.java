package rest;

import static io.restassured.RestAssured.given;
import org.junit.Test;

import io.restassured.http.ContentType;

public class EnvioTest {

	@Test
	public void EnviarDados() {
		
			
		 given()
	    .log().all()
		  
	   .when()
			 .get("http://restapi.wcaquino.me/v2/users?format=xml") //  query enviada solicitando uma resposta formato xml
		 .then()
			.log().all() 
			.statusCode(200) 
		    .contentType(ContentType.XML)
			;
		
		}
	
	@Test
	public void EnviarDadosviaParm() {
			
		 given()
		 .queryParam("format","json") // envio da query por parametro é possivel enviar N parametros
		 .log().all()
		  
	   .when()
			 .get("http://restapi.wcaquino.me/v2/users")
		 .then()
			.log().all() 
			.statusCode(200) 
		    .contentType(ContentType.JSON)
			;
		
		}

	@Test
	public void EnviarDadosHeader() {
			
		 given()
	     .log().all()
		 .accept(ContentType.HTML) // O que qero da resposta,o tipo de resposta- html 
	   .when()
			 .get("http://restapi.wcaquino.me/v2/users") //  query enviada solicitando uma resposta formato xml
		 .then()
			.log().all() 
			.statusCode(200) 
		    .contentType(ContentType.HTML)
			;
		
		}
	

	
}
