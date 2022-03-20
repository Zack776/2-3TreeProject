import java.util.ArrayList;
import java.util.Collections;



/**
 * This is very incorrect.
 * @author zackc
 *
 * @param <T>
 */
public class Tree<T extends Comparable<T>> {
	
	/**
	 * Inner class
	 * @author zackc
	 *
	 * @param <T>
	 */
		private static class Node<T extends Comparable<T>>{
		// put keys and child in data structure(not same one)
		private Node<T> parent;
		private ArrayList<Node<T>> children; // AL of children
		private ArrayList<T> keyList; // AL of keys in a node
		
		public Node(T data){
			keyList = new ArrayList<T>();
			keyList.add(data);
			children = new ArrayList<Node<T>>();	
		}
		
		public Node<T> search( T key) {
			return recursiveSearch(key);
		}
	
		private Node<T> recursiveSearch(T key){
			int index = 0;
			// base case:
			if(this == null || key.equals(keyList.get(index))) {
				return this;
			}
			else {
				while(index < keyList.size() && key.compareTo(keyList.get(index)) > 0) {
					index++;
				}
				return children.get(index).recursiveSearch(key);
			}
		}
		
		private Node<T> recursiveInsert(T key){
			// empty root
			int index = 0;
			if(isLeaf() == true) {
				keyList.add(key);
				Collections.sort(keyList);
				if(keyList.size() > 2) {
					split(this);
					if(keyList.get(0).compareTo(keyList.get(1)) > 0) {
						Collections.sort(keyList);
					}
				}
				return this;
			}
			else {
				while(index  < keyList.size() && key.compareTo(keyList.get(index)) > 0) {
					index++;
				}
				return children.get(index).recursiveInsert(key);
			}
		}
		
		public boolean contains(T key) {
			if(search(key) == null) {
				return false;
			}
			else
				return true;
		}
		
		public boolean isLeaf() {
			return children.isEmpty();
		}
		
		public void split(Node<T> node) {
			// where we take the y value s.t. x<y<z and move it up with its parent
			T max = Collections.max(keyList);
			T min = Collections.min(keyList);
			
			
			if(parent == null) {
				keyList.remove(max);
				keyList.remove(min);
				Node<T> rightChild = new Node<T>(max);
				Node<T> leftChild 	= new Node<T>(min);
				children.add(leftChild);
				children.add(rightChild);		
			}
			
			else
				keyList.remove(max);
				keyList.remove(min);
				parent.keyList.add(this.keyList.get(0));
				this.keyList.remove(0);
				Node<T> n1 = new Node<T>(max);
				Node<T> n2 = new Node<T>(min);
				parent.children.add(n1);
				parent.children.add(n2);
			
			if(parent.keyList.size() > 2){
				split(parent);
			}
		}
			
	} // end of node class
	
	private  Node<T> root; // root of this 2-3 tree.
	
	
	public Tree() {
		root = null;
	}
	
	// Tree methods:
	
	public  Node<T> getRoot(){
		return root;
	}
	
	public boolean insert(T key) {
		
		if(getRoot() == null) {
			Node<T> n1 = new Node<T>(key);
			root = n1;
			return true;
		}
		else {
			if(root.contains(key) == true) {
				System.out.println("Already within tree");
				return false;
			}
			else {
				root.recursiveInsert(key);
				return true;
			}
		}	
	}
	
	public static Tree<Integer> testTree(){
		Tree<Integer> tree = new Tree<Integer>();
		tree.insert(3);
		tree.insert(4);
		return tree;
	}
	public static void main(String[] args) {
		//Tree<Integer> tree = testTree();
		Tree<Integer> tree = new Tree<Integer>();
		tree.insert(2);
		
		//tree.insert(2);
		tree.insert(4);
		//tree.root.isLeaf();
		//System.out.println(tree.root.isLeaf());
	
	}
}
