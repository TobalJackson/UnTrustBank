package userTypes;

import bank.BankGlobal;

public class OperationManagerUser extends BasicUser {

	private static double GlobalInterestRate;
	
	public void getNewUserID(){
		this.userID = BankGlobal.getNewEmployeeID();
	}
	
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
