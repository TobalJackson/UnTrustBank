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

	
	public Request(BasicAccount accountRequest, double requestAmount, CustomerUser customerRequestor, int requestType){
		this.timeStamp = new DateTime();
		this.accountRequest=accountRequest;
		this.requestAmount=requestAmount;
		this.customerRequestor=customerRequestor;
		this.requestType=requestType;
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
}
