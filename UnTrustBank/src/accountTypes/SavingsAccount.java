package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.CustomerUser;
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
	public Transaction customerTransferWithdrawal(double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void respondToTimeChange(DateTime originalTime, DateTime newTime,
			Time timeDifference) {
		// TODO Auto-generated method stub
		
	}
}
