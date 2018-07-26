package com.services.utility;

import java.awt.AWTException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ZoomInAndZoomOut {
  
  public void zoomOut(WebDriver driver) throws AWTException {
      JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("document.body.style.transform = 'scale(0.33)';");
	 
  }
  
  public void zoomIn(WebDriver driver) throws AWTException {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("document.body.style.transformOrigin = '0';");

  }
  
  public void zoomOutGlobal(WebDriver driver) throws AWTException {
      JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("document.body.style.transform = 'scale(0.50)';");
	 
  }
  
}
