package userTypes;

import dateTime.DateTime;
import bank.BankGlobal;

public class OperationManagerUser extends BasicUser {

	private static double GlobalInterestRate;

	public OperationManagerUser(String firstName, String middleName, String lastName, boolean isMale, DateTime dob, char[] password, String username){
		super(firstName, middleName, lastName, isMale, dob, password, username);
		this.userType = BasicUser.OPERATION_MANAGER_USER_TYPE;
		BankGlobal.appendToGlobalEmployeeList(this);
	}
	public OperationManagerUser(){
		super();
		this.userType = BasicUser.OPERATION_MANAGER_USER_TYPE;
		BankGlobal.appendToGlobalEmployeeList(this);
	}
	
	
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
