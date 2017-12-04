package exceptions;

/**
 * This class is responsible to check if the file has .txt extension.
 * 
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class FileTypeException extends Exception {
	private static final long serialVersionUID = 1L;

	public FileTypeException(String message) {
		super(message);
	}
}
