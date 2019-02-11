
/*
 * CMSC 204 - A1
 * PasswordCheckerUtility
 * @Author Jonathan Mariano
 * 
 * Create a PasswordCheckerUtility class based on the Javadoc given you.  
 * The PasswordCheckerUtility class will have at least two methods:  
 * 		One method that checks the validity of one password that returns true if the password 
 * 			is valid and that throws an exception if invalid.  
 * 		One that checks an ArrayList of passwords and returns an ArrayList with the status of 
 * 			any invalid passwords (weak passwords are not considered invalid).  
 */
import java.util.ArrayList;
public class PasswordCheckerUtility {
	private static boolean lengthError = false;
    private static boolean numError = false;
    private static boolean lowercaseError = false;
    private static boolean uppercaseError = false;
    //private static boolean repeatError = false;
    
	public static boolean isValidPassword(String str) {
		System.out.println("The input to isValidPassword is " + str);
	    boolean repeatError = false;

		char prevchar = 0;//initially null
        char ch;
        int capitalFlag = 0;
        int lowerCaseFlag = 0;
        int numberFlag = 0;
        
        // Check length
        if(str.length() < 7) {
        	//System.out.println("\n TOO SHORT!");
        	lengthError = true;
        	throw new LengthException(str);
        	//return false;
        	
        }
        
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            //System.out.println(i + " index = " + ch);
            //System.out.println("prevchar = " + prevchar);
            
            if(prevchar == ch) {
            	//System.out.println("\n We got a REPEAT!");
            	repeatError = true;
            	//throw new InvalidSequenceException(str);
            	//return false;

            }
            
            if( Character.isDigit(ch)) {
            	//System.out.println("\n Theres a number!");
            	numberFlag = numberFlag + 1;
                
            }
            if (Character.isUpperCase(ch)) {
            	//System.out.println("\n Theres a CAPITAL!");
            	capitalFlag = capitalFlag + 1;
                
            } 
            if (Character.isLowerCase(ch)) {
            	//System.out.println("\n Theres a lowercase!");
            	lowerCaseFlag = lowerCaseFlag + 1;
                
            }
            prevchar = ch;
            
        }
        System.out.println("\n The capitalFlag = " + capitalFlag);
        System.out.println("\n The lowerCaseFlag = " + lowerCaseFlag);
        System.out.println("\n The numberFlag = " + numberFlag);
        System.out.println("The repeatError = " + repeatError);


        //VALID
        if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag > 0 && repeatError == false) {
        	System.out.println("VALID! \n");
            return true;
        }
        // MISSING LOWERCASE
        else if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag == 0 ) {
        	//System.out.println("\n Needs lowercase!");
        	
        	lowercaseError = true;
        	throw new NoLowerAlphaException(str);
        	//return false;
        }
        // MISSING UPPERCASE
        else if(numberFlag > 0 && capitalFlag == 0 && lowerCaseFlag > 0 ) {
        	//System.out.println("\n Needs upercase!");
        	uppercaseError = true;
        	throw new NoUpperAlphaException(str);

        	//return false;
        }
        // MISSING NUMBER
        else if(numberFlag == 0 && capitalFlag > 0 && lowerCaseFlag > 0 ){
        	//System.out.println("\n Needs number!");
        	numError = true;
        	throw new NoDigitException(str);

        	//return false;
        }
        // REPEAT
        else if(repeatError == true){
        	System.out.println("Should throw InvalidSequenceException!");
        	throw new InvalidSequenceException(str);
        }
        else {
        	

        	return true;
        }
    }   

	// isWeakPassword
	public static boolean isWeakPassword(String str) {
		System.out.println("INSIDE WEAK.");
		System.out.println("The password = " + str + "\n");

		if(str.length() > 6 && str.length() < 11) {
			System.out.println("The password is WEAK. \n");
			return true;
		}else {
			System.out.println("The password is NOT weak. \n");
			return false;
		}
	}


	public static ArrayList<String> validPasswords(ArrayList<String> pws) {
		System.out.println("\n The array of passwords = " + pws);
		
		ArrayList<String> test;
		test = new ArrayList<String>();
		
		for(String s : pws) {
			System.out.println("The password from array = " + s);
			try{
				isValidPassword(s);
			}
			catch(Exception e){
				System.out.println("Print MSG: " + e.getMessage());
				test.add(e.getMessage());
			}
		}
		
		return test;

	}

}
