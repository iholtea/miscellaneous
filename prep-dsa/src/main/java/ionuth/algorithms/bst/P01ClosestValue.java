package ionuth.algorithms.bst;

/*
 * BST - Binary Search Tree
 * A node is a valid BST node if
 * 		its value is strictly greater than the values of every node to its left
 * 		its value is less than or equal to the values of every node to its right
 * 		its children are either valid BST nodes or null
 * 
 * Write a function that takes in a BST and a target integer value and returns
 * the closest value to that value contained in the BST
 * (you can assume there is only one single value)
 * 
 * Example:
 * tree:
 *          10
 *        /    \
 *       5      15
 *      / \    /  \
 *     2   6  13   22
 *    /         \
 *   1          14 
 * 
 * targetValue: 12
 * 
 * Function should return 13
 */

public class P01ClosestValue {
	
	public static int findClosestValueInBst(BST tree, int target) {
		//return findClosestValueV2(tree, target, tree.value); 
		return findClosestValue(tree, target, tree.value);
	}
	
	/*
	 * start with closest value equal to some number( can be the root value or Integer.Max )
	 * 
	 * 1. in each call compare |target-tree.value| with |target-closestValue|
	 *    to see if the closestValue has to be updated with the current node.
	 * 
	 * 2. since we have a BST we can optimize the search.
	 *    - if the target is smaller than the current node value we can find the closest 
	 *    value in the left side of the tree and dismiss the right side.
	 *    - if the target is greater than the current node value we can find the closest 
	 *    value in the right side of the tree and dismiss the left side.    		
	 * 
	 */
	public static int findClosestValue(BST tree, int target, int closestValue) {
		
		if( Math.abs(tree.value-target) < Math.abs(closestValue-target) ) {
			closestValue = tree.value;
		}
		
		if( target<tree.value && tree.left!=null ) {
			return findClosestValue(tree.left, target, closestValue);
		} else if( target>tree.value && tree.right!=null ) {
			return findClosestValue(tree.right, target, closestValue);
		} else {
			return closestValue;
		}
	}
	
	/*
	 * Visit every node of the tree - O( n ) not optimal
	 */
	public static int findClosestValueV2(BST tree, int target, int closestValue) {
		
		if( Math.abs(tree.value-target) < Math.abs(closestValue-target) ) {
			closestValue = tree.value;
		}
		
		if(tree.left!=null) {
			closestValue = findClosestValueV2(tree.left, target, closestValue);
		}
		
		if( tree.right!=null ) {
			closestValue = findClosestValueV2(tree.right, target, closestValue);
		}
		
		return closestValue;
		
	}
	
	public static void displayRootFirst(BST tree) {
		System.out.print( tree.value + " ");
		if(tree.left!=null) {
			displayRootFirst(tree.left);
		}
		if(tree.right!=null) {
			displayRootFirst(tree.right);
		}
	}
	
	
	public static void main(String[] args) {
		
		BST node01 = new BST(1);
		BST node02 = new BST(2, node01, null);
		BST node06 = new BST(6);
		BST node05 = new BST(5,node02,node06);
		
		BST node14 = new BST(14);
		BST node13 = new BST(13, null, node14);
		BST node22 = new BST(22);
		BST node15 = new BST(15,node13,node22);
		
		BST tree = new BST(10, node05, node15);
		
		displayRootFirst(tree);
		System.out.println("");
		
		int val = findClosestValueInBst(tree, 12);
		System.out.println(val);
		
		val = findClosestValueInBst(tree, -1);
		System.out.println(val);
		
	}
}
