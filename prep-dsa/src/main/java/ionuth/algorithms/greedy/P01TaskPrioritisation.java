package ionuth.algorithms.greedy;

import java.util.Arrays;

/*
 * Input: an array of positive integers representing the amounts of time a task takes to execute 
 * 
 * A task waiting time = the time it must wait before its execution starts.
 * If a task is executed second it has to wait for the duration of the first.
 * If a task is executed third it has to wait for the sum of the duration of the first two.
 * 
 * Example: [1, 4, 5]
 * task1 has 0 waiting time
 * task2 has 1 waiting time
 * task3 has 1 + 4 = 5 waiting time
 * 
 * Write a function that returns the minimum amount of total waiting time for all tasks
 * so it re-orders( prioritize ) the tasks so the total waiting time is minimum
 * NOTE: we are allowed to mutate the input array(since we prioritize the tasks)
 * 
 * [1, 4, 5] has a total waiting time of 0 + 1 + (1+4) = 6
 * [5, 1, 4] has a total waiting time of 0 + 5 + (5+1) = 11 
 * [1, 5, 4] has a total waiting time of 0 + 1 + (1+5) = 7 
 * 
 * So its obvious the total waiting time is minimum when the tasks are sorted by duration.
 * 
 * Total waiting time = duration1 * (array_length-1) + duration2 * (array_length-1) + ....
 * 
 */


public class P01TaskPrioritisation {
	
	public static int minimumWaitingTime(int[] queries) {
		
		Arrays.sort(queries);
		
		int totalWaitTime = 0;
		for( int idx=0;idx<queries.length-1;idx++ ) {
			totalWaitTime += queries[idx] * (queries.length-idx-1);
		}
		return totalWaitTime;
	}
	
	public static void main(String[] args) {
		
		int[] tasks;
		
		tasks = new int[]{5,1,4};
		System.out.println( minimumWaitingTime(tasks) );
		
		tasks = new int[]{3,2,1,2,6};
		System.out.println( minimumWaitingTime(tasks) );
	}
}
