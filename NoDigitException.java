
public class NoDigitException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDigitException(String message){  
		
		super( message + " The password must contain at least one digit.");
		
	    System.out.println(message + " The password must contain at least one digit. \n");

 }
}
