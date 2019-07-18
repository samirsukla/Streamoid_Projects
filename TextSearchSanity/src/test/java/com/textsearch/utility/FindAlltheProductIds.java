package com.textsearch.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

public class FindAlltheProductIds {
	static public FileInputStream fis;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static int colCount;
	static String queryName;
	static List<String> PIDList;
	static GetActualProductList getAct;
	static File file;
	//static FileOutputStream outputStream;
	

	public static void inputExcel() throws IOException {
		file = new File("src/test/resources/NDCG_Implementation.xls");
		fis = new FileInputStream(file);
		workbook = new HSSFWorkbook(fis);
		sheet = workbook.getSheet("ListOfPIDs");
		colCount = sheet.getRow(0).getLastCellNum();
		//rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	}

	
	public static String extractQuery(int colNum) throws IOException {
		
		inputExcel();

		Row row = sheet.getRow(0);

		queryName = row.getCell(colNum).getStringCellValue();

		return queryName;
	}


	public int returnColumnCount() throws IOException {
		inputExcel();
		return colCount;
	}
	
	public static String getJSONResponse(int colNum) throws IOException {
		//ext = new ExtractInfoFromExcel();
		queryName = extractQuery(colNum);
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
	
	public List<String> getProductList(int colNum) throws IOException, ParseException {
		
	
			String response = getJSONResponse(colNum);
			List<String> product_ids = new ArrayList<String>();
			
			product_ids = JsonPath.read(response, "$.products[*].unique_id");

			return product_ids;
		}
	
	public int getProductCount(int colNum) throws IOException {
		String response = getJSONResponse(colNum);
		List<String> product_ids = new ArrayList<String>();
		
		product_ids = JsonPath.read(response, "$.products[*].unique_id");
		int product_count = product_ids.size();
		return product_count;
		
	}
	
	
	@Test
	public void writeDataToExcel() throws IOException, ParseException {
		inputExcel();
		returnColumnCount();
		
		for (int i=0;i<colCount;i++) {
			int prod_count = getProductCount(i);
			List<String> prod_list = getProductList(i);
			for(int j=0;j<prod_count;j++) {
				Row newRow = sheet.createRow(j+1);
				Cell cell = newRow.createCell(i);
				cell.setCellValue(prod_list.get(j));
				
			}
			
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
						
		}
		
		
		//workbook.close();
		
		
	}

	

}
