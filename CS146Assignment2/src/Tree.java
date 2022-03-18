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
		private static final int nodeCapacity = 2; // for dealing with overflow of nodes
		private static final int childrenCapacity = 3;
		private Node<T> parent;
		private ArrayList<Node<T>> children; // AL of children
		private ArrayList<T> keyList; // AL of keys in a node
		
		public Node(T data){
			keyList.add(data);
			children = new ArrayList<Node<T>>();	
		}
		
		public Node<T> search( T key) {
			return recursiveSearch(this, key);
		}
		// new method with array use
		private Node<T> recursiveSearch(Node<T> node, T key){
			int index = 0;
			// base case:
			if(node == null) {
				return null;
			}
			else if(key.equals(keyList.get(index))){
				return node;	
			}
			else {
				while(index  < keyList.size() && key.compareTo(keyList.get(index)) > 0) {
					index++;
				}
				return recursiveSearch(children.get(index), key);
			}
		}
		/*
		private Node<T> recursiveSearch(Node<T> node, T key){
			if(node == null) {
				return null; // key wasn't found
			}
			// Case 1: The node only has a single key.
			if(node.keyList.size() == 1) {
				if(key.equals(node.keyList.get(0))) {
					return node;
				}
				if(key.compareTo(node.keyList.get(0)) < 0) {
					return recursiveSearch(node.children.get(0), key); // Left child is placed in left most spot in list
				}
				else {
					return recursiveSearch(node.children.get(1), key); // right child is placed right of left child
				}
			}
			// Case 2: the node has two keys within it.
			else {
				if( key.equals(node.keyList.get(0)) || key.equals(node.keyList.get(1))) {
					return node; // want to return the specific key I think
				}
				if(key.compareTo(node.keyList.get(0)) < 0 ) {
					return recursiveSearch(node.children.get(0), key);
				}
				else if(key.compareTo(node.keyList.get(0)) > 0 && key.compareTo(node.keyList.get(1)) < 0) {
					return recursiveSearch(node.children.get(2), key); // middle child will be put in index 2
				}
				else {
					return recursiveSearch(node.children.get(1), key);
				}
			}	
		}
		*/
		
		public boolean contains(T key) {
			return search(key) != null;
		}
		
		public boolean isLeaf(Node<T> node) {
			return node.children.isEmpty();
		}
		
		public void split(Node<T> node) {
			// where we take the y value s.t. x<y<z and move it up with its parent
			// recursively call split until there is no more overflow.
			
		
		}
		public T getMedian(T key1, T key2, T key3) {
			
			ArrayList<T> Tvals = new ArrayList<T>();
			Tvals.add(key1);
			Tvals.add(key2);
			Tvals.add(key3);
			Collections.sort(Tvals);
			return Tvals.get(1);
		
			// helper method for split that returns the the key with the middle value.
			// assign the returned key as parent of the other two keys?
		}
		
		
		
		
	} // end of node class
	
	private  Node<T> root; // root of this 2-3 tree.
	
	/**
	 * No args constructor:
	 */
	public Tree() {
		root = null;
	}
	
	// Tree methods:
	
	public  Node<T> getRoot(){
		return root;
	}
	
	//public void insert(T key) {
	//	
	//}
	
	public boolean insert(T key) {
		// check to see if key is already in tree. If so, we don't need to insert
		if(root.contains(key) == true) {
			return false;
		}
		else {
			recurseInsert(root,key);
			return true;
		}
	}
	
	
	private Node<T> recurseInsert(Node<T> node, T key){
		// first check and see if this is the first key to be added.
			if(node == null) {
				root = new Node<T>(key);
			}
		// Case 1: There exists 1 key
			if(node.numKeys == 1) {
				if(isLeaf(node) == true) {
					if(key.compareTo(node.key1) < 0) {
						T key1val = node.key1;
						node.key1 = key; // the passed in key becomes new key1; key1 is lesser key
						node.key2 = key1val;
						//return recurseInsert(node.leftChild, key);
					}
					else
						node.key2 = key;
				}
				else {
					if(key.compareTo(node.key1) < 0) {
						return recurseInsert(node.leftChild, key);
					}
					else
						return recurseInsert(node.rightChild, key);
				}
				
			}
			// Case 2: There are two keys
			if(node.numKeys == 2) {
				if(key.compareTo(node.key1) < 0) {
					return recurseInsert(node.leftChild, key);
				}
				else if(key.compareTo(node.key2) < 0) {
					return recurseInsert(node.middleChild, key);	
				}
				else {
					return recurseInsert(node.rightChild, key);
				}
			}
		return node;	
	}
	
	public int size() {
		return recurseSize(root);
	}
	
	/**
	 * Should return size of the tree- the number of keys(not nodes) in the tree.
	 * @return
	 */
	private int recurseSize(Node<T> node) { // doesnt look at each key in node
		if(node == null) {
			return 0;
		}
		else
			return 1 + recurseSize(node.leftChild) + recurseSize(node.rightChild) + recurseSize(node.middleChild);
	
	}
	
}
