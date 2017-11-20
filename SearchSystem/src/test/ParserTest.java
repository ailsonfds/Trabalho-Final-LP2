package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import index.Reader;
import utils.Parser;
import utils.Trie;

public class ParserTest {

	@Test
	public void paserTeste() {
		Reader reader;
		Parser parser = new Parser();
		Trie trie = new Trie();
		try {
			reader = new Reader("test.txt");
			ArrayList<String> line = reader.readBreackedLine();
			parser.buildTrie(line, trie);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		trie.printRoots();
	}

}
