/**
 * Archieve that contains class defined a tree.
 */
package tree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Valmir Correa
 * @author Ailson Forte dos Santos
 * @author Larissa Moura
 */
public class Trie {

	private ArrayList<Node> root;

	public Trie() {
		root = new ArrayList<>();
	}

	/**
	 * Constructor
	 */
	public Trie(String key, Pair<String, HashMap<Integer, Integer>> fileInfo) {
		insert(key, fileInfo);
	}

	/**
	 * 
	 * 
	 */
	public void insert(String key, Pair<String, HashMap<Integer, Integer>> fileInfo) {

		Node current = getRoot(key);

		if (current == null) {
			root.add(new Node(key, fileInfo));
		} else {

			for (char ch : key.toCharArray()) {
				// check if character is present;
				Node node = current.getChild(ch);

				// if not present create a new node and enter the character in the current node;
				if (node == null) {
					node = new Node(key, fileInfo);
					current.getChildrens().put(ch, node);
				}
				current = node;
			}
			current.setInfo(true);
		}
	}

	/**
	 * TODO Remove a node of the tree
	 */
	public void remove(String key) {
		Node current = getRoot(key).getChild(key.charAt(1));
		for (char ch : key.toCharArray()) {
			Node node = current.getChild(ch);

			if (node == null) {
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
	public boolean search(String key) {
		for (Node current : root) {
			if (current.getKey() == key.charAt(0)) {
				key = key.substring(1);
				for (char ch : key.toCharArray()) {
					Node node = current.getChild(ch);
					if (node == null)
						return false;
					current = node;
				}
				if (current.getInfo() == true)
					return true;
			}
		}
		return false;
	}

	public Node getRoot(String key) {
		Node root = null;
		for (Node current : this.root)
			if (current.getKey() == key.charAt(0))
				root = current;
		return root;
	}

	// public void setRoot(Node root) {
	// this.root = root;
	// }
}
