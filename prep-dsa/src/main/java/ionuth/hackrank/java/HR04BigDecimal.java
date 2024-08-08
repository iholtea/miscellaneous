package ionuth.hackrank.java;

import java.math.BigDecimal;

/*
 * sort an input list of strings representing decimal numbers
 * as decimals - inverse sort - the highest number is the first
 */
public class HR04BigDecimal {
	
	public static void sortDecimals(String[] s) {
		
		boolean swapped = false;
		do {
			swapped = false;
			for(int i=1; i<s.length; i++) {
				BigDecimal a = new BigDecimal(s[i-1]);
				BigDecimal b = new BigDecimal(s[i]);
				if( a.compareTo(b) < 0 ) {
					String temp = s[i-1];
					s[i-1] = s[i];
					s[i] = temp;
					swapped = true;
				}
			}
		} while(swapped);
		
		for(int i=0; i<s.length; i++) {
			System.out.println(s[i]);
		}
			
	}
	
	public static void main(String[] args) {
		
		String[] s = { "-100", "50", "0", "56.6", "90", "0.12", ".12", "02.34", "000.000" };
		sortDecimals(s);
	}
	
}
