package accountTypes;

import userTypes.CustomerUser;
import bank.Transaction;

public class CDAccount extends BasicAccount implements Withdrawable, WithdrawRequestable {
int CDDuration;


public CDAccount(CustomerUser owner, int accountID,
			Transaction mytransaction, int myCDDuration) {
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
			
		// TODO Auto-generated constructor stub
	}
}
	
//must have positive transaction in constructor
	
	
	
