package test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import index.Parser;
import utils.Trie;
import utils.TrieNode;

public class ParserTest {

	@Test
	public void paserTeste() {
		Parser parser = new Parser();
		Trie trieTest = null;
		System.out.println("#### TESTA LEITURA DE DETERMINADA LINHA ####");
		try {
			parser.open("test.txt");
			ArrayList<String> line = parser.gotToLine(3);
			for (int i = 0; i < line.size(); i++) {
				String str = line.get(i);
				System.out.println(str);
			}
		} catch (IOException | NullPointerException | StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		System.out.println("#### TESTA QUANTIDADE DE PALAVRAS DO TEXTO ####");
		System.out.println(parser.contWords("test.txt"));

		System.out.println("#### TESTA CONSTRUÇÃO DA ÁRVORE ####");
		trieTest = parser.fillTrie("test.txt");
		trieTest.printRoots();
		System.out.println("#### TESTA PALAVRAS NA TRIE ####");
		System.out.println(trieTest.getWords("Google"));

		System.out.println("#### IMPRIME ÁRVORE ####");
		for (TrieNode node : parser.getTree().getAllRoots()) {
			for(String word : parser.getTree().getWords("" + node.getKey())) {
				System.out.println(word + "-" + parser.getTree().search(word).getValue());
			}
		}
	}

}
