package com.textsearch.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.textsearch.utility.CompareTwoLists;
import com.textsearch.utility.ExtractInfoFromExcel;
import com.textsearch.utility.FindTheDifference;
import com.textsearch.utility.GetActualProductList;

public class GetTextSearchStatus {
	static ExtractInfoFromExcel ext;
	static CompareTwoLists compList;
	static FindTheDifference findDiff;
	static GetActualProductList getAct;
	static int rowCount;
	static String status;
	static List<String> diffList;
	static int diffSize;
	static String queryName;
	static List<String> expectedList;
	static List<String> actualList;
//	static String expectedStringList = "";	
//	static String actualStringList = "";
//	static String diffStringList = "";
	
	@BeforeClass
	public void setup() throws IOException {
		ext = new ExtractInfoFromExcel();
		compList = new CompareTwoLists();
		getAct = new GetActualProductList();
		findDiff = new FindTheDifference();
		diffList = new ArrayList<String>();
		rowCount = ext.returnRowCount();
	}
  @Test
  public void getQueryStatus() throws IOException, ParseException {
	  
	  	Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> queryNameList = new ArrayList<String>();
		List<String> diffNoList = new ArrayList<String>();
		List<String> diffPIDsList = new ArrayList<String>();
		List<String> statusList = new ArrayList<String>();
		List<String> expectedPIDList = new ArrayList<String>();
		List<String> actualPIDList = new ArrayList<String>();
		List<String> totalProdCount = new ArrayList<String>();
	 
	  for (int i = 1; i <= rowCount; i++) {
		  queryName = ext.extractQuery(i);
		  status = compList.getQueryStatus(i);
		  expectedList = ext.extractPIDList(i);
		  actualList = getAct.getProductList(i);
		  
		  	String expectedStringList = "";	
			String actualStringList = "";
			String diffStringList = "";
		  diffList.clear();
		  for (String s : expectedList)
		  {
			  expectedStringList += s + "\n";
		  }
		  
		  for (String s : actualList)
		  {
			  actualStringList += s + "\n";
		  }
		  
		  //System.out.println(status);
		  if(status=="Red") {
			  
			  diffList = findDiff.differenceList(i);
			  diffSize = diffList.size();
			 // System.out.println(diffSize + "======"+diffList);
		  }
		  else {
			  diffSize=0;
			  diffList.add("NA");
		  }
		  for (String s : diffList)
		  {
			  diffStringList += s + "\n";
		  }
		  
		  queryNameList.add(queryName);
		  diffNoList.add(Integer.toString(diffSize));
		  statusList.add(status);
		  diffPIDsList.add(diffStringList);
		  expectedPIDList.add(expectedStringList);
		  actualPIDList.add(actualStringList);
		  totalProdCount.add(Integer.toString(getAct.getProductCount(i)));
		 
		  
	  }
	  	map.put("queryName", queryNameList);
		map.put("noOfDiff", diffNoList);
		map.put("expectedList", expectedPIDList);
		map.put("actualList", actualPIDList);
		map.put("diffPIDList", diffPIDsList);
		map.put("totalProductCount", totalProdCount);
		map.put("status", statusList);
		ITestResult testResult = Reporter.getCurrentTestResult();
		testResult.setAttribute("key", map); 
	  
  }
}
