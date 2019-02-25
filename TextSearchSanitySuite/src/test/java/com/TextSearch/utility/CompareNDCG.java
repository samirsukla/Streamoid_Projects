package com.TextSearch.utility;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class CompareNDCG {
	static CalculateNDCG calndcg;
	static ExtractInfoFromExcelSheet ext;
	static String status;

	public String getQueryStatus(int rowNum) throws IOException, ParseException {
		
		
	    
		calndcg = new CalculateNDCG();
		ext = new ExtractInfoFromExcelSheet();

		Double expectedNDCG = ext.storedNDCGValue(rowNum);
		Double actualNDCG = calndcg.calculateNDCG(rowNum);
        
		if ((actualNDCG == expectedNDCG)
				|| ((actualNDCG >= expectedNDCG - 0.05) && (actualNDCG <= expectedNDCG + 0.05))) {
//			 System.out.println("Green");
			return status = "Green";
		} else if (actualNDCG >= expectedNDCG - 0.3 && actualNDCG < expectedNDCG - 0.05) {
//			 System.out.println("Yellow");
			return status = "Yellow";
		} else {
//			 System.out.println("Red");
			return status = "Red";
		}
	}
}
