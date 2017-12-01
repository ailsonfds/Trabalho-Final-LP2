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
		index.addDocument(filename3);
	//	index.addDocument(filename2);
	//	index.addDocument(filename);
		index.listDocuments();
		index.removeDocument(filename3);
		index.listDocuments();
	}

}
