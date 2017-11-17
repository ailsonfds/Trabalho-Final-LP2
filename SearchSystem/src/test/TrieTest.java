package test;

import org.junit.Test;

import tree.Node;
import tree.Trie;

public class TrieTest {
	@Test
	public void trieTest() {
		Trie w = new Trie();
		w.insert("testes", null);
		w.insert("larissa", null);
		w.insert("testeailson", null);

		System.out.println(w.search("testes"));
		System.out.println(w.search("larissa"));
		System.out.println(w.search("testeailson"));
		
		if (w.search("testes") && w.search("larissa") && w.search("testeailson")) {
			Node nodeTestes = w.getRoot("testes");
			Node nodeLarissa = w.getRoot("larissa");
			Node nodeTesteailson = w.getRoot("testeailson");

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
