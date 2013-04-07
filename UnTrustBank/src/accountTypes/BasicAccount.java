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
	
	
	public double getMinimumAccountBalance(){
		return minimumAccountBalance;
	}
	public void setMinimumAccountBalance(double min){
		minimumAccountBalance = min;
	}
	
	public double getMaximumAccountBalance(){
		return maximumAccountBalance;
	}
	public void setMaximumAccountBalance(double max){
		maximumAccountBalance = max;
	}
 
	public void appendTransaction(Transaction transaction, BasicUser initiator){
		if(isActiveAccount){
			transactionList.add(transaction);
			updateCurrentAccountBalance();
		}
		else{
			accountClosedError();
			}
	}
	
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
		t.setIsFraudulent();
	}
	public void unflagFraudulentTransaction(Transaction t){
		t.setIsNotFraudulent();
	}
	
	public ArrayList<Transaction> getFraudulentTransactions(){
		ArrayList<Transaction> fraudulentTransactions = new ArrayList<Transaction>();
		for (Transaction t : transactionList){
			if (t.getIsFraudulent()){
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
	}
	

