/*
 * CMSC 204 - A1
 * NoLowerAlphaException from PasswordCheckerUtilityTest
 * @Author Jonathan Mariano
 * 
 */
public class NoLowerAlphaException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoLowerAlphaException(String str) {
		
		super( str + " The password must contain at least one lowercase alphabetic character. \n");
		
		System.out.println( str + " -NOT SUPER- The password must contain at least one lowercase alphabetic character \n");

	}
	
}
