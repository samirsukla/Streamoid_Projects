package com.textsearch.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.textsearch.utility.ExtractInfoFromExcel;

public class GetActualProductList {
	static ExtractInfoFromExcel ext;
	static String queryName;
	static String product_id;
	static int product_count;
	
	public static String getJSONResponse(int rowNum) throws IOException {
		ext = new ExtractInfoFromExcel();
		queryName = ext.extractQuery(rowNum);
		//queryName = "plus size sweatshirts";

		URL url = new URL("http://search.staging.streamoid.com/textsearch/?query=" + queryName
				+ "&vendor=v_search_test_new&response=normalized&start=0&rows=5000&version=2&channel=best");
		String inline = "";
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();

		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode : " + responseCode);
		} else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();
			}

			sc.close();
		
		return inline;
		}
			
	}
	
	public List<String> getProductList(int rowNum) throws IOException, ParseException {
		//public void getProductList() throws IOException, ParseException {
	
			String response = getJSONResponse(rowNum);
			List<String> product_ids = new ArrayList<String>();
			
			product_ids = JsonPath.read(response, "$.products[0:10].unique_id");
			
			/*JSONParser jpar = new JSONParser();
			JSONObject jobj = (JSONObject) jpar.parse(response);
			JSONArray jarr = (JSONArray) jobj.get("products");

			for (int j = 0; j < 10; j++) {

				JSONObject jobj1 = (JSONObject) jarr.get(j);

				product_id = (jobj1.get("unique_id")).toString();
				product_ids.add(product_id);

			}*/
			//System.out.println(product_ids);

			return product_ids;
		}

	
	public int getProductCount(int rowNum) throws IOException {
		String response = getJSONResponse(rowNum);
		List<String> product_ids = new ArrayList<String>();
		
		product_ids = JsonPath.read(response, "$.products[*].unique_id");
		product_count = product_ids.size();
		return product_count;
		
	}
	
}
