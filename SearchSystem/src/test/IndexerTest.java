package test;

import org.junit.Test;

import index.Indexer;

public class IndexerTest {

	@Test
	public void indexerTest() {
		Indexer index = new Indexer();
		String filename = "test.txt";
		String filename2 = "t.txt";
		String filename3 = "teste.txt";
		String filename4 = "biblia-em-txt.txt";
		System.out.println("#### INSERÇÃO TESTE ####");
	//	index.addDocument(filename);
	//	index.addDocument(filename2);
	//	index.addDocument(filename3);
		index.addDocument(filename4);
		index.listDocuments();
		
		System.out.println("#### REMOÇÃO TESTE ####");
	//	index.removeDocument(filename3);
	//	index.removeDocument(filename);
		index.listDocuments();
	}

}
