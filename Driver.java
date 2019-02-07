import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;//For ArrayList and Iterator

public class Driver {

    private ArrayList<String> list;
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
    int passwordLength = 0;
    String password;
    

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
        checkPasswordButton = new JButton("Check Password");
        
        // COMPARE PASSWORDS
        checkPasswordButton.addActionListener(new ActionListener() {
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
                	JOptionPane.showMessageDialog(null, "Not Equal");
                }
            }
        });
        
        
        panel.add(checkPasswordButton);
        
        checkPasswordInFileButton = new JButton("Check Password in File");
        panel.add(checkPasswordInFileButton);
        
        exitButton = new JButton("Exit");
        
        //EXIT BUTTON functionality
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        
        panel.add(exitButton);
        
        contentPane.add(panel);
    }
    
    
    private static boolean checkPassword(String str) {
        char prevchar = 0;
        char ch;
        int capitalFlag = 0;
        int lowerCaseFlag = 0;
        int numberFlag = 0;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            System.out.println(i + " index = " + ch);
            
            if(prevchar == ch) {
            	JOptionPane.showMessageDialog(null, "The password cannot contain more than two of the same "
            			+ "character in sequence.");
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
        if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag > 0) {
        	System.out.println("\n Theres a number & CAPITAL & lower!");
            return true;
        }
        else if(numberFlag > 0 && capitalFlag > 0 && lowerCaseFlag == 0) {
        	JOptionPane.showMessageDialog(null, "The password must contain at least one lowercase alphabetic character.");
        	return false;
        }
        else if(numberFlag > 0 && capitalFlag == 0 && lowerCaseFlag > 0) {
        	JOptionPane.showMessageDialog(null, "The password must contain at least one uppercase alphabetic character.");
        	return false;
        }
        else if(numberFlag == 0 && capitalFlag > 0 && lowerCaseFlag > 0){
        	JOptionPane.showMessageDialog(null, "The password must contain at least one digit.");
        	return false;
        }
        else{
        	return false;
        }
       // return false;
    }
    
    
   
	
}
