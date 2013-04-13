package userTypes;
import bank.BankGlobal;
import dateTime.*;
public abstract class BasicUser {
	private String firstName;
	private String middleName;
	private String lastName;
	private DateTime dob;
	private boolean isMale;
	private char[] password;
	private String username;
	protected int userID;
	protected int userType = -1;
	public static final int CUSTOMER_USER_TYPE = 0, ACCOUNTANT_USER_TYPE = 1, ACCOUNT_MANAGER_USER_TYPE = 2, 
			AUDITOR_USER_TYPE = 3, OPERATION_MANAGER_USER_TYPE = 4, TELLER_USER_TYPE = 5;
	protected static int johnDoeIndex = 0;
	public BasicUser(String firstName, String middleName, String lastName, boolean isMale,
			DateTime dob, char[] password, String username) throws IllegalArgumentException{
		if (BankGlobal.userNameExists(username)){
			throw new IllegalArgumentException("This user name is already taken");
		}
		else BankGlobal.addLoginInfo(this);
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.isMale = isMale;
		this.dob = dob;
		this.password = password;
		this.username = username;
//		this.userID = BankGlobal.getNewCustomerID(); //have the userID set in either the CustomerUser or employee user classes.
		getNewUserID();
	}
	
	public BasicUser(){ //for testing purposes
		this("John", "Michael", "Doe", true, new DateTime(1987, 2, 8, 8, 0, 0), new char[]{'D', 'o', 'e'}, "JohnDoe" + johnDoeIndex++);
	}
	
	public BasicUser(String FirstName){
		this(FirstName, "M", "Doe", true, new DateTime(1988, 2, 8, 8, 0, 0), new char[]{'D', 'o', 'e'}, FirstName);
	}
	
	public int getUserType(){
		return userType;
	}
	
	/**
	 * abstract method implemented separately by CustomerUser and <employee>User classes to get customer and employee ID numbers separately.
	 */
	public abstract void getNewUserID();
	
	
	public String getName(){
		return (this.firstName + " " + this.middleName + " " + this.lastName);
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getMiddleName(){
		return this.middleName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public boolean getIsMale(){
		return this.isMale;
	}
	public DateTime getDOB(){
		return this.dob;
	}
	public boolean isPasswordValid(char[] providedPass){
		if (this.password.equals(providedPass)){
			return true;
		}
		else return false;
	}
	public String getUsername(){
		return this.username;
	}
	public int getUserID(){
		return this.userID;
	}
}
