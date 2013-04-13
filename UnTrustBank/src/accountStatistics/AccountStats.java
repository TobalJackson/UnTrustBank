package accountStatistics;
import accountTypes.BasicAccount;
import dateTime.DateTime;
import bank.Transaction;
import java.util.ArrayList;


public abstract class AccountStats {	
	private int totalTransactions;
	private int totalAppliedTransactions;
	private int totalDebits;
	private int totalCredits;
	private double totalDebitAmount;
	private double totalCreditAmount;
	private DateTime timeStamp1;
	private DateTime timeStamp2;
	private final int WITHDRAWAL = 0, DEPOSIT = 1, INTEREST = 2, PENALTY = 3, OTHER = 4, SERVICE_CHARGE = 5;
	private ArrayList<Transaction> allTransactions;
	private ArrayList<Transaction> appliedTransactions;
	
	
	/**
	 * constructor assigns values to all above fields
	 * it does so by iterating through the transaction histories and if it is a deposit or withdrawal
	 * then the integer number of the count of type of interaction is added by one
	 * and the double value of total monetary amount of all these interactions is added
	 * @param account
	 */
	public AccountStats(BasicAccount account){
		int numWithdraw=0;
		int numDeposit=0;
		int sumWithdraw=0;
		int sumDeposit=0;
		this.totalTransactions=account.getFullTransactionHistory().size();
		this.totalAppliedTransactions=account.getAppliedTransactionHistory().size();
		allTransactions = account.getFullTransactionHistory();
		appliedTransactions = account.getAppliedTransactionHistory();
		
		for(Transaction t: account.getAppliedTransactionHistory()){
			if(t.getTransactionType()==WITHDRAWAL){
				numWithdraw++;
			}
		}
		this.totalDebits=numWithdraw;
		
		for(Transaction t: account.getAppliedTransactionHistory()){
			if(t.getTransactionType()==DEPOSIT){
				numDeposit++;
			}
		}
		this.totalCredits=numDeposit;
		
		for(Transaction t: account.getAppliedTransactionHistory()){
			if(t.getTransactionType()==WITHDRAWAL){
				sumWithdraw+=t.getAmount();
			}
		}
		this.totalDebitAmount=sumWithdraw;
		
		for(Transaction t: account.getAppliedTransactionHistory()){
			if(t.getTransactionType()==DEPOSIT){
				sumDeposit+=t.getAmount();
			}
		}
		this.totalCreditAmount=sumDeposit;
	}
	
	/**
	 * constructor assigns values to all above fields
	 * it does so by iterating through the transaction histories and if it is a deposit or withdrawal
	 * then the integer number of the count of type of interaction is added by one
	 * and the double value of total monetary amount of all these interactions is added
	 * though by first confirming that the transaction is within the window of the time indicated by the timeStamps
	 * by comparing the timeStamps using the DateTime object
	 * @param account, timeStamp1, timeStamp2
	 */
	public AccountStats(BasicAccount account, DateTime timeStamp1, DateTime timeStamp2){
		int numWithdraw=0;
		int numDeposit=0;
		int sumWithdraw=0;
		int sumDeposit=0;
		this.totalTransactions=account.getAppliedTransactionHistory().size();
		this.totalAppliedTransactions=account.getAppliedTransactionHistory().size();
		allTransactions = account.getFullTransactionHistory();
		appliedTransactions = account.getAppliedTransactionHistory();
		
		for(Transaction t: account.getFullTransactionHistory()){
			if((t.getTransactionType()==WITHDRAWAL)&&((t.getTimeStamp().compare(timeStamp1)) > 0)&&(t.getTimeStamp().compare(timeStamp2)) < 0){
				numWithdraw++;
			}
		}
		this.totalDebits=numWithdraw;
		for(Transaction t: account.getAppliedTransactionHistory()){
			if((t.getTransactionType()==DEPOSIT)&&((t.getTimeStamp().compare(timeStamp1))>0)&&(t.getTimeStamp().compare(timeStamp2))<0){
				numDeposit++;
			}
		}
		
		this.totalCredits=numDeposit;
		for(Transaction t: account.getAppliedTransactionHistory()){
			if((t.getTransactionType()==WITHDRAWAL)&&((t.getTimeStamp().compare(timeStamp1))>0)&&(t.getTimeStamp().compare(timeStamp2))<0){
				sumWithdraw+=t.getAmount();
			}
		}
		this.totalDebitAmount=sumWithdraw;
		for(Transaction t: account.getAppliedTransactionHistory()){
			if((t.getTransactionType()==DEPOSIT)&&((t.getTimeStamp().compare(timeStamp1))>0)&&(t.getTimeStamp().compare(timeStamp2))<0){
				sumDeposit+=t.getAmount();
			}
		}
		this.totalCreditAmount=sumDeposit;
	}
	
	public int getTotalTransactions(){
		return totalTransactions;
	}
	
	public int getTotalAppliedTransactions(){
		return totalAppliedTransactions;
	}
	
	public int getTotalDebits(){
		return totalDebits;
	}
	
	public int getTotalCredits(){
		return totalCredits;
	}
	
	public double getTotalDebitAmount(){
		return totalDebitAmount;
	}
	
	public double getTotalCreditAmount(){
		return totalCreditAmount;
	}
	
//	public AccountStats getAccountStatsInDateRange(BasicAccount account, DateTime timeStamp1, DateTime timeStamp2){
//		return AccountStats(account, timeStamp1, timeStamp2);
//	}
  }
