/*
 * CMSC 204 - A1
 * LengthException from PasswordCheckerUtilityTest
 * @Author Jonathan Mariano
 * 
 */
public class LengthException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LengthException(String str) {
		
		super( str + " The password must be at least 6 characters long. digit \n"); 
		
		System.out.println(str + " The password must be at least 6 characters long. digit \n");

	}

}
