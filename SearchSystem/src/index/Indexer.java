/**
 * 
 */
package index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import utils.Pair;
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

	public Indexer(String fileName) {
		occurences = new HashSet<String>();
		p = new Parser(fileName);
		tree = new Trie();
	}
	
	public Trie addToTrie(ArrayList<String> words, Trie trie, int line) {
		for (String word : words) {
			TrieNode node = trie.search(word);
			Pair<String,HashMap<Integer,Integer>> fileInfo = null;
			if(node != null) {
				fileInfo = node.getValue();
				if(fileInfo != null && fileInfo.getSecond() != null)
					if(fileInfo.getSecond().get(line) != null)
						fileInfo.getSecond().put(line, fileInfo.getSecond().get(line) + 1);
					else
						fileInfo.getSecond().put(line, 1);
				trie.insert(word, fileInfo);
			}else
				trie.insert(word, fileInfo);
		}
		return trie;
	}
}
