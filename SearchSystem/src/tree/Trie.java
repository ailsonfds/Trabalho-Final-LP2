/**
 * Archieve that contains class defined a tree.
 */
package tree;

/**
 * @author Valmir Correa
 * @author
 * @author
 */
public class Trie {

	private Node root;

	/**
	 * Constructor
	 */
	public Trie() {
		setRoot(new Node());
	}

	/**
	 * 
	 * 
	 */
	public void insert(String key) {
		Node current = getRoot();

		for (char ch : key.toCharArray()) {
			// check if character is present;
			Node node = current.getChildrens().get(ch);

			// if not present create a new node and enter the character in the current node;
			if (node == null) {
				node = new Node();
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
