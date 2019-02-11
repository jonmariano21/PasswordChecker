/*
 * CMSC 204 - A1
 * NoUpperAlphaException from PasswordCheckerUtilityTest
 * @Author Jonathan Mariano
 * 
 */
public class NoUpperAlphaException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUpperAlphaException(String str) {
		
		super( str + " The password must contain at least one uppercase alphabetic character. \n");
		
		System.out.println( str + " The password must contain at least one uppercase alphabetic character. \n");

	}
	
}
