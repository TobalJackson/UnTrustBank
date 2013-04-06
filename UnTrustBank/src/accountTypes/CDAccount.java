package accountTypes;

import userTypes.CustomerUser;
import bank.BankGlobal;
import bank.Transaction;

public class CDAccount extends BasicAccount implements Withdrawable, WithdrawRequestable {
private int CDDuration;
private double interestrate;


public CDAccount(CustomerUser owner, int accountID,
			Transaction mytransaction, int myCDDuration, double minaccountbalance) {
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
				
			interestrate=BankGlobal.getInterestRateCD(myCDDuration);
			
		// TODO Auto-generated constructor stub
	}
}
	
//must have positive transaction in constructor
	
	
	
