package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Pair;
import utils.Trie;
import utils.TrieNode;

public class Parser {
	Reader reader;
	Writer writer;

	public Parser(String fileName) {
		try {
			reader = new Reader(fileName);
			writer = new Writer("index.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
