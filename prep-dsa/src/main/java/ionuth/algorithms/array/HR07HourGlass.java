package ionuth.algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *  In hourglass in 2D array ( matrix ) is a portion from the array like
 *  a b c
 *    d
 *  e f g
 *  
 *  The sum of an hourglass is the sum of all numbers inside it
 *  
 *  Problem: print the maximum sum from all hourglass values in a given 2D array
 *  the 2D array is defined like a List<List>. Each inner list represent a line in the matrix    
 */

public class HR07HourGlass {
	
	public static void main(String[] args) {
		
		List<List<Integer>> arr = new ArrayList<>();
		arr.add( Arrays.asList( 1, 1, 1, 0, 0, 0 ) );
		arr.add( Arrays.asList( 0, 1, 0, 0, 0, 0 ) );
		arr.add( Arrays.asList( 1, 1, 1, 0, 0, 0 ) );
		arr.add( Arrays.asList( 0, 0, 2, 4, 4, 0 ) );
		arr.add( Arrays.asList( 0, 0, 0, 2, 0, 0 ) );
		arr.add( Arrays.asList( 0, 0, 1, 2, 4, 0 ) );
		
		int maxSum = Integer.MIN_VALUE;
		for( int lnr=0; lnr<arr.size()-2; lnr++) {
			for( int cnr=0; cnr<arr.size()-2; cnr++) {
				List<Integer> hglass = new ArrayList<Integer>();
				hglass.add( arr.get(lnr).get(cnr) );
				hglass.add( arr.get(lnr).get(cnr+1) );
				hglass.add( arr.get(lnr).get(cnr+2) );
				hglass.add( arr.get(lnr+1).get(cnr+1) );
				hglass.add( arr.get(lnr+2).get(cnr) );
				hglass.add( arr.get(lnr+2).get(cnr+1) );
				hglass.add( arr.get(lnr+2).get(cnr+2) );
				int sum = hglass.stream().reduce(0, Integer::sum);
				if(sum>maxSum) {
					maxSum = sum;
				}
			}
		}
		System.out.println(maxSum);
	}
	
	static void printHourGlass(List<Integer> hglass) {
		System.out.println( hglass.get(0) + " " + hglass.get(1) + " " + hglass.get(2) );
		System.out.println( "  " + hglass.get(3) + "  " );
		System.out.println( hglass.get(4) + " " + hglass.get(5) + " " + hglass.get(6) );
		System.out.println("");
	}
	
	
}
