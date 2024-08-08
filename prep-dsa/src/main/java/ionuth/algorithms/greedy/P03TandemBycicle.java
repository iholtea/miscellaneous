package ionuth.algorithms.greedy;

import java.util.Arrays;

/*
 * Tandem bike - is operated by two people A and B
 * Both people pedal the bike, but person that pedals faster dictates the speed.
 * So if A pedals at speed 5 and B pedals at speed 4 the bike moves with speed 5
 * 
 *  Input: two lists of positive integers representing speed of riders
 *  one list for red shirts, one list of blue shirts.
 *  Number of people in blue is equal to the ones in red.
 *  
 *  Goal: pair every rider in red with one in blue on a tandem bike.
 *  
 *  Write a function that returns the maximum or minimum total speed 
 *  of all tandem bikes based on an input boolean fastest
 *  
 *  Example:
 *  red:  [1, 3]
 *  blue: [4, 5]
 *  
 *  Max total speed is 4 + 5
 *  Min total speed is 4 + 5
 *  ( in this case, as blue riders have all greater speed than all of red riders)  
 *  
 *  Sample input:
 *  red:  [ 5, 5, 3, 9, 2 ]
 *  blue: [ 3, 6, 7, 2, 1 ]
 *  totalSpeed = 5 + 6 + 7 + 9 + 2 = 29
 *  
 *  sortedRed:  [ 2, 3, 5, 5, 9]
 *  sortedBlue: [ 1, 2, 3, 6, 7]
 *  totalSpeed = 2 + 3 + 5 + 6 + 9 = 25 
 *  
 *  sortedRed:         [ 2, 3, 5, 5, 9]
 *  reverseSortedBlue: [ 7, 6, 3, 2, 1]
 *  totalSpeed = 7 + 6 + 5 + 5 + 9 = 32
 *  
 *  So it is kind of obvious that
 *  minimum speed is when we match the sorted teams 
 *  	fastest riders from a team are canceling out the fastest riders from the other
 *  maximum speed is when we match slowest riders from one team with the fastest from the other
 *  	in this case we are taking advantage of the fastest riders from each team 
 *  
 */

public class P03TandemBycicle {
	
	public static int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
		
		Arrays.sort(redShirtSpeeds);
		Arrays.sort(blueShirtSpeeds);
		
		if(fastest) {
			reverseArrayInPlace(blueShirtSpeeds);
		}
		
		int totalSpeed = 0;
		for(int idx=0;idx<redShirtSpeeds.length;idx++) {
			int redSpeed = redShirtSpeeds[idx];
			int blueSpeed = blueShirtSpeeds[idx];
			int bikeSpeed = redSpeed >= blueSpeed ? redSpeed : blueSpeed;
			totalSpeed += bikeSpeed;
		}
		
		return totalSpeed;
	}
	
	public static void reverseArrayInPlace(int[] array) {
		int leftIdx = 0;
		int rightIdx = array.length-1;
		while( leftIdx<rightIdx ) {
			int temp = array[rightIdx];
			array[rightIdx] = array[leftIdx];
			array[leftIdx]=temp;
			leftIdx++;
			rightIdx--;
		}
	}
	
	public static void main(String[] args) {
		
		int[] redShirtSpeeds;
		int[] blueShirtSpeeds;
		
		redShirtSpeeds =  new int[] { 5, 5, 3, 9, 2};
		blueShirtSpeeds = new int[] { 3, 6, 7, 2, 1};
		
		System.out.println( "max: " + tandemBicycle(redShirtSpeeds, blueShirtSpeeds, true) );
		System.out.println( "min: " + tandemBicycle(redShirtSpeeds, blueShirtSpeeds, false) );
		
	}
	
}
