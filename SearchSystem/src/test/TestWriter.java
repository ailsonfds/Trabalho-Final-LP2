package test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import index.Writer;

/**
 * Test class to test class Writer
 * @author Ailson Forte dos Santos
 *
 */
public class TestWriter {
	@Test
	public void writeTest() {
		Writer w;
		try {
			w = new Writer("test.txt");
			ArrayList<String> line = new ArrayList<>();
			line.add("Just");
			line.add("a");
			line.add("test");
			line.add("to");
			line.add("Writer!");
			w.writeBrackedLine(line, " ");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
