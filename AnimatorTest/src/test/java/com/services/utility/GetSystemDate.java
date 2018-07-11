package com.services.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetSystemDate {
  
  public String getCurrentDateAndTime() {
	  DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	  Date date = new Date();
	  
	  String currentDate = df.format(date);
	  return currentDate;
	  
	  
  }
}
