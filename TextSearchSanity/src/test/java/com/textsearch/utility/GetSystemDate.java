package com.textsearch.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

public class GetSystemDate {
	
	public String[] getPresentDate() {
		String[] currDay = new String[2];
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();
		String currDate = sdf.format(date);
		
		DateFormat format2=new SimpleDateFormat("EEEE"); 
		String finalDay=format2.format(date);
		
		currDay[0] = currDate;
		currDay[1] = finalDay;
		//System.out.println("Todays date is "+ finalDay );
		
		return currDay;
		

	}

}
