package userTypes;

import accountTypes.*;
import bank.BankGlobal;
import dateTime.DateTime;
import java.util.Scanner;

public class AccountManagerUser {
// LOC account interest rate is an offset from a global interest rate defined by the operations manager.
// So I used interestRate as the variable name.  If you decide to use something else, then change it in the 
// getLOC method in BankGlobal.java
//	+getCustomerSSN(CustomerUser):int
//	+openAccount(CustomerUser, BasicAccount):void
//	+closeAccount(CustomerUser, BasicAccount):void
//	+setLOCCap(LOCAccount, double):void
//	+getGlobalLoanCap():double
	
	/**
	 * Method to fetch a customerUser's SSN
	 * @param c - the customer
	 * @return <b>int</b> - returns the customer's SSN
	 */
	public int getCustomerSSN(CustomerUser c)
	{
		return c.getCustomerSSN(this);
	}
	
	public void enrollNewCustomer(){
		Scanner input = new Scanner(System.in);
		String firstName, middleName, lastName, username;
		boolean isMale;
		DateTime dob;
		int ssn, userID;
		String password;
		
		System.out.println("Enter the customer's First Name: ");
		firstName = input.next();
		System.out.println("Enter the customer's Middle Name: ");
		middleName = input.next();
		System.out.println("Enter the customer's Last Name: ");
		lastName = input.next();
		System.out.println("Is the customer male? (y/n)");
		char choice = input.next().charAt(0);
		if((choice == 'y') || (choice == 'Y')){
			isMale = true;
		}
		else isMale = false;
		System.out.println("Enter the customer's Date of Birth (YYYY/MM/DD): ");
		int year = input.useDelimiter("/").nextInt();
		int month = input.useDelimiter("/").nextInt();
		int day = input.useDelimiter("/").nextInt();
		dob = new DateTime(year, month, day, 12, 0, 0);
		System.out.println("Enter the customer's SSN (no dashes): ");
		ssn = input.nextInt();
		System.out.println("Enter the user's password: ");
		password = input.next();
		System.out.println("Enter the user's user name: ");
		username = input.next();
		userID = BankGlobal.getNewCustomerID();
		char[] password2 = password.toCharArray();
		
		
		CustomerUser c = new CustomerUser(firstName, middleName, lastName, isMale, dob, ssn, password2, username, userID);
		openNewCustomerAccount(c);
		input.close();
	}
	public void openNewCustomerAccount(CustomerUser c)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("What kind of account will " + c.getFirstName() + " have?\n1. Checking\n2. Savings\n3. Line of Credit\n4. Loan\n5. Certificate of Deposit\n(Enter [1-5]: ");
		int choice = input.nextInt();
		switch (choice){
		case 1: 
			setupCheckingAccount(c);
			break;
		case 2: 
			setupSavingsAccount(c);
			break;
		case 3: 
			setupLOCAccount(c);
			break;
		case 4: 
			setupLoanAccount(c);
			break;
		case 5: 
			setupCDAccount(c);
			break;		
		}	
		input.close();
	}
	
	//all interest rates should be positive, between 0 and 1
	
	public void setupCheckingAccount(CustomerUser c){
		CheckingAccount a = new CheckingAccount(c, BankGlobal.getNewAccountID());
		System.out.println("Checking account " + a.getAccountID() + " created successfully.");
	}
	
	public void setupSavingsAccount(CustomerUser c){ 
		//System.out.println("How much will your initial deposit be?");

		SavingsAccount a = new SavingsAccount(c, BankGlobal.getNewAccountID(), );
		System.out.println("Savings account " + a.getAccountID() + " created successfully.");
	}
	
	public void setupLOCAccount(CustomerUser c){
		Scanner input = new Scanner(System.in);
		System.out.println("What will be the LOCAccount's cap?\n");
		double amount = input.nextDouble();
		LOCAccount a = new LOCAccount(c, BankGlobal.getNewAccountID(), amount);
		System.out.println("LOCAccount " + a.getAccountID() + " created successfully.");
		input.close();
	}
	
	public void setupLoanAccount(CustomerUser c){
		// Loan can either have a personalized interest rate, or the bank Global "getInterestLoanRate"
		//if its personalized, it has to be "offset" from the global rate, so just ask the user how much they want it offset
		//if they want it the same, just put 0
		// so I suppose have a line to ask if they want to use the global, and if not, what do they want the rate to be?
		//LoanAccount a = new LoanAccount(c, BankGlobal.getNewAccountID(), interest, minPayment,)//need to change Loan account constructor, make initial loan an amount, construct transaction within constructor.
	}
	
	public void setupCDAccount(CustomerUser c){
		//CDAccount a = new CDAccount(c, BankGlobal.getNewAccountID(), )// do same for CDAccount.
	}
	
	public void setAccountLOCCap(LOCAccount account, double cap){
		account.setMinimumAccountBalance(cap);
	}
	public void closeAccount(CustomerUser c , BasicAccount a)
	{
		c.getCustomerAccountsList().remove(a);
	}
	
	public void getLOCCap(LOCAccount a)
	{
		a.getMinimumAccountBalance();
	}
}
