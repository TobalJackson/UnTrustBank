package userTypes;

public class AccountManagerUser {
// LOC account interest rate is an offset from a global interest rate defined by the operations manager.
// So I used interestRate as the variable name.  If you decide to use something else, then change it in the 
// getLOC method in BankGlobal.java
	private static double interestRate;
	public static double getInterestRate()
	{
		return interestRate;
	}
}
