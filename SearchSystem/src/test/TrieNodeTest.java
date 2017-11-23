package test;

import java.util.HashMap;

import org.junit.Test;

import utils.Pair;
import utils.TrieNode;

/**
 * A class test to test class TrieNode
 * @author Ailson Forte dos Santos
 * @author Valmir Correa
 */
public class TrieNodeTest {
	private TrieNode test;
	private TrieNode test2;
	
	@Test
	public void testNode() {
		test = new TrieNode(test,"Ailson",null);
		System.out.println(test);
	}
	
	@Test
	public void testNode2() {
		HashMap<Integer, Integer> occurences = new HashMap<>();
		occurences.put(10, 10);
		test2 = new TrieNode("Valmir.", (new Pair<String,HashMap<Integer, Integer>>("TEXTO.txt", occurences)));
		System.out.println(test2);			// toString ta ok
	}
}