package bankUI;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import bank.BankGlobal;

import userTypes.*;
public class BankUI {
//	public static BankGlobal bank = new BankGlobal();
	private BasicUser currentUser;
	private ArrayList<BasicUser> loggedInUsers = new ArrayList<BasicUser>();
	Scanner input = new Scanner(System.in);
	Stack<Integer> menuHistory = new Stack<Integer>();
	public BankUI(){
		displayMainMenu();
	}
	public void displayMainMenu(){
		System.out.println("Hello! Welcome to UnTrust Bank!\n" +
				"Please enter your user name: \n");
		String userName = input.next();
		input.close();
		if (BankGlobal.userNameExists(userName)){
			System.out.println("Please input your password: ");
			String password = input.next();
			if (password.equals(BankGlobal.getUserByUserName(userName).isPasswordValid(password.toCharArray()))){
				currentUser = BankGlobal.getUserByUserName(userName);
				loggedInUsers.add(currentUser);
				getUserContext(currentUser);
			}
		}
	}
	//Commented out because of angry red squiggles - Dania. Feel free to try and fix it
	
	public void getUserContext(BasicUser user){
//		int userType = user.getUserType();
//		switch(userType){
//		case BasicUser.CUSTOMER_USER_TYPE:
//			CustomerUI custUI = new CustomerUI((CustomerUser)user, this);
//			break;
//		case BasicUser.ACCOUNT_MANAGER_USER_TYPE:
//			AccountManagerUI acmgrUI = new AccountManagerUI(user);
//			break;
//		case BasicUser.ACCOUNTANT_USER_TYPE:
//			AccountantUI acntntUI = new AccountantUI(user);
//			break;
//		case BasicUser.AUDITOR_USER_TYPE:
//			AuditorUI auditUI = new AuditorUI(user);
//			break;
//		case BasicUser.OPERATION_MANAGER_USER_TYPE:
//			OperationManagerUI opmgrUI = new OperationManagerUI(user);
//			break;
//		case BasicUser.TELLER_USER_TYPE:
//			TellerUI tlrUI = new TellerUI(user);
//			break;
		}
	}

