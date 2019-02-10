
public class NoUpperAlphaException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUpperAlphaException(String message) {
		
		super(message + " The password must contain at least one uppercase alphabetic character");
		
		System.out.println(message + " The password must contain at least one uppercase alphabetic character. \n");

	}
}
