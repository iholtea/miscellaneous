package ionuth.algorithms.bt;

import java.util.Deque;
import java.util.LinkedList;
/*
 * 
 * Example:
 *  
 *  tree:
 *           1   
 *        /     \
 *       2       3
 *      / \     / \ 
 *     4   5   6   7
 *    / \   \
 *   8   9  10
 */

public class P101Traversals {
	
	static void levelOrderTraversal(BinaryTree tree) {
		Deque<BinaryTree> queue = new LinkedList<BinaryTree>();
		queue.addLast(tree);
		while(!queue.isEmpty()) {
			BinaryTree node = queue.removeFirst();
			System.out.print(node.value + " ");
			if( node.left!=null ) {
				queue.addLast(node.left);
			}
			if( node.right!=null ) {
				queue.addLast(node.right);
			}
			
		}
	}
	
	static int findMax(BinaryTree tree, int max) {
		if( tree.value>max ) {
			max = tree.value;
		}
		if( tree.left!=null ) {
			max = findMax(tree.left, max);
		}
		if( tree.right!=null ) {
			max = findMax(tree.right, max);
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		
		BinaryTree node06 = new BinaryTree(6);
		BinaryTree node07 = new BinaryTree(7);
		BinaryTree node08 = new BinaryTree(8);
		BinaryTree node09 = new BinaryTree(9);
		BinaryTree node10 = new BinaryTree(10);
		
		BinaryTree node04 = new BinaryTree(4, node08, node09);
		BinaryTree node05 = new BinaryTree(5, null, node10);
		
		BinaryTree node02 = new BinaryTree(2, node04, node05);
		BinaryTree node03 = new BinaryTree(3, node06, node07);
		
		BinaryTree tree = new BinaryTree(1, node02, node03);
		
		System.out.print("Level Order Traversal: ");
		levelOrderTraversal(tree);
		
		System.out.println("");
		System.out.println("-------------------------------");
		
		int max = findMax(tree, 0);
		System.out.println("Max value: " + max);
	}
}
