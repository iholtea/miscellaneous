package ionuth.hackrank.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * 	So we have an abstract class Tree 
 * 		value color and depth as properties
 * 		abstract accept( visitor ) method
 * 
 * 	TreeNode extends Tree - has a list of children which might be
 * 		trees or leaves
 * 
 * 	TreeLeaf extends Tree - the leaf implementation
 * 
 * TODO - not all tests pass because the input does not meet a requirement
 * where when defining a certain edge the edge closer to root involving one of the 
 * nodes was previously defined. 
 * The edges definition has to be loaded in a data structure and use a DFS/BFS approach
 * to load the edges
 * https://www.hackerrank.com/challenges/java-vistor-pattern/problem
 */

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }
    public int getValue() {
        return value;
    }
    public Color getColor() {
        return color;
    }
    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);
}

/*
 * Return the sum of the values in the tree's leaves only
 */
class SumInLeavesVisitor extends TreeVis {
    
	int sum = 0;
	
	public int getResult() {
      	return sum;
    }

    public void visitNode(TreeNode node) {
      	// does nothing. The accept calls the visit on its children
    }

    public void visitLeaf(TreeLeaf leaf) {
      	sum+= leaf.getValue();
    }
}

/*
 * Return the product of values stored in all red nodes, including leaves 
 * computed modulo 10^9 + 7. 
 * Note that the product of zero values is equal to 1
 */
class ProductOfRedNodesVisitor extends TreeVis {
    
	int product = 1;
	
	public int getResult() {
      	int modulo = (int)(Math.pow(10, 9) + 7); 
		return product%modulo;
    }

    public void visitNode(TreeNode node) {
      	if( node.getColor()==Color.RED && node.getValue()!=0 ) {
      		product *= node.getValue();
      	}
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if( leaf.getColor()==Color.RED && leaf.getValue()!=0 ) {
      		product *= leaf.getValue();
      	}
    }
}

/*
 *  return the absolute difference between 
 *  	the sum of values stored in the tree's non-leaf nodes at even depth and 
 *  	the sum of values stored in the tree's green leaf nodes
 */
class FancyVisitor extends TreeVis {
	
	int sumEven = 0;
	int sumGreen = 0;
	
	public int getResult() {
      	return Math.abs(sumEven-sumGreen);
    }

    public void visitNode(TreeNode node) {
    	if(node.getDepth()%2==0) {
    		sumEven += node.getValue();
    	}
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if( leaf.getColor()==Color.GREEN ) {
    		sumGreen += leaf.getValue();
    	}
    }
}

public class HR05Visitor {
	
	/*
	 * Read and build the tree. Sample input
	 * 
	 * 5
	 * 4 7 2 5 12
	 * 0 1 0 0 1
	 * 1 2
	 * 1 3
	 * 3 4
	 * 3 5
	 * 
	 * 1st line - number of nodes
	 * 2nd line - value of nodes
	 * 3rd line - colors 0 is red, 1 is green
	 * rest of the lines - the edges
	 * 		1 2 means 2nd node is child of 1st node
	 * 		1 3 means 3rd node is child of 1st node
	 * 		3 4 means 4th node is child of 3rd node
	 */
	public static Tree solve() {
		TreeNode root = null;
		try {
			
			/*
			URL inputUrl = Result09Visitor.class.getClassLoader().getResource("result09-tree.in");
			Path inputPath = Paths.get(inputUrl.toURI());
			List<String> lines = Files.lines(inputPath).collect(Collectors.toList());
			*/
			
			InputStream inputStream = HR05Visitor.class.getClassLoader()
					.getResourceAsStream("result09-tree-02.in");
			//InputStream inputStream = System.in;
			
			List<String> lines = new ArrayList<String>();
			String fileLine;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while( (fileLine=br.readLine())!=null ) {
				lines.add(fileLine);
			}
			
			List<Integer> values = Stream.of(lines.get(1).split(" "))
					.map( val -> Integer.valueOf(val))
					.collect(Collectors.toList());
			List<Color> colors = Stream.of(lines.get(2).split(" "))
					.map( val -> "0".equals(val) ? Color.RED : Color.GREEN)
					.collect(Collectors.toList());
			
			Set<Integer> treeNodes = lines.stream().skip(3)
					.map( line -> Integer.valueOf(line.split(" ")[0]) )
					.collect(Collectors.toSet());
			
			Map<Integer, Tree> treeMap = new HashMap<Integer, Tree>();
			
			// create root
			root = new TreeNode(values.get(0), colors.get(0), 0);
			treeMap.put(1, root);
			
			for( int i=3; i<lines.size(); i++) {
				
				int parentIdx = Integer.valueOf(lines.get(i).split(" ")[0]);
				int childIdx = Integer.valueOf(lines.get(i).split(" ")[1]);
				
				// assume the inputs are in such a natural order that 
				// the parent has already been created
				TreeNode parent = (TreeNode)treeMap.get(parentIdx);
				
				if(parent==null) {
					// the assumption of the order of defining edges in the input was not correct
					// net to store all the edges in a HashMap
					// and the implemented a dfs/bfs on thos edges to be able to build the tree
				}
				
				Tree child = null;
				int childValue = values.get(childIdx-1);
				Color childColor = colors.get(childIdx-1);
				int childDepth = parent.getDepth()+1;
				if( treeNodes.contains(childIdx) ) {
					child = new TreeNode(childValue, childColor, childDepth);
				} else {
					child = new TreeLeaf(childValue, childColor, childDepth);
				}
				parent.addChild(child);
				treeMap.put(childIdx, child);
			}
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return root;
	}
	
	
	public static void main(String[] args) {
		
		Tree root = solve();
		
		SumInLeavesVisitor visitorSum = new SumInLeavesVisitor();
		root.accept(visitorSum);
		int sumLeaves = visitorSum.getResult();
		System.out.println("Sum of all leaves: " + sumLeaves);
		
		ProductOfRedNodesVisitor visitorProd = new ProductOfRedNodesVisitor();
		root.accept(visitorProd);
		int productRed = visitorProd.getResult();
		System.out.println("Product of all red nodes: " + productRed);
		
		FancyVisitor fancyVisitor = new FancyVisitor();
		root.accept(fancyVisitor);
		int fancyResult = fancyVisitor.getResult();
		System.out.println("Fancy result: " + fancyResult);
	}
	
}
