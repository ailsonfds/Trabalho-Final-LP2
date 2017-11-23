package test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

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
	Trie w;

	public TrieTest() {
		w = new Trie();
	}

	@Test
	public void mainTest() {
		TrieTest t = new TrieTest();
		System.out.println("####################################");
		System.out.println("Iniciando Teste da inserção");
		t.trieTest();
		System.out.println("####################################");
		System.out.println("Iniciando busca por começo de palvra");
		t.testGetWords();
		System.out.println("####################################");
	}

	public void trieTest() {
		HashMap<Integer, Integer> occurences = new HashMap<>();
		occurences.put(1, 1);
		HashMap<String, HashMap<Integer, Integer>> valueTest = new HashMap<>();
		valueTest.put("teste.txt", occurences);
		w.insert("testes", valueTest);
		w.insert("larissa", null);
		w.insert("testeailson", null);
		w.insert("e", null);
		w.insert("empresa", null);

		System.out.println("Deu certo? " + (w.search("testes").getInfo() && w.search("larissa").getInfo()
				&& w.search("testeailson").getInfo() && w.search("e").getInfo() && w.search("empresa").getInfo()));

		if (w.search("testes") != null && w.search("larissa") != null && w.search("testeailson") != null) {
			TrieNode nodeTestes = w.getRoot("testes");
			TrieNode nodeLarissa = w.getRoot("larissa");
			TrieNode nodeTesteailson = w.getRoot("testeailson");
			TrieNode nodeE = w.getRoot("e");
			TrieNode nodeEmpresa = w.getRoot("empresa");

			System.out.println(nodeTestes);
			System.out.println(nodeLarissa);
			System.out.println(nodeTesteailson);
			System.out.println(nodeE);
			System.out.println(nodeEmpresa);
		}
		w.remove("larissa");
		System.out.println("remoção de larissa do grupo " + (w.search("larissa") == null ? "funfou" : "bugou"));
		w.remove("testes");
		System.out.println("remoção de testes " + (w.search("testes") == null ? "funfou" : "bugou"));
		w.remove("empresa");
		System.out.println("remoção de empresa " + (w.search("empresa") == null ? "funfou" : "bugou"));
		w.remove("e");
		System.out.println("remoção de e " + (w.search("e") == null ? "funfou" : "bugou"));
		ArrayList<TrieNode> roots = w.getAllRoots();
		for (TrieNode s : roots) {
			System.out.println(s);
		}
		w.insert("e", null);
		w.insert("empresa", null);
		System.out.println(
				"verificando nova inserção da palavra empresa " + (w.search("empresa") != null ? "funfou" : "bugou"));
		System.out.println("verificando nova inserção da palavra e " + (w.search("e") != null ? "funfou" : "bugou"));
		try {
			w.remove("ttestes");
		} catch (NullPointerException e) {
			System.out.println("Deu certo");
		}
	}

	public void testGetWords() {
		for (TrieNode n : w.getAllRoots()) {
			ArrayList<String> words = w.getWords("" + n.getKey());
			for (String s : words) {
				System.out.println(s);
			}
		}
	}
}
