package userTypes;

import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.*;
import bank.BankGlobal;
import bank.Request;
import bank.Transaction;
import accountTypes.Withdrawable;
import accountStatistics.AccountStats;



public class TellerUser extends BasicUser {
	protected ArrayList<Request> requests;
	public TellerUser(String firstName, String middleName, String lastName, boolean isMale,
			DateTime dob, char[] password, String username, int userID){
		super(firstName, middleName, lastName, isMale, dob, password, username);
	}
	
	public TellerUser(){
		super();
	}
	
	public void getNewUserID(){
		this.userID = BankGlobal.getNewEmployeeID();
	}
	/**
	 * method will return account details of the account by inputting the account as a parameter in AccountStats class
	 * @return AccountStats object made using the account
	 */
//	public AccountStats getCurrentAccountDetails(BasicAccount account){
//		//return account stats
//		return new AccountStats(account);
//	}
	
	/**
	 * creates two Transactions of withdrawal and deposit and applies them to the accounts to move amount from
	 * account1 to account2
	 * @param amount
	 * @param account1
	 * @param account2
	 * @param customer
	 */
	public void transferMoneyFromChecking(double amount, CheckingAccount account1, CheckingAccount account2, CustomerUser customer){
		//transfer funds of amount amount from account1 to account2
		Transaction w = new Transaction(amount, customer, this, Transaction.WITHDRAWAL);
		Transaction d = new Transaction(amount, customer, this, Transaction.DEPOSIT);
	}
	
	
	//What does this do?
	public void setUpAutomaticDeposits(BasicAccount account, double amount){
		//automatically deposit amount to account
	}
	public void withdraw(Transaction transaction, BasicAccount account){
		//withdraw amount from account after parsing through the Withdrawable interface
		account.appendTransaction(transaction, this);
		
	}
	public void deposit(Transaction transaction, BasicAccount account){
		//deposit amount into account
		account.appendTransaction(transaction, this);
	}
	
	/**
	 * sets the field in BasicAccount to indicate that there is a tellerCharge
	 * @param account
	 */
	public void incurTellerCharge(BasicAccount account){
		//does the account incur a teller charge
		account.setTellerCharge(true);
	}
	
	/**
	 * sets the field in BasicAccount to indicate that there is not a tellerCharge
	 * @param account
	 */
	public void waiveTellerCharge(BasicAccount account){
		//waive the teller charge on account
		account.setTellerCharge(false);
	}
	
	/**
	 * Method for tellers to request a list of unapproved transactions from BankGlobal.
	 * @return
	 */
	//how are the requests being sent to the Teller to be added to this ArrayList?
	public ArrayList<Request> getPendingRequests(){
		//return ArrayLists<Request>
		return BankGlobal.getPendingRequests();
	}
	
	/**
	 * turns request directly into a new transaction
	 * @param request
	 */
	public void approveRequests(Request request){
		//approve request and turn it into a transaction
		if(request.getRequestType() == Transaction.WITHDRAWAL){
			if (request.getAccount().getCurrentAccountBalance() - request.getRequestAmount() > 0){
				Transaction t = new Transaction(request.getRequestAmount() * -1, request.getCustomer(), this, Transaction.WITHDRAWAL);
				request.getAccount().appendTransaction(t, request.getCustomer());
				BankGlobal.markRequestProcessed(request);
			}
		}
		else{
			Transaction t = new Transaction(request.getRequestAmount(), request.getCustomer(), this, Transaction.OTHER);
			request.getAccount().appendTransaction(t, request.getCustomer());
			BankGlobal.markRequestProcessed(request);
		}
	}
}
