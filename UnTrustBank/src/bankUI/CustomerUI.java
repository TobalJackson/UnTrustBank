package bankUI;
import userTypes.*;
import java.util.Scanner;
public class CustomerUI {
	CustomerUser user;
	Scanner input = new Scanner(System.in);
	BankUI menuInstance;
	public CustomerUI(CustomerUser user, BankUI uiInstance){
		menuInstance = uiInstance;
		this.user = user;
		customerMenu();
	}
	
	public void customerMenu(){
		int choice;
		System.out.println("Customer Menu for " + user.getUsername() + "\n\n" +
				"Welcome " + user.getFirstName() + ", what would you like to do today?\n\n" +
				"1. View accounts\n" +
				"2. Make account deposit\n" +
				"3. Make account withdrawal\n" +
				"4. View account overview\n\n" +
				"0. Switch Users");
		choice = input.nextInt();
		input.close();
		menuChoice(choice);
	}
	
	public void menuChoice(int choice){
		switch (choice){
//		case 1:
//			viewAccountsMenu();
//			break;
		case 2:
			depositMenu();
			break;
		case 3:
			withdrawalMenu();
			break;
		case 4:
			viewAccountStats();
			break;
		case 0:
			this.menuInstance.displayMainMenu();
		}
	}
	private void viewAccountsMenu(int accountID) {
		// TODO Auto-generated method stub
		
	}
	private void depositMenu() {
		// TODO Auto-generated method stub
		
	}
	private void withdrawalMenu() {
		// TODO Auto-generated method stub
		
	}
	private void viewAccountStats() {
		System.out.println("Account Overview for " + user.getUsername() + "\n\n");
		user.printAccountSummary();
		System.out.println("Enter an account ID to view more options for that account, or enter '0' to return");
		int choice = input.nextInt();
		if (choice == 0){
			this.customerMenu();
		}
		else viewAccountsMenu(choice);
	}
}
