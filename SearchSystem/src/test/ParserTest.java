package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import index.Parser;
import index.Reader;
import utils.Trie;

public class ParserTest {

	@Test
	public void paserTeste() {
		Reader reader;
		Parser parser = new Parser("test.txt");
		Trie trie = new Trie();
		try {
			reader = new Reader("test.txt");
			ArrayList<String> line = reader.readBreackedLine();			
			parser.addToTrie(line, trie, 1);
		} catch (FileNotFoundException | NullPointerException | StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		trie.printRoots();
	}

}
