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
		switch(duration)
		{
		case 0:
			return this.InterestRateCD6Month;
			break;
		case 1:
			return InterestRateCD1Year;
			break;
		case 2:
			return InterestRateCD2Year;
			break;
		case 3:
			return InterestRateCD3Year;
			break;
		case 4:
			return InterestRateCD4Year;
			break;
		case 5:
			return InterestRateCD5Year;
			break;
		}
	}
	void getInterestRateCD(int duration)
	{
		
	}

	
	
	
	
}
