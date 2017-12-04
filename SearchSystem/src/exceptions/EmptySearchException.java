package exceptions;

/**
 * This class is responsible to check if the search is empty.
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class EmptySearchException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptySearchException(String message) {
		super(message);
	}

}
