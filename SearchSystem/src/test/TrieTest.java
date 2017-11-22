package test;

import java.util.HashMap;

import utils.Trie;
import utils.TrieNode;

/**
 * A class that contains a test to Trie class
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Moura
 *
 */
public class TrieTest {
	public static void main(String [] args) {
		TrieTest.trieTest();
	}
	
	public static void trieTest() {
		Trie w = new Trie();
		HashMap<Integer, Integer> occurences = new HashMap<>();
		occurences.put(1, 1);
		HashMap<String, HashMap<Integer, Integer>> valueTest = new HashMap<>();
		valueTest.put("teste.txt", occurences);
		w.insert("testes", valueTest);
		w.insert("larissa", null);
		w.insert("testeailson", null);
		w.insert("e", null);
		w.insert("empresa", null);

		System.out.println(w.search("testes").getInfo());
		System.out.println(w.search("larissa").getInfo());
		System.out.println(w.search("testeailson").getInfo());
		System.out.println(w.search("e").getInfo());
		System.out.println(w.search("empresa").getInfo());
		
		if (w.search("testes") != null && w.search("larissa") != null && w.search("testeailson") != null) {
			TrieNode nodeTestes = w.getRoot("testes");
			TrieNode nodeLarissa = w.getRoot("larissa");
			TrieNode nodeTesteailson = w.getRoot("testeailson");
			TrieNode nodeE= w.getRoot("e");
			TrieNode nodeEmpresa= w.getRoot("empresa");

			System.out.println(nodeTestes);
			System.out.println(nodeLarissa);
			System.out.println(nodeTesteailson);
			System.out.println(nodeE);
			System.out.println(nodeEmpresa);
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
