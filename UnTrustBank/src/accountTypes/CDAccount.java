package accountTypes;

import java.util.Iterator;

import userTypes.BasicUser;
import userTypes.CustomerUser;
import bank.BankGlobal;
import bank.Transaction;
import dateTime.DateTime;
import dateTime.Time;

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
			
			
			switch(myCDDuration)
			{
			case 0:
				Time timetomature = new Time(183,0,0,0);
				maturitydate = accountCreatedOn.add(timetomature) ;
				break;
			case 1:
				Time timetomature1 = new Time(365,0,0,0);
				maturitydate = accountCreatedOn.add(timetomature1) ;
				break;
			case 2:
				Time timetomature2 = new Time(730,0,0,0);
				maturitydate = accountCreatedOn.add(timetomature2) ;
				break;
			case 3:
				Time timetomature3 = new Time(1095,0,0,0);
				maturitydate = accountCreatedOn.add(timetomature3) ;
				break;
			case 4:
				Time timetomature4 = new Time(1461,0,0,0);
				maturitydate = accountCreatedOn.add(timetomature4) ;
				break;
			case 5:
				Time timetomature5 = new Time(1862,0,0,0);
				maturitydate = accountCreatedOn.add(timetomature5) ;
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
public void respondToTimeChange(DateTime originalTime, DateTime newTime,
		Time timeDifference) {

}



@Override
public void requestWithdrawal(double amount) {
	// TODO Auto-generated method stub
	
}



@Override
public Transaction customerTransferWithdrawal(double amount) {
	// TODO Auto-generated method stub
	return null;
}
}
	
	
	
	
