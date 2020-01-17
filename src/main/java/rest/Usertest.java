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
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class Usertest {

	
//	@Test
//    public void verificaNivel(){ // verifica nivel1 forma facil 
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
//    public void verificaOutrasformasN1(){ // verifica dados em nivel1 de formas diferentes 
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
//    public void verificaNivel2(){ // verifica dados em nivel2
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
	
/*//	@Test
//    public void verificaLista(){ // verifica lista que nao esteja na raiz
//		given()
//		.when()
//		  .get("http://restapi.wcaquino.me/users/3")
//		 
//		  .then()
//		    .statusCode(200)
//		    .body(containsString(("Ana")))
//		    .body("filhos",hasSize(2))
//		    .body("filhos[0].name",is("Zezinho"))
//		    .body("filhos[1].name",is("Luizinho"))
//		    .body("filhos.name",hasItem("Luizinho"))
//		    .body("filhos.name",hasItems("Zezinho","Luizinho")) ;
//}
//	
//	
//	@Test
//    public void verificaMsgErroUserInex(){ // verifica mensagem de erro 
//		given()
//		.when()
//		  .get("http://restapi.wcaquino.me/users/4")
//		 
//		  .then()
//		    .statusCode(404)
//		    .body("error", is("Usuário inexistente")); 
//      ;
//	}
//	
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
	}*/

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
		   * */ 
		    
		    .body("age.findAll{it <= 25}.size()", is(2)) 
    		.body("age.findAll{it <= 25 && it> 20}.size()", is(1)) 
    		.body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina"))      
    		.body("findAll{it.age <= 25 }[0].name", is("Maria Joaquina")) //transforma em objeto o que pega por isso consegue fazer com is,o indice 0 é para iterar na ordem de cima para baixo e pegar o primeiro regristro
    		.body("findAll{it.age <= 25 }[-1].name", is("Ana Júlia")) // Para pegar o ultimo registro da lista colocar -1, para que seja feita a iteração inversa 
    		.body("find{it.age <= 25 }.name", is("Maria Joaquina"))// tras apenas o primeiro registro que encontrar
    		.body("findAll{it.name.contains('n')}.name", hasItems("Maria Joaquina","Ana Júlia")) // verifica se contem N no nome da pessoa 
    		.body("findAll{it.name.length() > 10}.name", hasItems("João da Silva","Maria Joaquina"))// nomes com mais de 10 caracteres
    		.body("name.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))// transforma  em maiuscula
    		.body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))
    	  //.body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", allOf(hasItem("MARIA JOAQUINA"), hasSize(1)))//(não funciona por causa to tipo de argumento dos Matchers) procura todos nomes com maria e verificar o retorno
    	  //.body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", allOf((arrayContaining("MARIA JOAQUINA"), arrayWithSize(1))) // o mesmo de cima porem em array
    		.body("age.collect{it*2}",hasItems(60,50,40)) // multiplica a idade por 2
    		.body("id.max()",is(3)) //qual é o maior ID
    		.body("salary.min()",is(1234.5678f)) //qual é o mmenor salario
    		.body("salary.findAll{it != null}.sum()",is(closeTo(3734.5678f, 0.001))) //procura salario dif de null e soma todos arredondando "margem de erro de 3 casas decimais"
    		.body("salary.findAll{it != null}.sum()",allOf(greaterThan(3000d),lessThan(5000d))) //mesmo do de cima porem coloca a faixa de valores
    ;
	}

	@Test
    public void unirJsonPathComjava(){
		
		ArrayList<String> nomes =  // o retorno do extract é um List por isso criado aqui  - junção do que o path trouxe com java
		given()
		.when()
		  .get("http://restapi.wcaquino.me/users")
		 
		  .then()
		    .statusCode(200)
		    .body("$",hasSize(3))
		    .extract().path("name.findAll{it.startsWith('Maria')}") // extrai do jsonpath lista de nomes começados com maria 
		  
        ;
		
	Assert.assertEquals(1,nomes.size()); //valida quantidade de registros retornados
	Assert.assertTrue(nomes.get(0).equalsIgnoreCase("mAria Joaquina")); // valida se o primeiro regristro é maria ingoando o case do nome 
	Assert.assertEquals(nomes.get(0).toUpperCase(), "maria joaquina".toUpperCase());// valida se maria joaquina em maiusculo
	}

}   


