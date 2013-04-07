package accountTypes;
// test comment
//will this stay?
//stay put
// <3
import javax.swing.*;
import dateTime.Time;
import bank.Transaction;
import java.util.ArrayList;

import userTypes.BasicUser;
import userTypes.CustomerUser;
import dateTime.DateTime;

public abstract class BasicAccount implements Iterable<BasicAccount>{
	protected ArrayList<Transaction> transactionList;
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
	public BasicAccount(CustomerUser owner, int accountID){ //default constructor only accepts Customer and account ID
		this.owner = owner;
		this.accountID = accountID;
		
		this.isActiveAccount = true;
		this.accountCreatedOn = new DateTime(); //new DateTime() constructor specifies the time as now
		this.isEmployeesAccount = owner.getIsEmployee(); //accounts are not employee accounts by default
		this.accruedInterest = 0;
		//this.transactionList.add(mytransaction);
	}
	

	public abstract void respondToTimeChange(DateTime originalTime, DateTime newTime, Time timeDifference);
	
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
			updateCurrentAccountBalance();
		}
		else{
			accountClosedError();
			}
	}
	
	/**
	 * Method which will update the accountBalance field based on all Active Transactions in the transaction list.
	 * @see {@link #appendTransaction} {@link #accountBalance}
	 */
	public void updateCurrentAccountBalance(){
		double currentbalance=0;
		if(transactionList.size()>0){
		for(Transaction t:transactionList){
		currentbalance+=t.getAmount();
		}
		}
		accountBalance=currentbalance;
	}
	
	public CustomerUser getOwner(){
		return owner;
	}
	
	public double getCurrentAccountBalance(){
		return accountBalance;
	}
	
	public ArrayList<Transaction> getFullTransactionHistory(){
		return transactionList;
	}
	public void flagFraudulentTransaction(Transaction t){
		t.setIsFlaggedFraudulent();
	}
	public void unflagFraudulentTransaction(Transaction t){
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
		Transaction closingTransaction = new Transaction((-accountBalance), owner, initiator, 4);
		transactionList.add(closingTransaction);
		isActiveAccount = false;
		updateCurrentAccountBalance();
		
	}
	
	public void accountClosedError(){
		String st="this account is closed. you're not allowed to do this :/";
		JOptionPane.showMessageDialog(null,st);	
	}
	public CustomerUser getAccountOwner(){
		return this.owner;
	}
	
	public boolean getIsActiveAccount(){
		return isActiveAccount;
	}
}
	

