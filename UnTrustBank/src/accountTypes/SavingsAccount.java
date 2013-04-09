package accountTypes;

import bank.Request;
import bank.Transaction;

public class SavingsAccount extends BasicAccount implements CustomerTransferSource, WithdrawRequestable{
	public void requestWithdrawal(double amount){
		if (this.getCurrentAccountBalance() - amount > 0){
			Request r = new Request(this, amount, this.getAccountOwner(), Transaction.WITHDRAWAL);
		}
	}
//intitial positive transaction required in constructor
}
