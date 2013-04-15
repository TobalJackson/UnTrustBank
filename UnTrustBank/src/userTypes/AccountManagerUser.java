package userTypes;

import accountTypes.*;
import bank.BankGlobal;
import bank.Transaction;
import dateTime.DateTime;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.pipe.NextAction;

public class AccountManagerUser extends BasicUser{
// LOC account interest rate is an offset from a global interest rate defined by the operations manager.
// So I used interestRate as the variable name.  If you decide to use something else, then change it in the 
// getLOC method in BankGlobal.java

	public AccountManagerUser(String firstName, String middleName, String lastName, boolean isMale, DateTime dob, char[] password, String username){
		super(firstName, middleName, lastName, isMale, dob, password, username);
		this.userType = BasicUser.ACCOUNT_MANAGER_USER_TYPE;
		BankGlobal.appendToGlobalEmployeeList(this);
	}
	
	public AccountManagerUser(){
		super("Sandy", false);
		this.userType = BasicUser.ACCOUNT_MANAGER_USER_TYPE;
		BankGlobal.appendToGlobalEmployeeList(this);
	}
	
	/**
	 * Method to fetch a customerUser's SSN
	 * @param c - the customer
	 * @return <b>int</b> - returns the customer's SSN
	 */
	public int getCustomerSSN(CustomerUser c)
	{
		return c.getCustomerSSN(this);
	}
	
	public void getNewUserID(){
		this.userID = BankGlobal.getNewEmployeeID();
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
		
		
		CustomerUser c = new CustomerUser(firstName, middleName, lastName, isMale, dob, ssn, password2, username);
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
		input.close();
	}
	
	public void setupLOCAccount(CustomerUser c){
		Scanner input = new Scanner(System.in);
		System.out.println("What will be the LOCAccount's cap? (input positive number)");
		double amount = input.nextDouble();
		if(( -amount+ BankGlobal.getUsedLoanCap())<BankGlobal.getLoanCap()){
			System.out.println("This amount exceeds the bank's global loan cap. The maximum of the LOC account may not exceed " + Math.abs(BankGlobal.getLoanCap()-BankGlobal.getUsedLoanCap()) + " at this time.");
			
			while(amount> Math.abs(BankGlobal.getLoanCap()-BankGlobal.getUsedLoanCap()));
				System.out.println("Please enter new amount or enter -1 to cancel the LOC Account.");
				amount=input.nextDouble();
		}
		if(amount==-1){
			System.exit((0));
		}
		
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
		//LoanAccount a = new LoanAccount(c, BankGlobal.getNewAccountID(), interest, minPayment,)//need to change Loan account 
		//constructor, make initial loan an amount, construct transaction within constructor.
		
		Scanner input = new Scanner(System.in);
		System.out.println("You are creating a loan for " + c.getFirstName() + " " + c.getLastName());
		System.out.println("How much are we loaning Customer " + c.getLastName() + "? (input as positive number)");
		double amount = input.nextDouble();
		if(( -amount+ BankGlobal.getUsedLoanCap())<BankGlobal.getLoanCap()){
			System.out.println("This amount exceeds the bank's global loan cap. The Loan amount may not exceed " + Math.abs(BankGlobal.getLoanCap()-BankGlobal.getUsedLoanCap()) + " at this time.");
			
			while(amount> Math.abs(BankGlobal.getLoanCap()-BankGlobal.getUsedLoanCap()));
				System.out.println("Please enter new amount or enter -1 to cancel the Loan.");
				amount=input.nextDouble();
		}
		if(amount==-1){
			System.exit((0));
		}
		
		System.out.println("How much with the interest rate on this loan be offset from the global rate of" + BankGlobal.getInterestRateLoan() + "?");
		System.out.println("(If it is not offset, please enter 0.");
		double interestoffset = input.nextDouble();
		System.out.println("How much is the minimum monthly payment ($) ?");
		double payments = input.nextDouble();
		input.close();
		LoanAccount LA = new LoanAccount(c, interestoffset, payments, new Transaction((-amount),c,this, 0));
		System.out.println("New loan with account # " + LA.getAccountID() + " successfully created for " + c.getFirstName() + " " + c.getLastName());
	
	}
	
	public void setupCDAccount(CustomerUser c){
		Scanner input = new Scanner(System.in);
		System.out.println("You are creating a new Certificate of Deposit for" + c.getFirstName() + " " + c.getLastName() + ".");
		System.out.println("For how much is this CoD? ($)");
		double CDamount = input.nextDouble();
		System.out.println("What is the duration of the loan in years? Options: .5, 1, 2, 3, 4, 5");
		double _duration = input.nextDouble();
		if(_duration==.5){
			_duration=0;
		}
		int duration=(int) _duration;
		System.out.println("What will be the minimum balance required in this account?");
		double minbalance=input.nextDouble();
		while(minbalance>CDamount){
			System.out.println("The minimum balance must be less than amount of the Certificate of Deposit. Please try again");
			minbalance=input.nextDouble();
		}
		input.close();
		CDAccount ACDC = new CDAccount(c,new Transaction(CDamount, c,this,1), duration, minbalance );
		System.out.println("New Certificate of Deposit with account #" + ACDC.getAccountID() + " successfully created for "+ c.getFirstName() + " " + c.getLastName() );

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
