/**
 * A package to make tests of package index and package search
 */
package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import index.Reader;

/**
 * Test class to test class Reader
 * 
 * @author Ailson Forte dos Santos
 *
 */
public class ReaderTester {
	@Test
	public void readLineTest() {
		Reader reader;
		try {
			reader = new Reader("test.txt");
			ArrayList<String> line = reader.readBreackedLine();
			ArrayList<String> result = reader.removeCharacters(line);
			ArrayList<String> result2 = reader.removeNumbers(result);
			for (String word : result2) {
				System.out.println(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
