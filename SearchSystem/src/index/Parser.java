package index;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	Reader reader;
	Writer writer;
	String fileName;

	public Parser() {
		try {
			reader = new Reader("index.txt");
			writer = new Writer("index.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> parsear(int lineNumber) {
		ArrayList<String> words;
		do {
			words = reader.readBreackedLine();
		}while (words != null && lineNumber-- > 0);
		words = reader.readBreackedLine();
		try {
			reader.close();
			open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
	
	public void open(String fileName) throws IOException{
		reader.close();
		reader = new Reader(fileName);
		this.fileName = fileName;
	}
}
