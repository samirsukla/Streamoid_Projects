package com.services.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GUICheckForSimilarProducts {
	
static takeScreenShot scrshot;
	
	public boolean getIdsFromGUI(List<String> prodUsingApi ,WebDriver driver)  {
	
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
