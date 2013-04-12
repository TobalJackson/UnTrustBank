package drivers;
import userTypes.CustomerUser;
import userTypes.TellerUser;
import userTypes.AccountManagerUser;
import dateTime.DateTime;

public class CustomerUserDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateTime dobbob = new DateTime(1994, 10, 19, 13, 13, 13);
		String pass = "password";
		char[] password = new char[pass.length()];
		for(int i=0; i<pass.length(); i++){
			password[i]=pass.charAt(i);
		}
		CustomerUser bob = new CustomerUser("Jesse","Charles","Everett", true, dobbob, 555555555, password, "jceverett", 1);
		TellerUser sally = new TellerUser();
		AccountManagerUser Chris = new AccountManagerUser();
		System.out.println(bob.getFirstName());
		System.out.println(bob.getMiddleName());
		System.out.println(bob.getLastName());
		if(bob.getIsMale()){
			System.out.println("Male");
		}
		else{
			System.out.println("Female");
		}
		System.out.println(dobbob.getYear());
		System.out.println(dobbob.getMonth());
		System.out.println(dobbob.getDay());
		System.out.println(bob.getCustomerSSN(Chris));
		System.out.println(bob.getUsername());
		System.out.println(bob.getUserID());
		
	}

}
