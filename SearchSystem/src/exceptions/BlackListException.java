package exceptions;

/** 
 * Exception to throw when a black word is called
 * @author Ailson Forte dos Santos
 *
 */
public class BlackListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlackListException(String message) {
		super(message);
	}
}