package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;
import bank.BankGlobal;
import bank.Request;
import bank.Transaction;

public class SavingsAccount extends BasicAccount implements CustomerTransferSource, WithdrawRequestable{
	private double interestrate;
	
	public SavingsAccount(CustomerUser owner, Transaction initialDeposit) throws IllegalArgumentException {
		super(owner);
		if(initialDeposit.getAmount()<=0){
			throw new IllegalArgumentException("intial deposit must be positive");
		}
		transactionList.add(initialDeposit);
		interestrate=BankGlobal.getInterestRateSavings();
		updateCurrentAccountBalance();
		
	}

//intitial positive transaction required in constructor
@Override
	public void appendTransaction(Transaction transaction, BasicUser initiator) throws IllegalArgumentException{
		if(isActiveAccount){
		if(getCurrentAccountBalance() - transaction.getAmount() < 0 && (!(transaction.getTransactionType()==5))) {
			throw new IllegalArgumentException("Sorry, this would cause your account to go below 0");
		}
		transactionList.add(transaction);
		updateCurrentAccountBalance();
			
		}
	}
	
	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void respondToTimeChange(OperationManagerUser OM) {
		if(isActiveAccount){
		interestrate=BankGlobal.getInterestRateSavings();
		double todaysinterest = interestrate * getCurrentAccountBalance();
		accruedInterest+=todaysinterest;
		Transaction Interest = new Transaction(todaysinterest, owner, OM, 2);
		transactionList.add(Interest);
		updateCurrentAccountBalance();
		}
		if(getCurrentAccountBalance()<BankGlobal.getserviceChargeSavingsthreshold()){
			Transaction BANKisSTEALINGurMoney = new Transaction(BankGlobal.getServiceChargeSavings(), owner, OM, 3);
			transactionList.add(BANKisSTEALINGurMoney);
			updateCurrentAccountBalance();
		}
		
		updateCurrentAccountBalance();
	}
	
}
