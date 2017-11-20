/**
 * Package that contains all class needed to define a DAS Trie.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This a class that model a DAS Trie
 * 
 * @author Valmir Correa
 * @author Ailson Forte dos Santos
 * @author Larissa Moura
 */
public class Trie {

	private ArrayList<TrieNode> root; // contains all sub-trees within the first value

	public Trie() {
		root = new ArrayList<>();
	}

	/**
	 * Constructor
	 * 
	 * @param key
	 *            the word to store on this tree
	 * @param fileInfo
	 *            a pair containing the info of the word
	 */
	public Trie(String key, Pair<String, HashMap<Integer, Integer>> fileInfo) {
		insert(key, fileInfo);
	}

	/**
	 * A method to insert nodes in this tree
	 * 
	 * @param key
	 *            the word to store on this tree
	 * @param fileInfo
	 *            a pair containing the info of the word
	 */
	public void insert(String key, Pair<String, HashMap<Integer, Integer>> fileInfo) {

		TrieNode current = getRoot(key);

		if (current == null) {
			root.add(new TrieNode(key, fileInfo));
		} else {
			int index = 1; // Serve to know where to begin the substring to add
			for (char ch : key.substring(1).toCharArray()) {
				// if ch not present create a new node and enter the character in the current
				// node;
				if (current.getChild(ch) == null) {
					TrieNode next = new TrieNode(key.substring(index), fileInfo);
					current.getChildrens().put(ch, next);
					break;
				}
				// check if character is present;
				current = current.getChild(ch);
				// increment actual index of this for
				index++;
			}
		}
	}

	/**
	 * Remove a node of the tree
	 * 
	 * @param key
	 *            the word to remove on this tree
	 */
	public void remove(String key) {
		TrieNode current = getRoot(key).getChild(key.charAt(1));
		for (char ch : key.toCharArray()) {
			TrieNode node = current.getChild(ch);

			if (node == null) {
				current.getChildrens().remove(ch);
			} else {
				current = node;
			}
		}
		current.setInfo(false);
	}

	/**
	 * A method to search words in this tree
	 * 
	 * @param key
	 *            the word to seek
	 * @return the last node containing the last letter on the key and containing
	 *         the info of the file
	 */
	public TrieNode search(String key) {
		for (TrieNode current : root) {
			if (current.getKey() == key.charAt(0)) {
				key = key.substring(1);
				for (char ch : key.toCharArray()) {
					TrieNode node = current.getChild(ch);
					if (node == null)
						return null;
					current = node;
				}
				if (current.getInfo() == true)
					return current;
			}
		}
		return null;
	}

	/**
	 * A method to get a path to the key
	 * 
	 * @param key
	 *            the word to determine a path
	 * @return a node containing the path of a (sub-)tree
	 */
	public TrieNode getRoot(String key) {
		TrieNode root = null;
		for (TrieNode current : this.root)
			if (current.getKey() == key.charAt(0))
				root = current;
		return root;
	}

	public void printRoots()
	{
		System.out.println(root);
	}
}
