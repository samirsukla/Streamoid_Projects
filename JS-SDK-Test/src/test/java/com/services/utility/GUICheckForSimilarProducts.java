package com.services.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GUICheckForSimilarProducts {
	

	
	public boolean getIdsFromGUIforAllenSolly(List<String> prodUsingApi ,WebDriver driver)  {
	
		
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

	public boolean getIdsFromGUIforGlobusFashion(List<String> prodUsingApi ,WebDriver driver)  {
		
		
		List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div/div[1]/div[@class=\"globus_container\"]/a"));
		List<String> prodUsingUI = new ArrayList<String>();
		
		for(WebElement elem : similar_Ids) {
			String id1 = elem.getAttribute("id");
			prodUsingUI.add(id1);
			if(prodUsingUI.size()==5) {
				break;
			}
		}
		//Collections.reverse(prodUsingUI);
		System.out.println(prodUsingUI);
		if(prodUsingApi.equals(prodUsingUI)) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
public boolean getIdsFromGUIforLouisPhilippe(List<String> prodUsingApi ,WebDriver driver)  {
	
		
		List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/section/div[2]/div/div/div[2]/div/div[@class=\"louis_philippe_vertical_section\"]/a"));
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
