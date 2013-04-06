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
	public ArrayList<Transaction> getFullTransactionHistory(){
		ArrayList<Transaction> fullTransactionHistory;
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
