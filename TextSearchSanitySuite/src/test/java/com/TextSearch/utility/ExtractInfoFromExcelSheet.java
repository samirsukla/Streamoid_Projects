package com.TextSearch.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ExtractInfoFromExcelSheet {
	static public FileInputStream fis;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static int rowCount;
	static String queryName;
	static List<String> queryList;
	static Map<String, Double> map;
	static String product_id;
	static Double scoreD;
	static String scoreS;
	static int rowNum;

	public static void inputExcel() throws IOException {
		fis = new FileInputStream("src/test/resources/NDCG_Implementation.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(1);
		rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	}

	public String extractQuery(int rowNum) throws IOException {
		queryList = new ArrayList<String>();
		inputExcel();

		Row row = sheet.getRow(rowNum);

		queryName = row.getCell(0).getStringCellValue();

		return queryName;
	}

	public Map<String, Double> extractPIDScore(int rowNum) throws IOException {
		map = new LinkedHashMap<String, Double>();
		inputExcel();

		Row row = sheet.getRow(rowNum);
		for (int j = 1; j <= 10; j++) {
			String cellContent = row.getCell(j).getStringCellValue();
			product_id = cellContent.substring(0, 32);
			scoreS = cellContent.substring(33);
			scoreD = Double.parseDouble(scoreS);
			map.put(product_id, scoreD);

		}

		return map;

	}

	public int returnRowCount() throws IOException {
		inputExcel();
		return rowCount;
	}

	public Double storedNDCGValue(int rowNum) throws IOException {
		inputExcel();
		Row row = sheet.getRow(rowNum);
		Double expectedNDCG = row.getCell(11).getNumericCellValue();

		return expectedNDCG;
	}

}
