package userTypes;

import bank.BankGlobal;

public class OperationManagerUser {

	private static double GlobalInterestRate;
	
	public void setGlobalInterestRate(double newRate)
	{
		this.GlobalInterestRate = newRate;
	}
	public static double getGlobalInterestRate()
	{
		return GlobalInterestRate;
	}
	
	public void CauseTimeChange(){
		BankGlobal.causeTimeChange(this);
	}
	
}
