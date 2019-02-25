package com.TextSearch.test;

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

import com.TextSearch.utility.CalculateNDCG;
import com.TextSearch.utility.CompareNDCG;
import com.TextSearch.utility.CompareTwoMaps;
import com.TextSearch.utility.ExtractInfoFromExcelSheet;
import com.TextSearch.utility.GetFinalScores;
import com.TextSearch.utility.TriggerTextSearchAPI;

public class GetSearchQueryStatus {

	static ExtractInfoFromExcelSheet ext;
	static TriggerTextSearchAPI ttsa;
	static CompareTwoMaps compmap;
	static CalculateNDCG calndcg;
	static GetFinalScores getfscore;
	static CompareNDCG compndcg;
	static int rowCount;
	static Double expectedNDCG;
	static String queryName;
	static Double actualNDCG;
	static String status;

	@BeforeClass
	public void setUp() throws IOException {
		ext = new ExtractInfoFromExcelSheet();
		ttsa = new TriggerTextSearchAPI();
		compmap = new CompareTwoMaps();
		calndcg = new CalculateNDCG();
		getfscore = new GetFinalScores();
		compndcg = new CompareNDCG();
		rowCount = ext.returnRowCount();
	}

	@Test
	public void getQueryStatus() throws IOException, ParseException {
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> queryNameList = new ArrayList<String>();
		List<String> expectedNdcgList = new ArrayList<String>();
		List<String> actualNdcgList = new ArrayList<String>();
		List<String> statusList = new ArrayList<String>();
		
		for (int i = 1; i <= rowCount; i++) {

			
			actualNDCG = calndcg.calculateNDCG(i);
			expectedNDCG = ext.storedNDCGValue(i);
			queryName = ext.extractQuery(i);
			status = compndcg.getQueryStatus(i);
			
			queryNameList.add(queryName);
			expectedNdcgList.add(Double.toString(expectedNDCG));
			actualNdcgList.add(Double.toString(actualNDCG));
			statusList.add(status);
			System.out.println("The Status for row Number " + i + "is " + status);
		}
		map.put("queryName", queryNameList);
		map.put("expectedNDCGList", expectedNdcgList);
		map.put("actualNDCGList", actualNdcgList);
		map.put("status", statusList);
		ITestResult testResult = Reporter.getCurrentTestResult();
		testResult.setAttribute("key", map);

	}
}
