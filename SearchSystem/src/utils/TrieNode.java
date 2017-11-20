/**
 * Archive that contains a implementation of the class of node for one tree.
 */
package utils;

import java.util.HashMap;

/**
 * Class that represent a node of a Trie tree.
 * 
 * @author Valmir Correa
 * @author Larissa Moura
 * @author Ailson Forte dos Santos
 */
public class TrieNode {

	private Character key;
	private HashMap<Character, TrieNode> children; // children of this node
	private boolean info; // true if a node form a word
	private Pair<String, HashMap<Integer, Integer>> value; // Contains the file name (String), line number and the occurrence number in line.

	/**
	 * Constructor
	 * 
	 * @param key the string to store
	 * @param values the info of the key
	 */
	public TrieNode(String key, Pair<String, HashMap<Integer, Integer>> values) {
		children = new HashMap<Character, TrieNode>();
		if (!key.isEmpty()) {
			this.key = key.charAt(0);
			if (key.length() > 1) {
				children.put(key.charAt(0), new TrieNode(key.substring(1), values));
				value = null;
			} else {
				children.put(key.charAt(0), null);
				info = true;
				value = values;
			}
		}
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
	 * @param info true if a node form a word
	 */
	public void setInfo(boolean info) {
		this.info = info;
	}

	/**
	 * @return the children
	 */
	public HashMap<Character, TrieNode> getChildrens() {
		return children;
	}

	/**
	 * @param key that specify a single child
	 * @return a node to the child within the key
	 */
	public TrieNode getChild(char key) {
		for (Character child : children.keySet())
			if (children.get(child) != null && children.get(child).getKey() == key)
				return children.get(child);
		return null;
	}

	public Pair<String,HashMap<Integer,Integer>> getValue(){
		if(info)
			return value;
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retorno = "";
		if(key != null) retorno += key;
		for (char child : children.keySet())
			if(children.get(child) != null)
				retorno += children.get(child);
		if (info && value != null)
			retorno += value.toString();
		return retorno;
	}
}
