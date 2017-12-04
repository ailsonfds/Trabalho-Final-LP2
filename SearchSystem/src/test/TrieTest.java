package test;

import java.util.ArrayList;

import org.junit.Test;

import utils.Trie;
import utils.TrieNode;

/**
 * A class that contains a test to Trie class
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Moura
 * @author Valmir Correa
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

	@SuppressWarnings("deprecation")
	public void trieTest() {
		System.out.println("\n--------------inserção-------------\n");

		w.insert("testes", "teste.txt", 1, 1);
		w.insert("larissa", "teste.txt", 1, 1);
		w.insert("testeailson", "teste.txt", 1, 1); // Por quê esta imprimindo ?
		w.insert("testeailson", "teste.txt", 1, 2);
		w.insert("testeailson", "testou.txt", 1, 1);
		w.insert("testeailson", "outro.txt", 3, 1);
		w.insert("testeailson", "outro.txt", 3, 2);
		w.insert("e", null, null, null);
		w.insert("empresa", null, null, null);

		System.out.println("Deu certo? " + (w.search("testes").getInfo() && w.search("larissa").getInfo()
				&& w.search("testeailson").getInfo() && w.search("e").getInfo() && w.search("empresa").getInfo()));

		System.out.println("\n--------------Verifica se foi inserido-------------\n");

		System.out.println("Encontrou 'testes': " + w.search("testes").getInfo());
		System.out.println("Encontrou 'Larissa': " + w.search("larissa").getInfo());
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson").getInfo());
		System.out.println("Encontrou 'e': " + w.search("e").getInfo());
		System.out.println("Encontrou 'empresa': " + w.search("empresa").getInfo());

		System.out.println("\n--------------Imprimir cada Arvore-------------\n");

		if (w.search("testes") != null && w.search("larissa") != null && w.search("testeailson") != null) {
			TrieNode nodeTestes = w.getRoot("testes");
			TrieNode nodeLarissa = w.getRoot("larissa");
			TrieNode nodeTesteailson = w.getRoot("testeailson");

			TrieNode nodeE = w.getRoot("e");
			TrieNode nodeEmpresa = w.getRoot("empresa");

			System.out.println(nodeTestes);

			// comparar a saida com o conteúdo, não bate.
			System.out.println(nodeTestes);

			System.out.println(nodeLarissa);
			System.out.println(nodeTesteailson);
			System.out.println(nodeE); // não é imprimido, imprime "empresa" além de apenas 'e'
			System.out.println(nodeEmpresa);
		}

		System.out.println("\n--------------Simulação da Busca-------------\n");

		System.out.println("Encontrou o nó 'testes': " + w.search("testes"));
		System.out.println("Encontrou 'larissa': " + w.search("larissa"));
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson"));
		System.out.println("Encontrou 'e': " + w.search("e"));
		System.out.println("Encontrou 'empresa': " + w.search("empresa"));
		System.out.println("Encontrou 'NaoDeveEncontrar': " + w.search("NaoDeveEncontrar"));

		System.out.println("\n--------------Remoção de elementos-------------\n");
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
		w.insert("empresa", null, null, null);
		System.out.println(
				"verificando nova inserção da palavra empresa " + (w.search("empresa") != null ? "funfou" : "bugou"));
		w.insert("e", null, null, null);
		System.out.println("verificando nova inserção da palavra e " + (w.search("e") != null ? "funfou" : "bugou"));

		System.out.println("Encontrou 'larissa': " + w.search("larissa"));

		// removeu 'testes', mas 'testeailson' deve imprimir, mesmo tendo o mesmo
		// caminho até o segunda letra 'e'.
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson")); // acabou interferindo
		// depois, comentem a remoção do 'teste' e testem novamente.

		try {
			w.remove("ttestes");
		} catch (NullPointerException e) {
			System.out.println("Deu certo... Deu nao");
		}
	}

	@SuppressWarnings("deprecation")
	public void testGetWords() {
		w.insert("torah", null, null, null);
		w.insert("tomus", null, null, null);
		w.insert("t", null, null, null);
		w.insert("test", null, null, null);
		for (TrieNode n : w.getAllRoots()) {
			ArrayList<String> words = w.getWords("" + n.getKey());
			for (String s : words) {
				System.out.println(s);
			}
		}
	}
}