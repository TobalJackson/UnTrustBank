package userTypes;

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
	
}
