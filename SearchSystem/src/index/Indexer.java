/**
 * Package to generate a Trie object containing indexed words
 */
package index;

import java.util.HashMap;

import utils.Trie;

/**
 * An indexer of files.
 * 
 * It is a boundary class
 * {@link https://www.ibm.com/support/knowledgecenter/en/SS6RBX_11.4.2/com.ibm.sa.oomethod.doc/topics/c_Boundary_Class.html}
 * 
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Moura
 */
/*
 * (non-Javadoc) Essa classe é apenas uma classe de interface com outras
 * classes, ou seja, ela é classe de fronteira(Ela não é <interface> apenas
 * interage com outras classes)
 */
public class Indexer {
	Parser p;
	HashMap<String, HashMap<Integer, Integer>> value;
	// ArrayList<String> files;

	public Indexer() {
		value = new HashMap<String, HashMap<Integer, Integer>>();
		// files = new ArrayList<>();
		p = new Parser();
	}

	/**
	 * Add a document to the database
	 * 
	 * @param String
	 *            filename The Filename that will be add in the database
	 * 
	 */
	public void addDocument(String filename) {
		Trie tree = null;
		tree = p.fillTrie(filename);
		if (tree != null) { 
			value.put(filename, null);
		}
	}

	/**
	 * Remove a document to the database
	 * 
	 * @param String
	 *            filename The Filename that will be removed in the database
	 * 
	 */
	public void removeDocument() {

	}

	/**
	 * Update the documents from the database
	 */
	public void updateDocuments() {

	}

	/**
	 * Will print a list of documents that are present on database
	 */
	public void listDocuments() {

	}

	/**
	 * Add a blacklist of words
	 */
	public void readBlacklist() {

	}

}
