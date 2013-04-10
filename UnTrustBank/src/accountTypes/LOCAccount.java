package accountTypes;

import java.util.Iterator;

import bank.Transaction;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.BasicUser;
import userTypes.CustomerUser;

public class LOCAccount extends BasicAccount implements Loanable{
	private double thismonthspaid;

	
	public LOCAccount(CustomerUser owner, int accountID, double maxallowedtospend) {
		super(owner, accountID);

	if(maxallowedtospend>0){
		throw new IllegalArgumentException("cap must be set as negative");
	}
	setMinimumAccountBalance(maxallowedtospend);
<<<<<<< HEAD
	setMaximumAccountBalance(0);
	thismonthspaid=0;
	
	
=======
	setMaximumAccountBalance(0);	
>>>>>>> branch 'master' of https://github.com/TobalJackson/UnTrustBank.git
	}
<<<<<<< HEAD
	
	@Override
public void appendTransaction(Transaction transaction, BasicUser initiator){
		
		//Withdrawals
		if(transaction.getAmount()<0 && (transaction.getAmount() + getCurrentAccountBalance()) > getMinimumAccountBalance() ){
			throw new IllegalArgumentException("Hey! You can't go past your LOC Limit");
			
		}
		
		
		//Deposits
		
		if((transaction.getAmount()+getCurrentAccountBalance())>0){
			throw new IllegalArgumentException("This deposit would cause the account balance to go over 0.");
		}
		transactionList.add(transaction);
		thismonthspaid+=transaction.getAmount();
		
	}
	
	
	
// initial transaction.....?
	// Nevermind, BankGlobal stuff doesn't change how stuff should work here. - Michael
=======

>>>>>>> branch 'master' of https://github.com/TobalJackson/UnTrustBank.git

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
