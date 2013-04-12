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

private double ALLtheaccounts;
	
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
	
	LOCLimitsAvg=Math.round((LOCLimits/LOCNumber)*100)/100;
	LOCBalanceAvg=Math.round((LOCCurrentBalances/LOCNumber)*100)/100;
	LoanAvg=Math.round((LoanBalance/LoanNumber)*100)/100;
	CheckingAvg=Math.round((CheckingBalance/CheckingNumber)*100)/100;
	CDAvg= Math.round((CDBalance/CDNumber)*100)/100;
	
	ALLtheaccounts= CDBalance + CheckingBalance  + LoanBalance + SavingsBalance + LOCLimits;
	
	}

public void toConsole(){
	
	String mystring="Account Stats";
	System.out.println(mystring);
	System.out.println("UnTrust's Current Balance of all accounts is " + ALLtheaccounts);
	System.out.println("Individual Account Types:");
	
	
	String CDstring1 = "CD Accounts Total Balances: $" + CDBalance;
	System.out.println(CDstring1);
	String CDstring2 = "Number of CD Accounts: " + CDNumber;
	System.out.println(CDstring2);
	String CDstring3 = "Average of CD Account Balances: $" + CDAvg;
	System.out.println(CDstring3);
	
	String CheckingString1 = "Checking Accounts Total Balances: $" + CheckingBalance;
	System.out.println(CheckingString1);
	String CheckingString2 = "Number of Checking Accounts: " + CheckingNumber;
	System.out.println(CheckingString2);
	String CheckingString3 = "Average of Checking Accounts Balances: $" + CheckingAvg;
	System.out.println(CheckingString3);
	
	String SavingsString1 = "Savings Accounts Total Balances: $" + SavingsBalance;
	System.out.println(SavingsString1);
	String SavingsString2 = "Number of Savings Accounts: $" + SavingsNumber;
	System.out.println(SavingsString2);
	String SavingsString3 = "Average of Savings Accounts $" + SavingsAvg;
	System.out.println(SavingsString3);
	
	String LoanString1 = "Loan Accounts Total Balances Owed: $" + Math.abs(LoanBalance);
	System.out.println(LoanString1);
	String LoanString2 = "Number of Loan Accounts: " + LoanNumber;
	System.out.println(LoanString2);
	String LoanString3 = "Average of Loan Accounts: $" + Math.abs(LoanAvg);
	System.out.println(LoanString3);
	
	String LOCString1 = "Line-of-Credit Accounts Total Limits: $" + Math.abs(LOCLimits);
	System.out.println(LOCString1);
	String LOCString3="LOC Accounts Total Current Balances owed: $" + Math.abs(LOCCurrentBalances);
	System.out.println(LOCString3);
	String LOCString2 = "Number of LOC Accounts: " + LOCNumber;
	System.out.println(LOCString2);
	String LOCString4 = "LOC Limits Average: $" + Math.abs(LOCLimitsAvg);
	System.out.println(LOCString4);
	String LOCString5 = "LOC Current Balances Average: $" + Math.abs(LOCBalanceAvg);
	
	
	
	
	
	
	
	
	//NOTE!!!! NEED TO REPORT ALL ACCOUNTS AS POSITIVE NUMBER. USE MATH.ABS ON LOANS AND LOC. WE'LL IGNORE THE CASE THAT THE CHECKING COULD BE UNDER AVERAGE,
	//UNLESS YOU WANT TO DEAL WITH THAT NOW. G2G... - DANIA
	
	// basically need to take everything above and put it into console lines. should take 5 minutes to write out

	
}
	
	
	
	
}
	

