/*
 * CMSC 204 - A1
 * InvalidSequenceException from PasswordCheckerUtilityTest
 * @Author Jonathan Mariano
 * 
 */
public class InvalidSequenceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSequenceException(String str){  
		
		super( str + " The password cannot contain more than two of the same character in sequence. \n");   
	    
		System.out.println( str + " DERP The password cannot contain more than two of the same character in sequence. \n");
	} 
	
}
