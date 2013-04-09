package bank;

import java.util.HashMap;

import dateTime.DateTime;
import dateTime.Time;
import accountTypes.BasicAccount;

import userTypes.AccountManagerUser;
import userTypes.BasicUser;
import userTypes.CustomerUser;

public class BankGlobal {
	
	private static HashMap<Integer, CustomerUser> customers = new HashMap<Integer, CustomerUser>();
	private static HashMap<Integer, BasicAccount> accounts = new HashMap<Integer, BasicAccount>();
	private static HashMap<Integer, BasicUser> employees = new HashMap<Integer, BasicUser>();
	private static HashMap<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
	private static HashMap<Integer, Request> requests = new HashMap<Integer, Request>();
	public static DateTime banktime = new DateTime();
	//CHECK TO MAKE SURE THESE THINGS BELOW BELONG IN THE BANKGLOBAL OBJECT
	//Yes, but we may need to break up the bank global object to be less cumbersome, divide up the
	//different control systems for bank.
	
	// Transaction
	private static int currentCustomerID;
	private static int currentTransactionID;
	private static int currentAccountID;
	private static int currentRequestID;
	
	
	/**
	 * Method used by Tellers to get all pending requests.
	 * @return <b>ArrayList<Request></b> - returns an ArrayList of current pending requests.
	 */
	public static HashMap<Integer, Request> getPendingRequests(){
		HashMap<Integer, Request> pendingRequests = new HashMap<Integer, Request>();
		for (Request r : pendingRequests.values()){
			if (!r.isRequestApproved()){
				pendingRequests.put(currentRequestID, r);
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
		accounts.put(currentAccountID, a);
	}
	
	/**
	 * Method to add a CustomerUser to the Global CustomerUser list.
	 * @param c - the CustomerUser to be added.
	 */
	public static void appendToGlobalCustomerList(CustomerUser c){
		customers.put(currentCustomerID,c);
	}
	
	/**
	 * Static method to both increment and return a new TransactionID to tag a Transaction with.  Each transaction will have a numeric transactionID field to index them by.  calculation of age of transaction can be expedited by referring to only it's numeric ID, like customerID and accountID.
	 * @return <b>Long</b> - returns long transactionID of each new transaction processed.
	 */
	public static int getNewCustomerID(){
		currentCustomerID++;
		return currentCustomerID;
		
	}
	public static long getCurrentCustomerID(){
		return currentCustomerID;
	}
	
	public static long getNewTransactionID(){
		currentTransactionID++;
		return currentTransactionID;
		
	}
	public static long getCurrentTransactionID(){
		return currentTransactionID;
	}
	
	public static long getNewAccountID(){
		currentAccountID++;
		return currentAccountID;
		
	}
	public static long getCurrentAccountID(){
		return currentAccountID;
	}

	
	// Savings
	private double serviceChargeSavings;
	private double interestRateSavings;
	public double getServiceChargeSavings()
	{
		return this.serviceChargeSavings;
	}
	public void setServiceChargeSavings(double newSCS)
	{
		this.serviceChargeSavings = newSCS;
	}
	public double getInterestRateSavings()
	{
		return this.interestRateSavings;
	}
	public void setInterestRateSavings(double newIRS)
	{
		this.interestRateSavings = newIRS;
	}
	
	//CD
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
			rate = InterestRateCD6Month;
			break;
		case 1:
			rate = InterestRateCD1Year;
			break;
		case 2:
			rate = InterestRateCD2Year;
			break;
		case 3:
			rate = InterestRateCD3Year;
			break;
		case 4:
			rate = InterestRateCD4Year;
			break;
		case 5:
			rate = InterestRateCD5Year;
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
	public static void causeTimeChange(Time mytime){
		
		DateTime newbanktime=banktime.add(mytime);
			//loop thru all accounts and call respondToTimeChange
		
		for (BasicAccount b: accounts.values())
		{
			b.respondToTimeChange(banktime, newbanktime, mytime);
		}
		banktime = newbanktime;
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
	
	// Loan
	private double maximumLoansTotal;
	private double currentLoansTotal;
	private double interestRateLoan;
	private double penaltyFeeLoan;

	double getMaximumLoansTotal()
	{
		return this.maximumLoansTotal;
	}
	void setMaximumLoansTotal(double newMax)
	{
		this.maximumLoansTotal = newMax;
	}
	double getCurrentLoansTotal()
	{
		return this.currentLoansTotal;
	}
	double getInterestRateLoan()
	{
		return this.interestRateLoan;
	}
	void setInterestRateLoan(double newRate)
	{
		this.interestRateLoan = newRate;
	}
	double getPenaltyFeeLoan()
	{
		return this.penaltyFeeLoan;
	}
	void setPenaltyFeeLoan(double newFee)
	{
		this.penaltyFeeLoan = newFee;
	}
	
	// LOC
	private static double LOCoffset;
	
	public static void setLOCoffset(double newOffset)
	{
		LOCoffset = newOffset;
	}
	public static double getLOC(double newOffset)
	{
		return AccountManagerUser.getInterestRate() + LOCoffset;
	}
	
	// Cap
	private int cap;
	private int usedCap;
	
	public void setCap(int newCap)
	{
		cap = newCap;
	}
	public int getCap()
	{
		return this.cap;
	}
	public void addToUsedCap(int used)
	{
		usedCap += used;
	}
	public int getUsedCap()
	{
		return this.usedCap;
	}
	
}
