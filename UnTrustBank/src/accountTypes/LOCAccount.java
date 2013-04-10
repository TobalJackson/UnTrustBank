package accountTypes;

public class LOCAccount extends BasicAccount implements Loanable{
// initial transaction.....?
	// Nevermind, BankGlobal stuff doesn't change how stuff should work here. - Michael
	private double locCap;
	public double getLOCCap()
	{
		return this.locCap;
	}
	public void setLOCCap(double newLOCCap)
	{
		this.locCap = newLOCCap;
	}
}
