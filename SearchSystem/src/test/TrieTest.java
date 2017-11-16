package test;

import tree.Node;
import tree.Trie;

//TESTE APENAS
public class TrieTest {

	public TrieTest() {
		// TODO Auto-generated constructor stub
	}

	// TODO Auto-generated constructor stub
	public static void main(String[] args) {
		Trie w = new Trie();
		w.insert("testes", null);
		w.insert("larissa", null);

		if (w.search("testes") && w.search("larissa")) {
			Node nodeTestes = w.getRoot("testes");
			Node nodeLarissa = w.getRoot("larissa");

			System.out.println(nodeTestes);
			System.out.println(nodeLarissa);
		}
		w.remove("larissa");
		System.out.println(w.search("larissa"));
		w.remove("testes");
		try {
			w.remove("ttestes");
		}catch (NullPointerException e) {
			System.out.println("Deu certo");
		}
	}
}
