package userTypes;

import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.BasicAccount;
import accountTypes.CustomerTransferSource;
import accountTypes.LOCAccount;
import accountTypes.LoanAccount;
import accountTypes.CDAccount;
import accountTypes.CheckingAccount;
import accountTypes.SavingsAccount;
import accountTypes.ServiceChargeable;
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
							Transaction withdraw = ((CustomerTransferSource)source).customerTransferWithdrawal(amount);
							//source.appendTransaction(withdraw, this); //handled by the customerTransferWithdrawal method
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
	 * Method will allow CustomerUser to request a deposit into an account, adding the request to the accounts pendingRequestList, for view/retrieval by the Customer, as well as to the GlobalRequestList for access by Tellers.
	 * @param amount - the amount of the deposit request.
	 * @param account - the account being deposited into.
	 */
	public void requestDeposit(double amount, BasicAccount account){//any account is eligible to request a deposit into
		if (amount > 0){
			if (account.getAccountOwner() == this){
				Request r = new Request(account, amount, account.getAccountOwner(), Request.DEPOSIT);
				account.addRequest(r);
			}
		}
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
