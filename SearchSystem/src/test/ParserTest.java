package test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import index.Parser;

public class ParserTest {

	@Test
	public void paserTeste() {
		Parser parser = new Parser();
		try {
			parser.open("test.txt");
			ArrayList<String> line = parser.gotToLine(3);
			for(String str : line) {
				System.out.println(str);
			}
		} catch (IOException | NullPointerException | StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

}
