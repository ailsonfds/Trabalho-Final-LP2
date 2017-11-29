/**
 * Package to generate a Trie object containing indexed words
 */
package index;

import java.util.HashSet;
import java.util.Set;

/**
 * An indexer of files.
 * 
 * It is a boundary class
 * {@link https://www.ibm.com/support/knowledgecenter/en/SS6RBX_11.4.2/com.ibm.sa.oomethod.doc/topics/c_Boundary_Class.html}
 * 
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Moura
 */
/*
 * (non-Javadoc)
 * Essa classe é apenas uma classe de interface com outras classes, ou seja, ela é classe de fronteira(Ela não é <interface> apenas interage com outras classes)
 */
public class Indexer {
	Set<String> occurences;
	Parser p;

	public Indexer() {
		occurences = new HashSet<String>();
		p = new Parser();
	}

}
