package com.services.test;

import java.io.File;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NewTest {
  @Test
  public void test() {
	  
	  RequestSpecification req = RestAssured.given();
 	
	
	String resp= req.multiPart("uploadedfile", new File("/Users/samirsukla/Desktop/Animator_Images/makeup-catalog.jpg")).
			formParam("appID","3dfe253d3f564efa819c90beb032cdde").
			contentType("multipart/form-data").when().
			post("https://backend.animator.streamoid.com/animator/image-upload").then().statusCode(200).extract().response().asString();
	
	
	  
	  System.out.println("Response is " + resp);
	  System.out.println("Running Successfully");
  }


  
}
