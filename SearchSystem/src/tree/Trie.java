/**
 * Archieve that contains class defined a tree.
 */
package tree;

import java.util.HashMap;

/**
 * @author Valmir Correa
 * @author
 * @author
 */
public class Trie {

	private Node root;
	
	public Trie() {
		setRoot(new Node("a",null));
	}

	/**
	 * Constructor
	 */
	public Trie(String key, Pair<String, HashMap<Integer, Integer>> fileInfo) {
		setRoot(new Node(key,fileInfo));
	}

	/**
	 * 
	 * 
	 */
	public void insert(String key, Pair<String, HashMap<Integer, Integer>> fileInfo) {
		Node current = getRoot();

		for (char ch : key.toCharArray()) {
			// check if character is present;
			Node node = current.getChildrens().get(ch);

			// if not present create a new node and enter the character in the current node;
			if (node == null) {
				node = new Node(key, fileInfo);
				current.getChildrens().put(ch, node);
			}
			current = node;
		}
		current.setInfo(true);
	}

	/**
	 * 
	 */
	public void sort() {

	}

	/**
	 * Remove a node of the tree
	 */
	public void remove(String key) {
		Node current = getRoot();
		for (char ch : key.toCharArray()) {
			Node node = current.getChildrens().get(ch);

			if (node.getInfo() == true) {
				current.getChildrens().remove(ch);
			} else {
				current = node;
			}
		}
		current.setInfo(false);
	}

	/**
	 * 
	 */
	public void rearranje() {

	}

	/**
	 * 
	 */
	public boolean search(String key) {
		Node current = getRoot();

		for (char ch : key.toCharArray()) {
			Node node = current.getChildrens().get(ch);
			if (node == null)
				return false;
			current = node;
		}
		if (current.getInfo() == true)
			return true;
		else
			return false;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
}
