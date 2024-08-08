package ionuth.algorithms.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Node depth in a binary tree = distance between a node and the root
 * So the depth of the root node is 0
 *  
 *  Write a function that takes in a binary tree and returns the sum of 
 *  all its nodes' depths
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
 *  Output: 19
 *  depth of node 2 is 1
 *  depth of node 4 is 2
 *  depth of node 8 is 3
 *  .....
 *  their sum = 2*1 + 4*2 + 3*3 = 19 
 */


public class P02NodeDepths {
	
	public static int nodeDepths(BinaryTree root) {
		
		List<Level> depthList = new ArrayList<>();
		nodeDepths(root, 0, depthList);
		int sum = depthList.stream().map(elem -> elem.depth).reduce(0, Integer::sum);
		
		depthList.forEach(System.out::println);
		
		return sum;
		
	}
	
	public static void nodeDepths(BinaryTree root, int depth, List<Level> depthList) {
		
		depthList.add(new Level(root,depth));
		if( root.left!=null ) {
			nodeDepths(root.left, depth+1, depthList);
		}
		if( root.right!=null ) {
			nodeDepths(root.right, depth+1, depthList);
		}
		
	}
	
	
	public static int nodeDepthsV2(BinaryTree root) {
		int sumDepths = 0;
		Stack<Level> stack = new Stack<>();
		stack.push(new Level(root,0));
		while( !stack.isEmpty() ) {
			Level level = stack.pop();
			sumDepths += level.depth;
			if( level.tree.left!=null ) {
				stack.push( new Level(level.tree.left, level.depth+1) );
			}
			if( level.tree.right!=null ) {
				stack.push( new Level(level.tree.right, level.depth+1) );
			}
		}
		return sumDepths;
	}
	
	
	static class Level {
		BinaryTree tree;
		int depth;
		Level(BinaryTree tree, int depth) {
			this.tree = tree;
			this.depth = depth;
		}
		@Override
		public String toString() {
			return "tree node: " + tree.value + " depth: " + depth;
		}
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
		
		System.out.println("sum of depths: " + nodeDepths(tree) );
		
		System.out.println("sum of depths via iterative stack implementation: " + nodeDepthsV2(tree) );
	}
}
