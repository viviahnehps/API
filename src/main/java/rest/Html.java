package rest;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.http.ContentType;

public class Html {

	@Test
	public void Buscahtml() {
			
		 given()
	     .log().all()
		 //.accept(ContentType.HTML) // O que qero da resposta,o tipo de resposta- html 
	   .when()
			 .get("http://restapi.wcaquino.me/v2/users") //  query enviada solicitando uma resposta formato xml
		 .then()
			.log().all() 
			.statusCode(200) 
		    .contentType(ContentType.HTML)
			.body("html.body.div.table.tbody.tr.size()",is(3)) // conta a qtde de registros
			.body("html.body.div.table.tbody.tr[1].td[2]",is("25")) //localiza na segunda linha tr2 a celula 3 td2
			.appendRootPath("html.body.div.table.tbody") //	
			.body("tr.find{it.toString().startsWith('2')}.td[1]", is("Maria Joaquina"))
		    ;
		
		}
	
	
	@Test
	public void BuscahtmlXpath() {
			
		 given()
	     .log().all()
		 //.accept(ContentType.HTML) // O que qero da resposta,o tipo de resposta- html 
	   .when()
			 .get("http://restapi.wcaquino.me/v2/users?format=clean") //  query enviada solicitando uma resposta formato xml
		 .then()
			.log().all() 
			.statusCode(200) 
		    .contentType(ContentType.HTML)
			.body(hasXPath("count(//table/tr)",is("4")))
			.body(hasXPath("//td[text()='2']/../td[2]",is("Maria Joaquina")))
			
					
					
		;
		
		}
	
	
	
}
