/**
 * 
 */
package tree;

import java.awt.List;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author valmir
 *
 */
public class Node {
	
	private Map<Charset, Node> childrens;
	private boolean info;
	private HashMap <List<String,Map.Entry<Integer, Integer>>> value;

	/**
	 * 
	 */
	public Node() {
		
		node = new HashMap<Charset,Node>();
	}

}
