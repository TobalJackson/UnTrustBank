package bank;

import java.util.ArrayList;
import dateTime.DateTime;
import dateTime.Time;
import accountTypes.BasicAccount;

import userTypes.CustomerUser;

public class BankGlobal {
	
	ArrayList<CustomerUser> customers = new ArrayList<CustomerUser>();
	ArrayList<BasicAccount> account = new ArrayList<BasicAccount>();
	public static DateTime banktime = new DateTime();
	//CHECK TO MAKE SURE THESE THINGS BELOW BELONG IN THE BANKGLOBAL OBJECT
	//Yes, but we may need to break up the bank global object to be less cumbersome, divide up the
	//different control systems for bank.
	
	// Transaction
	private static long currentTransactionID;
	
	/**
	 * Static method to both increment and return a new TransactionID to tag a Transaction with.  Each transaction will have
	 * a numeric transactionID field to index them by.  calculation of age of transaction can be expedited by referring
	 * to only it's numeric ID, like customerID and accountID.
	 * @return <b>Long</b> - returns long transactionID of each new transaction processed.
	 */
	public static long getNewTransactionID(){
		currentTransactionID++;
		return currentTransactionID;
		
	}
	public static long getCurrentTransactionID(){
		return currentTransactionID;
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
	public static void causeTimechange(Time mytime){
		
		DateTime newbanktime=banktime.add(mytime);
			//loop thru all acounts and call respondToTimeChange
		banktime=newbanktime;
		
	}
	
	public static DateTime getBankTime(){
		return banktime;
	}
	
	//Checking
	private static double serviceChargeChecking;
	private static double overdraftFee;
	private static double serviceChargeLimitChecking;
	private static boolean underlimitfee;
	

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
	double getServiceChargeChecking()
	{
		return serviceChargeLimitChecking;
	}
	void setServiceChargeChecking(double newSCS)
	{
		serviceChargeLimitChecking = newSCS;
	}
	
}
