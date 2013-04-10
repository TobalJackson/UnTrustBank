package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import bank.Request;
import bank.Transaction;

public class SavingsAccount extends BasicAccount implements CustomerTransferSource, WithdrawRequestable, OtherCustomerDepositable{
	public void requestWithdrawal(double amount){
		if (this.getCurrentAccountBalance() - amount > 0){
			Request r = new Request(this, amount, this.getAccountOwner(), Transaction.WITHDRAWAL);
		}
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
