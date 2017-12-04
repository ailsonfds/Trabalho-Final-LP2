package index;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to make a text insertion on file
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class Writer extends BufferedWriter {
	private String fileName; // name address to file

	/**
	 * Constructor
	 * 
	 * @param fileName the name of the file
	 * @throws IOException an exception case can't open file
	 */
	public Writer(String fileName) throws IOException {
		super(new FileWriter(fileName));
		this.fileName = fileName;
	}

	/**
	 * Write a braced line on file
	 * 
	 * @param line a list containing the line words
	 * @param separator an separator to the words on list
	 */
	public void writeBrackedLine(ArrayList<String> line, String sep) {
	    try {
	    	for(String str : line) {
	    		write(str + sep);
	    	}
	    	newLine();
	    }
	    catch(IOException ex) {
	        System.out.println("Erro ao escrever no arquivo: " + fileName);
	    }
	}
}
