package com.services.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RestAPICheckForOutfitter {
	
	public List<String> outfitterProducts(String product_id,String clientName) throws Exception {
		
	
		List<String> out_prod_ids = new ArrayList<String>();
		URL url = new URL("https://outfitter.service.streamoid.com/insecure/outfitter/"+clientName+"/"+product_id+"/normalized");
		
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
			
			
			
				
				
				JSONArray jarr1 = (JSONArray) jarr.get(0);
				
				for(int j=0;j<jarr1.size();j++) {
				JSONObject jobj2 = (JSONObject) jarr1.get(j);

				String outfitter_product_id = (jobj2.get("product_id")).toString();
				out_prod_ids.add(outfitter_product_id);
				
				
				if(j==4) {
					break;
				}
				}
			}
		
    
		
		//System.out.println(out_prod_ids);
		return out_prod_ids;

		}
		
}


