package test;

import org.junit.Test;

import utils.TrieNode;

/**
 * A class test to test class TrieNode
 * @author Ailson Forte dos Santos
 *
 */
public class TrieNodeTest {
	private TrieNode test;
	
	@Test
	public void testNode() {
		test = new TrieNode(test,"Ailson",null);
		System.out.println(test);
	}
}
