package userTypes;

import accountTypes.*;
import bank.BankGlobal;
import bank.Transaction;
import dateTime.DateTime;
import java.util.Scanner;

public class AccountManagerUser extends BasicUser{
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
	
	
	/**
	 * Method called when the AccountManager selects to enroll a new customer into the system.  Creates a new CustomerUser,
	 * then prompts to set up an account to give that user.
	 */
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
	
	/**
	 * Method to add a new account to a CustomerUser's accounts.
	 * @param c - the customer to add the account to.  The customer will end up with one of the 5 account types.
	 */
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
		CheckingAccount a = new CheckingAccount(c);
		System.out.println("Checking account " + a.getAccountID() + " created successfully.");
	}
	
	public void setupSavingsAccount(CustomerUser c){ 
		Scanner input = new Scanner(System.in);
		System.out.println("How much will the initial deposit be?");
		double indep = input.nextDouble();
		SavingsAccount a = new SavingsAccount(c, new Transaction(indep, c, this, 1));
		System.out.println("Savings account " + a.getAccountID() + " created successfully.");
	}
	
	public void setupLOCAccount(CustomerUser c){
		Scanner input = new Scanner(System.in);
		System.out.println("What will be the LOCAccount's cap? (input positive number)");
		double amount = input.nextDouble();
		System.out.println("What will the interest offset from global(if any, positive or negative or 0)?");
		double offset=input.nextDouble();
		LOCAccount a = new LOCAccount(c, (- amount), offset);
		System.out.println("LOCAccount " + a.getAccountID() + " created successfully.");
		input.close();
	}
	
	public void setupLoanAccount(CustomerUser c){
		// Loan can either have a personalized interest rate, or the bank Global "getInterestLoanRate"
		//if its personalized, it has to be "offset" from the global rate, so just ask the user how much they want it offset
		//if they want it the same, just put 0
		// so I suppose have a line to ask if they want to use the global, and if not, what do they want the rate to be?
		//LoanAccount a = new LoanAccount(c, BankGlobal.getNewAccountID(), interest, minPayment,)//need to change Loan account constructor, make initial loan an amount, construct transaction within constructor.
		Scanner input = new Scanner(System.in);
		//input those things needed for the constructor below
		//LoanAccount crapbag = new LoanAccount(c, crapbag.getAccountID(), myinterestrateoffset, myminmontlyloanpayment, initialloan)
	
	}
	
	public void setupCDAccount(CustomerUser c){
		//CDAccount a = new CDAccount(c, BankGlobal.getNewAccountID(), )// do same for CDAccount.
	}
	
	/**
	 * Method to set an account's minimum account balance for use with LOCAccounts.  The value should be negative to represent the borrowed limit.
	 * @param account - the account to set the LOCCap for.
	 * @param cap - the amount to set the cap to.
	 */
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
