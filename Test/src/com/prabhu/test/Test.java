package com.prabhu.test;

import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		Solution  s = new Solution();
		long l1 = System.currentTimeMillis();
		System.out.println(s.isNumber("100.00f"));
		long l2 = System.currentTimeMillis();
		System.out.println((l2-l1));

	}
	
	private static class Solution {
		
	    public boolean isNumber(String s) {
	    	Pattern p = Pattern.compile("\\d+.\\d+");
	    	
	        
	        	if(p.matcher(s).matches()){
	        		  return true;
	        	}else{
	        		return false;
	        	}
	    }
	}

}
