package com.services.utility;

import java.util.ArrayList;
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
		//Collections.reverse(prodUsingUI);
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
//			if(prodUsingUI.size()==5) {
//				break;
//			}
		}
		//Collections.reverse(prodUsingUI);
		//System.out.println(prodUsingUI);
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
		//Collections.reverse(prodUsingUI);
		//System.out.println(prodUsingUI);
		if(prodUsingApi.equals(prodUsingUI)) {
			return true;
		}else {
			return false;
		}
		
		
	}
public boolean getIdsFromGUIforVanHeusen(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/section/div[2]/div/div/div[2]/div/div[@class=\"vanheusen_vertical_section\"]/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
public boolean getIdsFromGUIforFabIndia(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> outfitter_Ids=driver.findElements(By.xpath("/html/body/form/div[3]/center/div/div/center/div[1]/div[3]/div[2]/div[3]/div/div/div[1]/div[2]/div/div/div[@class=\"fabindia_india_container\"]/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : outfitter_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
public boolean getIdsFromGUIforPeterEngland(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/div[3]/div/div[5]/div/div[@class=\"peter_england_container\"]/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
public boolean getIdsFromGUIforPeople(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/section/div[2]/div/div/div[2]/div/div[@class=\"people_vertical_section\"]/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
public boolean getIdsFromGUIforPlanetFashion(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/section/div[2]/div/div/div[2]/div/div[@class=\"planet_fashion_vertical_section\"]/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
public boolean getIdsFromGUIforRaymondNext(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[3]/div[4]/div/div[@class=\"raymond_container\"]/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}

public boolean getIdsFromGUIforAbof(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/div[3]/div[3]/div/div[@class='v_abof_container']/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
public boolean getIdsFromGUIforSimonCarter(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/div[2]/div[3]/div/div[@class='simon_carter_container']/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
public boolean getIdsFromGUIforPantaloons(List<String> prodUsingApi ,WebDriver driver)  {
	
	
	List<WebElement> similar_Ids=driver.findElements(By.xpath("/html/body/section/div[1]/div[3]/div/div[4]/div/div[@class='pantaloons_container']/a"));
	List<String> prodUsingUI = new ArrayList<String>();
	
	for(WebElement elem : similar_Ids) {
		String id1 = elem.getAttribute("id");
		prodUsingUI.add(id1);
		
	}
	//Collections.reverse(prodUsingUI);
	//System.out.println(prodUsingUI);
	if(prodUsingApi.equals(prodUsingUI)) {
		return true;
	}else {
		return false;
	}
	
	
}
}

