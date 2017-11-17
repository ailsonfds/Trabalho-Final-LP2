package index;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Ailson Forte dos Santos
 *
 */
public class Writer extends BufferedWriter {
	private String fileName;

	public Writer(String fileName) throws IOException {
		super(new FileWriter(fileName));
		this.fileName = fileName;
	}

	public void writeBrackedLine(ArrayList<String> line) {
	    try {
	    	for(String str : line) {
	    		write(str + " ");
	    	}
	    	newLine();
	    }
	    catch(IOException ex) {
	        System.out.println("Erro ao escrever no arquivo: " + fileName);
	    }
	}
}
