package accountTypes;

import java.util.Iterator;

import bank.BankGlobal;
import bank.Transaction;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;

public class LOCAccount extends BasicAccount implements Loanable{
	private double thismonthspaid;
	//private double interestOffset;
	private double interestrate;
	
	public LOCAccount(CustomerUser owner, int accountID, double maxallowedtospend, double interestOffsetFromGlobal) throws IllegalArgumentException {
		super(owner);

	if(maxallowedtospend>0){
		throw new IllegalArgumentException("cap must be set as negative");
	}
	setMinimumAccountBalance(maxallowedtospend);
	if(interestOffsetFromGlobal+BankGlobal.getLOCinterest()< 0 || interestOffsetFromGlobal+BankGlobal.getLOCinterest()> 1  ){
		throw new IllegalArgumentException("the final interest rate must be between 0 and 1");
		
	}
	interestrate=interestOffsetFromGlobal+BankGlobal.getLOCinterest();
	
	
	setMaximumAccountBalance(0);
	thismonthspaid=0;
	
	
	

	setMaximumAccountBalance(0);
	updateCurrentAccountBalance();

	}
	@Override
public void appendTransaction(Transaction transaction, BasicUser initiator){
		
		//Withdrawals - Errors, cant go over limit, unless with penalty
		if(transaction.getAmount()<0 && (transaction.getAmount() + getCurrentAccountBalance()) > getMinimumAccountBalance() ){
			if(!(transaction.getTransactionType()==3)){
			throw new IllegalArgumentException("Hey! You can't go past your LOC Limit");
			}
			else{	
			transactionList.add(transaction);} //this must be a penatly
		}
		//Deposits - cant exceed 0
		if((transaction.getAmount()+getCurrentAccountBalance())>0){
			throw new IllegalArgumentException("This deposit would cause the account balance to go over 0.");
		}
		
		transactionList.add(transaction);  //should be legal deposits and withdrawals
		thismonthspaid+=transaction.getAmount();
		updateCurrentAccountBalance();
			
	}
	
public void setOffset(double mynewoffset)throws IllegalArgumentException{
	if(mynewoffset+BankGlobal.getLOCinterest() >1 || mynewoffset+BankGlobal.getLOCinterest() <0 ){
		throw new IllegalArgumentException("the final interest rate must be between 0 and 1");
	}
	interestrate=mynewoffset+BankGlobal.getLOCinterest();
	updateCurrentAccountBalance();
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

	public double needToPayThisMonth(){
		double owed = Math.max((interestrate*getCurrentAccountBalance()), BankGlobal.getLOCMinPayment());
		
		return owed;
	}
	
	@Override
	public void respondToTimeChange(OperationManagerUser OM) {

		if(isActiveAccount){
		double needToPagarThisMonth=needToPayThisMonth();
		if(thismonthspaid < needToPagarThisMonth){
			Transaction PenaltyTime = new Transaction(BankGlobal.getPenaltyFeeLoanLC(), owner, OM, 3);
			transactionList.add(PenaltyTime);
		}	//close if need penalty
		
		if(!(getCurrentAccountBalance()==0)){
		double todaysinterest = interestrate*getCurrentAccountBalance(); //interest not paid on penalty
		
		if(todaysinterest+getCurrentAccountBalance()<getMinimumAccountBalance()){ //if interest would push past limit
			todaysinterest=getMinimumAccountBalance()-getCurrentAccountBalance();
		}
		Transaction MoarMoneyz2Bank = new Transaction(todaysinterest, owner, OM, 2);
		transactionList.add(MoarMoneyz2Bank);
		accruedInterest+=todaysinterest;
		}//close if not zero
		} //close isActive
		thismonthspaid=0;
		updateCurrentAccountBalance();	
	}

}
