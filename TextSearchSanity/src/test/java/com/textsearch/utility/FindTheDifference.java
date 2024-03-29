package com.textsearch.utility;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

public class FindTheDifference {
	static GetActualProductList getpidlist;
	static ExtractInfoFromExcel ext;
	static String status;
	
  
  public List<String> differenceList(int rowNum) throws IOException, ParseException {
	  getpidlist = new GetActualProductList();
		ext = new ExtractInfoFromExcel();

		
		List<String> expectedList = ext.extractPIDList(rowNum);
		List<String> actualList = getpidlist.getProductList(rowNum);
		
		actualList.removeAll(expectedList);
		return actualList;
  }
}
