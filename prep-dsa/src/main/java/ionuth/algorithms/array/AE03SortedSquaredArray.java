package ionuth.algorithms.array;

import java.util.Arrays;


/*
 * Write a function that takes an array of integers that are sorted
 * and outputs a new array with the squares of the original integers also sorted.
 * 
 *  The original array can contain negative numbers
 *  input:  [ -5, 2, 4, 5, 6 ]
 *  output: [ 4, 16, 25, 25, 36 ] 
 *  
 *  Simplest solution would be to just compute all the squares values 
 *  into the output array and then sort it - O( n*log(n) )
 *  We can do better in O(n)
 */

public class AE03SortedSquaredArray {
	
	/*
	 * keep 2 pointers(indexes) for the left and right of the input array
	 * in the right part there are the biggest square values of positive numbers
	 * in the left  part there are the biggest square values of negative numbers
	 * 
	 * loop the original array from length to 0
	 * and write to the output array from length to 0 the biggest square value which can be 
	 * either the most left squared value
	 * either the most right square value
	 * increase/decrease the 2 pointers accordingly
	 */
	public static int[] sortedSquaredArrayV2(int[] array) {
		
		int[] outArray = new int[array.length];
		
		int leftIdx = 0;
		int rightIdx = array.length-1;
		
		for(int idx=array.length-1;idx>=0;idx--) {
			int leftValue = array[leftIdx];
			int rightValue = array[rightIdx];
			if( Math.abs(leftValue)<=Math.abs(rightValue) ) {
				outArray[idx] = rightValue*rightValue;
				rightIdx--;
			} else {
				outArray[idx] = leftValue*leftValue;
				leftIdx++;
			}
		}
		
		return outArray;
		
	}
	
	/*
	 * Compute the squares in 2 separate arrays - one for positive and one for negative numbers
	 * Note that the negative square array has to be filled in the reverse order
	 * Then merge them into the result output array
	 */
	public static int[] sortedSquaredArrayV1(int[] array) {
		
		int negIdx = 0;
		int posIdx = 0;
		for( int number : array ) {
			if( number<0 ) {
				negIdx++;
			} else {
				posIdx++;
			}
		}
		
		int[] negArr = new int[negIdx];
		int[] posArr = new int[posIdx];
		posIdx=0;
		
		for( int number : array ) {
			if( number<0 ) {
				negArr[negIdx-1]=number*number;
				negIdx--;
			} else {
				posArr[posIdx]=number*number;
				posIdx++;
			}
		}
		
		if(negArr.length==0) {
			return posArr;
		}
		if(posArr.length==0) {
			return negArr;
		}
		
		negIdx=0;
		posIdx=0;
		
		int[] outArr = new int[array.length];
		int outIdx=0;
		while( posIdx<posArr.length && negIdx<negArr.length ) {
			int posNr = posArr[posIdx];
			int negNr = negArr[negIdx];
			if( posNr<=negNr ) {
				outArr[outIdx] = posNr;
				outIdx++;
				posIdx++;
			} else {
				outArr[outIdx] = negNr;
				outIdx++;
				negIdx++;
			}
		}
		
		while(posIdx<posArr.length) {
			outArr[outIdx] = posArr[posIdx];
			outIdx++;
			posIdx++;
		}
		while(negIdx<negArr.length) {
			outArr[outIdx] =negArr[negIdx];
			outIdx++;
			negIdx++;
		}
		
		return outArr;
		
	}
	
	public static void main(String[] args) {
		
		int[] inArray;
		int[] outArray;
		
		inArray = new int[] { 3, 4, 5 };
		outArray = sortedSquaredArrayV1(inArray);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] { -5, 3, 4, 6 };
		outArray = sortedSquaredArrayV1(inArray);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] { -5, 3, 4, 5, 6 };
		outArray = sortedSquaredArrayV1(inArray);
		System.out.println( Arrays.toString(outArray) );
		
		System.out.println("-------------------------------------");
		
		inArray = new int[] { 3, 4, 5 };
		outArray = sortedSquaredArrayV2(inArray);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] { -5, 3, 4, 6 };
		outArray = sortedSquaredArrayV2(inArray);
		System.out.println( Arrays.toString(outArray) );
		
		inArray = new int[] { -5, 3, 4, 5, 6 };
		outArray = sortedSquaredArrayV2(inArray);
		System.out.println( Arrays.toString(outArray) );
		
	}
	
}
