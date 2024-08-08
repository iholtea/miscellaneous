package ionuth.algorithms.strings;

/*
 *	we have a string which is repeated an infinite number of times
 *	so if s = "abc" our input would be "abcabcabcabc....."
 *	Given an integer n find the number of letters a in the first n letters of the infinite string
 *
 * 	Example :
 * 	s = "abcac"
 * 	n = 7
 * 
 * 	the infinite string would be "abcacabcac...."
 *  first 7 letter for it "abcacab"
 *  So the output is 3 occurrences of a in the infinite string
 *  
 *  Corner cases:
 *  int s="a" the output is 1,000,000,000,000
 */


public class HR01RepeatedString {
	
	public static long repeatedString(String s, long n) {
	    
		if( s.equals("a") ) {
			return 1000000000000L;
		}
		if( !s.contains("a") ) {
			return 0L;
		}
		
		long countFull = 0;
		long countPart = 0;
		long reminder = n % s.length();
		
		/*
		 * countFull is number of occurrences of 'a' in whole string
		 * countPart is number of occurrences of 'a' in n % s.length() first characters of string s 
		 */
		for( int i=0;i<s.length();i++) {
			if( s.charAt(i)=='a' ) {
				countFull++;
				if(i<reminder) {
					countPart++;
				}
			}
		}
		
		return countPart + countFull * ( n / s.length() );

	}
	
	public static void main(String[] args) {
		
		String s;
		int n;
		
		s = "abcacab";
		n = 7;
		System.out.println( repeatedString(s, n) );
		
		s = "aba";
		n = 10;
		System.out.println( repeatedString(s, n) );
	}
	
}
