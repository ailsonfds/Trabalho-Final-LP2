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

	public void trieTest() {
		HashMap<Integer, Integer> occurences = new HashMap<>();
		occurences.put(1, 1);
<<<<<<< HEAD
		
		System.out.println ("\n--------------Inser��o-------------\n");
		
		w.insert("testes", (new Pair<String,HashMap<Integer, Integer>>("teste.txt", occurences)));
		w.insert("larissa", (new Pair<String,HashMap<Integer, Integer>>("LarissaEstaAqui.txt", occurences)));
		w.insert("testeailson", (new Pair<String,HashMap<Integer, Integer>>("testeailson.txt", occurences)));	// Por qu� esta imprimindo ?
		w.insert("e", null);
		w.insert("empresa", null);

		System.out.println ("\n--------------Verifica se foi inserido-------------\n");
		
		System.out.println("Encontrou 'testes': " + w.search("testes").getInfo());
		System.out.println("Encontrou 'Larissa': " + w.search("larissa").getInfo());
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson").getInfo());
		System.out.println("Encontrou 'e': " + w.search("e").getInfo());
		System.out.println("Encontrou 'empresa': " + w.search("empresa").getInfo());
		
		System.out.println ("\n--------------Imprimir cada Arvore-------------\n");
		
=======
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

>>>>>>> 641d0bdee4b101c13f6d35c81234766f76ae277f
		if (w.search("testes") != null && w.search("larissa") != null && w.search("testeailson") != null) {
			TrieNode nodeTestes = w.getRoot("testes");
			TrieNode nodeLarissa = w.getRoot("larissa");
			TrieNode nodeTesteailson = w.getRoot("testeailson");
<<<<<<< HEAD
			TrieNode nodeE= w.getRoot("e");
			TrieNode nodeEmpresa= w.getRoot("empresa");
			
			// comparar a saida com o conte�do, n�o bate.
			System.out.println(nodeTestes);		
=======
			TrieNode nodeE = w.getRoot("e");
			TrieNode nodeEmpresa = w.getRoot("empresa");

			System.out.println(nodeTestes);
>>>>>>> 641d0bdee4b101c13f6d35c81234766f76ae277f
			System.out.println(nodeLarissa);
			System.out.println(nodeTesteailson);
			System.out.println(nodeE);		// n�o � imprimido, imprime "empresa" al�m de apenas 'e'
			System.out.println(nodeEmpresa);
		}
		
		System.out.println ("\n--------------Simula��o da Busca-------------\n");
		
		System.out.println("Encontrou o n� 'testes': " + w.search("testes"));
		System.out.println("Encontrou 'larissa': " + w.search("larissa"));
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson"));
		System.out.println("Encontrou 'e': " + w.search("e"));
		System.out.println("Encontrou 'empresa': " + w.search("empresa"));
		System.out.println("Encontrou 'NaoDeveEncontrar': " + w.search("NaoDeveEncontrar"));
		
		
		System.out.println ("\n--------------Remo��o de elementos-------------\n");
		
		w.remove("testes"); // Remo��o aqui nao deve interferir no caminho de "testeailson"
		System.out.println("Encontrou o n� 'testes': " + w.search("testes"));
		
		w.remove("larissa");
<<<<<<< HEAD
		System.out.println("Encontrou 'larissa': " + w.search("larissa"));
		
		// removeu 'testes', mas 'testeailson' deve imprimir, mesmo tendo o mesmo caminho at� o segunda letra 'e'. 
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson")); // acabou interferindo
		// depois, comentem a remo��o do 'teste' e testem novamente.
		
=======
		System.out.println("remoção de larissa do grupo " + (w.search("larissa")==null?"funfou":"bugou"));
		w.remove("testes");
		System.out.println("remoção de testes " + (w.search("testes")==null?"funfou":"bugou"));
		w.remove("empresa");
		System.out.println("remoção de empresa " + (w.search("empresa")==null?"funfou":"bugou"));
		w.remove("e");
		System.out.println("remoção de e " + (w.search("e")==null?"funfou":"bugou"));
		w.insert("e", null);
		w.insert("empresa", null);
		System.out.println("verificando nova inserção da palavra empresa " + (w.search("empresa")!=null?"funfou":"bugou"));
		System.out.println("verificando nova inserção da palavra e " + (w.search("e")!=null?"funfou":"bugou"));
>>>>>>> 641d0bdee4b101c13f6d35c81234766f76ae277f
		try {
			w.remove("ttestes");
		} catch (NullPointerException e) {
			System.out.println("Deu certo... Deu nao");
		}
	}

	public void testGetWords() {
		for (TrieNode n : w.getAllRoots()) {
			ArrayList<String> words = w.getWords("" + n.getKey());
			for (String s : words) {
				System.out.println(s);
			}
		}
		w.printRoots();
	}
}
