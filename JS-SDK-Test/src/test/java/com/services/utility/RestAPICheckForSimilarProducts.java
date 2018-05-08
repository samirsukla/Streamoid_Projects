package com.services.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RestAPICheckForSimilarProducts {
	
	public List<String> similarProducts(String product_id,String clientName) throws Exception {
		
	
		List<String> sim_prod_ids = new ArrayList<String>();
		URL url = new URL("https://similar.service.streamoid.com/insecure/similar/"+clientName+"/"+product_id+"/normalized");
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
			
			JSONArray jarr = (JSONArray) jobj.get("data");
			
			for(int j=0; j<jarr.size(); j++) {
				
				JSONObject jobj1 = (JSONObject) jarr.get(j);				
				
				long similar_product_id = (Long) jobj1.get("product_id");
				sim_prod_ids.add(Long.toString(similar_product_id));
				
				
				if(j==4) {
					break;
				}
			}
    
    
		}
		//System.out.println(sim_prod_ids);
		return sim_prod_ids;

		}
	}		



