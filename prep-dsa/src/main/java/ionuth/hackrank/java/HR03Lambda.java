package ionuth.hackrank.java;

/*
 * Implement lambda expression to return a PerformOperation
 * for isOdd, isPrime, isPalindrome
 */

interface PerformOperation {
	boolean check(int a);
}

class MyMath {

	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}
	
	public static PerformOperation isOdd() {
		return (int a) -> a % 2 != 0;
	}
	
	/*
	 * an optimization would be to check a%2
	 * and then the for loop just till sqrt(a), not a/2
	 */
	public static PerformOperation isPrime() {
		return (int a) -> {
			if( a==1 ) {
				return false;
			} 
			for(int i=2;i<=a/2;i++) {
				if ( a%i==0 ) {
					return false;
				}
			}
			return true;
		};
	}
	
	public static PerformOperation isPalindrome() {
		return (int a) -> {
			String str = String.valueOf(a);
			StringBuilder sb = new StringBuilder( String.valueOf(a) );
			String reversed = sb.reverse().toString();
			return str.equals(reversed);
		};
	}
	
}

public class HR03Lambda {

}
