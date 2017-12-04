package exceptions;

/**
 * This class is responsible to check if the file is already in the system.
 * 
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class FileAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public FileAlreadyExistsException(String message) {
		super(message);
	}
}
