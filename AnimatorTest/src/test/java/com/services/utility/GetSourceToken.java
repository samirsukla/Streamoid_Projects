package com.services.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetSourceToken {
	
 
  public String getToken(Properties prop) throws ParseException, IOException {
	  
		URL url = new URL("https://backend.animator.streamoid.com/images/query.php?action=getSources");
		String inline="";
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();
		
		if(responseCode!=200) {
			throw new RuntimeException("HttpResponseCode : " + responseCode);
		}
		else
		{
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext()) {
				inline+=sc.nextLine();
			}
			
			
			sc.close();
			
			JSONParser jpar = new JSONParser();
			JSONObject jobj = (JSONObject)jpar.parse(inline);
			
			JSONObject jobj1 = (JSONObject) jobj.get("data");
			String tokenId = (jobj1.get(prop.getProperty("url1"))).toString();
			
			
		//System.out.println(tokenId);
		return tokenId;
  }

}
}