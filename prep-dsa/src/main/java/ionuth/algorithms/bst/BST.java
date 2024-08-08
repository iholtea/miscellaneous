package ionuth.algorithms.bst;

public class BST {
	
	int value;
	BST left;
	BST right;
	
	public BST(int value) {
		this.value = value;
	}
	
	public BST(int value, BST left, BST right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
