package accountStatistics;
import accountTypes.BasicAccount;
import dateTime.DateTime;


public class AccountStats {	
	private int totalTransactions;
	private int totalAppliedTransactions;
	private int totalDebts;
	private int totalCredits;
	private double totalDebitAmount;
	private double totalCreditAmount;
	private DateTime timeStamp1;
	private DateTime timeStamp2;
	
//	public AccountStats(int totalTransactions, int totalDebts, int totalCredits, double totalDebitAmount, double totalCreditAmount){
//		this.totalTransactions=totalTransactions;
//		this.totalDebts=totalDebts;
//		this.totalCredits=totalCredits;
//		this.totalDebitAmount=totalDebitAmount;
//		this.totalCreditAmount=totalCreditAmount;
//	}
//	
//	public AccountStats(int totalTransactions, int totalDebts, int totalCredits, double totalDebitAmount, double totalCreditAmount, DateTime timeStamp1, DateTime timeStamp2){
//		this.totalTransactions=totalTransactions;
//		this.totalDebts=totalDebts;
//		this.totalCredits=totalCredits;
//		this.totalDebitAmount=totalDebitAmount;
//		this.totalCreditAmount=totalCreditAmount;
//		this.timeStamp1=timeStamp1;
//		this.timeStamp2=timeStamp2;
//	}
	
	public AccountStats(BasicAccount account){
		this.totalTransactions=account.getFullTransactionHistory().size();
		this.totalAppliedTransactions=account.getAppliedTransactionHistory().size();
		this.totalDebts=
		this.totalCredits=
		this.totalDebitAmount=
		this.totalCreditAmount=
	}
	
	public int getTotalTransactions(){
		return totalTransactions;
	}
	
	public int getTotalAppliedTransactions(){
		return totalAppliedTransactions;
	}
	
	public int getTotalDebits(){
		return totalDebts;
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
	
	public AccountStats getAccountStatsInDateRange(BasicAccount account, DateTime timeStamp1, DateTime timeStamp2){
		BasicAccount dummyAccount = account;
		
		return dummyAccount;
	}
}
