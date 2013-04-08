package userTypes;

import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.*;
import bank.Request;
import bank.Transaction;
import accountTypes.Withdrawable;
import accountStatistics.AccountStats;

public class TellerUser extends BasicUser {
	public TellerUser(String firstName, String middleName, String lastName, boolean isMale,
			DateTime dob, char[] password, String username, int userID){
		super(firstName, middleName, lastName, isMale, dob, password, username, userID);
	}
	
	public TellerUser(){
		super();
	}
	/**
	 * method will return account details of the account
	 */
	public getCurrentAccountDetails(BasicAccount account){
		//return account stats
		return new AccountStats(account);
	}
	public void transferMoneyFromChecking(double amount, CheckingAccount account1, CheckingAccount account2){
		//transfer funds of amount amount from account1 to account2
	}
	public void setUpAutomaticDeposits(BasicAccount account, double amount){
		//automatically deposit amount to account
	}
	public void withdraw(Transaction transaction, Withdrawable account){
		//withdraw amount from account after parsing through the Withdrawable interface
	}
	public void deposit(Transaction transaction, BasicAccount account){
		//deposit amount into account
	}
	public void incurTellerCharge(BasicAccount account){
		//does the account incur a teller charge
	}
	public void waiveTellerCharge(BasicAccount account){
		//waive the teller charge on account
	}
	public pendingRequests(){
		//return ArrayLists<Request>
	}
	public void approveRequests(Request request){
		//approve request and turn it into a transaction
	}
}
