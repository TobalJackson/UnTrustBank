package accountTypes;


import java.util.Iterator;

import userTypes.BasicUser;
import userTypes.CustomerUser;
import userTypes.OperationManagerUser;
import accountStatistics.*;
import bank.BankGlobal;
import bank.Transaction;
import dateTime.DateTime;
import dateTime.Time;

public class CheckingAccount extends BasicAccount implements CustomerTransferSource, OtherCustomerDepositable {

	public CheckingAccount(CustomerUser owner) {
					
		super(owner);
		this.accountType = BasicAccount.CHECKING_ACCOUNT_TYPE;
//		updateCurrentAccountBalance();
		
	}
	
@Override
	public void appendTransaction(Transaction transaction, BasicUser initiator){
//transaction may be positive or negative. just need to make sure a negative transaction...
//...wouldn't put us under the limit
		if (transaction.getTransactionType() == Transaction.WITHDRAWAL){
			if (accountBalance + transaction.getAmount() < BankGlobal.getOverdraftLimit()){
				transactionList.add(new Transaction(BankGlobal.getOverdraftFee(), owner, owner, 3));
			}
			else{
				transactionList.add(transaction);
			}
		}
		else transactionList.add(transaction);	
		updateCurrentAccountBalance();
	}
	
//	public void applyUnderLimitServiceCharge(BasicUser initiator){
//		if(BankGlobal.getunderlimitfeecheckingboolean()){
//			if(this.accountBalance<BankGlobal.getServiceChargeLimitChecking()){
//				Transaction ServiceCharge = new Transaction(BankGlobal.getServiceChargeChecking(), owner, initiator, 5);
//				transactionList.add(ServiceCharge);
//				updateCurrentAccountBalance();
//			}//if this account is under limit
//		} //close if boolean==true
//		updateCurrentAccountBalance(); //just to be safe I think
//	} //close method
	

	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	//Need to complete the following method!!!!!!!
	@Override
	public void respondToTimeChange(OperationManagerUser OM) {
		if(isActiveAccount){
		//So I think that if the bank is currently charging the service fee for being under a certain balance
		//, (as indicated by the underlimitfeechecking boolean, this will charge people every month for being under
		//the limit, so do we just treat it as a negative interest if the boolean is true?
		// otherwise, the applyUnderLimitServiceCharge method above should work
		if(BankGlobal.getunderlimitfeecheckingboolean()){
			Transaction Penatly = new Transaction(BankGlobal.getServiceChargeLimitChecking(), owner, OM, 3);
			transactionList.add(Penatly);
		}
		
		}
		
		
		updateCurrentAccountBalance();
		
	}

	public void changeBalance(double amount){
	
	}
	public AccountStats getAccountStats(){
		return new CheckingAccountStats(this);
	}
	
}
