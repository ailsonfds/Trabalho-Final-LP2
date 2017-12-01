package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Trie;
import utils.TrieNode;

/**
 * This class is responsible to look into the files and make new files using the
 * pattern on .pdf project description.
 * 
 * A cotroller class
 * {@link https://www.ibm.com/support/knowledgecenter/en/SS6RBX_11.4.3/com.ibm.sa.oomethod.doc/topics/c_Control_Class.html}
 * 
 * @author Ailson Forte dos Santos
 *
 */
public class Parser {
	Reader reader;
	Writer writer;

	String fileName;
	Trie tree;

	public Parser() {
		try {
			reader = new Reader("index.txt");
			writer = new Writer("index.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		tree = new Trie();
	}

	public ArrayList<String> gotToLine(int lineNumber) {
		ArrayList<String> words;
		try {
			reader.close();
			open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		do {
			words = reader.readBreackedLine();
		} while (words != null && lineNumber-- > 0);
		return words;
	}

	public void open(String fileName) throws IOException {
		reader.close();
		reader = new Reader(fileName);
		this.fileName = fileName;
	}

	public Trie fillTrie(String fileName) {
		try {
			open(fileName);
		} catch (IOException e) {
			System.out.println(e);
		}
		ArrayList<String> words = null;
		int line = 0;
		do {
			words = gotToLine(line);
			if (words != null) {
				for (String word : words) {
					if (word != null) {
						TrieNode node = tree.search(word);
						HashMap<String, HashMap<Integer, Integer>> fileInfo = null;
						if (node != null) {
							fileInfo = node.getValue();
							if (fileInfo != null) {
								if (fileInfo.get(fileName) != null && fileInfo.get(fileName).get(line) != null) {
									fileInfo.get(fileName).replace(line, fileInfo.get(fileName).get(line) + 1);
								} else {
									HashMap<Integer, Integer> info = new HashMap<>();
									info.put(line, 1);
									fileInfo.replace(fileName, info);
								}
								tree.insert(word, fileName, line, fileInfo.get(fileName).get(line) + 1);
							}
						} else {
							tree.insert(word, fileName, line, 1);
						}
					}
				}
				line++;
			}
		} while (words != null);
		return tree;
	}

	public Trie removeFromTrie(String filename) {
		try {
			open(fileName);
		} catch (IOException e) {
			System.out.println(e);
		}
		ArrayList<String> words = null;
		int line = 0;
		do {
			words = gotToLine(line);
			if (words != null) {
				for (String word : words) {
					if (word != null) {
						tree.remove(word);
					}
				}
			}
		} while (words != null);

		return tree;
	}

	public int contWords(String filename) {
		ArrayList<String> words = null;
		int line = 0, cont = 0;
		try {
			open(fileName);
		} catch (IOException e) {
			System.out.println(e);
		}
		do {
			words = gotToLine(line);
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

	public Trie getTree() {
		return tree;
	}
}
