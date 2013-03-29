package userTypes;
import dateTime.*;
public abstract class BasicUser {
	private String firstName;
	private String middleName;
	private String lastName;
	private DateTime dob;
	private boolean isMale;
	private char[] password;
	private String username;
	private int userID;
	public BasicUser(String firstName, String middleName, String lastName, boolean isMale,
			DateTime dob, char[] password, String username, int userID){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.isMale = isMale;
		this.dob = dob;
		this.password = password;
		this.username = username;
		this.userID = userID;
	}
	public BasicUser(){
		this("John", "Michael", "Doe", true, new DateTime(0, 0, 0, 0, 0, 0), new char[]{'D', 'o', 'e'}, "JohnDoe", 0);
	}
	public String getName(){
		return (this.firstName + " " + this.middleName + " " + this.lastName);
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
