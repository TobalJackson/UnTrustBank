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
private double LOCLimitsAvg;
private double LOCBalanceAvg;

private double LoanBalance;
private int LoanNumber;
private double LoanAvg;

private double CheckingBalance;
private int CheckingNumber;
private double CheckingAvg;

private double CDBalance;
private int CDNumber;
private double CDAvg;

private double SavingsBalance;
private int SavingsNumber;
private double SavingsAvg;
	
	
public GlobalStats(double myLOCLimits, double myLOCCurrentBalances, int myLOCNumber, double myLoanBalance, int myLoanNumber, double myCheckingBalance, int myCheckingNumer, double myCDBalance, int myCDNumber, double mySavingsBalance, int mySavingsNumber){
	//LOC, Loan, Checking, CDA, Savings
	LOCLimits=myLOCLimits;
	LOCCurrentBalances=myLOCCurrentBalances;
	LOCNumber=myLOCNumber;
	LoanBalance=myLoanBalance;
	LoanNumber=myLoanNumber;
	CheckingBalance = myCheckingBalance;
	CheckingNumber=myCheckingNumer;
	CDBalance=myCDBalance;
	CDNumber=myCDNumber;
	SavingsBalance=mySavingsBalance;
	SavingsNumber=mySavingsNumber;
	
	LOCLimitsAvg=LOCLimits/LOCNumber;
	LOCBalanceAvg=LOCCurrentBalances/LOCNumber;
	LoanAvg=LoanBalance/LoanNumber;
	CheckingAvg=CheckingBalance/CheckingNumber;
	CDAvg= CDBalance/CDNumber;
	
	
	
	}

public void toConsole(){
	
	String mystring="";
	String CDstring1 = "CD Accounts Total Balances: " + CDBalance;
	System.out.println(CDstring1);
	String CDstring2 = "Number of CD Accounts: " + CDNumber;
	System.out.println(CDstring2);
	//NOTE!!!! NEED TO REPORT ALL ACCOUNTS AS POSITIVE NUMBER. USE MATH.ABS ON LOANS AND LOC. WE'LL IGNORE THE CASE THAT THE CHECKING COULD BE UNDER AVERAGE,
	//UNLESS YOU WANT TO DEAL WITH THAT NOW. G2G... - DANIA
	
	// basically need to take everything above and put it into console lines. should take 5 minutes to write out

	
}
	
	
	
	
}
	

