package com.TextSearch.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.TextSearch.utility.ExtractInfoFromExcelSheet;

public class TriggerTextSearchAPI {
	static ExtractInfoFromExcelSheet ext;
	static String queryName;

	static String product_id;

	public Map<String, Double> triggerSearchAPI(int rowNum) throws IOException, ParseException {

		ext = new ExtractInfoFromExcelSheet();
		queryName = ext.extractQuery(rowNum);

		Map<String, Double> product_ids = new LinkedHashMap<String, Double>();

		URL url = new URL("http://search.staging.streamoid.com/textsearch/?query=" + queryName
				+ "&vendor=v_search_test_new&response=normalized&start=0&rows=20&version=2");
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

			JSONParser jpar = new JSONParser();
			JSONObject jobj = (JSONObject) jpar.parse(inline);
			JSONArray jarr = (JSONArray) jobj.get("products");

			for (int j = 0; j < 10; j++) {

				JSONObject jobj1 = (JSONObject) jarr.get(j);

				product_id = (jobj1.get("unique_id")).toString();
				product_ids.put(product_id,  -1.0);

			}
			// Iterator it1 = product_ids.entrySet().iterator();
			// while(it1.hasNext()) {
			// Map.Entry pair = (Entry) it1.next();
			// System.out.println(pair.getKey() +" = " + pair.getValue());
			// }

		}

		return product_ids;

	}
}
