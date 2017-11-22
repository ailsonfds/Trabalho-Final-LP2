package utils;

import java.util.ArrayList;

public class Parser {

	public Parser() {
		
	}

	public Trie buildTrie(ArrayList<String> words, Trie trie) {
		for (String word : words) {
			// System.out.println(word);
			trie.insert(word, null);
			// trie.printRoots();
		}
		return trie;
	}
}
