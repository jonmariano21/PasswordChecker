
public class NoLowerAlphaException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoLowerAlphaException(String message) {
		super(message + " The password must contain at least one lowercase alphabetic character");
		System.out.println(message + " The password must contain at least one lowercase alphabetic character \n");

	}
	
}
