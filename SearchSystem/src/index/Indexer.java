/**
 * Package to generate a Trie object containing indexed words
 */
package index;

import java.util.ArrayList;
import java.util.Collections;

import exceptions.EmptyWordException;
import exceptions.FileAlreadyExistsException;
import exceptions.FileNotFoundException;
import exceptions.FileTypeException;
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
 * (non-Javadoc) Essa classe � apenas uma classe de interface com outras
 * classes, ou seja, ela � classe de fronteira(Ela n�o � <interface> apenas
 * interage com outras classes)
 */
public class Indexer {
	Parser p;
	ArrayList<String> files;
	String extension;

	public Indexer() {
		files = new ArrayList<>();
		p = new Parser();
		extension = ".txt";
	}

	/**
	 * Add a document to the database
	 * 
	 * @param String
	 *            filename The Filename that will be add in the database
	 * @throws FileTypeException
	 * 
	 */
	public void addDocument(String filename) throws FileTypeException, FileAlreadyExistsException {
		if (!(filename.toLowerCase().contains(extension.toLowerCase()))) {
			throw new FileTypeException("Please, insert a file with .txt extension.");
		} else if ((files.contains(filename))) {
			throw new FileAlreadyExistsException("This file already exists in the system.");
		} else {
			if (filename != null) {
				p.fillTrie(filename);
			}
			if (p.fillTrie(filename) != null) {
				files.add(filename);
			}
		}

		// TESTE TEMPORARIO PARA CONFERIR NOS
		for (TrieNode node : p.getTree().getAllRoots()) {
			for (String word : p.getTree().getWords("" + node.getKey())) {
				System.out.println(word + "-" + p.getTree().search(word).getValue());
			}
		} // APAGAR DEPOIS
	}

	/**
	 * Remove a document to the database
	 * 
	 * @param String
	 *            filename The Filename that will be removed in the database
	 * @throws FileTypeException
	 * 
	 */
	public void removeDocument(String filename) throws FileNotFoundException {
		if (!(files.contains(filename))) {
			throw new FileNotFoundException("You can't remove the file because he is not in the system");
		} else {
			files.remove(filename);
			p.removeFromTrie(filename);
		}
	}

	/**
	 * Update the documents from the database
	 */
	public void updateDocuments(String filename) throws FileTypeException {
		files.remove(filename);
		p.removeFromTrie(filename);
		if (!(filename.toLowerCase().contains(extension.toLowerCase()))) {
			throw new FileTypeException("Please, insert a file with .txt extension.");
		} else {
			if (filename != null) {
				p.fillTrie(filename);
			}
			if (p.fillTrie(filename) != null) {
				files.add(filename);
			}
		}
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
			System.out.println("Arquivo " + cont + ": " + file + " com: " + p.contWords(file) + " palavras. ");
		}
	}

	public void searchOR(String word) throws EmptyWordException {
		if (word != null || word.equals(" ")) {
			throw new EmptyWordException("This search is empty, please insert a valid text");
		}

	}

	public void searchAND(String word) throws EmptyWordException {
		if (word != null || word.equals(" ")) {
			throw new EmptyWordException("This search is empty, please insert a valid text");
		}

	}

	/**
	 * Add a blacklist of words
	 */
	public void readBlacklist() {

	}

}