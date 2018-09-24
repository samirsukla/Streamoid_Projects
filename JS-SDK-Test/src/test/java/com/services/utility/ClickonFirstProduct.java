package com.services.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickonFirstProduct {
	static Actions action;
	static GenerateRandomNumber random;
	
	
	public String clickOnAllen_VanHProduct(WebDriver driver) throws InterruptedException {
		List<WebElement> productList = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div"));
		int noOfProducts = productList.size();
		random = new GenerateRandomNumber();
		int randomNo = random.generateRandomNo();
		
		if(randomNo >=5) {
			
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)"); 
			Thread.sleep(2000);
			
		}
		if(randomNo<=noOfProducts) {
			String product_url = "/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div["+randomNo+"]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement element_link = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div["+randomNo+"]/div"));
			String product_id = element_link.getAttribute("id").replaceAll("product_wrap_", "");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
			
		}
		else {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)"); 
			String product_url = "/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement element_link = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div"));
			String product_id = element_link.getAttribute("id").replaceAll("product_wrap_", "");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
		}
		
		
	}
	
	public String clickOnGlobusProduct(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> productList = driver.findElements(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div[5]/ul/li"));
		int noOfProducts = productList.size();
		random = new GenerateRandomNumber();
		int randomNo = random.generateRandomNo();
		
		if(randomNo >=5) {
			
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)"); 
			Thread.sleep(2000);
			
		}
		if(randomNo<=noOfProducts) {
			String product_url = "/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div[5]/ul/li["+randomNo+"]/a/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div[5]/ul/li["+randomNo+"]/div[1]/button"));
			String product_id = first_element_link.getAttribute("sku");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
			
		}
		else {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)"); 
			String product_url = "/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div[5]/ul/li[1]/a/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div[5]/ul/li[1]/div[1]/button"));
			String product_id = first_element_link.getAttribute("sku");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
		}
		
		
	}
	
	
	public String clickOnFabIndiaProduct(WebDriver driver) throws InterruptedException {
		
		List<WebElement> productList = driver.findElements(By.xpath(".//*[@id='Productshowcase']/div[1]/div/div"));
		int noOfProducts = productList.size();
		
		random = new GenerateRandomNumber();
		int randomNo = random.generateRandomNo()+1;
		

		if(randomNo<=noOfProducts) {
			String product_url = ".//*[@id='Productshowcase']/div[1]/div/div["+randomNo+"]/div[2]/a[1]/img";
			WebElement elementToClick = driver.findElement(By.xpath(product_url));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToClick);
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement first_element_link = driver.findElement(By.xpath("//div[@id='Productshowcase']/div/div/div["+randomNo+"]"));
			String product_id = first_element_link.getAttribute("data-productid");
			
			
			elementToClick.click();
			return product_id;
			
		}
		else {
			
			String product_url = ".//*[@id='Productshowcase']/div[1]/div/div[2]/div[2]/a[1]/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement first_element_link = driver.findElement(By.xpath("//div[@id='Productshowcase']/div/div/div[2]"));
			String product_id = first_element_link.getAttribute("data-productid");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
		}
		
		
	}
	
	public String clickOnPeterEnglandProduct(WebDriver driver) throws InterruptedException {
		
		List<WebElement> productList = driver.findElements(By.xpath("/html/body/div[3]/div[4]/div[1]/div"));
		int noOfProducts = productList.size();
		random = new GenerateRandomNumber();
		int randomNo = random.generateRandomNo();
		
		if(randomNo >=5) {
			
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)"); 
			Thread.sleep(2000);
			
		}
		if(randomNo<=noOfProducts) {
			String product_url = "/html/body/div[3]/div[4]/div[1]/div["+randomNo+"]/div/div[1]/div/ul/li[2]/a/div/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[3]/div[4]/div[1]/div["+randomNo+"]"));
			String product_id = first_element_link.getAttribute("data-productid");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
			
		}
		else {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)"); 
			String product_url = "/html/body/div[3]/div[4]/div[1]/div[1]/div/div[1]/div/ul/li[2]/a/div/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[3]/div[4]/div[1]/div[1]"));
			String product_id = first_element_link.getAttribute("data-productid");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
		}
		
	}
	
	public String clickOnPeople_Planet_LouisProduct(WebDriver driver) throws InterruptedException {
		List<WebElement> productList = driver.findElements(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div"));
		int noOfProducts = productList.size();
		random = new GenerateRandomNumber();
		int randomNo = random.generateRandomNo();
		
		if(randomNo >=5) {
			
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)"); 
			Thread.sleep(2000);
			
		}
		if(randomNo<=noOfProducts) {
			String product_url = "/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div["+randomNo+"]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement element_link = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div["+randomNo+"]/div"));
			String product_id = element_link.getAttribute("id").replaceAll("product_wrap_", "");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
			
		}
		else {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)"); 
			String product_url = "/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div[1]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img";
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
			
			WebElement element_link = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div[1]/div"));
			String product_id = element_link.getAttribute("id").replaceAll("product_wrap_", "");
			
			driver.findElement(By.xpath(product_url)).click();
			return product_id;
		}
		
	}
	

public void clickOnPothysProduct(WebDriver driver) throws InterruptedException {
	
	List<WebElement> productList = driver.findElements(By.xpath(".//*[@id='products-grid']/li"));
	int noOfProducts = productList.size()*3;
	random = new GenerateRandomNumber();
	int randomNo = random.generateRandomNo();
	
	int rowNum = (randomNo/3)+1;
	int colNum = (randomNo%3)+1;
	

	if(randomNo<=noOfProducts) {
		String product_url = ".//*[@id='products-grid']/li["+rowNum+"]/div["+colNum+"]/div/div[1]/div[1]/span/a/img";
		WebElement elementToClick = driver.findElement(By.xpath(product_url));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToClick);
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick);
		
		
	}
	else {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)"); 
		
		String product_url = ".//*[@id='products-grid']/li[1]/div[1]/div/div[1]/div[1]/span/a/img";
		WebElement elementToClick = driver.findElement(By.xpath(product_url));
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick);
		
	}
	
}

public String clickOnAbofProduct(WebDriver driver) throws InterruptedException {
	List<WebElement> productList = driver.findElements(By.xpath("/html/body/div[2]/div/div/div[3]/div[2]/div/div[2]/div[2]/div"));
	int noOfProducts = productList.size();
	random = new GenerateRandomNumber();
	int randomNo = random.generateRandomNo();
	
	if(randomNo >=4) {
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)"); 
		Thread.sleep(2000);
		
	}
	if(randomNo<=noOfProducts) {
		String product_url = "/html/body/div[2]/div/div/div[3]/div[2]/div/div[2]/div[2]/div["+randomNo+"]/div[1]/div[1]/div/ul/li[2]/a/div/img";
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
		
		WebElement element_link = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div[2]/div/div[2]/div[2]/div["+randomNo+"]/input"));
		String product_id = element_link.getAttribute("id").replaceAll("dyProductID_", "");
		
		driver.findElement(By.xpath(product_url)).click();
		return product_id;
		
	}
	else {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)"); 
		String product_url = "/html/body/div[2]/div/div/div[3]/div[2]/div/div[2]/div[2]/div[1]/div[1]/div[1]/div/ul/li[2]/a/div/img";
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_url)));
		
		WebElement element_link = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div[2]/div/div[2]/div[2]/div[1]/input"));
		String product_id = element_link.getAttribute("id").replaceAll("dyProductID_", "");
		
		driver.findElement(By.xpath(product_url)).click();
		return product_id;
	}
	
}

public void clickOnRaymondProduct_1(WebDriver driver) throws InterruptedException {
	Thread.sleep(2000);
		String product_url ="/html/body/div[1]/main/div[2]/div/div[2]/div[2]/div[1]/div/div/div[1]/div[1]/a/div";
		driver.findElement(By.xpath(product_url)).click();
		
	}

public void clickOnRaymondProduct_2(WebDriver driver) throws InterruptedException {
	Thread.sleep(2000);
	String product_url ="/html/body/div[1]/main/div/div/div[3]/div[2]/div[1]/div/div/div[1]/div[1]/a/div";
	driver.findElement(By.xpath(product_url)).click();
	
}










	public void moveToElementandClick(WebElement main_category , WebElement product_category , WebDriver driver) {
		action = new Actions(driver);
		action.moveToElement(main_category).moveToElement(product_category).click().build().perform();
	}
	
	
	
	public void moveToElement_only(WebElement product_Category , WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		action = new Actions(driver);
		action.moveToElement(product_Category).build().perform();

	}
	
	
	
	public String getCategoryName(WebElement element) {
		return  element.getText();
		
	}

	

}
