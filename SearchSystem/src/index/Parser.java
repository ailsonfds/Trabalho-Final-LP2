package index;

import java.io.IOException;
import java.util.HashMap;

import utils.Pair;

public class Parser {
	Reader reader;
	Writer writer;
	HashMap<String,Pair<Integer,Integer>> words;

	public Parser(String fileName) {
		try {
			reader = new Reader(fileName);
			writer = new Writer("index.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		words = new HashMap<>();
	}

	
}
