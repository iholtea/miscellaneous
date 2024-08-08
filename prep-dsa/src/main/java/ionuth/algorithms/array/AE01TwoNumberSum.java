package ionuth.algorithms.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/*
 * Write a function that takes in an array of distinct integers and
 * another integer representing a target sum.
 * If any two numbers from the array sum up to the target sum 
 * return them in an array of 2 elements( the order does not matter)
 * Else return an empty array
 * 
 * Input: 
 * array = [3, 5, -4, 8, 11, 1, -1, 6]
 * targetSum = 10
 * 
 * Output [11, -1]
 * 
 */

public class AE01TwoNumberSum {
	
	/*
	 * O( n^2 ) solution
	 */
	public static int[] twoNumberSumV1(int[] inArray, int targetSum) {
		
		if( inArray.length==0 || inArray.length==1 ) {
			return new int[0];
		}
		
		for(int i=0;i<inArray.length-1;i++) {
			for(int j=i+1;j<inArray.length;j++) {
				if( inArray[i] + inArray[j] == targetSum) {
					int[] outArray = new int[2];
					outArray[0]=inArray[i];
					outArray[1]=inArray[j];
					return outArray;
				}
			}
		}
		
		return new int[0];
	}
	
	/* 
	 * Use a hash table to store the numbers from the array
	 * Get each number from the array.
	 * If hashTable.contains( targetSum-number ) we found it
	 * else hashTable.add( number )
	 * 
	 * Under reasonable assumptions, the expected time to search for an element 
	 * in a hash table is O(1).
	 * 
	 * So the time complexity is roughly O(n)
	 * 
	 * Fun fact:
	 * The most commonly used method for hashing integers is called modular hashing: 
	 * we choose the array size M to be prime, and, for any positive integer key k, 
	 * compute the remainder when dividing k by M. 
	 * This function is very easy to compute (k % M, in Java), 
	 * and is effective in dispersing the keys evenly between 0 and M-1.
	 */
	public static int[] twoNumberSumV2(int[] inArray, int targetSum) {
		
		if( inArray.length==0 || inArray.length==1 ) {
			return new int[0];
		}
		
		Set<Integer> numbers = new HashSet<Integer>();
		for( int num : inArray ) {
			int diff = targetSum - num;
			if( numbers.contains(diff) ) {
				return new int[] { diff, num };
			} else {
				numbers.add(num);
			}
		}
		
		return new int[0];
	}
	
	/*
	 * O( n*log(n) ) solution
	 * Sort the array then
	 * start with 2 indices from first and last element in the array
	 * (so the min and max after it was sorted) and loop while left<right
	 * tempSum = a[left] + a[right]
	 * if tempSum == targetSum - found it
	 * else if tempSum<targetSum  // we need a bigger number from left side
	 * 		left++
	 * else if tempSum>targetSum  // we need a smaller number from right side
	 * 		right--		 
	 * 
	 * another O( n*log(n) ) but more time consuming would be that after
	 * sorting the array for each num:array to binary search for targetSum-num
	 */
	public static int[] twoNumberSumV3(int[] inArray, int targetSum) {
		
		if( inArray.length==0 || inArray.length==1 ) {
			return new int[0];
		}
		
		// in place sort
		Arrays.sort(inArray);
		
		int left = 0;
		int right = inArray.length-1;
		while( left<right ) {
			int tempSum = inArray[left] + inArray[right];
			if( tempSum==targetSum ) {
				int[] outArray = new int[2];
				outArray[0]=inArray[left];
				outArray[1]=inArray[right];
				return outArray;
			} else if(tempSum<targetSum) {
				left++;
			} else {
				right--;
			}
		}
		
		return new int[0];
	}
	
	public static void main(String[] args) {
		
		int targetSum = 10;
		int[] inArray;
		int[] outArray;
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, -1, 6};
		outArray = twoNumberSumV1(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, 4, 6};
		outArray = twoNumberSumV1(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, 4, 12};
		outArray = twoNumberSumV1(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		System.out.println("-------------------------------");
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, -1, 6};
		outArray = twoNumberSumV2(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, 4, 6};
		outArray = twoNumberSumV2(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, 4, 12};
		outArray = twoNumberSumV2(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		System.out.println("-------------------------------");
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, -1, 6};
		outArray = twoNumberSumV3(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, 4, 6};
		outArray = twoNumberSumV3(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] {3, 5, -4, 8, 11, 1, 4, 12};
		outArray = twoNumberSumV3(inArray, targetSum);
		System.out.println( Arrays.toString(outArray) );
	}
	
}
