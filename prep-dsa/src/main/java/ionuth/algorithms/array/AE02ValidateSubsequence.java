package ionuth.algorithms.array;

import java.util.Arrays;
import java.util.List;

/*
 * Given two arrays of integers write a functions that determines whether
 * the second array is a subsequence of the first one
 * 
 * A subsequence is a set of numbers which are not necessarily adjacent
 * but they are in the same order that they are in the array
 * For instance
 * Given the array [1, 2, 3, 4]
 * [1, 3, 4] - is a subsequence
 * [2, 4] - is a subsequence
 * [2, 1] - in NOT a subsequence - it does not have the correct order
 * 
 * Note that a single value from the array is a subsequence.
 * 		the whole array is a subsequence of itself.
 *  
 */

public class AE02ValidateSubsequence {
	
	/*
	 * O( n ) solution
	 * Start with first number in the array and first number in the sequence.
	 * 		Increase the index in sequence only if the number from the array 
	 * 		is equal to the number from current sequence index
	 * 		Increase the index in the array for each number
	 * Return true if the sequence index is equal to the sequence size
	 * which means that every number from the sequence was found when looping the array values
	 * 
	 * arr: [2, 5, 4, 6, 1]
	 * seq: [2, 4, 1]
	 * 
	 * Step 1 
	 * 		arr[0] == seq[0] 2==2 -> arrIdx++  seqIdx++
	 * Step 2
	 * 		arr[1] != seq[1] 5==4 -> arrIdx++
	 * Step 3
	 * 		arr[2] == seq[1] 4==4 -> arrIdx++ seqIdx++
	 * Step 4
	 * 		arr[3] != seq[2] 6==3 -> arrIdx++ 
	 * Step 5
	 * 		arr[4] == seq[2] 1==1 -> seqIdx++
	 * 
	 *  As seqIdx is 3 - the size of the sequence return true
	 * 		
	 */
	public static boolean isValidSubsequenceV2(List<Integer> array, List<Integer> sequence) {
		int arrIdx = 0;
		int seqIdx = 0;
		while( arrIdx<array.size() && seqIdx<sequence.size() ) {
			if( array.get(arrIdx).equals(sequence.get(seqIdx)) ) {
				seqIdx++;
			}
			arrIdx++;
		}
		return seqIdx==sequence.size();
	}
	
	/*
	 * This is more a O( n^2 ) solution.
	 * For each number in the sequence
	 * 		check if it is found in the original array
	 * 		but search the original array starting from the last found index
	 * 		( in this way we ensure the check for the correct order and also
	 * 		  we solve potential problems for cases when numbers are NOT distinct 
	 * 		  as the not have to be )
	 * 		For example when the sequence contains 22 more times
	 * 		array: [5, 1, 22, 25, 6, -1, 8, 10],
  	 *		sequence: [5, 1, 22, 22, 6, -1, 8, 10]  
	 */
	public static boolean isValidSubsequenceV1(List<Integer> array, List<Integer> sequence) {
		
		int lastFoundIndex = -1;
		boolean found;
		for( int number : sequence ) {
			found = false;
			for(int i=lastFoundIndex+1;i<array.size();i++) {
				if( number==array.get(i) ) {
					lastFoundIndex = i;
					found = true;
					break;
				}
			}
			if(!found) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		List<Integer> array;
		List<Integer> sequence;
		
		array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
		
		sequence = Arrays.asList(1, 6, -1, 10);
		System.out.println(isValidSubsequenceV1(array, sequence));
		
		sequence = Arrays.asList(1, 6, -1, 7, 10);
		System.out.println(isValidSubsequenceV1(array, sequence));
		
		sequence = Arrays.asList(1, -1, 6, 10);
		System.out.println(isValidSubsequenceV1(array, sequence));
		
		sequence = Arrays.asList(6);
		System.out.println(isValidSubsequenceV1(array, sequence));
		
		sequence = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
		System.out.println(isValidSubsequenceV1(array, sequence));
		
		sequence = Arrays.asList(5, 1, 22, 22, 6, -1, 8, 10);
		System.out.println(isValidSubsequenceV1(array, sequence));
		
		System.out.println("-----------------------------");
		
		sequence = Arrays.asList(1, 6, -1, 10);
		System.out.println(isValidSubsequenceV2(array, sequence));
		
		sequence = Arrays.asList(1, 6, -1, 7, 10);
		System.out.println(isValidSubsequenceV2(array, sequence));
		
		sequence = Arrays.asList(1, -1, 6, 10);
		System.out.println(isValidSubsequenceV2(array, sequence));
		
		sequence = Arrays.asList(6);
		System.out.println(isValidSubsequenceV2(array, sequence));
		
		sequence = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
		System.out.println(isValidSubsequenceV2(array, sequence));
		
		sequence = Arrays.asList(5, 1, 22, 22, 6, -1, 8, 10);
		System.out.println(isValidSubsequenceV2(array, sequence));
		
	}
	
	
}
