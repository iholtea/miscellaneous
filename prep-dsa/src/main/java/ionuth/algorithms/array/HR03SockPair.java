package ionuth.algorithms.array;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *	There is a large pile of socks to be paired by color
 *	Given an array of integers each representing a color 
 *	determine how many pairs of socks there are
 *	
 *	Example:
 *	n=9
 *	arr = [ 1, 2, 1, 2, 1, 3, 2 ]
 *
 * 	So we have 
 * 	3 socks of color 1
 *  3 socks of color 2
 *  1 sock  of color 3
 *  
 *  so the answer is 2 pairs
 * 
 */

public class HR03SockPair {
	
	public static int sockMerchant(int n, List<Integer> ar) {
		
		Map<Integer, Integer> socks = new HashMap<Integer, Integer>();
		ar.forEach( color -> {
			if( socks.containsKey(color) ) {
				socks.put(color, socks.get(color) + 1);
			} else {
				socks.put(color, 1);
			}
		});
		
		return socks.values().stream().map( val -> val / 2 ).reduce(0, Integer::sum);

	}
	
	public static void main(String[] args) {
		
		int n = 9;
		List<Integer> ar = Arrays.asList(1, 2, 1, 2, 1, 3, 2);
		
		System.out.println( sockMerchant(n, ar) );
	}
	
}
