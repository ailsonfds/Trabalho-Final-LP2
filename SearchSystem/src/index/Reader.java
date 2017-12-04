/**
 * A package to index .txt files
 */
package index;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * A class to read from files
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class Reader extends BufferedReader {
	private String fileName; // The file name

	/**
	 * Constructor
	 * 
	 * @param fileName
	 *            the file name
	 * @throws FileNotFoundException
	 *             an exception case file can''t be opened
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
				String[] words = line.split(" ");
				for (String word : words)
					retorno.add(word);
			}
		} catch (IOException e) {
			System.out.println("Unable to read file: " + fileName);
		}
		retorno = removeCharacters(retorno);
		retorno = ajustsLowerCase(retorno);
		if (retorno.isEmpty())
			return null;
		return retorno;
	}

	/**
	 * Remove the special Characters of the text
	 * 
	 * @return a list within the words of a text
	 */
	public ArrayList<String> removeCharacters(ArrayList<String> text) {
		String myRegex = "[^-a-zA-Z0-9]"; // REMOVE TODOS OS CARACTERES ESPECIAIS
		int index = 0;
		for (String word : text) {
			word = Normalizer.normalize(word, Normalizer.Form.NFD);
			text.set(index, word.replaceAll("[^\\p{ASCII}]", "")); // REMOVE OS ACENTOS DA LETRAS
			text.set(index++, word.replaceAll(myRegex, ""));
		}
		return text;
	}

	/**
	 * Remove the numbers of the text
	 * 
	 * @return a list within the words of a text
	 */
	public ArrayList<String> ajustsLowerCase(ArrayList<String> text) {
		int index = 0;
		for (String word : text) {
			text.set(index++, word.toLowerCase());
		}
		return text;
	}

}
