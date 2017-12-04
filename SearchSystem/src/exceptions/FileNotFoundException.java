package exceptions;

/**
 * This class is responsible to check if the exists.
 * 
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class FileNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public FileNotFoundException(String message) {
		super(message);
	}
}
