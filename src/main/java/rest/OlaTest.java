package rest;

import static io.restassured.RestAssured.*;//import statico 
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaTest {

	
	
	@Test
	public void testeOla(){
	
		Response response = RestAssured.request(Method.GET,"http://restapi.wcaquino.me/ola");
        Assert.assertTrue("Esperava ola ",(response.getBody().asString().equals("Ola Mundo!")));
        Assert.assertEquals(200,response.statusCode());
	
        ValidatableResponse validacao = response.then();
	    validacao.statusCode(200);
	}
   
	@Test
	public void outrasFormasrest(){
	
		request(Method.GET,"http://restapi.wcaquino.me/ola");
        get("http://restapi.wcaquino.me/ola").then().statusCode(200); // Uma linha para as 3 de cima
	    
      //dado,quando então
	    given()
	    .when()
	     .get("http://restapi.wcaquino.me/ola")
	       .then()
	         .statusCode(200); 
	    
	}

	@Test
	public void metodoHamcrest(){
	
		Assert.assertThat("Maria",Matchers.is("Maria")); //verificar se é igual
		Assert.assertThat("Maria",Matchers.isA(String.class)); // verifica o tipo
		Assert.assertThat(128, Matchers.greaterThan(112)); // verificar se é maior que o valor do passado
		Assert.assertThat(128, Matchers.lessThan(169)); // verificar se é menor que o valor do passado
		
		//negativas
//		Assert.assertThat(128, Matchers.lessThan(12));
//		Assert.assertThat(128, Matchers.greaterThan(159));
//	
      List <Integer> impar = Arrays.asList(1,3,5,7,9);
		Assert.assertThat(impar, Matchers.hasSize(5));  //tamanho
		Assert.assertThat(impar, Matchers.contains(1,3,5,7,9)); // contem conjunto completo
		Assert.assertThat(impar, Matchers.containsInAnyOrder(1,3,5,9,7));  // se contem conjunto em qualquer ordem
		Assert.assertThat(impar, Matchers.hasItem(9)); // se contem o item
		Assert.assertThat(impar, Matchers.hasItems(5,7)); // se contem os items
		
		//Assert.assertThat("Maria", Matchers.is(not("João"))); // diferente de
	}
	
	@Test
	public void validaBody(){
		 given()
		    .when()
		     .get("http://restapi.wcaquino.me/ola")
		       .then()
		         .statusCode(200)
				.body(is(("Ola Mundo!"))) // se é ola mundo
				.body(containsString(("Mundo"))) // se contem a palavra mundo
				.body(is(not(nullValue()))); // se não é vazio
	
	}


}
