package bank;

import java.util.ArrayList;

import accountTypes.BasicAccount;

import userTypes.CustomerUser;

public class BankGlobal {

	ArrayList<CustomerUser> customers = new ArrayList<CustomerUser>();
	ArrayList<BasicAccount> account = new ArrayList<BasicAccount>();
	//CHECK TO MAKE SURE THESE THINGS BELOW BELONG IN THE BANKGLOBAL OBJECT
	// Savings
	double serviceChargeSavings;
	double interestRateSavings;
	double getServiceChargeSavings()
	{
		return this.serviceChargeSavings;
	}
	void setServiceChargeSavings(double newSCS)
	{
		this.serviceChargeSavings = newSCS;
	}
	double getInterestRateSavings()
	{
		return this.interestRateSavings;
	}
	void setInterestRateSavings(double newIRS)
	{
		this.interestRateSavings = newIRS;
	}
	
	//CD
	double minimumBalanceCD;
	double InterestRateCD6Month;
	double InterestRateCD1Year;
	double InterestRateCD2Year;
	double InterestRateCD3Year;
	double InterestRateCD4Year;
	double InterestRateCD5Year;
	double getInterestRateCD(int duration)
	{
	double rate = 0;
		switch(duration)
		{
		case 0:
			rate = this.InterestRateCD6Month;
			break;
		case 1:
			rate = InterestRateCD1Year;
			break;
		case 2:
			rate = InterestRateCD2Year;
			break;
		case 3:
			rate =InterestRateCD3Year;
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
	
	
}
