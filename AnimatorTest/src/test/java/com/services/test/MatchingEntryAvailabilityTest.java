package com.services.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.services.utility.DeleteExtraRows;
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
	static DeleteExtraRows deleteRows;
	int passCount=0;
	int failCount=0;
	Properties prop;
	
	@BeforeClass
	public void initialSetup() throws IOException, ParseException {
		prop = new Properties();
		FileInputStream in = new FileInputStream("src/test/resources/SourceName.properties");
		prop.load(in);
	    getSrcToken = new GetSourceToken();
		
		uploadexcel = new UploadResultonExcel();
		deleteRows = new DeleteExtraRows();
		
		
	}
  @Test
  public void getMatchingEntry() throws ParseException, IOException {
	  
	  String token = getSrcToken.getToken(prop);
	    String sourceName = prop.getProperty("url1");
	    URL url = new URL("https://backend.animator.streamoid.com/images/query.php?action=filterImages&source="+sourceName);
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
			int totalImage = jarr.size();
			System.out.println(totalImage);
			System.out.println("===========================Test Started===========================");
			
			for(int j=0; j<totalImage; j++) {
				
				JSONObject jobj1 = (JSONObject) jarr.get(j);				
				
				String clickableImageURL = (jobj1.get("clickable_img")).toString();
				String id = (jobj1.get("id")).toString();
				
				imgdwnld = new ImageDownloadFromURL();
				String imageName = imgdwnld.downloadImage(clickableImageURL);
				uploadimg = new UploadImageAndValidate();
				
				status = uploadimg.uploadImageAndCheckMatchEntry(imageName,token);
				System.out.println(status);
				
				if(status) 
				{
					uploadexcel.writeResultToExcel(clickableImageURL, id, "passed",j);
					passCount++;
				}
				else
				{
					uploadexcel.writeResultToExcel(clickableImageURL, id, "failed",j);
					failCount++;
					
				}
				}
  deleteRows.deleteExtraRows(totalImage+1);		
  uploadexcel.resultSummary(totalImage, passCount, failCount,sourceName);
  
  
		}
		
  }
  @AfterClass
  public void tearDown() {
	  System.out.println("=======================Test Completed======================");
  }
}
