package ionuth.algorithms.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Given a class with a even number of students.
 * Exactly half of the students wear red shirts, the other half blue shirts
 * 
 *  Students have to be arranges in two rows:
 *  	each row contains the same number of students
 *  	all students wearing red shirts in the same row
 *  	all students wearing blue shorts in the same row
 *  	each student in back row has to be strictly taller than the on in front on him
 *  
 *  Input: two arrays containing the heights of the students
 *  	one for those wearing red shirts, one for those wearing blue shirts
 *  
 *   Write a function which checks whether the students can be arranged
 *   to follow the rules above.
 *   
 *   Example:
 *   redShirtHeights  = [ 5, 8, 1, 3, 4 ]
 *   blueShortHeights = [ 6, 9, 2, 4, 5 ]
 *   
 *   ---------------------------------------------------------
 *   
 *   Since a row must contain students of the same shirt color the student having 
 *   the maximum height from all students will be in the back row. 
 *   And so all the students having the same shirt color as him also in the back row. 
 *   
 *   sortedRed  = [ 1, 3, 4, 5, 8 ]
 *   sortedBlue = [ 2, 4, 5, 6, 9 ]
 *   
 *   So for this input output=true ( the can be arranges as per the rules ) 
 *     
 */

public class P02ClassPhotos {
	
	public static boolean classPhotos(List<Integer> redShirtHeights, List<Integer> blueShirtHeights) {
		
		Collections.sort(redShirtHeights);
		Collections.sort(blueShirtHeights);
		
		String firstRowColor = redShirtHeights.get(0) > blueShirtHeights.get(0) ? "BLUE" : "RED";   
		for( int idx=0;idx<redShirtHeights.size();idx++) {
			if("RED".equals(firstRowColor)) {
				if( redShirtHeights.get(idx)>=blueShirtHeights.get(idx) ) {
					return false;
				}
			} else {
				if( redShirtHeights.get(idx)<=blueShirtHeights.get(idx) ) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		List<Integer> redShirtHeights;
		List<Integer> blueShirtHeights;
		
		redShirtHeights  = Arrays.asList( 5, 8, 1, 3, 4 );
		blueShirtHeights = Arrays.asList( 6, 9, 2, 4, 5 );
		System.out.println( classPhotos(redShirtHeights, blueShirtHeights) );
		
		redShirtHeights  = Arrays.asList( 6, 9, 2, 4, 5, 1 );
		blueShirtHeights = Arrays.asList( 5, 8, 1, 3, 4, 9 );
		System.out.println( classPhotos(redShirtHeights, blueShirtHeights) );	
		
	}
		    
}
