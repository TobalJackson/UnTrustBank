package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;
import bank.Request;
import bank.Transaction;

public class SavingsAccount extends BasicAccount implements CustomerTransferSource, WithdrawRequestable{
	public SavingsAccount(CustomerUser owner, int accountID) {
		super(owner, accountID);
		// TODO Auto-generated constructor stub
	}

//intitial positive transaction required in constructor

	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void respondToTimeChange(OperationManagerUser OM) {
		// TODO Auto-generated method stub
		updateCurrentAccountBalance();
	}
	
}
