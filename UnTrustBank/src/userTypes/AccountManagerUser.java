package userTypes;

import accountTypes.BasicAccount;
import accountTypes.LOCAccount;

public class AccountManagerUser {
// LOC account interest rate is an offset from a global interest rate defined by the operations manager.
// So I used interestRate as the variable name.  If you decide to use something else, then change it in the 
// getLOC method in BankGlobal.java
	public int getCustomerSSN(CustomerUser c)
	{
		return c.getCustomerSSN();
	}
	public void openAccount(CustomerUser c , BasicAccount a)
	{
		c.getCustomerAccountsList().add(a);
	}
	public void closeAccount(CustomerUser c , BasicAccount a)
	{
		c.getCustomerAccountsList().remove(a);
	}
	
	public void getLOCCap(LOCAccount a)
	{
		a.getLOCCap();
	}
	public void setLOCCap(LOCAccount a, double newCap)
	{
		a.setLOCCap(newCap);
	}
}
