package com.textsearch.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ExtractInfoFromExcel {
	static public FileInputStream fis;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static int rowCount;
	static String queryName;
	static List<String> PIDList;
	

	public static void inputExcel() throws IOException {
		fis = new FileInputStream("src/test/resources/NDCG_Implementation.xls");
		workbook = new HSSFWorkbook(fis);
		sheet = workbook.getSheetAt(1);
		rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	}

	public String extractQuery(int rowNum) throws IOException {
		
		inputExcel();

		Row row = sheet.getRow(rowNum);

		queryName = row.getCell(0).getStringCellValue();

		return queryName;
	}

	public List<String> extractPIDList(int rowNum) throws IOException {
		PIDList = new ArrayList<String>();
		inputExcel();

		Row row = sheet.getRow(rowNum);
		for (int j = 1; j <= 10; j++) {
			String cellContent = row.getCell(j).getStringCellValue();
			PIDList.add(cellContent);

		}

		return PIDList;

	}

	public int returnRowCount() throws IOException {
		inputExcel();
		return rowCount;
	}

	

}
