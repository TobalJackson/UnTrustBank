package userTypes;

import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.*;
import bank.Transaction;

/**
 * @author tobaljackson
 *
 */
public class CustomerUser extends BasicUser {
	
	private ArrayList<BasicAccount> customerAccounts;
	private boolean isEmployee;
	private boolean isActiveCustomer;
	
	
	public CustomerUser(String firstName, String middleName, String lastName, boolean isMale,
			DateTime dob, char[] password, String username, int userID){
		super(firstName, middleName, lastName, isMale, dob, password, username, userID);
	}
	public CustomerUser(){
		super();
	}
	
	/**
	 * Method will iterate through all of a CustomerUser's accounts, and return a full, unsorted list of transactions.
	 * @return <b>ArrayList<Transaction></b> - An ArrayList of all of a customer's transactions. 
	 *   //need to add for Transactions SortableByBasicAccount<BasicAccount> methods (interface?) for each field type 
	 *   //e.g. add sortType as an optional (overridden Method?)
	 */
	public ArrayList<Transaction> getFullTransactionHistory(){ // will get an ArrayList<Transaction> of all transactions a user.
		ArrayList<Transaction> fullTransactionHistory = new ArrayList<Transaction>();
		for(BasicAccount a : customerAccounts){
			for(Transaction t : a.getFullTransactionHistory()){
				fullTransactionHistory.add(t);
			}
		}
		return fullTransactionHistory;
	}
	/**
	 * This method will iterate through and return an Account's Transactions.
	 * @param account
	 * @return <b>Transaction</b> - An ArrayList<Transaction> containing all of an account's Transactions.
	 */
	public ArrayList<Transaction> getAccountTransactionHistory(BasicAccount account){//will do same as above, but for a BasicAccount(loan, checking, etc...)
		ArrayList<Transaction> fullTransactionHistory = new ArrayList<Transaction>();
		for (Transaction t : account.getFullTransactionHistory()){
			fullTransactionHistory.add(t);
		}
		return fullTransactionHistory;
	}
	
	
	/**
	 * Method to flag a transaction as fraudulent.
	 * @param t - the transaction to mark as fraudulent.  The transaction must be one owned by the CustomerUser.
	 */
	public void flagFraudulentTransaction(Transaction t){
		if(!(t.getCustomer() == this)){
			throw new IllegalArgumentException();
		}
		else t.setIsFraudulent();
	}
	
	/**
	 * Method to get all of a customer's fraudulent Transactions.
	 * @return <b>ArrayList<Transaction></b> - returns an ArrayList of all a customer's fraudulent flagged Transactions.
	 */
	public ArrayList<Transaction> getFraudulentTransactions(){
		ArrayList<Transaction> fraudulentTransactions = new ArrayList<Transaction>();
		for (Transaction t : this.getFullTransactionHistory()){
			if (t.getIsFraudulent()) fraudulentTransactions.add(t);
		}
		return fraudulentTransactions;		
	}
	
	/**
	 * Method to get all of a customer's fraudulent Transactions from an account they own.
	 * @param account - an account belonging to this CustomerUser.
	 * @return <b>ArrayList<Transaction></b> - returns an ArrayList of all a customer's fraudulent flagged Transactions from an account.
	 */
	public ArrayList<Transaction> getFraudulentTransactions(BasicAccount account){
		ArrayList<Transaction> fraudulentTransactions = new ArrayList<Transaction>();
		if (!(account.getAccountOwner() == this)){
			throw new IllegalArgumentException();
		}
		else for (Transaction t : this.getAccountTransactionHistory(account)){
			if (t.getIsFraudulent()) fraudulentTransactions.add(t);
		}
		return fraudulentTransactions;		
	}
	
	/**
	 * Method will append to the CusomerUser's list of owned accounts an <b>argument</b> account.
	 * @see {@link #customerAccounts}
	 * @param account - add an existing account BasicAccount to a CustomerUser's accountList (customerAccounts)
	 */
	public void addCustomerAccount(BasicAccount account){
		customerAccounts.add(account);
	}
	
	/**
	 * Getter to see whether CustomerUser is an Employee.
	 * @return <b>boolean</b> - true if Customer is Employee, else false.
	 */
	public boolean getIsEmployee(){
		return this.isEmployee;
	}
	
	/**
	 * Setter for making CustomerUser an employee.
	 */
	public void setIsEmployee(){
		this.isEmployee = true;
	}
	
	/**
	 * Setter for making CustomerUser not an employee
	 */
	public void setIsNotEmployee(){
		this.isEmployee = false;
	}
}
