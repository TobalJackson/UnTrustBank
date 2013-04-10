package accountTypes;


import java.util.Iterator;

import userTypes.BasicUser;
import userTypes.CustomerUser;
import bank.BankGlobal;
import bank.Transaction;
import dateTime.DateTime;
import dateTime.Time;

public class CheckingAccount extends BasicAccount implements CustomerTransferSource, OtherCustomerDepositable {

	public CheckingAccount(CustomerUser owner, int accountID) {
					
		super(owner, accountID);
		updateCurrentAccountBalance();
	}
	
@Override
	public void appendTransaction(Transaction transaction, BasicUser initiator){
//transaction may be positive or negative. just need to make sure a negative transaction...
//...wouldn't put us under the limit
	if((accountBalance-transaction.getAmount())<BankGlobal.getOverdraftLimit()){
		transactionList.add(new Transaction(BankGlobal.getOverdraftFee(), owner, owner, 3));
		}
	else{
		transactionList.add(transaction);
	}
	updateCurrentAccountBalance();
}
	
	/**
	 * Method  to construct a new transaction for Transfer.
	 * @return <b>Transaction</b> - returns a Transaction representing the amount to be withdrawn for the Transfer.
	 */
	public Transaction customerTransferWithdrawal(double amount){
		if (this.getCurrentAccountBalance() - amount > 0){
			Transaction withdraw = new Transaction(amount * -1, this.getAccountOwner(), this.getAccountOwner(), Transaction.TRANSFER);
			this.appendTransaction(withdraw, this.getAccountOwner());
			return withdraw;
		}
		else throw new IllegalArgumentException("Insufficient funds");
	}
	public void applyUnderLimitServiceCharge(BasicUser initiator){
		if(BankGlobal.getunderlimitfeecheckingboolean()){
			if(this.accountBalance<BankGlobal.getServiceChargeLimitChecking()){
				Transaction ServiceCharge = new Transaction(BankGlobal.getServiceChargeChecking(), owner, initiator, 5);
				transactionList.add(ServiceCharge);
				updateCurrentAccountBalance();
			}//if this account is under limit
		} //close if boolean==true
		updateCurrentAccountBalance(); //just to be safe I think
	} //close method
	

	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	//Need to complete the following method!!!!!!!
	@Override
	public void respondToTimeChange(DateTime originalTime, DateTime newTime,
			Time timeDifference) {
		
		//So I think that if the bank is currently charging the service fee for being under a certain balance
		//, (as indicated by the underlimitfeechecking boolean, this will charge people every month for being under
		//the limit, so do we just treat it as a negative interest if the boolean is true?
		// otherwise, the applyUnderLimitServiceCharge method above should work
		// TODO Auto-generated method stub
		
	}

	public void changeBalance(double amount){
	
	}
}
