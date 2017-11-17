/**
 * A package to index .txt files
 */
package index;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class to read from files
 * 
 * @author Ailson Forte dos Santos
 *
 */
public class Reader extends BufferedReader {
	private String fileName; // The file name

	/**
	 * Constructor
	 * 
	 * @param fileName the file name
	 * @throws FileNotFoundException an exception case file can''t be opened
	 */
	public Reader(String fileName) throws FileNotFoundException {
		super(new FileReader(fileName));
		this.fileName = fileName;
	}

	/**
	 * Read one line in the file and split her into space separated words
	 * 
	 * @return a list within the words of a line
	 */
	public ArrayList<String> readBreackedLine() {
		ArrayList<String> retorno = new ArrayList<>();
		String line;
		try {
			if ((line = super.readLine()) != null) {
				String word = "";
				for (char c : line.toCharArray()) {
					if(c == ' ' && !word.isEmpty()) {
						retorno.add(word);
						word = "";
					}else if(c != ' ')
						word += c;
				}
				retorno.add(word);// Adicionando a uĺtima palavra da linha
			}
		} catch (IOException e) {
			System.out.println("Unable to read file: " + fileName);
		}
		return retorno;
	}
}
