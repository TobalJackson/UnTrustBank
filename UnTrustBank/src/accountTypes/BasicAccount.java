package accountTypes;

import bank.Transaction;
import java.util.ArrayList;
import userTypes.CustomerUser;
import dateTime.DateTime;
//Dania makes edits?
public class BasicAccount {
	private ArrayList<Transaction> transactionList;
	private double accountBalance;
	private boolean isActiveAccount;
	private CustomerUser owner;
	private int accountID;
	private DateTime accountCreatedOn;
	private boolean isEmployeesAccount;
	private double accruedInterest;
	private double minimumAccountBalance;
	private double serviceFee;
	private double maximumAccountBalance;
	
	public double getMinAccountBalance(){
		return minimumAccountBalance;
	}
	public void setMinAccountBalance(double min){
		minimumAccountBalance = min;
	}
	public double getMaxAccountBalance(){
		return maximumAccountBalance;
	}
	public void setMaxAccountBalance(double max){
		maximumAccountBalance = max;
	}
	public void appendTransaction(Transaction transaction){
		transactionList.add(transaction);
	}
	public double getCurrentAccountBalance(){
		double total = 0;
		for (Transaction t : transactionList){
			if(t.getIsApplied()){
				if(t.getIsCredit()){
					total += t.getAmount();
				}
				if(!t.getIsCredit()){
					total -= t.getAmount();
				}
			}
		}
		return total;
	}
	public ArrayList<Transaction> getFullTransactionHistory(){
		return transactionList;
	}
	public void flagFraudulentTransaction(Transaction t){
		t.setIsFraudulent();
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
}
