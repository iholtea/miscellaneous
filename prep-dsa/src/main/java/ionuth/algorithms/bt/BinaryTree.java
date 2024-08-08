package ionuth.algorithms.bt;

public class BinaryTree {
	
	int value;
	BinaryTree left;
	BinaryTree right;
	
	public BinaryTree(int value) {
		this.value = value;
	}

	public BinaryTree(int value, BinaryTree left, BinaryTree right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "value: " + value;
	}
	
}
