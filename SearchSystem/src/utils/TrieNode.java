/**
 * Archive that contains a implementation of the class of node for one tree.
 */
package utils;

import java.util.HashMap;

/**
 * Class that represent a node of a Trie tree.
 * 
 * @author Valmir Correa
 * @author Larissa Gilliane Melo de Moura
 * @author Ailson Forte dos Santos
 */
public class TrieNode {

	private Character key;
	private HashMap<Character, TrieNode> children; // children of this node
	private boolean info; // true if a node form a word
	private HashMap<String, HashMap<Integer, Integer>> value; // Contains the file name (String), line number and the
																// occurrence number in line.
	private TrieNode darthVader; // father of this node

	/**
	 * Constructor
	 * 
	 * @param father
	 *            a pointer to his father
	 * @param key
	 *            the string to store
	 * @param values
	 *            the info of the key
	 */
	public TrieNode(TrieNode father, String key, HashMap<String, HashMap<Integer, Integer>> values) {
		children = new HashMap<Character, TrieNode>();
		this.setFather(father);
		if (!key.isEmpty()) {
			this.key = key.charAt(0);
			if (key.length() > 1) {
				children.put(key.charAt(1), new TrieNode(this, key.substring(1), values));
				info = false;
				value = null;
			} else {
				info = true;
				value = values;
			}
		}
	}

	/**
	 * Give a child to this node
	 * 
	 * @param key
	 *            the string to store
	 * @param values
	 *            the info of the key
	 */
	public TrieNode insertChild(String key, HashMap<String, HashMap<Integer, Integer>> values) {
		if (!key.isEmpty() && this.key == key.charAt(0) && key.length() > 1) {
			TrieNode child = new TrieNode(this, key.substring(1), values);
			children.put(key.charAt(1), child);
		} else if (key.length() == 1) {
			info = true;
			addValue(values);
		}
		return null;
	}

	/**
	 * @return the key of this node
	 */
	public char getKey() {
		return key;
	}

	/**
	 * Variable that informs if the way form one word.
	 * 
	 * @return information about word formed.
	 */
	public boolean getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            true if a node form a word
	 */
	public void setInfo(boolean info) {
		this.info = info;
	}

	/**
	 * @return the father
	 */
	public TrieNode getFather() {
		return darthVader;
	}

	/**
	 * @param father
	 *            the father to set
	 */
	public void setFather(TrieNode father) {
		this.darthVader = father;
	}

	/**
	 * @return the children
	 */
	public HashMap<Character, TrieNode> getChildrens() {
		return children;
	}

	/**
	 * @param key
	 *            that specify a single child
	 * @return a node to the child within the key
	 */
	public TrieNode getChild(char key) {
		if (children.containsKey(key))
			return children.get(key);
		return null;
	}

	/**
	 * @return the value of this node
	 */
	public HashMap<String, HashMap<Integer, Integer>> getValue() {
		if (info)
			return value;
		return null;
	}

	/**
	 * Add values to this node
	 * 
	 * @param value
	 *            a collection to add to value using addAll metod in Collection
	 *            class
	 */
	public void addValue(HashMap<String, HashMap<Integer, Integer>> value) {
		if (info)
			for (String fileName : value.keySet()) {
				if (this.value.containsKey(fileName)) {
					this.value.get(fileName).putAll(value.get(fileName));
				}
			}
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(HashMap<String, HashMap<Integer, Integer>> value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retorno = "";
		if (key != null)
			retorno += key;
		for (char child : children.keySet())
			if (children.get(child) != null)
				retorno += children.get(child);
		if (info && value != null) {
			for (String key : value.keySet()) {
				retorno += "{" + key;
				for (Integer key_int : value.get(key).keySet()) {
					retorno += "{" + key_int + "," + value.get(key).get(key_int) + "}";
				}
				retorno += "}";
			}
		}
		return retorno;
	}
}