package userTypes;
import dateTime.DateTime;
import java.util.ArrayList;
import accountTypes.BasicAccount;
import accountTypes.CustomerTransferSource;
import accountTypes.LOCAccount;
import accountTypes.LoanAccount;
import accountTypes.CDAccount;
import accountTypes.CheckingAccount;
import accountTypes.OtherCustomerDepositable;
import accountTypes.SavingsAccount;
import accountTypes.ServiceChargeable;
import accountTypes.WithdrawRequestable;
import bank.BankGlobal;
import bank.Request;
import bank.Transaction;

public class AuditorUser extends BasicUser {
//create instance of AccountStats

// display fraud accounts
	
//overturn flag
	
	public void getNewUserID(){
		this.userID = BankGlobal.getNewEmployeeID();
	}
public void overturnFlag(long TransactionID){
	Transaction hello = BankGlobal.findTransactionfromID(TransactionID);
	hello.setIsNotMarkedFraudulent();
}
	
}
