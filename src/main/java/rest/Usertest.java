package rest;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class Usertest {

	
//	@Test
//    public void verificaNivel(){
//		given()
//		.when()
//		 .get("http://restapi.wcaquino.me/users/1")
//		 .then()
//		 .statusCode(200)
//		  .body("id", is(1))
//		   .body(containsString(("Silva")))
//		    .body("age",greaterThan(18))
//		    ;
//		
//	}
//	
//	
//	@Test
//    public void verificaOutrasformasN1(){
//		Response response = RestAssured.request(Method.GET , "http://restapi.wcaquino.me/users/1");
//		
//		
//		 //path
//		Assert.assertEquals(new Integer (1),(response.path("id")));
//		Assert.assertEquals(new Integer (1),(response.path("%s","id")));
//		
//		//jsonPath
//		JsonPath jpat =new JsonPath(response.asString()); // passa o corpo da api totalmente
//		Assert.assertEquals(1,jpat.getInt("id"));
//		
//		//from
//		int id = from(response.asString()).getInt("id");
//		Assert.assertEquals(1,id);
//		
//	}
//
//	
//	@Test
//    public void verificaNivel2(){
//		given()
//		.when()
//		  .get("http://restapi.wcaquino.me/users/2")
//		 
//		  .then()
//		    .statusCode(200)
//		    .body(containsString(("Joaquina")))
//		    .body("endereco.rua", is("Rua dos bobos")) //acessa nivel 2
//		    ;
//		
//	}
//	
	
	@Test
    public void verificaLista(){
		given()
		.when()
		  .get("http://restapi.wcaquino.me/users/3")
		 
		  .then()
		    .statusCode(200)
		    .body(containsString(("Ana")))
		    .body("filhos",hasSize(2))
		    .body("filhos[0].name",is("Zezinho"))
		    .body("filhos[1].name",is("Luizinho"))
		    .body("filhos.name",hasItem("Luizinho"))
		    .body("filhos.name",hasItems("Zezinho","Luizinho")) ;
}
	
	
	@Test
    public void verificaMsgErroUserInex(){
		given()
		.when()
		  .get("http://restapi.wcaquino.me/users/4")
		 
		  .then()
		    .statusCode(404)
		    .body("error", is("Usuário inexistente")); 
      ;
	}
	
	@Test
    public void verificaListaRaiz(){ // Verifica dados buscando na lista raiz
		given()
		.when()
		  .get("http://restapi.wcaquino.me/users")
		 
		  .then()
		    .statusCode(200)
		    .body("$",hasSize(3))
		    .body("",hasSize(3)) 
		    .body("name", hasItems("João da Silva","Maria Joaquina","Ana Júlia")) 
		    .body("age[1]",is(25))
		    .body("filhos.name",hasItem(Arrays.asList("Zezinho","Luizinho")))
		    .body("salary",contains(1234.5678f,2500,null));
	        ;
	}

	@Test
    public void verificaAvacada(){
		given()
		.when()
		  .get("http://restapi.wcaquino.me/users")
		 
		  .then()
		    .statusCode(200)
		    .body("$",hasSize(3))
		   
		  /*findAll- efetua busca na lista Json , <= condicional do que querobusca
		   * it - é o iterador dentro da lista 
		   * <= operador logico do que quero busca
		 */
		  
		    .body("age.findAll{it <=25}", is(2)) 

;
	}

}


