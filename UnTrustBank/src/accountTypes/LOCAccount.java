package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.CustomerUser;

public class LOCAccount extends BasicAccount implements Loanable{

	public LOCAccount(CustomerUser owner, int accountID, double maxallowedtospend) {
		super(owner, accountID);

	if(maxallowedtospend>0){
		throw new IllegalArgumentException("cap must be set as negative");
	}
	setMinimumAccountBalance(maxallowedtospend);
	setMaximumAccountBalance(0);
	
	
	
	}
// initial transaction.....?
	// Nevermind, BankGlobal stuff doesn't change how stuff should work here. - Michael
<<<<<<< HEAD
	private double locCap;
	public double getLOCCap()
	{
		return this.locCap;
	}
	public void setLOCCap(double newLOCCap)
	{
		this.locCap = newLOCCap;
	}
=======

	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getLoanCapContribution() {
		return getMinimumAccountBalance();
	}

	@Override
	public void respondToTimeChange(DateTime originalTime, DateTime newTime,
			Time timeDifference) {
		// TODO Auto-generated method stub
		
	}


	
	
>>>>>>> branch 'master' of https://github.com/TobalJackson/UnTrustBank.git
}
