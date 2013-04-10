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

}
