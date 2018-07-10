package com.services.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class DeleteExtraRows {
	CreateAbsolutePath createpath;
	Row row;
	
	public void deleteExtraRows(int rowCount) throws IOException {
		  createpath = new CreateAbsolutePath();
		  String pathToResultFile = createpath.makeAbsolutePath();
		  FileInputStream fis = new FileInputStream(pathToResultFile+"/ResultSheet.xls");
		  HSSFWorkbook workbook = new HSSFWorkbook(fis);
		  HSSFSheet sheet = workbook.getSheetAt(0);
		  for(int i= rowCount;i<=sheet.getLastRowNum();i++) {
			row = sheet.getRow(i);
			sheet.removeRow(row);
			
		}
		  File file = new File(pathToResultFile+"/ResultSheet.xls");
		  FileOutputStream out = new FileOutputStream(file);
		  workbook.write(out);
		  out.flush();
		  out.close();
		  fis.close();
		  workbook.close();
	}

}
