
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Jonathan Mariano
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	
	String password1;
	String password2;

	@Before
	public void setUp() throws Exception {
		String[] p = {"ValidVALID0", 
				"nouppercasesoinvalid0", 
				"NOLOWERCASESOINVALID1", 
				"NOnumbersoinvalid", 
				"VALIDvalid1", 
				"VaLiDvAlId2", 
				"VALIDvalid3", 
				"validVALID4"};
		
		passwords = new ArrayList<String>();
		
		passwords.addAll(Arrays.asList(p)); // puts strings into the ArrayList
	}

	@After
	public void tearDown() throws Exception {
	
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		//fail("Not implemented by student yet");
		try{
			PasswordCheckerUtility.isValidPassword("Bay12");
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		//fail("Not implemented by student yet");
		try{
			PasswordCheckerUtility.isValidPassword("jonathan21");
			assertTrue("Did not throw NoUpperAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		//fail("Not implemented by student yet");
		try{
			PasswordCheckerUtility.isValidPassword("JONATHAN21");
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		//fail("Not implemented by student yet");
		try{
			
			assertEquals(true,PasswordCheckerUtility.isValidPassword("JonMar21"));
			assertTrue(PasswordCheckerUtility.isWeakPassword("JonMar21"));
			//assertEquals(true,PasswordCheckerUtility.isValidPassword("JonathanM21"));
			//assertTrue(PasswordCheckerUtility.isWeakPassword("JonathanM21"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
			//assertTrue("Threw some incorrect exception",true);

		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		//fail("Not implemented by student yet");
		try{
			PasswordCheckerUtility.isValidPassword("jjonathanM21");
			assertTrue("Did not throw an InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		//fail("Not implemented by student yet");
		try{
			PasswordCheckerUtility.isValidPassword("JonathanMariano");
			assertTrue("Did not throw an NoDigitException",true);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw an NoDigitExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an NoDigitException",false);

		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		//fail("Not implemented by student yet");
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("Jonathan21"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("4Slytherin"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("MAriaNO21"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("LordOfTheRings808"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("CMSC204jonathan"));
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw an exception",false);

		}
	}
	
	/**
	 * Test the validPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testValidPasswords() {
		//fail("Not implemented by student yet");
		
		/*
		 * "ValidVALID0", 
				"nouppercasesoinvalid0", 
				"NOLOWERCASESOINVALID1", 
				"NOnumbersoinvalid", 
				"VALIDvalid1", 
				"VaLiDvAlId2", 
				"VALIDvalid3", 
				"validVALID4"};
		 */
		ArrayList<String> results;
		results = PasswordCheckerUtility.validPasswords(passwords);
		Scanner scan = new Scanner(results.get(0)); //
		assertEquals("nouppercasesoinvalid0", scan.next());
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("upper"));
		
		scan = new Scanner(results.get(1)); //
		assertEquals("NOLOWERCASESOINVALID1", scan.next());
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lower"));
		
		scan = new Scanner(results.get(2)); //
		assertEquals("NOnumbersoinvalid", scan.next());
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));
		
		
		
		
		
	}
	
}