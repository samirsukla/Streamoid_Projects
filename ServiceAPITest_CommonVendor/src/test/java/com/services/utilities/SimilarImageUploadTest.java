package com.services.similartest;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class SimilarImageUploadTest {
	static String url = "https://similar.service.streamoid.com/insecure/similar/vanheusen/image-upload/normalized";
	static String imagePath = "src/test/resources/temp15.jpg";
  @Test
  public void similarImageUpload() throws FileNotFoundException{
	  
	 
		RequestSpecification req = RestAssured.given();
		 	
			
			String instantResp= req.multiPart("image", new File(imagePath)).
					contentType("multipart/form-data").when().
					post(url).then().statusCode(200).extract().response().asString();
			
			//System.out.println(instantResp);
			
			InputStream inputStream = new FileInputStream(new File ("src/test/resources/SimilarImageUpload.json"));
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			Schema schema = SchemaLoader.load(rawSchema);
			try {
			  schema.validate(new JSONObject(instantResp));
			  System.out.println("Validation Successful");
			} 
			catch (ValidationException e)  {
//			  System.out.println(e.getMessage());
//			  System.out.println(e.getAllMessages());
//			  e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
			 
			  Assert.assertTrue(false, e.getAllMessages().toString());;
	  
			}
	
	  
  }
}
