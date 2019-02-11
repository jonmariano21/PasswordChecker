/*
 * CMSC 204 - A1
 * NoDigitException from PasswordCheckerUtilityTest
 * @Author Jonathan Mariano
 * 
 */
public class NoDigitException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDigitException(String str){  
		
		super( str + " The password must contain at least one digit. \n");
		
	    System.out.println( str + " The password must contain at least one digit. \n");

	}
	
}
