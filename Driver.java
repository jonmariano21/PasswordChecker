/*
 * CMSC 204 - A1 - GUI
 * Driver
 * @Author Jonathan Mariano
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;//For ArrayList and Iterator

public class Driver {

    private ArrayList<String> list;
    private static ArrayList<String> illegalPasswords = new ArrayList<String>();
    private ListIterator<String> lit;
    
    // window frame
    private JFrame frame;
    private JPanel contentPane;
    
    // labels
    private JLabel instructions;
    private JLabel instruction1;
    private JLabel instruction2;
    private JLabel instruction3;
    private JLabel instruction4;
    private JLabel instruction5;
    private JLabel passwordLabel;
    private JLabel reTypePasswordLabel;
    
    // text fields
    private JTextField passwordText;
    private JTextField reTypePasswordText;
    
    // buttons
    private JButton checkPasswordButton;
    private JButton checkPasswordInFileButton;
    private JButton exitButton;
    
    // other
    private int passwordLength = 0;
    private String password;
    private static String s;
    private static String illegalPasswordsDisplay = "";
    
	private static String lengthErrorMsg = "The password must be greater than 6 characters.";
	private static String numErrorMsg = "The password must contain at least one digit.";
	private static String lowercaseErrorMsg = "The password must contain at least one lowercase alphabetic character.";
	private static String uppercaseErrorMsg = "The password must contain at least one uppercase alphabetic character.";
	private static String repeatErrorMsg = "The password cannot contain more than two of the same character in sequence.";
    
    private boolean lengthError = false;
    private static boolean numError = false;
    private static boolean lowercaseError = false;
    private static boolean uppercaseError = false;
    private static boolean repeatError = false;
    

    public static void main(String[] args) {
    	Driver passwordCheckerGUI = new Driver();
    	passwordCheckerGUI.start();
    }
    
    public void start() {
    	frame = new JFrame("Password Checker");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	contentPane = (JPanel)frame.getContentPane();
    	
    	makeContent();
    	
    	frame.pack();
    	frame.setVisible(true);
    }
    
    public void makeContent() {
    	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        
        // INSTRUCTIONS
        instructions = new JLabel("Use the following rules when creating your password:");
        panel.add(instructions);
        instruction1 = new JLabel("1. Length must be greater than 6; a strong password will "
        		+ "contain at least 10 characters");
        panel.add(instruction1);
        instruction2 = new JLabel("2. Must contain at least one upper case alpha character");
        panel.add(instruction2);
        instruction3 = new JLabel("3. Must contain at least one lower case alpha character");
        panel.add(instruction3);
        instruction4 = new JLabel("4. Must contain at least one numeric character");
        panel.add(instruction4);
        instruction5 = new JLabel("5. May not have more than 2 of the same character in sequence");
        panel.add(instruction5);
        
        // PASSWORD ENTRY
        passwordLabel = new JLabel("Password");
        panel.add(passwordLabel);
        
        // PASSWORD TEXT FIELD
        passwordText = new JTextField();
        panel.add(passwordText);
        
        // RE-TYPE PASSWORD TEXT FIELD
        reTypePasswordLabel = new JLabel("Re-type Password");
        panel.add(reTypePasswordLabel);
        
        reTypePasswordText = new JTextField();
        panel.add(reTypePasswordText);
        
        // BUTTONS
        
        // Check Password Button
        checkPasswordButton = new JButton("Check Password");
        checkPasswordButton.addActionListener(new CheckPasswordButtonListener() );
        panel.add(checkPasswordButton);
        
        //FILE: Check Password in File Button
        checkPasswordInFileButton = new JButton("Check Password in File");
        checkPasswordInFileButton.addActionListener(new FileListener() );
        panel.add(checkPasswordInFileButton);
        
        // EXIT BUTTON
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener() );
        panel.add(exitButton);
        
        contentPane.add(panel);
    }
    
    // Check Password button ActionListener
    private class CheckPasswordButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		String s1 = passwordText.getText();
    		String s2 = reTypePasswordText.getText();
    		
    		passwordLength = s1.length();
    		System.out.println("The length of the password is: " + passwordLength);
    		
    		System.out.println("\n The 1st Password is: " + s1);
    		System.out.println("\n The 2nd Password is: " + s2);
    		
    		// Check length of password
    		if(passwordLength < 7) {
    			JOptionPane.showMessageDialog(null, "The password must be greater than 6 characters.");	
    		}
    		else if(s1.contentEquals(s2) && 6 < passwordLength && passwordLength < 10) {
    			password = s1;//if the text fields match, set the password
    			if(checkPassword(password) == true) {
    				JOptionPane.showMessageDialog(null, "Password is OK, but weak.");
    			}
    		}
    		else if(s1.contentEquals(s2) && passwordLength > 9) {
    			password = s1;
    			if(checkPassword(password) == true) {
    				JOptionPane.showMessageDialog(null, "Password is valid.");
    			}        			
    		}
    		else{
            	JOptionPane.showMessageDialog(null, "The passwords do not match");
            }
    	}
    }
    
    // FILE: Check Password from File Button ActionListener
    private class FileListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		JFileChooser fc = new JFileChooser();
    		fc.showOpenDialog(frame); //Display the Open dialog box
    		File passwordFile = fc.getSelectedFile();
    		
    		//System.out.println("The file selected is: " + passwordFile);
    		
    		if(passwordFile == null) {
    			//System.out.println("The file is NULL!");
    			return;
    		}
    		
    		list = new ArrayList<String>();
    		try {
    			//System.out.println("\n Iside TRY");
                Scanner scan = new Scanner(passwordFile);
                while (scan.hasNext()){
                    //System.out.println("\n Iside WHILE");
                    String passwordFromFile = scan.next();
                    //System.out.println("\n Password from file is: " + passwordFromFile);
                    
                    
                    list.add(passwordFromFile);
                    //System.out.println("Add ")
                    //checkPasswordsFromFile(list);
                }
                System.out.println("The file added to array = " + list);
                checkPasswordsFromFile(list);
                if(illegalPasswords != null) {
                    System.out.println("\n illegalPasswords is not NULL");
                    for(String ss : illegalPasswords) {
                    	illegalPasswordsDisplay = illegalPasswordsDisplay + ss + "\n";
                    }
                    System.out.println("\n illegalPasswordsDISPLAY = " + illegalPasswordsDisplay);

                	JOptionPane.showMessageDialog(null, "Illegal Passwords \n" + illegalPasswordsDisplay);
                }
                scan.close();
    		}
    		catch(IOException e1) {
    			JOptionPane.showMessageDialog(frame, 
    	                "I/O error in file\n\n     " +
    	                    passwordFile.getName() +
    	                    "\n\nThe program will now close", 
    	                    "I/O Error", 
    	                    JOptionPane.ERROR_MESSAGE);
    	                System.exit(1);
    		}
    	}
    }
    
    // EXIT BUTTON ActionListener
    private class ExitButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		System.exit(0);
    	}
    }
    
    // Check Password Method from JTextFields
    private static boolean checkPassword(String str) {
    	/*
    	String numErrorMsg = "The password must contain at least one digit.";
    	String lowercaseErrorMsg = "The password must contain at least one lowercase alphabetic character.";
    	String uppercaseErrorMsg = "The password must contain at least one uppercase alphabetic character.";
    	String repeatErrorMsg = "The password cannot contain more than two of the same character in sequence.";
        */
        char prevchar = 0;//initially null
        char ch;
        int capitalFlag = 0;
        int lowerCaseFlag = 0;
        int numberFlag = 0;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            //System.out.println(i + " index = " + ch);
            //System.out.println("prevchar = " + prevchar);
            
            if(prevchar == ch) {
            	JOptionPane.showMessageDialog(null, repeatErrorMsg);
            	repeatError = true;
            	return false;

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
        if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag > 0) {
        	//System.out.println("\n Theres a number & CAPITAL & lower!");
            return true;
        }
        else if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag == 0) {
        	JOptionPane.showMessageDialog(null, lowercaseErrorMsg);
        	lowercaseError = true;
        	return false;
        }
        else if(numberFlag > 0 && capitalFlag == 0 && lowerCaseFlag > 0) {
        	JOptionPane.showMessageDialog(null, uppercaseErrorMsg);
        	uppercaseError = true;
        	return false;
        }
        else if(numberFlag == 0 && capitalFlag > 0 && lowerCaseFlag > 0){
        	JOptionPane.showMessageDialog(null, numErrorMsg);
        	numError = true;
        	return false;
        }
        else{
        	return false;
        }
       // return false;
    }
    
    
    // FILE: Check Passwords Method from FILE
    private static boolean checkPasswordsFromFile(ArrayList<String> array) {
    	//System.out.println("\n Inside the checkPasswordsFromFile method()");
    	System.out.println("\n The input array is: " + array);

    	/*String lengthErrorMsg = "The password must be greater than 6 characters.";
    	String numErrorMsg = "The password must contain at least one digit.";
    	String lowercaseErrorMsg = "The password must contain at least one lowercase alphabetic character.";
    	String uppercaseErrorMsg = "The password must contain at least one uppercase alphabetic character.";
    	String repeatErrorMsg = "The password cannot contain more than two of the same character in sequence.";
    	*/
    	for(String pw : array) {
    		System.out.println("\n checkItFromFile(pw) = " + checkItFromFile(pw));
    		
    		if(pw.length() < 7) {
    			System.out.println("\n The length is short! ");
    			s = pw + " " + lengthErrorMsg;
    			System.out.println(s);
    			illegalPasswords.add(s);
    		}
    		else {
    			if(checkItFromFile(pw) == false && numError == true) {
    				System.out.println("\n The checkItFromFile(pw) == false && numError == true ");
    				numError = false;
    				s = pw + " " + numErrorMsg;
    				illegalPasswords.add(s);
    			}
    			else if(checkItFromFile(pw) == false && lowercaseError == true) {
    				System.out.println("\n The checkItFromFile(pw) == false && lowercaseError == true ");

    				lowercaseError = false;
    				s = pw + " " + lowercaseErrorMsg;
    				illegalPasswords.add(s);
    			}
    			else if(checkItFromFile(pw) == false && uppercaseError == true) {
    				System.out.println("\n The checkItFromFile(pw) == false && uppercaseError == true ");

    				uppercaseError = false;
    				s = pw + " " + uppercaseErrorMsg;
    				illegalPasswords.add(s);
    			}
    			else if(checkItFromFile(pw) == false && repeatError == true){
    				System.out.println("\n The checkItFromFile(pw) == false && repeatError == true ");
    				s = pw + " " + repeatErrorMsg;
    				illegalPasswords.add(s);
    			}

    			
    		}
    		
    	}
    	
    	System.out.println("The illegal passwords are: " + illegalPasswords);
    	return true;
    }
    
    private static boolean checkItFromFile(String str) {
        System.out.println("\n Inside checkItFromFile()");
        System.out.println("\n The input string = " + str);

        char prevchar = 0;//initially null
        char ch;
        int capitalFlag = 0;
        int lowerCaseFlag = 0;
        int numberFlag = 0;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            System.out.println(i + " index = " + ch);
            //System.out.println("prevchar = " + prevchar);
            
            if(prevchar == ch) {
            	repeatError = true;
            	return false;

            }
            
            if( Character.isDigit(ch)) {
            	System.out.println("\n Theres a number!");
            	numberFlag = numberFlag + 1;
                
            }
            if (Character.isUpperCase(ch)) {
            	System.out.println("\n Theres a CAPITAL!");
            	capitalFlag = capitalFlag + 1;
                
            } 
            if (Character.isLowerCase(ch)) {
            	System.out.println("\n Theres a lowercase!");
            	lowerCaseFlag = lowerCaseFlag + 1;
                
            }
            prevchar = ch;
            
        }
        System.out.println("\n The capitalFlag = " + capitalFlag);
        System.out.println("\n The lowerCaseFlag = " + lowerCaseFlag);
        System.out.println("\n The numberFlag = " + numberFlag);

        if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag > 0) {
        	System.out.println("\n Theres a number & CAPITAL & lower!");
            return true;
        }
        else if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag == 0) {
        	System.out.println("\n Needs lowercase!");
        	lowercaseError = true;
        	return false;
        }
        else if(numberFlag > 0 && capitalFlag == 0 && lowerCaseFlag > 0) {
        	System.out.println("\n Needs upercase!");
        	uppercaseError = true;
        	return false;
        }
        else if(numberFlag == 0 && capitalFlag > 0 && lowerCaseFlag > 0){
        	System.out.println("\n Needs number!");
        	numError = true;
        	return false;
        }
        else{
        	return true;
        }
    }   
   
	
}
