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
	 * A method to insert nodes in this tree
	 * 
	 * @param key
	 *            the word to store on this tree
	 * @param fileInfo
	 *            a pair containing the info of the word
	 */
	@Deprecated
	public void insert(String key, String fileName, Integer lineNumber, Integer occurences) {
		if (key == null || key.isEmpty()) {
			return;
		}
		TrieNode current = getRoot(key);
		HashMap<Integer, Integer> occurence = new HashMap<>();
		HashMap<String, HashMap<Integer, Integer>> fileInfo = new HashMap<>();
		occurence.put(lineNumber, occurences);
		fileInfo.put(fileName, occurence);
		if (search(key) != null)
			search(key).addValue(fileInfo);

		if (current == null) {
			root.add(new TrieNode(current, key, fileInfo));
		} else {
			boolean inserted = false;
			int index = 1; // Serve to know where to begin the substring to add
			for (char ch : key.substring(1).toCharArray()) {
				// if ch not present create a new node and enter the character in the current
				// node;
				if (current.getChild(ch) == null) {
					TrieNode next = new TrieNode(current, key.substring(index), fileInfo);
					current.getChildrens().put(ch, next);
					inserted = true;
					break;
				}
				// check if character is present;
				current = current.getChild(ch);
				// increment actual index of this for
				index++;
			}
			if (!inserted) {
				current.setInfo(true);
			}
		}
	}

	/**
	 * A method to insert nodes in this tree
	 * 
	 * @param key
	 *            the word to store on this tree
	 * @param fileInfo
	 *            a pair containing the info of the word
	 */
	public void insert(String key, String fileName, Integer lineNumber) {
		if (key == null || key.isEmpty()) {
			return;
		}
		TrieNode current = getRoot(key);
		HashMap<Integer, Integer> occurence = new HashMap<>();
		HashMap<String, HashMap<Integer, Integer>> fileInfo = new HashMap<>();

		occurence.put(lineNumber, 1);
		fileInfo.put(fileName, occurence);

		if (current == null) {
			root.add(new TrieNode(current, key, fileInfo));
		} else {
			boolean inserted = false;
			int index = 1; // Serve to know where to begin the substring to add
			for (char ch : key.substring(1).toCharArray()) {
				// if ch not present create a new node and enter the character in the current
				// node;
				if (current.getChild(ch) == null) {
					TrieNode next = new TrieNode(current, key.substring(index), fileInfo);
					current.getChildrens().put(ch, next);
					inserted = true;
					break;
				}
				// check if character is present;
				current = current.getChild(ch);
				// increment actual index of this for
				index++;
			}
			
			if (!inserted) {
				current.setInfo(true);
				if (current.getValue() == null || current.getValue().isEmpty()) {
					occurence = new HashMap<>();
					fileInfo = new HashMap<>();
					occurence.put(lineNumber, 1);
					fileInfo.put(fileName, occurence);
					current.setValue(fileInfo);
				} else {
					fileInfo = current.getValue();
					if (fileInfo.containsKey(fileName)) {
						occurence = fileInfo.get(fileName);
						if (occurence.containsKey(lineNumber)) {
							occurence.put(lineNumber, occurence.get(lineNumber) + 1);
						} else {
							occurence.put(lineNumber, 1);
						}
					} else {
						occurence = new HashMap<>();
						occurence.put(lineNumber, 1);
						fileInfo.put(fileName, occurence);
					}
					current.setValue(fileInfo);
				}
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
		if (key == null)
			return;
		if (key.length() > 1) {
			TrieNode current = search(key);
			while (current != null && current.getChildrens() != null && current.getChildrens().isEmpty()) {
				TrieNode father = current.getFather();
				if (father != null) {
					current.setInfo(false);
					father.getChildrens().remove(current.getKey());
				}
				current = father;
			}
			if (current == null && getRoot(key) != null && getRoot(key).getChildrens() != null
					&& getRoot(key).getChildrens().isEmpty()) {
				root.remove(getRoot(key));
			}
		} else if (search(key) != null) {
			root.remove(search(key));
		}
	}

	/**
	 * Remove a node occurrence of the tree
	 * 
	 * @param key
	 *            the word to remove on this tree
	 * @param fileName
	 *            the name of the file to remove occurrence
	 */
	public void removeFileOccurrence(String key, String fileName) {
		if (key == null)
			return;
		if (key.length() > 1) {
			TrieNode current = search(key);
			if (current != null && current.getValue().containsKey(fileName)) {
				current.getValue().remove(fileName);
				if (current.getValue().isEmpty()) {
					while (current != null && current.getChildrens() != null && current.getChildrens().isEmpty()) {
						TrieNode father = current.getFather();
						if (father != null) {
							current.setInfo(false);
							father.getChildrens().remove(current.getKey());
						}
						current = father;
					}
					if (current == null && getRoot(key) != null && getRoot(key).getChildrens() != null
							&& getRoot(key).getChildrens().isEmpty()) {
						root.remove(getRoot(key));
					}
				}
			}
		} else if (search(key) != null && search(key).getValue().containsKey(fileName)) {
			search(key).getValue().remove(fileName);
			if (search(key).getValue().isEmpty()) {
				root.remove(search(key));
			}
		}
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
		if (key.isEmpty())
			return null;
		TrieNode current = getRoot(key);
		if (current != null) {
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
			if (!key.isEmpty() && current.getKey() == key.charAt(0))
				root = current;
		return root;
	}

	public void printRoots() {
		for (TrieNode node : root)
			System.out.println(node.getKey());
	}

	public ArrayList<TrieNode> getAllRoots() {
		return root;
	}

	/**
	 * Get all words that start with begin parameter
	 * 
	 * @param begin
	 *            The begin of a word to search
	 * @return a list of all words in the tree with the referenced begin
	 */
	public ArrayList<String> getWords(String begin) {
		ArrayList<String> words = new ArrayList<>();
		TrieNode current = getRoot(begin);
		if (begin.length() == 1 && current.getInfo() && current.getFather() == null) {
			words.add(begin);
		} else if (current != null & search(begin) != null) {
			words.add(begin);
		}
		int index = 0;
		for (char c : begin.toCharArray()) {
			if (current.getChild(c) != null && begin.length() > index) {
				current = current.getChild(c);
				index++;
			}
		}
		HashMap<Character, TrieNode> curChildrens = current.getChildrens();
		char c;
		String wordAux = begin;
		for (TrieNode child : curChildrens.values()) {
			c = child.getKey();
			words.addAll(getWords(wordAux + c));
		}
		return words;
	}
}