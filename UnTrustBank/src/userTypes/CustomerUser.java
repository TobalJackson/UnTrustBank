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
	public ArrayList<Transaction> getFullTransactionHistory(){ // will get an ArrayList<Transaction> of all transactions a user.
		ArrayList<Transaction> fullTransactionHistory = new ArrayList<Transaction>();
		for(BasicAccount a : customerAccounts){
			for(Transaction t : a.getFullTransactionHistory()){
				fullTransactionHistory.add(t);
			}
		}
		return fullTransactionHistory;
	}
	
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
