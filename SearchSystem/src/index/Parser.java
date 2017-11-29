package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Trie;
import utils.TrieNode;

/**
 * This class is responsible to look into the files and make new files using the pattern on .pdf project description.
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
		do {
			words = reader.readBreackedLine();
		} while (words != null && lineNumber-- > 0);
		words = reader.readBreackedLine();
		try {
			reader.close();
			open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}

	public void open(String fileName) throws IOException {
		reader.close();
		reader = new Reader(fileName);
		this.fileName = fileName;
	}
	
	public void fillTrie(String fileName) {
		try {
			open(fileName);
		} catch (IOException e) {

		}
		ArrayList<String> words = null;
		int line = 1;
		do {
			words = gotToLine(line);
			for (String word : words) {
				TrieNode node = tree.search(word);
				HashMap<String, HashMap<Integer, Integer>> fileInfo = null;
				if (node != null) {
					fileInfo = node.getValue();
					if (fileInfo.get(fileName) != null && fileInfo.get(fileName).get(line) != null) {
						fileInfo.get(fileName).replace(line, fileInfo.get(fileName).get(line) + 1);
					} else {
						HashMap<Integer, Integer> info = new HashMap<>();
						info.put(line, 1);
						fileInfo.replace(fileName, info);
					}
					tree.insert(word, fileName, line, fileInfo.get(fileName).get(line) + 1);
				} else {
					tree.insert(word, fileName, line, 1);
				}
			}
			line++;
		} while (words != null);
	}
}
