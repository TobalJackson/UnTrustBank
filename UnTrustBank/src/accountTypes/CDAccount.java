package accountTypes;

import java.util.Iterator;

import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;
import bank.BankGlobal;
import bank.Transaction;
import dateTime.DateTime;
import dateTime.Time;
import bank.Transaction;

public class CDAccount extends BasicAccount implements CustomerTransferSource, WithdrawRequestable {
private int CDDuration;
private double interestrate;
private DateTime maturitydate;


public CDAccount(CustomerUser owner, int accountID,
			Transaction mytransaction, int myCDDuration, double minaccountbalance) throws IllegalArgumentException {
			super(owner, accountID);
			if(myCDDuration>5 || myCDDuration <0){
				throw new IllegalArgumentException();
			}
			else{
			CDDuration=myCDDuration;
			}
			
			if(mytransaction.getAmount()<0){
				throw new IllegalArgumentException();
				}
			if(minaccountbalance<0){
				throw new IllegalArgumentException();
			}
			else{
				super.setMinimumAccountBalance(minaccountbalance);
			}
				

			//must have positive transaction in constructor
			if(mytransaction.getAmount() <0){
				throw new IllegalArgumentException();
			}
			transactionList.add(mytransaction);
			
				
			
			
			interestrate=BankGlobal.getInterestRateCD(myCDDuration);
			maturitydate= new DateTime();
			
			
			switch(myCDDuration)
			{
			case 0:
				maturitydate.addSixMonths();
				break;
			case 1:
				maturitydate.addYears(1);
				break;
			case 2:
				maturitydate.addYears(2);
				break;
			case 3:
				maturitydate.addYears(3);
				break;
			case 4:
				maturitydate.addYears(4);
				break;
			case 5:
				maturitydate.addYears(5);
			}
			
			setMinimumAccountBalance(BankGlobal.getminiumumBalanceCD());
			updateCurrentAccountBalance();
		
}
		// TODO Auto-generated constructor stub



@Override
public void appendTransaction(Transaction transaction, BasicUser initiator){
	DateTime currentbanktime = BankGlobal.getBankTime();
	if(!isActiveAccount){
		throw new IllegalStateException(); //account closed exception?
	}
	
	if(transaction.getAmount()<0){
	if(currentbanktime.compare(maturitydate)>0){
		double penaltyamount= 6*monthsinterest();
		transactionList.add(transaction);
		
				
		Transaction penalty = new Transaction(penaltyamount, owner, initiator, 3);
		transactionList.add(transaction);
		transactionList.add(penalty);
		updateCurrentAccountBalance();
			if(accountBalance< minimumAccountBalance){
				closeAccount(initiator);
				} //close dipping below account balance if statement
	} //close before maturity date if statement
	else{
		transactionList.add(transaction);
		updateCurrentAccountBalance();
	}//close checking maturity date
	
	
	
	}//close amount is negative
}//close method


@Override
public Iterator<BasicAccount> iterator() {
	// TODO Auto-generated method stub
	return null;
}

public double monthsinterest(){
	return (interestrate * accountBalance);
}

public int getCDDuration(){
	return CDDuration;
	}


@Override
public void respondToTimeChange(OperationManagerUser OM) throws IllegalStateException{
	DateTime currentbanktime = BankGlobal.getBankTime();
	if(!isActiveAccount){
		throw new IllegalStateException("The Account is closed"); //account closed exception?
	}
	
	if(currentbanktime.compare(maturitydate)>0){
		double addthisinterest = getCurrentAccountBalance()*interestrate;		
		Transaction ThisInterest= new Transaction(addthisinterest, owner, OM, 2);
			transactionList.add(ThisInterest);
			accruedInterest+=addthisinterest;
	}
	//collects interest

	updateCurrentAccountBalance();
	
}








}
	
	
	
	
