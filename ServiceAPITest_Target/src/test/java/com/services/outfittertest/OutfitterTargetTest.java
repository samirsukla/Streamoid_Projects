package com.services.outfittertest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OutfitterTargetTest {
	static String url = "https://outfitter.service.streamoid.com/v1/outfitter/target/52929107";
  @Test
  public void outfitterAPITarget() throws ParseException, IOException {
	  
	  	URL outfitterUrl = new URL(url);
		String instantResp="";
		HttpURLConnection conn = (HttpURLConnection)outfitterUrl.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();
		//System.out.println("Response Code is "+ responseCode);
		if(responseCode!=200) {
			throw new RuntimeException("HttpResponseCode : " + responseCode);
		}
		else
		{
			Scanner sc = new Scanner(outfitterUrl.openStream());
			while(sc.hasNext()) {
				instantResp+=sc.nextLine();
			}
			sc.close();
			InputStream inputStream = new FileInputStream(new File ("src/test/resources/OutfitterTargetPID.json"));
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
			 
			  Assert.assertTrue(false,e.getAllMessages().toString());
	  
			}
		}
  }
	
}