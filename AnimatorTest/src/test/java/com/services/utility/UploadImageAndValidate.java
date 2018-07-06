package com.services.utility;

import java.io.File;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class UploadImageAndValidate {
	static ImageDownloadFromURL imgdwnld;
	static GetSourceToken getsrc;
	static WebDriver driver;
	
  public boolean uploadImageAndCheckMatchEntry( String imageName,String token) throws ParseException, IOException {
	 
	  
	  RequestSpecification req = RestAssured.given();
	 	
		
		String resp= req.multiPart("uploadedfile", new File("/Users/samirsukla/Desktop/Animator_Images/"+imageName)).
				formParam("appID",token).
				contentType("multipart/form-data").when().
				post("https://backend.animator.streamoid.com/animator/image-upload").then().statusCode(200).extract().response().asString();
			JSONParser jpar = new JSONParser();  
			JSONObject jobj = (JSONObject)jpar.parse(resp);
			
			JSONArray jarr = (JSONArray) jobj.get("matches");
			
			if(jarr.size()>=0) {
				return true;
			}
			else {
				return false;
			}
	  
			
	  }
}

