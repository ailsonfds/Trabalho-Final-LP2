package test;

import org.junit.Test;

import tree.Node;

public class NodeTest {
	private Node test;
	
	@Test
	public void testNode() {
		test = new Node("Ailson",null);
		System.out.println(test);
	}
}
