package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import index.Reader;

/**
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
			for(String word : line) {
				System.out.println(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
