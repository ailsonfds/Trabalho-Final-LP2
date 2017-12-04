package exceptions;

/**
 * This class is responsible to check if some word is empty.
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Gilliane Melo de Moura
 *
 */
public class EmptyWordException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptyWordException(String message) {
		super(message);	
	}

}
