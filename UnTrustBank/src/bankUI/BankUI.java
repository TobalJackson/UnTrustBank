package bankUI;
import java.util.Stack;
import java.util.Scanner;

import bank.BankGlobal;
//import bank.BankGlobal;
public class BankUI {
//	public static BankGlobal bank = new BankGlobal();
	Scanner input = new Scanner(System.in);
	public BankUI(){
		Stack<Integer> menuHistory = new Stack<Integer>();
		System.out.println("Welcome to UnTrust Bank!\n" +
				"Please enter your user name: \n");
		String userName = input.next();
		input.close();
		//write BankGlobal method to loop thru all users - both customers and employees,  and 
		//compare the username to each user's username- check for a match
		
		//if BankGlobal.
	}
	
}
