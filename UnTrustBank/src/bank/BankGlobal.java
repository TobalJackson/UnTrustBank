package bank;

import java.util.ArrayList;

import accountTypes.BasicAccount;

import userTypes.CustomerUser;

public class BankGlobal {

	ArrayList<CustomerUser> customers = new ArrayList<CustomerUser>();
	ArrayList<BasicAccount> account = new ArrayList<BasicAccount>();
	//CHECK TO MAKE SURE THESE THINGS BELOW BELONG IN THE BANKGLOBAL OBJECT
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
	private static double getInterestRateCD(int duration)
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
	void setInterestRateCD(int duration, double newRate)
	{
		switch(duration)
		{
		case 0:
			this.InterestRateCD6Month = newRate;
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
	//Checking
	double serviceChargeChecking;
	double overdraftFee;
	double serviceChargeLimitChecking;

	double getServiceChargeLimitChecking()
	{
		return this.serviceChargeChecking;
	}
	void setServiceChargeLimitChecking(double newSCLC)
	{
		this.serviceChargeChecking = newSCLC;
	}
	double getOverdraftFee()
	{
		return this.overdraftFee;
	}
	void setOverdraftFee(double newOF)
	{
		this.overdraftFee = newOF;
	}
	double getServiceChargeChecking()
	{
		return this.serviceChargeLimitChecking;
	}
	void setServiceChargeChecking(double newSCS)
	{
		this.serviceChargeLimitChecking = newSCS;
	}
	
}
