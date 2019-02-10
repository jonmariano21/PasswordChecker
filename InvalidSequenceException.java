
public class InvalidSequenceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSequenceException(String message){  
		
		super( message + " The password cannot contain more than two of the same character in sequence. \n");   
	    
		System.out.println(message + " DERP The password cannot contain more than two of the same character in sequence. \n");
	} 
}
