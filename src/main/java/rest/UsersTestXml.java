package rest;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;

import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.internal.path.xml.NodeImpl;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UsersTestXml {

/*	@Test
	public void TralhandoXml(){
	
		given()
		  .when()
		    .get("http://restapi.wcaquino.me/usersXML/3")
		  .then()
		    .statusCode(200)
		    .rootPath("user") //definição de nó raiz do XML (user se repete)
		    .body("name", is("Ana Julia")) //busca o nome no xml
		    
		    .rootPath("user.filhos") //segundo nó do xml 
		    .detachRootPath("filhos") //retira mais um nível do nó
		    .body("@id", is("3")) //busca atributo ID sempre por "" pois todo valor vindo do XML é string, o @referencia o id
		    
		    .appendRootPath("filhos") //coloca mais um nivel no nó
		    .body("name.size()", is(2)) //verifica quantidade de filhos
		    .body("name[0]", is("Zezinho"))//como no json busca pelo indice o nome do filho
		    .body("name[1]", is("Luizinho"))
		    .body("name", hasItem("Zezinho"))//busca nome sem referencia direta ao indice
		    .body("name", hasItems("Zezinho","Luizinho"))//busca todos os nomes 
		    
		    
     	;
	   }

	@Test
	public void pesquisaAvancadaXML() {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		given()
		  .when()
		     .get("http://restapi.wcaquino.me/usersXML/")
		        .then()
		          .statusCode(200)
		          
		          .body("users.user.size()", is(3))
		          .body("users.user.findAll{it.age.toInteger() <= 25}.size()",is(2))
		          .body("users.user.@id", hasItems("1","2","3"))
		          .body("users.user.find{it.age == 25}.name", is("Maria Joaquina"))
		          .body("users.user.findAll{it.name.toString().contains('n')}.name", hasItems("Maria Joaquina","Ana Julia"))
		          .body("users.user.salary.find{it != null}.toDouble()", is(1234.5678d))
		          .body("users.user.age.collect{it.toInteger() * 2 }", hasItems(40,50,60))
		          .body("users.user.name.findAll{it.toString().startsWith('Maria')}.collect{it.toString().toUpperCase()}", is("MARIA JOAQUINA"))
		          
		          

		;
	}
	*/
	@Test
	public void pesquisaAvancadaXMLeJava() {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	//	String nome = given()
		
	ArrayList<NodeImpl> nomes = given()			
		
			.when()
		     .get("http://restapi.wcaquino.me/usersXML/")
		     
		        .then()
		          .statusCode(200)
		        //  .extract().path(("users.user.name.findAll{it.toString().startsWith('Maria')}"))
		          .extract().path(("users.user.name.findAll{it.toString().contains('n')}"))
		      	  ;
		
	//	Assert.assertEquals("Maria Joaquina".toUpperCase(), nome.toUpperCase());
		Assert.assertEquals(2, nomes.size());
		Assert.assertEquals("Maria Joaquina".toUpperCase(), nomes.get(0).toString().toUpperCase());
		Assert.assertTrue("ANA JULIA".equalsIgnoreCase(nomes.get(1).toString()))
		
		;
	}
	
	
	

	@Test
	public void pesquisaAvancadaXpath() {
                                     
	       given()			
			.when()
		     .get("http://restapi.wcaquino.me/usersXML/")
		        .then()
		          .statusCode(200)
		         .body(hasXPath("count(/users/user)", is("3"))) // no xpath o matcher sempre vai dentro do xpath nao fora como no xml
		         .body(hasXPath("/users/user[@id ='1']")) 
		         .body(hasXPath("//user[@id='3']"))
		         .body(hasXPath("//name[text()='Luizinho']/../../name", is("Ana Julia"))) // barra 2x desce niverl  ponto2x sobe nivel
		         .body(hasXPath("//name[text()='Ana Julia']/following-sbling:: filhos", allOf(containsString("Zezinho"),containsString("Luizinho"))))
		         
		          ;
	}
	
	
	
	
}