package accountTypes;

import java.util.Iterator;

import bank.Transaction;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;

public class LOCAccount extends BasicAccount implements Loanable{
	private double thismonthspaid;

	
	public LOCAccount(CustomerUser owner, int accountID, double maxallowedtospend) {
		super(owner, accountID);

	if(maxallowedtospend>0){
		throw new IllegalArgumentException("cap must be set as negative");
	}
	setMinimumAccountBalance(maxallowedtospend);

	setMaximumAccountBalance(0);
	thismonthspaid=0;
	
	

	setMaximumAccountBalance(0);	
	}
	@Override
public void appendTransaction(Transaction transaction, BasicUser initiator){
		
		//Withdrawals - Errors, cant go over limit, unless with penalty
		if(transaction.getAmount()<0 && (transaction.getAmount() + getCurrentAccountBalance()) > getMinimumAccountBalance() ){
			if(!(transaction.getTransactionType()==5)){
			throw new IllegalArgumentException("Hey! You can't go past your LOC Limit");
			}
			else{	
			transactionList.add(transaction);} //this must be a penatly
		}
		
		
		//Deposits
		
		if((transaction.getAmount()+getCurrentAccountBalance())>0){
			throw new IllegalArgumentException("This deposit would cause the account balance to go over 0.");
		}
		
		transactionList.add(transaction);
		thismonthspaid+=transaction.getAmount();
		
		
		
		updateCurrentAccountBalance();
			
	}
	
	
	
// initial transaction.....?
	// Nevermind, BankGlobal stuff doesn't change how stuff should work here. - Michael

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
	public void respondToTimeChange(OperationManagerUser OM) {
		//calc interest
		//check for penalties
		// TODO Auto-generated method stub
		updateCurrentAccountBalance();
		
	}

}
