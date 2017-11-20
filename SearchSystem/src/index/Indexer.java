/**
 * 
 */
package index;

import java.util.HashSet;
import java.util.Set;

import utils.Trie;

/**
 * An indexer of files
 * 
 * @author Ailson Forte dos Santos
 *
 */
public class Indexer {
	Set<String> occurences;
	Parser p;
	Trie tree;

	public Indexer(String fileName) {
		occurences = new HashSet<String>();
		p = new Parser(fileName);
		tree = new Trie();
	}
	
	public void readFile() {
		
	}
}
