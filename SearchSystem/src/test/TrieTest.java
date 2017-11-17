package test;

import java.util.HashMap;

import org.junit.Test;

import utils.Pair;
import utils.Trie;
import utils.TrieNode;

/**
 * A class that contains a test to Trie class
 * 
 * @author Ailson Forte dos Santos]
 * @author Larissa Moura
 *
 */
public class TrieTest {
	@Test
	public void trieTest() {
		Trie w = new Trie();
		HashMap<Integer, Integer> occurences = new HashMap<>();
		occurences.put(1, 1);
		w.insert("testes", (new Pair<String,HashMap<Integer, Integer>>("teste.txt", occurences)));
		w.insert("larissa", null);
		w.insert("testeailson", null);

		System.out.println(w.search("testes").getInfo());
		System.out.println(w.search("larissa").getInfo());
		System.out.println(w.search("testeailson").getInfo());
		
		if (w.search("testes") != null && w.search("larissa") != null && w.search("testeailson") != null) {
			TrieNode nodeTestes = w.getRoot("testes");
			TrieNode nodeLarissa = w.getRoot("larissa");
			TrieNode nodeTesteailson = w.getRoot("testeailson");

			System.out.println(nodeTestes);
			System.out.println(nodeLarissa);
			System.out.println(nodeTesteailson);
		}
		w.remove("larissa");
		System.out.println(w.search("larissa"));
		w.remove("testes");
		System.out.println(w.search("testes"));
		try {
			w.remove("ttestes");
		} catch (NullPointerException e) {
			System.out.println("Deu certo");
		}
	}
}
