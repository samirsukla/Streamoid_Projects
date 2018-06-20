package com.services.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetSystemDate {
	
	public String getPresentDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();
		String currDate = sdf.format(date);
		
		return currDate;

	}

}
