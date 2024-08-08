package ionuth.algorithms.array;

/*
 * 
 *	Hiker monitors the type of each step uphill - U or downhill - D
 *	each hike starts and ends at the the sea level
 *	
 *	mountain - sequence of steps above sea level 
 *		starting with a step up from sea level and ending with a step down to sea level
 *		
 *	valley - sequence of steps below sea level
 *	 	starting with a step down from sea level and ending with a step up to sea level
 *	
 *
 *	Given the sequence of up and down steps find the number of valleys walked through
 *	steps = [ DDUUUUDD ]	
 *	The hiker first enters a valley 2 units deep. 
 *	Then they climb out and up onto a mountain 2 units high. 
 *	Number of valleys = 1 	
 *
 */
public class HR04ValleyMountain {
	
	public static int countingValleys(int steps, String path) {
	    
		int valleys = 0;
		int level = 0;
		for( int i=0; i<path.length(); i++ ) {
			if( path.charAt(i)=='D' ) {
				if(level==0) {
					valleys++;
				}
				level--;
			} else {
				level++;
			}
		}
		
		return valleys;

	}
		
	public static void main(String[] args) {
		
		int steps = 8;
		String path = "DDUUUUDD";
		
		System.out.println( countingValleys(steps, path) );
		
	}
	
}
