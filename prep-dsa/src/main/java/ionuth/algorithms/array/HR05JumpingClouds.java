package ionuth.algorithms.array;

import java.util.Arrays;
import java.util.List;

/*
 *	There is a mobile game where you can jump on clouds
 *	There are 2 types of clouds - thunder and cumulus
 *	The player can jump only on cumulus clouds and only in steps of 1 or 2
 *	So he can jump on the next cloud or on the 2nd next cloud. No bigger jumps
 *	He cannot jump on thunder clouds
 *	
 *	Input - array where 0 is a cumulus( safe ) cloud and 1 is thunder cloud
 *	Example [ 0, 1, 0, 0, 0, 1, 0 ]
 *	
 *	Determine the minimum number of jumps to finish the game.
 *	Note : it is always possible to finish the game.
 *
 * 	For the above output we have possible winning paths( index of clouds in the array )
 * 	0 -> 2 -> 3 -> 4 -> 6 
 *  0 -> 2 -> 4 -> 6
 *  
 *  So shortest path has 3 jumps
 *  
 *  NOTE - check if this is the optimal solution. 
 *  	might not pass all the tests in the required time span. 
 *  
 */


public class HR05JumpingClouds {
	
	public static int jumpingOnClouds(List<Integer> c) {
	    	
		int jumps = 0;
		int currentIndex = 0;
			
		while( currentIndex < c.size()-1 ) {
			jumps++;
			// always jump 2 steps if possible
			// since it is always possible to win the game we have to check just 
			// if are on the before last cloud from where we can do just a 1 step jump
			if( currentIndex<c.size()-2 && c.get(currentIndex+2)==0 ) {
				currentIndex += 2;
			} 
			else {
				currentIndex += 1;
			}
		}
		return jumps;
	}
	
	public static void main(String[] args) {
		
		List<Integer> c = null;
		
		c = Arrays.asList(0, 0, 0, 1, 0, 0);
		System.out.println( jumpingOnClouds(c) );
		
		c = Arrays.asList(0, 1, 0, 0, 0, 1, 0);
		System.out.println( jumpingOnClouds(c) );
		
		c = Arrays.asList(0, 0, 1, 0, 0, 1, 0);
		System.out.println( jumpingOnClouds(c) );
		
		c = Arrays.asList(0, 0, 0, 0, 1, 0);
		System.out.println( jumpingOnClouds(c) );
		
		
		
	}
		
	
}
