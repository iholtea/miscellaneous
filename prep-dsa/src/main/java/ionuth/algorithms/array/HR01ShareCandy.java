package ionuth.algorithms.array;

import java.util.Arrays;
import java.util.List;

/*
 * Teacher want to give candies to children in the class
 * Each child has a merit score. 
 * The rule is that a child with a higher score should receive
 * a greater number of candies than the child with lower score next to him.
 * 
 * Input:
 * 		array with the scores of the children
 * Output:
 * 		compute the minimum amount of candies the teacher should give
 * 		
 * Solution
 * NOTE: the score array represents the fixed order of the children
 * 		it should not be ordered.
 *  
 * Input [ 1 2 2 ]
 * child1 - 1 candy
 * child2 - 2 candy
 * child3 - 1 candy   
 *
 * Input [ 2 3 5 4 3]
 * child1 - 1 candy
 * child2 - 2 candy
 * child3 - 3 candy
 * child4 - 1 candy
 * child5 - 1 candy
 *
 */

public class HR01ShareCandy {
	
	
	public static int distributeCandy(List<Integer> score) {
		
		int totalCandy = 1;
		int candyPerChild = 1;
		for( int i=1; i<score.size();i++ ) {
			if( score.get(i) > score.get(i-1)) {
				candyPerChild++;
			} else {
				candyPerChild=1;
			}
			totalCandy+=candyPerChild;
		}
		
		return totalCandy;
	}
	
	public static void main(String[] args) {
		
		List<Integer> score;
		
		score = Arrays.asList(1,2,2);
		System.out.println(distributeCandy(score));
		
		score = Arrays.asList(2,4,2,6,1,7,8,9,2,5);
		System.out.println(distributeCandy(score));

	}
	
}
