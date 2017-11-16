/**
 * Archive that contains a implementation of the class of node for one tree.
 */
package tree;

import java.util.HashMap;

/**
 * Class that represent a node of the tree.
 * 
 * @author Valmir Correa
 * @author Larissa Moura
 * @author Ailson Forte dos Santos
 */
public class Node {

	private Character key;
	private HashMap<Character, Node> childrens; // children of this node
	private boolean info; // info if a node form one word
	private Pair<String, HashMap<Integer, Integer>> value; // Contains a text, amount by line and line.

	/**
	 * Constructor
	 */
	public Node(String key, Pair<String, HashMap<Integer, Integer>> values) {
		if (!key.isEmpty() && key.length() == 1) {
			this.key = key.charAt(0);
			info = true;
			value = values;
		}
		childrens = new HashMap<Character, Node>();
	}

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

	public void setInfo(boolean info) {
		this.info = info;
	}

	public HashMap<Character, Node> getChildrens() {
		return childrens;
	}

	public Node getChild(char key) {
		for (Character child : childrens.keySet())
			if (childrens.get(child).getKey() == key)
				return childrens.get(child);
		return null;
	}

	public boolean setChildrens(String key) {
		if (!key.isEmpty() && key.charAt(0) == this.key && key.length() > 1) {
			Node node = getChild(key.charAt(1));
			if (node == null) {
				childrens.put(key.charAt(0), new Node(key.substring(1, key.length()), value));
				return true;
			} else {
				node.setChildrens(key);
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retorno = "" + key;
		for (char child : childrens.keySet())
			retorno += child;
		if (info)
			retorno += value.toString();
		return retorno;
	}
}
