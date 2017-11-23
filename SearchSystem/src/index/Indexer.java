/**
 * Package to generate a Trie object containing indexed words
 */
package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import utils.Trie;
import utils.TrieNode;

/**
 * An indexer of files
 * 
 * @author Ailson Forte dos Santos
 *
 */
public class Indexer {
	Set<String> occurences;
	Parser p;
	Trie tree;

	public Indexer() {
		occurences = new HashSet<String>();
		p = new Parser();
		tree = new Trie();
	}
	
	public void FillTrie(String fileName) {
		try {
			p.open(fileName);
		}catch (IOException e) {
			
		}
		ArrayList<String> words = null;
		int line = 1;
		do {
			words = p.parsear(line);
			for (String word : words) {
				TrieNode node = tree.search(word);
				HashMap<String, HashMap<Integer, Integer>> fileInfo = null;
				if(node != null) {
					fileInfo = node.getValue();
					if(fileInfo.get(fileName) != null && fileInfo.get(fileName).get(line) != null) {
						fileInfo.get(fileName).replace(line, fileInfo.get(fileName).get(line) + 1);
					}else {
						HashMap<Integer,Integer> info = new HashMap<>();
						info.put(line, 1);
						fileInfo.replace(fileName, info);
					}
					tree.insert(word, fileInfo);
				}else {
					tree.insert(word, fileInfo);
				}
			}
			line++;
		}while(words != null);
	}
}
