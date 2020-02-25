package rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import io.restassured.http.ContentType;

public class VerbosTest {
	
	@Test
	public void SalvarUser() {
	
	given()
	 .log().all()
	 .contentType("application/json") // informa ao java para não formatar pro seu padrao a string passada no body,mantela como json
	  .body("{\"name\":\"Julino\", \"age\":58}")
	  
   .when()
		 .post("http://restapi.wcaquino.me/users") // insere com o verbo post o valor
	 .then()
		.log().all() // captura todo o log da requisição
		.statusCode(201) // verifica se o status cod é 201- inserido com sucesso
	    .body("id",Matchers.notNullValue());// verificar se o ID nao retornou vazio
		
	 ;
	
	}
	
	
	@Test
	public void SalvarUserVazio() {
	
	given()
	 .log().all()
	 .contentType("application/json") 
	  .body("{\"age\": 58}")
	  
   .when()
		 .post("http://restapi.wcaquino.me/users") // insere com o verbo post o valor
	 .then()
		.log().all() // captura todo o log da requisição
		.statusCode(400) // verifica se o status cod é 201- inserido com sucesso
	    .body("id",is(nullValue()))// verificar se o ID nao retornou vazio
		.body( "error", is("Name é um atributo obrigatório"))
		
	 ;
	
	}
	
	@Test
	public void PostXml() {
		
		given()
		 .log().all()
		 .contentType(ContentType.XML) // enum  informando o tipo do formato do que será passado
		  .body("<user ><name>Jose da Silva</name><age>58</age></user>")
		  
	   .when()
			 .post("http://restapi.wcaquino.me/usersXML") // insere com o verbo post o valor
		 .then()
			.log().all() // captura todo o log da requisição
			.statusCode(201) // verifica se o status cod é 201- inserido com sucesso
		    .body("id",Matchers.notNullValue())// verificar se o ID nao retornou vazio
		    .body("user.@id",Matchers.notNullValue())
		    .body("user.name",is("Jose da Silva"))	
		    .body("user.age",is("58"))
		 ;
		
		}
		
	
	@Test
	public void AlteraUser() {
	
	given()
	 .log().all()
	 .contentType("application/json") 
	  .body("{\"name\":\"Jose Alterado\", \"age\":80}")
	  
   .when()
		 .put("http://restapi.wcaquino.me/users/1") // URL especifica put deve ser usado nesta url se não nao funciona
	 .then()
		.log().all() 
		.statusCode(200) 
	    .body("id",is(1))
	    .body("name",is("Jose Alterado"))
	    .body("age",is(80))
	 ;
	
	}
	

	@Test
	public void Urlparametriz() {
	
	given()
	 .log().all()
	 .contentType("application/json") 
	  .body("{\"name\":\"Jose Alterado\", \"age\":80}")
	  
   .when()
		 .put("http://restapi.wcaquino.me/{entidade}/{userID}","users", "1") // URL especifica put deve ser usado nesta url se não nao funciona
	 .then()
		.log().all() 
		.statusCode(200) 
	    .body("id",is(1))
	    .body("name",is("Jose Alterado"))
	    .body("age",is(80))
	 ;
	
	}

	@Test
	public void Urlparametrizpart2() {
	
	given()
	 .log().all()
	 .contentType("application/json") 
	  .body("{\"name\":\"Jose Alterado\", \"age\":80}")
	  .pathParam("entidade", "users") // passa parametro chave,valor para url parametrizada 
	  .pathParam("userID", "1")
   .when()
		 .put("http://restapi.wcaquino.me/{entidade}/{userID}") // URL especifica put deve ser usado nesta url se não nao funciona
	 .then()
		.log().all() 
		.statusCode(200) 
	    .body("id",is(1))
	    .body("name",is("Jose Alterado"))
	    .body("age",is(80))
	 ;
	
	}

	
	@Test
	public void DeleteUser() {
	
	given()
	 .log().all()
	  
   .when()
		 .delete("http://restapi.wcaquino.me/users/1") // URL especifica put deve ser usado nesta url se não nao funciona
	 .then()
		.log().all() 
		.statusCode(204) 
	 ;
	
	}

	
	@Test
	public void DeleteUserInvalido() {
	
	given()
	 .log().all()
	  
   .when()
		 .delete("http://restapi.wcaquino.me/users/100") // URL especifica put deve ser usado nesta url se não nao funciona
	 .then()
		.log().all() 
		.statusCode(400)
		.body("error",is( "Registro inexistente"))
	 ;
	
	}
	
	
	@Test
	public void SalvarUserMap() {
	Map<String, Object> param = new  HashMap<String, Object>();//cria interface Map chave valor
	param.put("name", "Julino Map");	// passa paramtro para o Map chave e valor
	param.put("age", 60);	
		
		given()
		 .log().all()
		 .contentType("application/json") 
		  .body(param)  //usa no body os parametros passado pelo map
		  
	   .when()
			 .post("http://restapi.wcaquino.me/users") 
		 .then()
			.log().all() 
			.statusCode(201) // verifica se o status cod é 201- inserido com sucesso
		    .body("name",is("Julino Map"))
		 ;
		
		}
		 
	@Test
	public void SalvarUserObjt() {
	
		Users user = new Users("Juino Obj",34); // classe bean nde capturamos o objeto java
		given()
		 .log().all()
		 .contentType("application/json") 
		  .body(user)  //usa no body o objeto java transformando em  json
		  
	   .when()
			 .post("http://restapi.wcaquino.me/users") 
		 .then()
			.log().all() 
			.statusCode(201) // verifica se o status cod é 201- inserido com sucesso
		    .body("name",is("Juino Obj"))
		 ;
		
		}
	
	@Test
	@SuppressWarnings("deprecation")
	public void desSerializaObjt() {
		
		Users user = new Users("Juino DesSerial",34); // classe bean nde capturamos o objeto java
		Users inserido = given()
		 .log().all()
		 .contentType("application/json") 
		  .body(user)  //usa no body o objeto java transformando em  json
		  
	   .when()
			 .post("http://restapi.wcaquino.me/users") 
		 .then()
			.log().all() 
			.statusCode(201) // verifica se o status cod é 201- inserido com sucesso
		    .extract().body().as(Users.class)
			
		 ;
		System.out.println(inserido);
		Assert.assertThat(inserido.getId(), notNullValue());
		Assert.assertEquals(inserido.getName(),is("Juino DesSerial"));
		Assert.assertThat(inserido.getAge(), is(35));
		
		
		}

	
	
	@Test
	public void serializaXml() {
		Users user = new Users("Juino XML",43); // classe bean nde capturamos o objeto java
		
			
		 given()
	    .log().all()
		 .contentType(ContentType.XML) // enum  informando o tipo do formato do que será passado
		  .body(user)
		  
	   .when()
			 .post("http://restapi.wcaquino.me/usersXML") // insere com o verbo post o valor
		 .then()
			.log().all() // captura todo o log da requisição
			.statusCode(201) // verifica se o status cod é 201- inserido com sucesso
		    .body("id",Matchers.notNullValue())// verificar se o ID nao retornou vazio
		    .body("user.@id",Matchers.notNullValue())
		    .body("user.name",is("Juino XML"))	
		    .body("user.age",is("43"))
		 ;
		
		}
		
	@SuppressWarnings("deprecation")
	@Test //não funcionou
	public void DesserializaXml() {
		Users user = new Users("Juino desXML",43); // classe bean nde capturamos o objeto java
		
			
		 Users inserido = given()
	     .log().all()
		 .contentType(ContentType.XML) // enum  informando o tipo do formato do que será passado
		  .body(user)
		  
	   .when()
			 .post("http://restapi.wcaquino.me/usersXML") // insere com o verbo post o valor
		 .then()
			.log().all() // captura todo o log da requisição
			.statusCode(201) 
			.extract().body().as(Users.class)//faz a extração do body
		 
			;
		    Assert.assertThat(inserido.getId(), notNullValue());
			Assert.assertEquals(inserido.getName(),is("Juino desXML"));
			Assert.assertThat(inserido.getAge(), is(43));
				
	
	}
		
	
}
	


