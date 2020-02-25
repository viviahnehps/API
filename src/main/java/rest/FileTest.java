package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import java.io.File;
import org.junit.Test;


public class FileTest {

	@Test
	public void EnviaArqFake() {
			
		 given()
	     .log().all()
   	   .when()
			 .post("http://restapi.wcaquino.me/upload") //  query enviada solicitando uma resposta formato xml
		 .then()
			.log().all() 
			.statusCode(404) //mais é 400 api fora do padrão 
		    .body("error", is("Arquivo não enviado"))		
					
		;
		
		}
	
 
  @Test
   public void EnviaArq() { //Não carregou o arquivo
	
	 given()
      .log().all()
      .multiPart("arquivoVi", new File("src/main/resources/testeErr.pdf")) //nome e caminho do arquivo
    
      .when()
		 .post("http://restapi.wcaquino.me/") 
	 .then()
		.log().all() 
		.statusCode(200) //mais é 400 API fora do padrão 
	    //.body("name", is("testeErr.pdf"))		
				
	;
	
	}

}