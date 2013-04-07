package bank;
import java.util.ArrayList;
import dateTime.DateTime;
import userTypes.CustomerUser;
import userTypes.BasicUser;
import accountTypes.BasicAccount;
public class Transaction {
	//who can create a Transaction? Tellers, Customers?  
	private DateTime timeStamp;
	private double amount;
	private boolean isFlaggedFraudulent;
	private CustomerUser customer; //CustomerUser who owns the transaction.
	private BasicUser initiator; //BasicUser processing the transaction. Can be tellerUser or CustomerUser?
	private BasicAccount account; //BasicAccount the transaction is in.
	private boolean isCredit;
	private boolean isApplied;
	private long transactionID;
	
	public Transaction(double amount, CustomerUser customer, BasicUser initiator, boolean isCredit){
		this.timeStamp = new DateTime(); //Transactions are timestamped with when they are instantiated.
		this.amount = amount;
		isFlaggedFraudulent = false; //by default, transactions are not fraudulent
		this.customer = customer;
		this.initiator = initiator;
		this.isCredit = isCredit;
		isApplied = true; //by default, transactions are applied to the account
		getNewTransactionID();
	}
	/**
	 * method called in the constructor to get and stamp the Transaction with the current TransactionID.
	 * @return <b>long</b> - returns long transactionID that the Transaction is then tagged with.
	 */
	public void getNewTransactionID(){
		this.transactionID = BankGlobal.getNewTransactionID();
	}
	/**
	 * This method is called on a transaction individually.
	 * @return <b>double</b> - The amount of money the transaction represents.
	 */
	public double getAmount(){ 
		return amount;
	}
	/**
	 * This method is called on a transaction to determine whether it was a credit or debit to an account.
	 * @return <b>boolean</b> - True if the transaction is a Credit, false otherwise.
	 */
	public boolean getIsCredit(){
		return isCredit;
	}
	
	/**
	 * Returns true if the Transaction should be applied to the Account when calculating accountBalance.
	 * @return <b>boolean</b> - true if the Transaction should count toward the account's balance.
	 */
	public boolean getIsApplied(){
		return isApplied;
	}
	
	/**
	 * Fetches the CustomerUser to which the Transaction belongs.
	 * @return <b>CustomerUser</b> - the CustomerUser to which the Transaction Belongs.
	 */
	public CustomerUser getCustomer(){
		return customer;
	}
	
	/**
	 * Fetches the Employee processing (creating) the Transaction.
	 * @return <b>BasicUser</b> - returns the employee who created the Transaction.
	 */
	public BasicUser getInitiator(){
		return initiator;
	}
	
	/**
	 * Method to fetch the BasicAccount that this Transaction belongs to.
	 * @return <b>BasicAccount</b> - returns the account to which this Transaction belongs.
	 */
	public BasicAccount getAccount(){
		return account;
	}
	/**
	 * Returns true if the transaction has been flagged Fraudulent by the CustomerUser.
	 * @return <b>boolean</b> - returns the FraudulentFlag.
	 */
	public boolean getIsFraudulent(){
		return isFlaggedFraudulent;
	}
	
	/**
	 * Method to set a Transaction's fraudulentFlag for use by CustomerUser.
	 */
	public void setIsFraudulent(){
		isFlaggedFraudulent = true;
	}
	
	/**
	 * Method to un-set a Transaction's fraudulentFlag for use by CustomerUser.
	 */
	public void setIsNotFraudulent(){
		isFlaggedFraudulent = false; 
	}
	
	/**
	 * Getter for the DateTime that this Transaction was created.
	 * @return <b>DateTime</b> - object representing the Transaction moment of instantiation.
	 */
	public DateTime getTimeStamp(){
		return this.timeStamp;
	}
	
	/**
	 * Compares the timeStamp of <b>this</b> Transaction to another argument Transaction's timeStamp.
	 * 
	 * @param transaction - The transaction to compare <b>this</b> against.
	 * @return <b>int</b> - returns -1, 0 , or 1 if <b>this</b> is less than, equal to, or greater than the argument Transaction.
	 */
	public int compareTime(Transaction transaction){ //allows ordering of transactions based on time
		return this.getTimeStamp().compare(transaction.getTimeStamp());
	}
	/**
	 * Compares the amount of <b>this</b> Transaction to another argument Transaction's amount.
	 * 
	 * @param transaction - The transaction to compare <b>this</b> against.
	 * @return <b>int</b> - returns -1, 0 , or 1 if <b>this</b> is less than, equal to, or greater than the argument Transaction.
	 */
	public int compareAmount(Transaction transaction){ //allows order of transactions based on amount
		if (this.getAmount() == transaction.getAmount()){
			return 0;
		}
		else if (this.getAmount() > transaction.getAmount()){
			return 1;
		}
		else return -1;
	}
}
