package com.services.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class UploadResultonExcel {
  
  public void writeResultToExcel(String clickableImageURL, String id, String status) throws IOException {
	  
	  FileInputStream fis = new FileInputStream("/Users/samirsukla/Desktop/Animator_Images/ResultSheet.xls");
	  HSSFWorkbook workbook = new HSSFWorkbook(fis);
	  HSSFSheet sheet = workbook.getSheetAt(0);
	  int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	  Row row = sheet.createRow(rowCount+1);
	  
	  Cell cell = row.createCell(0);
	  Cell cell1 = row.createCell(1);
	  cell.setCellValue(id + " : " + clickableImageURL);
	  cell1.setCellValue(status);
	  FileOutputStream fos = new FileOutputStream("/Users/samirsukla/Desktop/Animator_Images/ResultSheet.xls");
	  workbook.write(fos);
	  fos.close();
	  workbook.close();
	  
	  
	  
  }
}
