/**
 * Archive that contains a implementation of the class of node for one tree.
 */
package tree;

import java.awt.List;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Valmir Correa
 * @author
 * @author
 * Classs that represent a node of the tree.
 */
public class Node {
	
	private Map<Charset, Node> childrens;									// childrens of this node
	private boolean info;													// info if a node form one word
	private HashMap <String, ArrayList<Map.Entry<Integer, Integer>>> value;	// Contains a text, amount by line and line.

	/**
	 * Constructor 
	 */
	public Node() {
		
		childrens = new HashMap<Charset, Node>();
		info = false;
		value = new HashMap<String, ArrayList<Map.Entry<Integer, Integer>>>();
	}
	
	/**
	 * Variable that informs if the way form one word.
	 * @return information about word formed.
	 */
	public boolean getInfo () {
		return info;
	}
	
	@Override
	public String toString () {
		return null;
		// a gente define algo aqui
	}
	
}
