package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;
import bank.Transaction;

public class LoanAccount extends BasicAccount implements Loanable {
private double interestrate;
private double minloanmonthlyloanpayment;
private double thismonthspaid;

public LoanAccount(CustomerUser owner, int accountID,  double myinterestrate, double myminmontlyloanpayment, Transaction initialloan) throws IllegalArgumentException {
		super(owner, accountID);
		setMaximumAccountBalance(0);
		

		if(myinterestrate>1 || myinterestrate <= 0){
			throw new IllegalArgumentException("interest percentage must be between 0 and 1");
		}
		interestrate=myinterestrate;
		if(myminmontlyloanpayment<=0){
			throw new IllegalArgumentException("loan payments have to be positive unfortunately");
		}
		minloanmonthlyloanpayment=myminmontlyloanpayment;
		//minaccountbalance here needs to be the amount left on the loan for a BankGlobal
		//to work properly
		if(initialloan.getAmount()>=0){
			throw new IllegalArgumentException("this is a loan, so the intial transaction is negative");
		}
		updateCurrentAccountBalance();
		thismonthspaid=0;
		
		
		
		// TODO Auto-generated constructor stub
	}
//must have one negative transaction in constructor 

@Override
public void appendTransaction(Transaction transaction, BasicUser initiator){
	if(transaction.getAmount()<0){
		throw new IllegalArgumentException("You already got a loan! No more money for you!");
		
	}
	if((transaction.getAmount()+getCurrentAccountBalance())>0){
		throw new IllegalArgumentException("This deposit would cause the account balance to go over 0.");
	}
	transactionList.add(transaction);
	thismonthspaid+=transaction.getAmount();
	
}

	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void respondToTimeChange(OperationManagerUser OM){
		updateCurrentAccountBalance();
}

	@Override
	public double getLoanCapContribution() {
		return accountBalance;
	}
	
	
	//ok so I need help here - Dania <3
//	public void respondToTimeChange(DateTime originalTime, DateTime newTime,
//			Time timeDifference) {
//		DateTime tempDate = new DateTime(originalTime.getYear(), originalTime.getMonth(), originalTime.getDay(), originalTime.getHour(), originalTime.getMinute(), originalTime.getSecond());
//		
//		Time oneDay = new Time(1,0, 0,0);
//		int i=0; int month1=tempDate.getMonth(); int month2=tempDate.getMonth();
//		int monthcounter=0;
//		
//		for(i=0; i< timeDifference.getDays(); i++){
//			month1=tempDate.getMonth();
//			tempDate=tempDate.add(oneDay);
//			month2=tempDate.getMonth();	
//			if(!(month1==month2)){
//				monthcounter+=1;
//				// ??????
//			}
//		}
		
		//Interest accrues once per month
		// TODO Auto-generated method stub
		
	}
