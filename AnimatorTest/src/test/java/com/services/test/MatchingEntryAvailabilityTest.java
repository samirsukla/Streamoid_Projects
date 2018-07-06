package com.services.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.services.utility.GetSourceToken;
import com.services.utility.ImageDownloadFromURL;
import com.services.utility.UploadImageAndValidate;
import com.services.utility.UploadResultonExcel;

public class MatchingEntryAvailabilityTest {
	static ImageDownloadFromURL imgdwnld;
	static UploadImageAndValidate uploadimg;
	static boolean status;
	static GetSourceToken getSrcToken;
	static UploadResultonExcel uploadexcel;
  @Test
  public void getMatchingEntry() throws ParseException, IOException {
	  getSrcToken = new GetSourceToken();
		String token = getSrcToken.getToken();
	  
	  URL url = new URL("https://backend.animator.streamoid.com/images/query.php?action=filterImages&source=sales_demo");
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
				
				String clickableImageURL = (jobj1.get("clickable_img")).toString();
				String id = (jobj1.get("id")).toString();
				imgdwnld = new ImageDownloadFromURL();
				String imageName = imgdwnld.downloadImage(clickableImageURL);
				uploadimg = new UploadImageAndValidate();
				
				status = uploadimg.uploadImageAndCheckMatchEntry(imageName,token);
				System.out.println(status);
				uploadexcel = new UploadResultonExcel();
				if(status) 
				{
					uploadexcel.writeResultToExcel(clickableImageURL, id, "passed");
				}
				else
				{
					uploadexcel.writeResultToExcel(clickableImageURL, id, "failed");
					
				}
				}
  
  
		}
		
  }
}
