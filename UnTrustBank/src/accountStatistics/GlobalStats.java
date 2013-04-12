package accountStatistics;
import java.util.ArrayList;
import java.util.HashMap;

import dateTime.DateTime;
import dateTime.Time;
import accountTypes.BasicAccount;
import accountTypes.Loanable;
import accountTypes.CDAccount;

import sun.rmi.runtime.NewThreadAction;
import userTypes.AccountManagerUser;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import accountTypes.Loanable;
import userTypes.OperationManagerUser;
import bank.BankGlobal;
import bank.Request;
import bank.Transaction;
import accountStatistics.GlobalStats;
public class GlobalStats {
//REQUIREMENTS:
//	Number of accounts, sum of the balances of each type of account (as well as sum of credit limits for LOCs).  
	//If the sum is tracked, you should also be able to report the average (for obvious reasons), and it'd be nice to have standard deviation data, though I guess it's not strictly necessary here.
	//One last piece that'd be nice, but not required - the average utilization of LOC accounts - the % of the total credit limit currently used.
//
//	Also, obviously, the current "loan cap."
	
private	double LOCLimits;
private	double LOCCurrentBalances;
private int LOCNumber;
private double LoanBalance;
private int LoanNumber;
private double CheckingBalance;
private int CheckingNumber;
private double CDBalance;
private int CDNumber;
private double SavingsBalance;
private int SavingsNumber;
	
	
public GlobalStats(double myLOCLimits, double myLOCCurrentBalances, int myLOCNumber, double myLoanBalance, int myLoanNumber, double myCheckingBalance, int myCheckingNumer, double myCDBalance, int myCDNumber, double mySavingsBalance, int mySavingsNumber){
	//LOC, Loan, Checking, CDA, Savings
	LOCLimits=myLOCLimits;
	LOCCurrentBalances=myLOCCurrentBalances;
	LOCNumber=myLOCNumber;
	LoanBalance=myLoanBalance;
	LoanNumber=myLoanNumber;

	
	}


	
	
	
	
}
	

