package exceptions;

/**
 * This class is responsible to check if words are inserted in the Trie.
 * 
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class TrieInsertionException extends Exception {
	private static final long serialVersionUID = 1L;

	public TrieInsertionException(String message) {
		super(message);

	}

}
