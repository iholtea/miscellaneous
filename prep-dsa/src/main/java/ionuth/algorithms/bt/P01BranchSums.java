package ionuth.algorithms.bt;

import java.util.ArrayList;
import java.util.List;

/*
 * Write a function that takes in a BinaryTree and 
 * returns a list of its branch sums ordered from leftmost to rightmost
 * 
 *  A branch sum is a the sum of all values in a BinaryTree branch.
 *  A BinaryTree branch is a path of all nodes starting from root to a leaf
 *  
 *  Example:
 *  
 *  tree:
 *           1   
 *        /     \
 *       2       3
 *      / \     / \ 
 *     4   5   6   7
 *    / \   \
 *   8   9  10
 *  
 *  Output:
 *  [15, 16, 18, 10, 11] where
 *  15 = 1 + 2 + 4 + 8
 *  16 = 1 + 2 + 4 + 9
 *  18 = 1 + 2 + 5 + 10
 *  10 = 1 + 3 + 6
 *  11 = 1 + 3 + 7
 *  
 */

public class P01BranchSums {
	
	public static List<Integer> branchSums(BinaryTree root) {
		List<Integer> sums = new ArrayList<Integer>();
		branchSums(root, 0, sums);
		return sums;
	}
	
	public static void branchSums(BinaryTree tree, int currentSum, List<Integer> sums) {
		
		currentSum += tree.value;
		if(tree.left!=null) {
			branchSums(tree.left, currentSum, sums);
		}
		if(tree.right!=null) {
			branchSums(tree.right, currentSum, sums);
		}
		if(tree.left==null && tree.right==null) {
			sums.add(currentSum);
		}
	}
	
	public static List<List<Integer>> getPaths(BinaryTree root) {
		List<List<Integer>> paths = new ArrayList<List<Integer>>();
		List<Integer> currentPath = new ArrayList<Integer>();
		getPaths(root, currentPath, paths);
		return paths;
	}
	
	public static void getPaths(BinaryTree tree, List<Integer> currentPath, List<List<Integer>> paths) {
		
		currentPath.add(tree.value);
		
		if(tree.left!=null) {
			getPaths(tree.left, currentPath, paths);
		}
		if(tree.right!=null) {
			getPaths(tree.right,currentPath, paths);
		}
		if(tree.left==null && tree.right==null) {
			List<Integer> newList = new ArrayList<Integer>();
			newList.addAll(currentPath);
			paths.add(newList);
		}

		currentPath.remove(currentPath.size()-1);
		
	}
	
	public static void main(String[] args) {
		
		BinaryTree node06 = new BinaryTree(6);
		BinaryTree node07 = new BinaryTree(7);
		BinaryTree node08 = new BinaryTree(8);
		BinaryTree node09 = new BinaryTree(9);
		BinaryTree node10 = new BinaryTree(10);
		
		BinaryTree node04 = new BinaryTree(4, node08, node09);
		BinaryTree node05 = new BinaryTree(5, node10, null);
		
		BinaryTree node02 = new BinaryTree(2, node04, node05);
		BinaryTree node03 = new BinaryTree(3, node06, node07);
		
		BinaryTree tree = new BinaryTree(1, node02, node03);
		
		List<Integer> sums = branchSums(tree);
		System.out.println(sums);
		
		System.out.println("----------------------------------------");
		
		List<List<Integer>> paths = getPaths(tree);
		paths.forEach( System.out::println);
				
		
	}
}
