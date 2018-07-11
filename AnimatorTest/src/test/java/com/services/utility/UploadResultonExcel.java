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
	static GetSystemDate getDate;
  
  public void writeResultToExcel(String clickableImageURL, String id, String status,int rowCount) throws IOException {
	  createpath = new CreateAbsolutePath();
	  String pathToResultFile = createpath.makeAbsolutePath();
	  
	  FileInputStream fis = new FileInputStream(pathToResultFile+"/ResultSheet.xls");
	  HSSFWorkbook workbook = new HSSFWorkbook(fis);
	  HSSFSheet sheet = workbook.getSheetAt(0);
	  
	  Row row = sheet.createRow(rowCount+1);
	  
	  Cell url = row.createCell(0);
	  url.setCellValue(id + " : " + clickableImageURL);
	  Cell imageStatus = row.createCell(1);
	  imageStatus.setCellValue(status);
	  FileOutputStream fos = new FileOutputStream(pathToResultFile+"/ResultSheet.xls");
	  workbook.write(fos);
	  fos.close();
	  workbook.close();
	  
	  
	  
  }
  
  public void resultSummary(int totalImage, int matched, int unmatched, String sourceName) throws IOException {
	  double failPercentage= (double)((unmatched*100)/totalImage);
	  
	  createpath = new CreateAbsolutePath();
	  String pathToResultFile = createpath.makeAbsolutePath();
	  
	  getDate = new GetSystemDate();
	  String currentDate = getDate.getCurrentDateAndTime();
	  
	  FileInputStream fis = new FileInputStream(pathToResultFile+"/ResultSheet.xls");
	  HSSFWorkbook workbook = new HSSFWorkbook(fis);
	  HSSFSheet sheet = workbook.getSheetAt(1);
	 
	  Row row = sheet.createRow(1);
	  
	  Cell total = row.createCell(0);
	  total.setCellValue(totalImage);
	  Cell pass = row.createCell(1);
	  pass.setCellValue(matched);
	  Cell fail = row.createCell(2);
	  fail.setCellValue(unmatched);

	  Cell percentage = row.createCell(3);
	  percentage.setCellValue(failPercentage);
	  Cell source = row.createCell(4);
	  source.setCellValue(sourceName);
	  Cell timeOfTest = row.createCell(5);
	  timeOfTest.setCellValue(currentDate);

	  FileOutputStream fos = new FileOutputStream(pathToResultFile+"/ResultSheet.xls");
	  workbook.write(fos);
	  fos.close();
	  workbook.close();
	  
  }
}
