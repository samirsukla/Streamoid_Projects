package com.services.utility;   
import java.util.*;  
      
    public class GenerateRandomNumber 
    {  
     public int generateRandomNo(){  
    	 int max = 12;
    	 int min = 1;
    	 Random r = new Random();
    	 int randomNo = r.nextInt((max-min)+1)+1;
    	 System.out.println(randomNo);
    	 return randomNo;
          
     }
    }  