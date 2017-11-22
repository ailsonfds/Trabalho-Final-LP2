package test;

import java.util.HashMap;

import utils.Pair;
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
	public static void main(String [] args) {
		TrieTest.trieTest();
	}
	
	public static void trieTest() {
		Trie w = new Trie();
		HashMap<Integer, Integer> occurences = new HashMap<>();
		occurences.put(1, 1);
		
		System.out.println ("\n--------------Inserção-------------\n");
		
		w.insert("testes", (new Pair<String,HashMap<Integer, Integer>>("teste.txt", occurences)));
		w.insert("larissa", (new Pair<String,HashMap<Integer, Integer>>("LarissaEstaAqui.txt", occurences)));
		w.insert("testeailson", (new Pair<String,HashMap<Integer, Integer>>("testeailson.txt", occurences)));	// Por quê esta imprimindo ?
		w.insert("e", null);
		w.insert("empresa", null);

		System.out.println ("\n--------------Verifica se foi inserido-------------\n");
		
		System.out.println("Encontrou 'testes': " + w.search("testes").getInfo());
		System.out.println("Encontrou 'Larissa': " + w.search("larissa").getInfo());
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson").getInfo());
		System.out.println("Encontrou 'e': " + w.search("e").getInfo());
		System.out.println("Encontrou 'empresa': " + w.search("empresa").getInfo());
		
		System.out.println ("\n--------------Imprimir cada Arvore-------------\n");
		
		if (w.search("testes") != null && w.search("larissa") != null && w.search("testeailson") != null) {
			TrieNode nodeTestes = w.getRoot("testes");
			TrieNode nodeLarissa = w.getRoot("larissa");
			TrieNode nodeTesteailson = w.getRoot("testeailson");
			TrieNode nodeE= w.getRoot("e");
			TrieNode nodeEmpresa= w.getRoot("empresa");
			
			// comparar a saida com o conteúdo, não bate.
			System.out.println(nodeTestes);		
			System.out.println(nodeLarissa);
			System.out.println(nodeTesteailson);
			System.out.println(nodeE);		// não é imprimido, imprime "empresa" além de apenas 'e'
			System.out.println(nodeEmpresa);
		}
		
		System.out.println ("\n--------------Simulação da Busca-------------\n");
		
		System.out.println("Encontrou o nó 'testes': " + w.search("testes"));
		System.out.println("Encontrou 'larissa': " + w.search("larissa"));
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson"));
		System.out.println("Encontrou 'e': " + w.search("e"));
		System.out.println("Encontrou 'empresa': " + w.search("empresa"));
		System.out.println("Encontrou 'NaoDeveEncontrar': " + w.search("NaoDeveEncontrar"));
		
		
		System.out.println ("\n--------------Remoção de elementos-------------\n");
		
		w.remove("testes"); // Remoção aqui nao deve interferir no caminho de "testeailson"
		System.out.println("Encontrou o nó 'testes': " + w.search("testes"));
		
		w.remove("larissa");
		System.out.println("Encontrou 'larissa': " + w.search("larissa"));
		
		// removeu 'testes', mas 'testeailson' deve imprimir, mesmo tendo o mesmo caminho até o segunda letra 'e'. 
		System.out.println("Encontrou 'testeailson': " + w.search("testeailson")); // acabou interferindo
		// depois, comentem a remoção do 'teste' e testem novamente.
		
		try {
			w.remove("ttestes");
		} catch (NullPointerException e) {
			System.out.println("Deu certo... Deu nao");
		}
	}
}
