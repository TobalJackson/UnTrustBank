package userTypes;

import bank.BankGlobal;

public class OperationManagerUser extends BasicUser {

	
	public void getNewUserID(){
		this.userID = BankGlobal.getNewEmployeeID();
	}
	
	
	
	public void setLoanInterestRate(double newRate)
	{
		BankGlobal.setInterestRateLoan(newRate);
	}
	public static double getLoanInterestRate()
	{
		return BankGlobal.getInterestRateLoan();
	}
	
	public void CauseTimeChange(){
		BankGlobal.causeTimeChange(this);
	}
	
}
