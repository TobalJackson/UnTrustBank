package accountTypes;

import bank.Transaction;
import java.util.ArrayList;
import userTypes.CustomerUser;

public class BasicAccount {
	private ArrayList<Transaction> transactionList;
	private double accountBalance;
	private boolean isActiveAccount;
	private CustomerUser owner;
	
}
