package com.mbank.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class MbankUtil {
	
	public String createRandomAccountnumber() {
		 String accNumber = "MB";
	        Random value = new Random();
	    int r1 = value.nextInt(10);
	    int r2 = value.nextInt(10);
	    accNumber += Integer.toString(r1) + Integer.toString(r2) + "";
	    int count = 0;
	    int n = 0;
	    for(int i =0; i < 12;i++)
	    {
	        if(count == 4)
	        {
	        	accNumber += "";
	            count =0;
	        }
	        else 
	            n = value.nextInt(10);
	        accNumber += Integer.toString(n);
	            count++;            

	    }
    	return accNumber;
	}
}
