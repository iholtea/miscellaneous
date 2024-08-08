package ionuth.algorithms.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
 * Given an array on N integers
 * Find the maximum number of unique integers among all 
 * possible contiguous sub-arrays of size M
 * 
 * Example:
 * array = [ 5 3 5 2 3 2 ]
 * M = 3
 * 
 * So we need to check contiguous sub-arrays of size 3 in the original array
 * These are
 * 5 3 5 - 2 unique numbers
 * 3 5 2 - 3 unique numbers
 * 5 2 3 - 3 unique numbers
 * 2 3 2 - 2 unique numbers
 * 
 * So the answer is 3
 * 
 */
public class HR06Subarrays {
	
	// use a HashSet to check how many unique numbers there are
	// TODO check if this version passes all tests in a limited amount of time
	// https://www.hackerrank.com/challenges/java-dequeue/problem
	public static int maxUniquesV1( int[] input, int m ) {
		
		Deque<Integer> deque = new ArrayDeque<Integer>();
		int currentMax = 0;
		int first = 0;
		
		for(int i=0; i<input.length; i++ ) {
			int num = input[i];
			if(i>=m) {
				first = deque.poll();
			}
			deque.add(num);
			if( num==first ) {
				// if we add to the deque the same number we polled
				continue;
			}
			Set<Integer> set = new HashSet<Integer>();
			set.addAll( deque );
			if( set.size()>currentMax ) {
				currentMax = set.size();
				// slight optimization if unique numbers are already the size of sub-array
				// it cannot grow any bigger than that
				if( currentMax==m ) {
					break;
				}
			}
		}
		
		return currentMax;
	}
	
	public static int maxUniquesV2( int[] input, int m ) {
		
		Deque<Integer> deque = new ArrayDeque<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		int max = 0;
		int first = 0;
		
		for(int i=0; i<input.length; i++ ) {
			
			int num = input[i];
			deque.add(num);
			set.add(num);
			
			if(deque.size()==m) {
				if( set.size()>max ) {
					max = set.size();
					// slight optimization if unique numbers are already the size of sub-array
					// it cannot grow any bigger than that
					if( max==m ) {
						break;
					}
				}
				first = deque.poll();
				// first might still exist in the deque - was a duplicate
				if( !deque.contains(first) ) {
					set.remove(first);
				}
			}
			
		}
		
		return max;
	}

	
	public static void main(String[] args) {
		
		int[] input = {5, 3, 5, 2, 3, 2};
		int m = 3;
		
		System.out.println( maxUniquesV1(input, m) );
		
	}
}
