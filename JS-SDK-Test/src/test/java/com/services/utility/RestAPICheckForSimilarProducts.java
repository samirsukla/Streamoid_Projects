package com.services.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
				

				String similar_product_id = (jobj1.get("product_id")).toString();
				sim_prod_ids.add(similar_product_id);
				
				
//				if(j==4) {
//					break;
//				}
			}
    
    
		}
		//System.out.println(sim_prod_ids);
		return sim_prod_ids;

		}
	
	public List<String> similarProducts_abof(String product_id) throws Exception {
		
		
		List<String> sim_prod_ids = new ArrayList<String>();
		RestAssured.baseURI = "https://similar.service.streamoid.com/v1/similar/search/v_abof/";
		RequestSpecification request = RestAssured.given();
		Response resp = request.get(product_id);
		String inline = resp.asString();
		int responseCode = resp.getStatusCode();
		
//		URL url = new URL("https://similar.service.streamoid.com/v1/similar/search/v_abof/"+product_id);
//		String inline="";
//		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.connect();
//		int responseCode = conn.getResponseCode();
		
		if(responseCode!=200) {
			throw new RuntimeException("HttpResponseCode : " + responseCode);
		}
		else
		{
//			Scanner sc = new Scanner(url.openStream());
//			while(sc.hasNext()) {
//				inline+=sc.nextLine();
//			}
//			
//			
//			sc.close();
			
			JSONParser jpar = new JSONParser();
			JSONObject jobj = (JSONObject)jpar.parse(inline);
			
			JSONObject jobj1 = (JSONObject) jobj.get("data");
			JSONArray jarr = (JSONArray) jobj1.get("products");
			
			for(int j=0; j<jarr.size(); j++) {
				
				JSONObject jobj2 = (JSONObject) jarr.get(j);				
				

				String similar_product_id = (jobj2.get("productId")).toString();
				sim_prod_ids.add(similar_product_id);
				
				
//				if(j==4) {
//					break;
//				}
			}
    
    
		}
		System.out.println(sim_prod_ids);
		return sim_prod_ids;

		}
	
	}		



