package bank;

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
import bank.Transaction;
import accountStatistics.GlobalStats;
import accountTypes.*;


public class BankGlobal {
	
	private static HashMap<Integer, CustomerUser> customers = new HashMap<Integer, CustomerUser>();
	private static HashMap<Integer, BasicAccount> accounts = new HashMap<Integer, BasicAccount>();
	private static HashMap<Integer, BasicUser> employees = new HashMap<Integer, BasicUser>();
	private static HashMap<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
	private static HashMap<Integer, Request> requests = new HashMap<Integer, Request>();
	private static HashMap<String, BasicUser> credentials = new HashMap<String, BasicUser>();
	public static DateTime banktime = new DateTime(1);
	//CHECK TO MAKE SURE THESE THINGS BELOW BELONG IN THE BANKGLOBAL OBJECT
	//Yes, but we may need to break up the bank global object to be less cumbersome, divide up the
	//different control systems for bank.
	
	// Transaction
	private static int currentCustomerID;
	private static int currentTransactionID;
	private static int currentAccountID;
	private static int currentRequestID;
	private static int currentEmployeeID;
	
	

	
	/**
	 * Method used by Tellers to get all pending requests.
	 * @return <b>ArrayList<Request></b> - returns an ArrayList of current pending requests.
	 */
	public static ArrayList<Request> getPendingRequests(){
		ArrayList<Request> pendingRequests = new ArrayList<Request>();
		for (Request r : requests.values()){
			if (!r.isRequestApproved()){
				pendingRequests.add(r);
			}
		}
		return pendingRequests;
	}
	
	/**
	 * Method used by Tellers to mark a pending request as processed.
	 * @param request - the Request to mark as processed.
	 */
	public static void markRequestProcessed(Request request){
		request.setRequestApproved();
	}
	
	/**
	 * Method called by Request instantiation to be appended to global list of requests.
	 * @param r - the request that is added to the global list of requests.
	 */
	public static void appendToGlobalRequestList(Request r){
		requests.put(currentRequestID, r);
	}
	
	/**
	 * Method to add a Transaction to the Global Transaction list.
	 * @param t - the Transaction to be added.
	 */
	public static void appendToGlobalTransactionList(Transaction t){
		transactions.put(currentTransactionID, t);
	}
	
	/**
	 * Method to add a BasicAccount to the Global BasicAccount list.
	 * @param a - the BasicAccount to be added.
	 */
	public static void appendToGlobalAccountList(BasicAccount a){
		accounts.put(a.getAccountID(), a);
	}
	
	/**
	 * Method to add a CustomerUser to the Global CustomerUser list.
	 * @param c - the CustomerUser to be added.
	 */
	public static void appendToGlobalCustomerList(CustomerUser c){
		customers.put(c.getUserID(),c);
	}
	
	public static void appendToGlobalEmployeeList(BasicUser c){
		employees.put(c.getUserID(), c);
	}
	
	/**
	 * Static method to both increment and return a new TransactionID to tag a Transaction with.  Each transaction will have a numeric transactionID field to index them by.  calculation of age of transaction can be expedited by referring to only it's numeric ID, like customerID and accountID.
	 * @return <b>Long</b> - returns long transactionID of each new transaction processed.
	 */
	public static int getNewCustomerID(){
		currentCustomerID++;
		return currentCustomerID;
	}
	public static int getCurrentCustomerID(){
		return currentCustomerID;
	}
	public static CustomerUser getCustomerByID(int id){
		return customers.get(id);
	}
	
	public static int getNewEmployeeID(){
		currentEmployeeID++;
		return currentEmployeeID;
	}
	public static int getCurrentEmployeeID(){
		return currentEmployeeID;
	}
	public static BasicUser getEmployeeByID(int id){
		return employees.get(id);
	}
	
	public static int getNewTransactionID(){
		currentTransactionID++;
		return currentTransactionID;
		
	}
	public static int getCurrentTransactionID(){
		return currentTransactionID;
	}
	public static Transaction getTransactionByID(int id){
		return transactions.get(id);
	}
	
	public static int getNewAccountID(){
		currentAccountID++;
		return currentAccountID;
		
	}
	public static int getCurrentAccountID(){
		return currentAccountID;
	}
	public static BasicAccount getAccountByID(int id){
		return accounts.get(id);
	}
	
	
	/**
	 * Method to check if a user name exists in the system.  Returns true if the user name is associated with a customerUser or employee.
	 * @param userName - the user name to check for (case sensitive).
	 * @return <b>boolean</b> - returns true if the User name is associated with a user.
	 */
	public static boolean userNameExists(String userName){
		boolean result = false;
		if(credentials.containsKey(userName)){
			result = true;
		}
		return result;
	}
	
	public static void addLoginInfo(BasicUser user){
		credentials.put(user.getUsername(), user);
	}
	
	public static BasicUser getUserByUserName(String userName){
		return credentials.get(userName);
	}
	
	private static double CDSavingsOffset;
	
	public double getCDSavingsOffset(){
		
		return CDSavingsOffset;
	}

	public void setCDSavingsOffset(double newoffset){
		if(newoffset > 0 || newoffset <1){
			CDSavingsOffset=newoffset;
		}
		else{
			throw new IllegalArgumentException("the percent of interest must be between 0 and 1, unless you really want to be an asshole");
		}
	}
	
	
	// Savings
	private static double serviceChargeSavings;
	private static double interestRateSavings;
	private static double serviceChargeSavingsthreshold;
	
	public static double getServiceChargeSavings()
	{
		return serviceChargeSavings;
	}
	public static void setServiceChargeSavings(double newSCS)
	{
		serviceChargeSavings = newSCS;
	}
	//this is good- Dania
	public static double getInterestRateSavings()
	{
		return interestRateSavings+CDSavingsOffset;
	}
	public static void setInterestRateSavingsoffset(double newIRS)
	{
		interestRateSavings = newIRS;
	}
	public static double getserviceChargeSavingsthreshold(){
		return serviceChargeSavingsthreshold;
	}
	
	public static void setserviceChargeSavingsthreshold(double newthreshold){
		serviceChargeSavingsthreshold=newthreshold;
		
	}
	
	
	public static Transaction findTransactionfromID(long ID){
				for (Transaction t : transactions.values()){
					if (t.getTransactionID()==ID){
						return t;
					}
				}
					return null;
			
	}
	
	//CD -this is good
	private static double minimumBalanceCD;
	private static double InterestRateCD6Month;
	private static double InterestRateCD1Year;
	private static double InterestRateCD2Year;
	private static double InterestRateCD3Year;
	private static double InterestRateCD4Year;
	private static double InterestRateCD5Year;
	
	public static double getInterestRateCD(int duration)
	{
	double rate = 0;
		switch(duration)
		{
		case 0:
			rate = InterestRateCD6Month + CDSavingsOffset;
			break;
		case 1:
			rate = InterestRateCD1Year + CDSavingsOffset;
			break;
		case 2:
			rate = InterestRateCD2Year + CDSavingsOffset;
			break;
		case 3:
			rate = InterestRateCD3Year + CDSavingsOffset;
			break;
		case 4:
			rate = InterestRateCD4Year + CDSavingsOffset;
			break;
		case 5:
			rate = InterestRateCD5Year + CDSavingsOffset;
			break;	
		}
	return rate;
	}
	
	public static double getminiumumBalanceCD(){
		return minimumBalanceCD;
	}
	
	void setInterestRateCD(int duration, double newRate)
	{
		switch(duration)
		{
		case 0:
			BankGlobal.InterestRateCD6Month = newRate;
			break;
		case 1:
			InterestRateCD1Year = newRate;
			break;
		case 2:
			InterestRateCD2Year = newRate;
			break;
		case 3:
			InterestRateCD3Year = newRate;
			break;
		case 4:
			InterestRateCD4Year = newRate;
			break;
		case 5:
			InterestRateCD5Year = newRate;
			break;	
		}
	}
	
	//look this over, people
	public static void causeTimeChange(OperationManagerUser OperationManagerCausingTimeChange){
		
		banktime.JumpAMonth();
			//loop thru all accounts and call respondToTimeChange
		
		for (BasicAccount b: accounts.values())
		{
			b.respondToTimeChange(OperationManagerCausingTimeChange);
		}
	
	}
	
	public static DateTime getBankTime(){
		return banktime;
	}
	
	//Checking
	
	private static double overdraftFee;
	private static double overdraftlimit;
	
	private static double serviceChargeLimitChecking;
	private static boolean underlimitfeechecking;
	private static double serviceChargeChecking;

	public static double getServiceChargeLimitChecking()
	{
		return serviceChargeChecking;
	}
	public static void setServiceChargeLimitChecking(double newSCLC)
	{
		serviceChargeChecking = newSCLC;
	}
	
	public static double getOverdraftFee()
	{
		return overdraftFee;
	}
	public static void setOverdraftFee(double newOF)
	{
		overdraftFee = newOF;
	}
	public static double getServiceChargeChecking()
	{
		return serviceChargeLimitChecking;
	}
	public static void setServiceChargeChecking(double newSCS)
	{
		serviceChargeLimitChecking = newSCS;
	}
	public static boolean getunderlimitfeecheckingboolean()
	{
		
		return underlimitfeechecking; 
	}
	public static void setunderlimitfeecheckingboolean(boolean tf){
		underlimitfeechecking=tf;
			}
	
	
	public static double getOverdraftLimit()
	{
		return overdraftlimit;
	}
	public static void setOverdraftLimit(double newodl)
	{
		overdraftlimit = newodl;
	}
	
	
	
	
	//LOC and Loan
	private static double LOCinterestOffset;
	public void setLOCinterestOffset(double newoffset){
		if(newoffset > 0 || newoffset <1){
			LOCinterestOffset=newoffset;
		}
		else{
			throw new IllegalArgumentException("the percent of interest must be between 0 and 1, unless you really want to be a cocky asshole");
		}	
	}
	

	
private static double Loancap;
//private double usedLoanCap;
	
public static void setLoanCap(double newCap)
{
		Loancap = newCap;
}

public static double getLoanCap(){
		return Loancap;
}

	
public static double getUsedLoanCap()
{
double used=0;
	for (BasicAccount account : accounts.values()){
			if (account instanceof Loanable){
		used+=((Loanable) account).getLoanCapContribution();
		}
	}
return used;

}
private static double interestRateLoan;
private static double penaltyFeeLoanLC;

public static double getInterestRateLoan()
{
	return interestRateLoan;
}
public static void setInterestRateLoan(double newRate)
{
	interestRateLoan = newRate;
}
public static double getPenaltyFeeLoanLC()
{
	return penaltyFeeLoanLC;
}
public static void setPenaltyFeeLoan(double newFee)
{
	penaltyFeeLoanLC = newFee;
}


public static double getLOCinterest(){
	return getInterestRateLoan()+LOCinterestOffset;
}

public static double LOCMinPayment;
public static void setLOCMinPayment(double mymin){
	if(mymin<0){
		throw new IllegalArgumentException("payments must be positive");
	}
	LOCMinPayment=mymin;
}
public static double getLOCMinPayment(){
	return LOCMinPayment;
}

public static HashMap getAccounts(GlobalStats GS){
	return accounts;
}

public static GlobalStats getGlobalStats(){
	double LOCLimits=0;
	double LOCBalance=0;
int LOCNumber=0;
double LoanBalance=0;
int LoanNumber=0;
 double CheckingBalance=0;
int CheckingNumber=0;
double CDBalance = 0;
 int CDNumber=0;
double SavingsBalance=0;
 int SavingsNumber=0;
		
	for (BasicAccount a : accounts.values()){
		if (a instanceof CDAccount){
			CDBalance+=a.getCurrentAccountBalance();
			CDNumber+=1;
		}
		else if(a instanceof CheckingAccount){
			CheckingBalance+=a.getCurrentAccountBalance();
			CheckingNumber+=1;	
		}
		else if(a instanceof LoanAccount){
			LoanBalance=a.getCurrentAccountBalance();
			LoanNumber+=1;
		}
		else if(a instanceof LOCAccount){
			LOCBalance=a.getCurrentAccountBalance();
			LOCLimits+=a.getMinimumAccountBalance();
			LOCNumber+=1;
			
		}
		else if(a instanceof SavingsAccount){
			SavingsBalance+=a.getCurrentAccountBalance();
			SavingsNumber+=1;
		}
	}	
	
		GlobalStats MYGlobalStats = new GlobalStats(LOCLimits, LOCBalance, LOCNumber, LoanBalance, LoanNumber, CheckingBalance, CheckingNumber, CDBalance, CDNumber, SavingsBalance, SavingsNumber);	

	return MYGlobalStats;
	
}

///////////////// INITIALIZE BANK! GOOD LUCK!
public static void initializeBank(){
currentCustomerID=1;
currentTransactionID=1;
currentAccountID=1;
currentRequestID=1;
currentEmployeeID=500;
CDSavingsOffset=.05;
serviceChargeSavings=10;
interestRateSavings=.02;
serviceChargeSavingsthreshold=50;
minimumBalanceCD=100;
InterestRateCD6Month=.1;
InterestRateCD1Year=.11;
InterestRateCD2Year=.12;
InterestRateCD3Year=.13;
InterestRateCD4Year=.14;
InterestRateCD5Year=.15;
overdraftFee=5;
overdraftlimit=0;
serviceChargeLimitChecking=50;
underlimitfeechecking=true;
serviceChargeChecking=5;
LOCinterestOffset=.05;
Loancap=-2000;
interestRateLoan=.07;
penaltyFeeLoanLC=75;
LOCMinPayment=40;



}




}


