package userTypes;

import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.BasicAccount;
import accountTypes.CustomerTransferSource;
import accountTypes.LOCAccount;
import accountTypes.LoanAccount;
import accountTypes.CDAccount;
import accountTypes.CheckingAccount;
import accountTypes.OtherCustomerDepositable;
import accountTypes.SavingsAccount;
import accountTypes.ServiceChargeable;
import accountTypes.WithdrawRequestable;
import bank.BankGlobal;
import bank.Request;
import userTypes.AccountManagerUser;
import bank.Transaction;
import userTypes.CustomerUser;
import accountStatistics.*;
public class AccountantUser extends BasicUser {
private GlobalStats	myGlocalStats;

public AccountantUser(String Firstname, String MiddleName, String LastName, boolean IsMale, DateTime DOB, char[] PassWord, String UserName ){
	//String firstName, String middleName, String lastName, boolean isMale,
	//DateTime dob, char[] password, String username, int userID
	super(Firstname, MiddleName, LastName, IsMale, DOB, PassWord, UserName);
	this.userType = BasicUser.ACCOUNTANT_USER_TYPE;
	BankGlobal.appendToGlobalEmployeeList(this);
}
public AccountantUser(){
	super("Carl the Accountant");
	this.userType = BasicUser.ACCOUNTANT_USER_TYPE;
	BankGlobal.appendToGlobalEmployeeList(this);
}

public void getNewUserID(){
	this.userID = BankGlobal.getNewEmployeeID();
}
public void DisplayGlobalStats(){
	myGlocalStats = BankGlobal.getGlobalStats();
	myGlocalStats.toConsole();
}

public void setGlobalLoanCap(){
	//need new stream reader
	//BankGlobal.setLoanCap(Cap);
	
}

}

















