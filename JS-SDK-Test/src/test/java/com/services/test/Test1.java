package com.services.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.services.utility.takeScreenShot;

public class Test1 {

	static takeScreenShot scrshot;
	
	public boolean getIds(List<String> prodUsingApi ,WebDriver driver)  {
	
		scrshot = new takeScreenShot();
		List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/section/div[2]/div/div/div[2]/div/div[@class=\"allen_solly_vertical_section\"]/a"));
		List<String> prodUsingUI = new ArrayList<String>();
		
		for(WebElement elem : similar_Ids) {
			String id1 = elem.getAttribute("id");
			prodUsingUI.add(id1);
		}
		Collections.reverse(prodUsingUI);
		//System.out.println(prodUsingUI);
		if(prodUsingApi.equals(prodUsingUI)) {
			return true;
		}else {
			return false;
		}
		
		
	}
}
