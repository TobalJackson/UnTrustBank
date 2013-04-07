package accountTypes;
// test comment
//will this stay?
//stay put
// <3
import javax.swing.*;

import bank.Transaction;
import java.util.ArrayList;
import userTypes.CustomerUser;
import dateTime.DateTime;
public abstract class BasicAccount implements Iterable<BasicAccount>{
	private ArrayList<Transaction> transactionList;
	//private double accountBalance;
	private boolean isActiveAccount;
	private CustomerUser owner;
	private double accountBalance; 
	private int accountID; //acountID is based on other existant accounts, therefore should be generated rather than specified
	private DateTime accountCreatedOn;
	private boolean isEmployeesAccount;
	private double accruedInterest;
	private double minimumAccountBalance;
	private double serviceFee;
	private double maximumAccountBalance;
	public BasicAccount(CustomerUser owner, int accountID){ //default constructor only accepts Customer and account ID
		this.owner = owner;
		this.accountID = accountID;
		
		this.isActiveAccount = true;
		this.accountCreatedOn = new DateTime(); //new DateTime() constructor specifies the time as now
		this.isEmployeesAccount = owner.getIsEmployee(); //accounts are not employee accounts by default
		this.accruedInterest = 0;
		//this.transactionList.add(mytransaction);
	}
	

	
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
 
	public void appendTransaction(Transaction transaction){
		if(isActiveAccount){
			transactionList.add(transaction);
		}
		else{
			accountClosedError();
			}
	}
	
	public void updateCurrentAccountBalance(){
		double currentbalance=0;
		for(Transaction t:transactionList){
		currentbalance+=t.getAmount();
		}
		accountBalance=currentbalance;
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
	public void closeAccount(){
		isActiveAccount = false;
	}
	public void accountClosedError(){
		String st="this account is closed. you're not allowed to do this :/";
		JOptionPane.showMessageDialog(null,st);	
	}
	}
	

