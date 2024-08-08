package ionuth.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
 * Given k = number of workers.
 * and an array of positive integers representing durations of tasks.
 * All tasks are independent and can be completed in any order.
 * The number of tasks is 2k - so 2 tasks for each worker.
 * 
 * Workers will work in parallel between themselves
 * and they will each have to complete 2 tasks( those are not in parallel )
 * 
 *  The total time for all the tasks to be completed will be equal to the 
 *  time taken to complete the longest pair of tasks.
 * 
 * Problem:
 * Write a function that returns the optimal assignment of tasks so that
 * all the tasks are completed as fast as possible.
 *  
 *  Sample input:
 *  tasks = [ 1, 3, 5, 3, 1, 4 ];
 *  k=3 
 *  
 *  Sample output - it contains the indices of tasks assigned to each worker
 *  [
 *  	[0, 2],  // task 1 of duration 1 and task 3 of duration 5 - total=6
 *  	[4, 5],  // task 4 of duration 1 and task 5 of duration 4 - total=6
 *  	[1, 3],  // task 2 of duration 3 and task 4 of duration 3 - total=6
 *  ]
 *  
 *  Solution:
 *  to minimize the time for all tasks to be completed each worker has to be assigned
 *  a pair of tasks that have their summed duration as close as the pair of tasks
 *  assigned to other so that the work is spread as even
 *  As the workers are working in parallel
 *  
 *  so sort the tasks by duration and assign each worker a pair with
 *  one task with lower duration and other task with higher duration
 *  
 *  To track back the original index of a task 
 *  create a list of Pair <duration, originalIndex> and sort it by duration.   
 */

public class P04TaskAssignment {
	
	public static ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
		
		AtomicInteger index = new AtomicInteger();
		List<DurationIndex> diList = tasks.stream()
				.map(val -> new DurationIndex(val, index.getAndIncrement()))
				.collect(Collectors.toList());
		
		Collections.sort(diList);
		
		for(int i=0;i<k;i++) {
			int idx1 = diList.get(i).index;
			int idx2 = diList.get(diList.size()-1-i).index;
			ArrayList<Integer> workerTasks = new ArrayList<Integer>();
			workerTasks.add(idx1);
			workerTasks.add(idx2);
			result.add(workerTasks);
		}
		
		return result;  
	}
	
	static class DurationIndex implements Comparable<DurationIndex> {
		
		int duration;
		int index;
		
		public DurationIndex(int duration, int index) {
			this.duration = duration;
			this.index = index;
		}
		
		@Override
		public String toString() {
			return "duration: " + duration + " index: " + index;
		}

		@Override
		public int compareTo(DurationIndex other) {
			return Integer.compare(this.duration, other.duration);
		}
		
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> tasks =  new ArrayList<Integer>(Arrays.asList( 1, 3, 5, 3, 1, 4));
		ArrayList<ArrayList<Integer>> result = taskAssignment(3, tasks);
		
		for( List<Integer> task : result) {
			System.out.println( "[" + task.get(0) + "," +task.get(1) + "]" );
		}
	}
}
