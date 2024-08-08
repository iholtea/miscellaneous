package ionuth.algorithms.array;

/*
 * We have an array containing values 0 and 1
 * We play the following game: 
 * if we are positioned on the element i we can :
 * 		move backwards one position if a[i-1] is 0
 * 		move forward one or a given step_size if a[i+1] or a[i+step_size] is 0
 * 
 * So from position i we can move to i-1, i+1 or i+step_size if the value of the element is 0
 * 
 * The game can be won if we could reach a destination greater than n-1
 * ( so it can be n - the last position in the array or anything greater than n )
 * 
 * Input  
 * 	1st line - number of games
 *  then one line with n - size of array and leap( size of the step )
 *  then the actual array. 
 * 
 * TODO - the solution is either not optimal either not correct
 * check for the recursive solution on teh internets
 * https://www.hackerrank.com/challenges/java-1d-array/problem
 */

public class HR08JumpRecursive {
	
	public static boolean canWin(int leap, int[] game) {
		boolean won = false;
		int pos = 0;
		boolean canJump = true;
		String state = "";
		int backMoves = 0;
		while( canJump ) {
			
			// check if we can leap or leap wins the game
			if( pos + leap >= game.length ) {
				won = true;
				break;
			}
			else {
				if( game[pos+leap] == 0 ) {
					if( pos+leap == game.length - 1 ) {
						won=true;
						break;
					} else {
						pos = pos + leap;
						state = "JUMP";
						continue;
					}
				}
			}
			
			// do not move forward after moving backward
			if( !"BACKWARD".equals(state) ) {
				if( game[pos+1]==0 ) {
					if( pos + 1 == game.length - 1) {
						won=true;
						break;
					} else {
						pos++;
						state = "FORWARD";
						continue;
					}
				} 
			}
			
			// optimization do not move backward more than leap positions 
			if( !"BACKWARD".equals(state)  ) {
				backMoves = 0;
			}
			if( leap!=1 && backMoves<leap && pos-1>0 && game[pos-1]==0 ) {
				game[pos]=1;
				pos--;
				backMoves++;
				state = "BACKWARD";
				continue;
			}
			
			canJump = false;
		}
		
		return won;
	}
	
	public static void main(String[] args) {
		
		boolean won;
		
		won = canWin(3, new int[]{ 0, 0, 0, 0, 0 });
		System.out.println( won ? "YES" : "NO" );
		
		won = canWin(5, new int[]{ 0, 0, 1, 1, 1 });
		System.out.println( won ? "YES" : "NO" );
		
		won = canWin(3, new int[]{ 0, 0, 1, 1, 1, 0 });
		System.out.println( won ? "YES" : "NO" );
		
		won = canWin(1, new int[]{ 0, 1, 0 });
		System.out.println( won ? "YES" : "NO" );
	}
	
	
}
