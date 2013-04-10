package userTypes;

import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.BasicAccount;
import accountTypes.CustomerTransferSource;
import accountTypes.LOCAccount;
import accountTypes.LoanAccount;
import accountTypes.CDAccount;
import accountTypes.CheckingAccount;
import accountTypes.OtherCustomerDepositable;
import accountTypes.SavingsAccount;
import accountTypes.ServiceChargeable;
import accountTypes.WithdrawRequestable;
import bank.BankGlobal;
import bank.Request;
import bank.Transaction;


/**
 * @author tobaljackson
 *
 */
public class CustomerUser extends BasicUser {
	
	private ArrayList<BasicAccount> customerAccounts;
	private boolean isEmployee;
	private boolean isActiveCustomer;
	private int SSN;
	
	public ArrayList<BasicAccount> getCustomerAccountsList()
	{
		return customerAccounts;
	}
	
	public CustomerUser(String firstName, String middleName, String lastName, boolean isMale,
			DateTime dob,int ssn, char[] password, String username, int userID){
		super(firstName, middleName, lastName, isMale, dob, password, username, userID);
		this.SSN = ssn;
		BankGlobal.appendToGlobalCustomerList(this);
	}
	public CustomerUser(){
		super();
		BankGlobal.appendToGlobalCustomerList(this);
	}
	
	public int getCustomerSSN()
	{
		return this.SSN;
	}
	
	/**
	 * Method will iterate through all of a CustomerUser's accounts, and return a full, unsorted list of transactions.
	 * @return <b>ArrayList<Transaction></b> - An ArrayList of all of a customer's transactions. 
	 *   //need to add for Transactions SortableByBasicAccount<BasicAccount> methods (interface?) for each field type 
	 *   //e.g. add sortType as an optional (overridden Method?)
	 */
	public ArrayList<Transaction> getFullTransactionHistory(){ 
		ArrayList<Transaction> fullTransactionHistory = new ArrayList<Transaction>();
		for(BasicAccount a : customerAccounts){
			for(Transaction t : a.getFullTransactionHistory()){
				fullTransactionHistory.add(t);
			}
		}
		return fullTransactionHistory;
	}
	
	/**
	 * This overloaded method will iterate through and return an Account's Transactions.
	 * @param account
	 * @return <b>Transaction</b> - An ArrayList<Transaction> containing all of an account's Transactions.
	 */
	public ArrayList<Transaction> getFullTransactionHistory(BasicAccount account){
		ArrayList<Transaction> fullTransactionHistory = new ArrayList<Transaction>();
		for (Transaction t : account.getFullTransactionHistory()){
			fullTransactionHistory.add(t);
		}
		return fullTransactionHistory;
	}
		
	/**
	 * Method to get all transactions from CustomerUser's open accounts.
	 * @return <b>ArrayList<Transaction></b> - returns an ArrayList containing all transactions from all of a user's open accounts, unsorted.
	 */
	public ArrayList<Transaction> getOpenAccountTransactionHistory(){
		ArrayList<Transaction> openAccountTransactionHistory = new ArrayList<Transaction>();
		for (BasicAccount account : customerAccounts){
			if (account.getIsActiveAccount()){
				openAccountTransactionHistory.addAll(account.getFullTransactionHistory());
			}
		}
		return openAccountTransactionHistory;
	}
		
	/**
	 * Method to flag a transaction as fraudulent.
	 * @param t - the transaction to mark as fraudulent.  The transaction must be one owned by the CustomerUser.
	 */
	public void flagFraudulentTransaction(Transaction t){ //BasicAccount can also do this. remove 1? or make 1 use the other...
		if(!(t.getCustomer() == this)){
			throw new IllegalArgumentException();
		}
		else t.setIsFlaggedFraudulent();
	}
	
	/**
	 * Method to get all of a customer's fraudulent Transactions.
	 * @return <b>ArrayList<Transaction></b> - returns an ArrayList of all a customer's fraudulent flagged Transactions.
	 */
	public ArrayList<Transaction> getFraudulentTransactions(){
		ArrayList<Transaction> fraudulentTransactions = new ArrayList<Transaction>();
		for (Transaction t : this.getFullTransactionHistory()){
			if (t.getIsFlaggedFraudulent()) fraudulentTransactions.add(t);
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
		else for (Transaction t : this.getFullTransactionHistory(account)){
			if (t.getIsFlaggedFraudulent()) fraudulentTransactions.add(t);
		}
		return fraudulentTransactions;		
	}
	
	/**
	 * Method for a customerUser to transfer an amount of money from one owned account to another.  Only able to transfer from accounts of type Checking, CD, and Savings, each of which implement the interface CustomerTransferSource.
	 * @param amount - the amount of money to withdraw.
	 * @param source - the source account to transfer from.
	 * @param destination - the destination account to transfer to.
	 */
	public void transferBetweenOwnAccounts(double amount, BasicAccount source, BasicAccount destination){
		if(source instanceof CustomerTransferSource){
			if(source.getAccountOwner() == this){
				if(destination.getAccountOwner() == this){
					if(!(source == destination)){
						if(amount > 0){
							Transaction withdraw = new Transaction(amount, source.getAccountOwner(), source.getAccountOwner(), Transaction.WITHDRAWAL);
							source.appendTransaction(withdraw, this); 
							Transaction deposit = new Transaction(amount, source.getAccountOwner(), source.getAccountOwner(), Transaction.TRANSFER);
							destination.appendTransaction(deposit, this);
						}
						else throw new IllegalArgumentException("Transfers must be positive!");
					}
					else throw new IllegalArgumentException("Source and destination accounts cannot be the same!");
				}
				else throw new IllegalArgumentException("You must own the account you're transferring to!");
			}
			else throw new IllegalArgumentException("You must own the account you're transferring from!");
		}
		else throw new IllegalArgumentException("You may not transfer from this account type!");
	}
	
	/**
	 * Method for a customer to transfer funds from an account they own to another customer's account.
	 * @param amount - the amount to be transferred. Must be greater than 0.
	 * @param source - the account to transfer the funds from. Must be an account the customer owns.
	 * @param destination - the destination account to transfer into.
	 */
	public void transferToOtherCustomerAccount(double amount, BasicAccount source, BasicAccount destination){
		if(amount > 0){
			if(source.getAccountOwner() == this){
				if(source instanceof CustomerTransferSource){
					if(source.getCurrentAccountBalance() - amount > 0){
						if(destination.getAccountOwner() != this){
							Transaction withdraw = new Transaction(amount, this, this, Transaction.WITHDRAWAL);
							source.appendTransaction(withdraw, this);
							Transaction transfer = new Transaction(amount, destination.getAccountOwner(), this, Transaction.TRANSFER);
							destination.appendTransaction(transfer, this);
						}
						else throw new IllegalArgumentException("This function is for transferring into another user's account!");
					}
					else throw new IllegalArgumentException("Insufficient funds!");
				}
				else throw new IllegalArgumentException("You may only initiate a transfer from Checking, COD, or Savings account!");
			}
			else throw new IllegalArgumentException("You may only transfer from an account you own");
		}
		else throw new IllegalArgumentException("Transfer amounts must be positive");
	}
	
	/**
	 * Method will allow CustomerUser to request a deposit into an account, adding the request to the accounts pendingRequestList, for view/retrieval by the Customer, as well as to the GlobalRequestList for access by Tellers.
	 * @param amount - the amount of the deposit request.
	 * @param account - the account being deposited into.
	 */
	public void requestDeposit(double amount, BasicAccount account){
		if (amount > 0){
			if (account.getAccountOwner() == this){//any account belonging to the customer is eligible to request a deposit into.
				Request r = new Request(account, amount, account.getAccountOwner(), Request.DEPOSIT);
				account.addRequest(r);
			}
			else if(account instanceof OtherCustomerDepositable){//otherwise only checking and savings account can be deposited into.
				Request r = new Request(account, amount, this, Request.DEPOSIT);
				account.addRequest(r);
			}
			else throw new IllegalArgumentException("Deposits can only be made into own accounts, or Checking and Savings account of other users");
		}
		else throw new IllegalArgumentException("Deposits must be positive");
	}
	
	/**
	 * CustomerUser may request a withdrawal from a COD or Savings account they own.  The request amount should be positive.  The request will be sent to the account's pending requests, and a teller may approve it.
	 * @param amount - the amount of money to request to withdraw
	 * @param account - the customer's account to request withdrawing from
	 */
	public void requestWithdrawal(double amount, BasicAccount account) throws IllegalArgumentException{//customer can only request withdraw from COD and savings.
		if(account instanceof WithdrawRequestable){
			if(amount > 0){
				if(account.getAccountOwner() == this){
					Request r = new Request(account, amount, this, Transaction.WITHDRAWAL);
					account.addRequest(r);
				}
				else throw new IllegalArgumentException("You may only request a withdrawal from an account you own");
			}
			else throw new IllegalArgumentException("Request amount must be positive!");
		}
		else throw new IllegalArgumentException("You may only withdraw from COD and Savings accounts");
	}
	
	/**
	 * Method to retrieve the customer's active status.
	 * @return <b>boolean</b> -returns true if the customer is an active one, othewise false.
	 */
	public boolean isCustomerActive(){
		return this.isActiveCustomer;
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
	 * Method to return a list of a customer's closed accounts.
	 * @return <b>ArrayList<BasicAccount></b> - an arraylist of the customer's closed accounts.
	 */
	public ArrayList<BasicAccount> getClosedAccounts(){
		ArrayList<BasicAccount> results = new ArrayList<BasicAccount>();
		for(BasicAccount a : customerAccounts){
			if(!a.getIsActiveAccount()){
				results.add(a);
			}
		}
		return results;
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
