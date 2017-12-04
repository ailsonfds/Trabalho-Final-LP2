package test;

import org.junit.Test;

import exceptions.EmptyWordException;
import exceptions.FileAlreadyExistsException;
import exceptions.FileNotFoundException;
import exceptions.FileTypeException;
import index.Indexer;

public class IndexerTest {

	@Test
	public void indexerTest() {
		Indexer index = new Indexer();
		String filename = "test.txt";
		String filename2 = "t.txt";
		String filename3 = "teste.txt";
		String filename4 = "biblia-em-txt.txt";
		String word1 = "Google";
		String word2 = "começou";
		String word3 = "Califórnia";
		String word4 = "número fundou";
		System.out.println("#### INSERÇÃO TESTE ####");
		try {
			index.addDocument(filename);
		} catch (FileTypeException | FileAlreadyExistsException e3) {
			e3.printStackTrace();
		} 
		try {
			index.addDocument(filename2);
		} catch (FileTypeException | FileAlreadyExistsException e2) {
			e2.printStackTrace();
		}
		try {
			index.addDocument(filename3);
		} catch (FileTypeException | FileAlreadyExistsException e1) {
			e1.printStackTrace();
		} 		
	/*	try {
			index.addDocument(filename4);
		} catch (FileTypeException | FileAlreadyExistsException e) {
			e.printStackTrace();
		}  */
		
		index.listDocuments();
		
		System.out.println("#### BUSCA OR TESTE ####");
		try {
			index.searchOR(word1);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		try {
			index.searchAND(word1);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		
		try {
			index.searchOR(word2);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		try {
			index.searchAND(word2);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		
		try {
			index.searchOR(word3);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		try {
			index.searchAND(word3);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		
		try {
			index.searchOR(word4);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		try {
			index.searchAND(word4);
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		
		try {
			index.searchOR("blabla");
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		try {
			index.searchAND("blabla");
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		
		try {
			index.searchAND("lacinia");
		} catch (EmptyWordException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("#### REMOÇÃO TESTE ####");
		try {
			index.removeDocument(filename2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			index.removeDocument(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
		index.listDocuments();
	}

}
