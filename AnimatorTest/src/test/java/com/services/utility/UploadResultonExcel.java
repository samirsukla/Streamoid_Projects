package com.services.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class UploadResultonExcel {
	static CreateAbsolutePath createpath;
  
  public void writeResultToExcel(String clickableImageURL, String id, String status,int rowCount) throws IOException {
	  createpath = new CreateAbsolutePath();
	  String pathToResultFile = createpath.makeAbsolutePath();
	  
	  FileInputStream fis = new FileInputStream(pathToResultFile+"/ResultSheet.xls");
	  HSSFWorkbook workbook = new HSSFWorkbook(fis);
	  HSSFSheet sheet = workbook.getSheetAt(0);
	  
	  Row row = sheet.createRow(rowCount+1);
	  
	  Cell cell = row.createCell(0);
	  cell.setCellValue(id + " : " + clickableImageURL);
	  Cell cell1 = row.createCell(1);
	  cell1.setCellValue(status);
	  FileOutputStream fos = new FileOutputStream(pathToResultFile+"/ResultSheet.xls");
	  workbook.write(fos);
	  fos.close();
	  workbook.close();
	  
	  
	  
  }
  
  public void resultSummary(int total, int matched, int unmatched) throws IOException {
	  double failPercentage= (double)((unmatched*100)/total);
	  
	  createpath = new CreateAbsolutePath();
	  String pathToResultFile = createpath.makeAbsolutePath();
	  
	  FileInputStream fis = new FileInputStream(pathToResultFile+"/ResultSheet.xls");
	  HSSFWorkbook workbook = new HSSFWorkbook(fis);
	  HSSFSheet sheet = workbook.getSheetAt(1);
	 
	  Row row = sheet.createRow(1);
	  
	  Cell cell = row.createCell(0);
	  cell.setCellValue(total);
	  Cell cell1 = row.createCell(1);
	  cell1.setCellValue(matched);
	  Cell cell2 = row.createCell(2);
	  cell2.setCellValue(unmatched);

	  Cell cell3 = row.createCell(3);
	  cell3.setCellValue(failPercentage);

	  FileOutputStream fos = new FileOutputStream(pathToResultFile+"/ResultSheet.xls");
	  workbook.write(fos);
	  fos.close();
	  workbook.close();
	  
  }
}
