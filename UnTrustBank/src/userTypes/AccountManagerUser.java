package userTypes;

import accountTypes.BasicAccount;
import accountTypes.LOCAccount;
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
		return c.getCustomerSSN();
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
	}
	public void openNewCustomerAccount(CustomerUser c)
	{
		
	}
	public void closeAccount(CustomerUser c , BasicAccount a)
	{
		c.getCustomerAccountsList().remove(a);
	}
	
	public void getLOCCap(LOCAccount a)
	{
		a.getMinimumAccountBalance();
	}
	public void setLOCCap(LOCAccount a, double newCap)
	{
		a.setMinimumAccountBalance(newCap);
	}
}
