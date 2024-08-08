package ionuth.algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Rotate array definition:
 * for array [ 1 2 3 ] 
 * rotate with 1 position  -> [ 2 3 1 ]
 * rotate with 2 positions -> [ 3 1 2 ]
 * rotate with 3 positions -> [ 1 2 3 ]
 * rotate with 4 positions -> [ 2 3 1 ]
 * 
 *  So rotate with length of the array position means the original array again
 *  
 *  Input: 2 arrays. First with the data, the second with rotation operations
 *  to be performed on the data array
 *  
 *  Ex:
 *  a = [ 1, 2, 3 ]
 *  rotate = [ 1, 2, 3, 4 ]
 *  
 *  Problem:
 *  	print an array containing the indexes of the maximum element in the data array
 *  	for each rotate operation.
 *  
 *  In the example above we have the rotations
 *  [ 2 3 1 ], [ 3 1 2 ], [ 1 2 3 ], [ 2 3 1 ]
 *  Maximum element is 3, and its positions after rotations [ 1 2 3 4 ] are
 *  [1, 0, 2, 1 ]
 *  
 * 
 */
public class HR02RotateArray {
	
	
	public static List<Integer> getMaxElementIndexes(
			List<Integer> a, List<Integer> rotate) {
		
		List<Integer> indexList = new ArrayList<Integer>();
		
		int maxIndex = 0;
		int max = Integer.MIN_VALUE;
		
		// compute the index of the max element in the data array
		for( int i=0; i<a.size();i++ ) {
			if( a.get(i)>max ) {
				max = a.get(i);
				maxIndex = i;
			}
		}
		
		// for each rotation the new index of max element is maxIndex - rotation_nr
		// since rotations of array size means the original array use rotations % 
		// when we rotate the first element it goes to the last position into the array
		for( int i=0; i<rotate.size(); i++ ) {
			int idx = maxIndex - ( rotate.get(i) % a.size() );
			if(idx<0) idx= a.size() - 1;
			indexList.add( idx );
		}
		
		return indexList;
		
	}
	
	public static void main(String[] args) {
		
		List<Integer> a;
		List<Integer> rotate;
		List<Integer> indexList = null;
		
		a =	Arrays.asList(1,2,3);
		rotate = Arrays.asList(1,2,3,4);
		indexList = getMaxElementIndexes(a, rotate);
		System.out.println(indexList);
		
		a = Arrays.asList(1,2,4,3);
		rotate = Arrays.asList(1,3);
		indexList = getMaxElementIndexes(a, rotate);
		System.out.println(indexList);
		
		a = Arrays.asList(1,2,4,3,5);
		rotate = Arrays.asList(6,9,12);
		indexList = getMaxElementIndexes(a, rotate);
		System.out.println(indexList);

	}
	
}
