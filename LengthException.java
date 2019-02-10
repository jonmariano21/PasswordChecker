
public class LengthException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LengthException(String str) {
		
		super( str + " The password must be at least 6 characters long."); 
		
		System.out.println(str + " The password must be at least 6 characters long. \n");

	}

}
