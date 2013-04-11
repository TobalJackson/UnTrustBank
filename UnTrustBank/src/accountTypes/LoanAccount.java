package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;
import bank.BankGlobal;
import bank.Transaction;

public class LoanAccount extends BasicAccount implements Loanable {
private double interestrate;
private double minloanmonthlyloanpayment;//have fixed monthly payment required, cannot change after account creation
private double thismonthspaid;


public LoanAccount(CustomerUser owner, int accountID,  double myinterestrateoffset, double myminmontlyloanpayment, Transaction initialloan) throws IllegalArgumentException {
		super(owner, accountID);
		setMaximumAccountBalance(0);
		if(myinterestrateoffset+BankGlobal.getInterestRateLoan()>1 || myinterestrateoffset+BankGlobal.getInterestRateLoan() <= 0){
			throw new IllegalArgumentException("interest percentage must be between 0 and 1");
		}
	
		else{
		interestrate=BankGlobal.getInterestRateLoan()+myinterestrateoffset;
		}
		
		if(myminmontlyloanpayment<=0){
			throw new IllegalArgumentException("loan payments have to be positive unfortunately");
		}
		minloanmonthlyloanpayment=myminmontlyloanpayment;
		//minaccountbalance here needs to be the amount left on the loan for a BankGlobal
		//to work properly
		if(initialloan.getAmount()>=0){ //must have one negative transaction in constructor 
			throw new IllegalArgumentException("this is a loan, so the intial transaction is negative");
		}
		updateCurrentAccountBalance();
		thismonthspaid=0;
		

	}


@Override
public void appendTransaction(Transaction transaction, BasicUser initiator){
	if(transaction.getAmount()<0){
		throw new IllegalArgumentException("You already got a loan! No more money for you!");
		
	}	
	if((transaction.getAmount()+getCurrentAccountBalance())>0){
		transaction.setAmount(-getCurrentAccountBalance());
	}
	transactionList.add(transaction);
	thismonthspaid+=transaction.getAmount();
	updateCurrentAccountBalance();
	if(getCurrentAccountBalance()==0){
		closeAccount(owner);
	}
	updateCurrentAccountBalance();
	
}

	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void respondToTimeChange(OperationManagerUser OM){
		if(isActiveAccount){	
		double currentinterest=interestrate*getCurrentAccountBalance(); //will be negative cuz account balance is negative
		Transaction interest = new Transaction(currentinterest, owner, OM, 2);
		transactionList.add(interest);
		accruedInterest+=currentinterest;
		
				double tempminpayment=minloanmonthlyloanpayment;
				
				if(minloanmonthlyloanpayment > (-getCurrentAccountBalance())){
				tempminpayment=-getCurrentAccountBalance();
				}	
		
				if(thismonthspaid < tempminpayment){
				//incur penatly
				Transaction Penalty = new Transaction(BankGlobal.getPenaltyFeeLoanLC(), owner, OM, 3);
				transactionList.add(Penalty);
				}
		}
		thismonthspaid=0;
		updateCurrentAccountBalance();
}

	@Override
	public double getLoanCapContribution() {
		return accountBalance;
	}
	
	

	}
