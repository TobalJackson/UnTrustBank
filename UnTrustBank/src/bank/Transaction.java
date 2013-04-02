package bank;
import dateTime.DateTime;
import userTypes.CustomerUser;
import userTypes.BasicUser;
public class Transaction {
	private DateTime timeStamp;
	private double amount;
	private boolean isFlaggedFraudulent;
	private CustomerUser customer;
	private BasicUser initiator;
	private boolean isCredit;
	private boolean isApplied;//comment here wwooo00t
	
	public Transaction(DateTime timeStamp, double amount, CustomerUser customer, BasicUser initiator, boolean isCredit){
		this.timeStamp = timeStamp;
		this.amount = amount;
		isFlaggedFraudulent = false; //by default, transactions are not fraudulent
		this.customer = customer;
		this.initiator = initiator;
		this.isCredit = isCredit;
		isApplied = true; //by default, transactions are applied to the account
		
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
	public boolean getIsApplied(){
		return isApplied;
	}
	public CustomerUser getCustomer(){
		return customer;
	}
	public BasicUser getInitiator(){
		return initiator;
	}
}
