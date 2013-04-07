package userTypes;

import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.*;
import bank.Transaction;

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

	
	
	public void addCustomerAccount(BasicAccount account){
		customerAccounts.add(account);
	}
	
	public boolean getIsEmployee(){
		return this.isEmployee;
	}
	
	public void setIsEmployee(){
		this.isEmployee = true;
	}
	
	public void setIsNotEmployee(){
		this.isEmployee = false;
	}
}
