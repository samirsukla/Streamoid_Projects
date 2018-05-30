package com.services.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class RunTestNGThroughJavaClass {

	
	
	public static void main(String[] args) {
		
		TestNG runner=new TestNG();
		 
		
		List<String> suitefiles=new ArrayList<String>();
		 
		
		suitefiles.add("/home/streamoid/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/Streamoid Projects/automation-scripts/JS-SDK-Test/testng.xml");
		 
		
		runner.setTestSuites(suitefiles);
		 
		
		runner.run();
		

	}

}
