package index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.Trie;

/**
 * This class is responsible to look into the files and make new files using the
 * pattern on .pdf project description.
 * 
 * A cotroller class
 * {@link https://www.ibm.com/support/knowledgecenter/en/SS6RBX_11.4.3/com.ibm.sa.oomethod.doc/topics/c_Control_Class.html}
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class Parser {
	Reader reader;
	Writer writer;

	Trie tree;

	/**
	 * Constructor
	 * 
	 * @throws FileNotFoundException
	 *             an exception case file can''t be opened
	 */
	public Parser() {
		try {
			reader = new Reader("index.txt");
			writer = new Writer("index.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		tree = new Trie();
	}

	/**
	 * 
	 * Receives a line from the file and reads.
	 * 
	 * @param lineNumber
	 *            the line that will be read
	 * @param filename
	 *            The file name
	 * @return a list within the words of a line
	 */
	public ArrayList<String> gotToLine(int lineNumber, String filename) {
		ArrayList<String> words = null;
		try {
			reader.close();
			open(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		do {
			words = reader.readBreackedLine();
		} while (words != null && lineNumber-- > 0);
		return words;
	}

	/**
	 * 
	 * Open a document .txt
	 * 
	 * @param filename
	 *            The file name
	 */
	public void open(String filename) throws IOException {
		reader.close();
		reader = new Reader(filename);
	}

	/**
	 * 
	 * Put the words of the file into a Trie.
	 * 
	 * @param filename
	 *            The file name
	 * @return A Trie with the words from the file
	 * 
	 */
	public Trie fillTrie(String filename) {
		ArrayList<String> words = null;
		int line = 0;
		do {
			words = gotToLine(line, filename);
			if (words != null) {
				for (String word : words) {
					if (word != null) {
						tree.insert(word, filename, line);
					}
				}
				line++;
			}
		} while (words != null);
		return tree;
	}

	/**
	 * 
	 * Remove the words of the file from the Trie.
	 * 
	 * @param filename
	 *            The file name
	 * @return A Trie with the words from the file
	 * 
	 */
	public Trie removeFromTrie(String filename) {
		ArrayList<String> words = null;
		int line = 0;
		do {
			words = gotToLine(line++, filename);
			if (words != null) {
				for (String word : words) {
					tree.removeFileOccurrence(word, filename);
				}
			}
		} while (words != null);

		return tree;
	}

	/**
	 * 
	 * Calculates the number of words in the file.
	 * 
	 * @param filename
	 *            The file name
	 * @return The number of words present in the file.
	 * 
	 */
	public int contWords(String filename) {
		ArrayList<String> words = null;
		int line = 0, cont = 0;
		do {
			words = gotToLine(line, filename);
			if (words != null) {
				for (String word : words) {
					if (word != null) {
						cont++;
					}
				}
				line++;
			}
		} while (words != null);
		return cont;
	}

	/**
	 * 
	 * Return the Trie
	 * 
	 * @return The number of words present in the file.
	 * 
	 */
	public Trie getTree() {
		return tree;
	}
}