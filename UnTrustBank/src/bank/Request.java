package bank;
import java.util.ArrayList;
import dateTime.DateTime;
import userTypes.CustomerUser;
import userTypes.BasicUser;
import accountTypes.BasicAccount;
public class Request {

	/**
	 * @param args
	 */
	private BasicAccount accountRequest;
	private double requestAmount;
	private CustomerUser customerRequestor;
//	private double depositAmount;
//	private double withdrawalAmount;
	private DateTime timeStamp;
	private int requestType;
	private boolean requestApproved = false;
	public static final int WITHDRAWAL = 0, DEPOSIT = 1, INTEREST = 2, PENALTY = 3, OTHER = 4, SERVICE_CHARGE = 5, TRANSFER = 6;


	
	public Request(BasicAccount accountRequest, double requestAmount, CustomerUser customerRequestor, int requestType) throws IllegalArgumentException{
		if (requestAmount < 0){
			throw new IllegalArgumentException("Requests must be positive!");
		}
		else{
			this.timeStamp = new DateTime();
			this.accountRequest=accountRequest;
			this.requestAmount=requestAmount;
			this.customerRequestor=customerRequestor;
			this.requestType=requestType;
			BankGlobal.appendToGlobalRequestList(this);
		}
	}
	
	public double getRequestAmount(){
		return requestAmount;
	}
	
	public CustomerUser getCustomer(){
		return customerRequestor;
	}
	
	public BasicAccount getAccount(){
		return accountRequest;
	}
	
	public DateTime getTime(){
		return timeStamp;
	}
	
	public int getRequestType(){
		return requestType;
	}
	
	/**
	 * Method to fetch status of the request.
	 * @return <b>boolean</b> true if a Teller has approved the request, false otherwise.
	 */
	public boolean isRequestApproved(){
		return requestApproved;
	}
	
	/**
	 * Method to mark the request as approved and processed. Called by Teller through BankGlobal.
	 */
	public void setRequestApproved(){
		requestApproved = true;
	}
}
