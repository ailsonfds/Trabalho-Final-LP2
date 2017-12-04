package test;

import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;

import exceptions.EmptySearchException;
import exceptions.EmptyWordException;
import exceptions.FileAlreadyExistsException;
import exceptions.FileNotFoundException;
import exceptions.FileTypeException;
import index.Indexer;
import utils.TrieNode;

public class IndexerTest {

	@Test
	public void indexerTest() {
		Indexer index = new Indexer();
		String filename = "test.txt";
		String filename2 = "t.txt";
		String filename3 = "teste.txt";
		// String filename4 = "biblia-em-txt.txt";
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
		// try {
		// index.addDocument(filename4);
		// } catch (FileTypeException | FileAlreadyExistsException e) {
		// e.printStackTrace();
		// }

		index.listDocuments();

		System.out.println("#### BUSCA OR TESTE ####");
		try {
			printSearchResult(index.searchOR(word1));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word1 + " em busca OR");
		}
		try {
			printSearchResult(index.searchAND(word1));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word1 + " em busca AND");
		}

		try {
			printSearchResult(index.searchOR(word2));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word2 + " em busca OR");
		}
		try {
			printSearchResult(index.searchAND(word2));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word2 + " em busca AND");
		}

		try {
			printSearchResult(index.searchOR(word3));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word3 + " em busca OR");
		}
		try {
			printSearchResult(index.searchAND(word3));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word3 + " em busca AND");
		}

		try {
			printSearchResult(index.searchOR(word4));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word4 + " em busca OR");
		}

		try {
			printSearchResult(index.searchAND(word4));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou " + word4 + " em busca AND");
		}

		try {
			printSearchResult(index.searchOR("blabla"));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou blabla em busca OR");
		}

		try {
			printSearchResult(index.searchAND("blabla"));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou blabla em busca AND");
		}

		try {
			printSearchResult(index.searchAND("lacinia"));
		} catch (EmptyWordException | EmptySearchException e) {
			System.out.println("Não encontrou lacinia em busca AND");
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

		System.out.println("#### UPDATE TESTE ####");
		try {
			index.updateDocuments(filename3);
		} catch (FileTypeException e) {
			e.printStackTrace();
		}
	}

	private void printSearchResult(final HashMap<String, TrieNode> result) {
		for(String keySearch : result.keySet()) {
			TrieNode node = result.get(keySearch);
			System.out.println("### Ocorrencia de '" + keySearch + "' em:");
			for (String file : node.getValue().keySet()) {
				HashMap<Integer, Integer> occr = node.getValue().get(file);
				System.out.println("## " + file + ":");
				for (Integer line : occr.keySet()) {
					System.out.println("-> " + line + " : " + occr.get(line));
				}
			}
		}
	}

}
