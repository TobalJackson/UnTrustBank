package accountTypes;
// test comment
//will this stay?
//stay put
// <3

import javax.swing.*;
import dateTime.Time;
import bank.BankGlobal;
import bank.Transaction;
import java.util.ArrayList;
import bank.Request;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;
import dateTime.DateTime;
import userTypes.AuditorUser;

public abstract class BasicAccount implements Iterable<BasicAccount>{
	protected ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	protected ArrayList<Request> requestList = new ArrayList<Request>();
	//private double accountBalance;
	protected boolean isActiveAccount;
	protected CustomerUser owner;
	protected double accountBalance; 
	protected int accountID; //acountID is based on other existant accounts, therefore should be generated rather than specified
	protected DateTime accountCreatedOn;
	protected boolean isEmployeesAccount;
	protected double accruedInterest;
	protected double minimumAccountBalance;
	protected double serviceFee;
	protected double maximumAccountBalance;
	protected int numWithdrawals;
	protected int numDeposits;
	protected boolean hasTellerCharge;
	
	public BasicAccount(CustomerUser owner){ //default constructor only accepts Customer and account ID
		this.owner = owner;
		owner.addCustomerAccount(this); //adds the account to the Customer's list of accounts.
		this.accountID = BankGlobal.getNewAccountID();
		
		
		this.isActiveAccount = true;
		this.accountCreatedOn = new DateTime(1); //new DateTime() constructor specifies the time as now
		this.isEmployeesAccount = owner.getIsEmployee(); //accounts are not employee accounts by default
		this.accruedInterest = 0;
		//this.transactionList.add(mytransaction);
		BankGlobal.appendToGlobalAccountList(this);
	}
	

	public abstract void respondToTimeChange(OperationManagerUser OperationManagerIntiatingTimeChange);
	
	public void addRequest(Request request){
		requestList.add(request);
	}
	
	/**
	 * Method to get a list of pending requests for an account.
	 * @return <b>ArrayList<Request></b> - returns an arrayList of an accounts pending requests for use by Customer.
	 */
	public ArrayList<Request> getPendingRequests(){
		ArrayList<Request> result = new ArrayList<Request>();
		for(Request r : requestList){
			if(!r.isRequestApproved()){
				result.add(r);
			}
		}
		return result;
	}
	
	/**
	 * Method to return the account's Minimum allowable Balance.
	 * @return <b>double</b> - returns the account's minimum allowable Balance, set by Operation Manager, can be referenced from BankGlobal.
	 */
	public double getMinimumAccountBalance(){
		return minimumAccountBalance;
	}
	
	/**
	 * Method to set the account's minimum allowable balance.
	 * @param min - the double amount corresponding to this account's minimum allowable balance. Should be set by Operations manager 
	 * or indirectly through BankGlobal process.
	 */
	public void setMinimumAccountBalance(double min){
		minimumAccountBalance = min;
	}
	
	/**
	 * Method to get this account's maximum allowable balance (intended to be used with account's whose balance is usually negative e.g. Loan accounts)
	 * @return - returns a double amount corresponding to the account's maximum allowable balance (should be 0 with most loan accounts).
	 */
	public double getMaximumAccountBalance(){
		return maximumAccountBalance;
	}
	
	/**
	 * Method to set the account's maximum allowable balance.  Should most likely be set by Operation manager or by BankGlobal subroutine.
	 * @param max - the double value to set the account's maximum allowable balance to.
	 */
	public void setMaximumAccountBalance(double max){
		maximumAccountBalance = max;
	}
 
	/**
	 * Method to append a transaction to the account's transaction log.  Appending a transaction to an account will automatically update the account's accountBalance field. 
	 * @param transaction - the transaction to be appended to the account's transaction list.
	 * @param initiator - the user initiating the transaction.
	 */
	public void appendTransaction(Transaction transaction, BasicUser initiator){
		if(isActiveAccount){
			transactionList.add(transaction);
			updateCurrentAccountBalance(); //could be optimized; can add only the new amount each transaction...
		}
		else{
			accountClosedError();
			}
	}
	
	/**
	 * Method which will update the accountBalance field based on all Active Transactions in the transaction list. Ignores transactions marked as Fraudulent.
	 * @see {@link #appendTransaction} {@link #accountBalance}
	 */
	public void updateCurrentAccountBalance(){
		double currentbalance=0;
		if(transactionList.size()>0){
			for(Transaction t:transactionList){
				System.out.println(transactionList.size());
				if(t.getIsApplied() == true){
					currentbalance+=t.getAmount();
					System.out.println(t.getAmount());
				}
			}
		}
		accountBalance=currentbalance;
	}

	/**
	 * Method to return the CustomerUser who owns the account.
	 * @return <b>CustomerUser</b> - the owner of this account.
	 */
//	public CustomerUser getOwner(){
//		return owner;
//	}
	
	/**
	 * Gets the accountBalance as updated by updateCurrentAccountBalance().
	 * @return - <b>double</b> - the double balance of the account.
	 */
	public double getCurrentAccountBalance(){
		return accountBalance;
	}
	
	public ArrayList<Transaction> getFullTransactionHistory(){
		return transactionList;
	}
	
	/**
	 * Gets a the transaction list of <b>applied</b> transactions only.  Not to be confused with {@link #getFullTransactionHistory()} which will include non-applied transactions (MarkedFraudulent).
	 * @return <ArrayList<Transaction> - list of Transactions that are applied to account (count toward balance.
	 */
	public ArrayList<Transaction> getAppliedTransactionHistory(){
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		for(Transaction t : transactionList){
			if (t.getIsApplied()){
				list.add(t);
			}
		}
		return list;
	}
	public void flagFraudulentTransaction(Transaction t){
		t.setIsFlaggedFraudulent();
	}
	public void unflagFraudulentTransaction(Transaction t, AuditorUser AM){
		t.setIsNotFlaggedFraudulent();
	}
	
	public ArrayList<Transaction> getFraudulentTransactions(){
		ArrayList<Transaction> fraudulentTransactions = new ArrayList<Transaction>();
		for (Transaction t : transactionList){
			if (t.getIsFlaggedFraudulent()){
				fraudulentTransactions.add(t);
			}
		}
		return fraudulentTransactions;
	}
	public void closeAccount(BasicUser initiator){
		if(accountBalance>0 || accountBalance <0){
		Transaction closingTransaction = new Transaction((-accountBalance), owner, initiator, 4);
		transactionList.add(closingTransaction);}
		updateCurrentAccountBalance();
		isActiveAccount = false;
	
	}
	
	public void accountClosedError(){
		String st="this account is closed. you're not allowed to do this :/";
		JOptionPane.showMessageDialog(null,st);	
	}
	
	/**
	 * Method to return the CustomerUser who owns the account.
	 * @return <b>CustomerUser</b> - the owner of this account.
	 */
	public CustomerUser getAccountOwner(){
		return this.owner;
	}
	
	public int getAccountID(){
		return accountID;
	}
	public boolean getIsActiveAccount(){
		return isActiveAccount;
	}
	
	public int getNumWithdrawals(){
		return numWithdrawals;
	}
	
	public int getNumDeposits(){
		return numDeposits;
	}
	
	public void setTellerCharge(boolean yes){
		hasTellerCharge=yes;
	}
	
//	@Override
//	public String toString(){
//		return "" + this.
//	}
}
	

