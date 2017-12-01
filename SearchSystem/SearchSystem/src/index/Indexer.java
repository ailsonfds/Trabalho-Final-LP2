/**
 * Package to generate a Trie object containing indexed words
 */
package index;

import java.util.ArrayList;
import java.util.Collections;

import utils.Trie;
import utils.TrieNode;

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
	ArrayList<String> files;

	public Indexer() {
		files = new ArrayList<>();
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
		if (filename != null) {
			p.fillTrie(filename);
		}
		if (p.fillTrie(filename) != null) {
			files.add(filename);
		}
		//TESTE TEMPORARIO PARA CONFERIR NOS
		for (TrieNode node : p.getTree().getAllRoots()) {
			for (String word : p.getTree().getWords("" + node.getKey())) {
				System.out.println(word + "-" + p.getTree().search(word).getValue());
			}
		}
	}

	/**
	 * Remove a document to the database
	 * 
	 * @param String
	 *            filename The Filename that will be removed in the database
	 * 
	 */
	public void removeDocument(String filename) {
		files.remove(filename);
		p.removeFromTrie(filename);
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
		System.out.println("Contidos no sistema:");
		int cont = 0;
		Collections.sort(files);
		for (String file : files) {
			cont++;
			System.out.println("Arquivo " + cont + ": " + file + " com: " + p.contWords(file) + " palavras ");
		}
	}

	/**
	 * Add a blacklist of words
	 */
	public void readBlacklist() {

	}

}
