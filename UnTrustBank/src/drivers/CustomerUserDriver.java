package drivers;
import userTypes.CustomerUser;
import userTypes.TellerUser;
import userTypes.AccountManagerUser;
import dateTime.DateTime;
import accountTypes.CheckingAccount;
import bank.Transaction;

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
		
		CustomerUser bob = new CustomerUser("Jesse","Charles","Everett", true, dobbob, 555555555, password, "jceverett");
		TellerUser sally = new TellerUser();
		AccountManagerUser Chris = new AccountManagerUser();
		CheckingAccount dollabills = new CheckingAccount(bob);
		CheckingAccount dolladollabills = new CheckingAccount(bob);
		Transaction moneyz = new Transaction(7000000, bob, sally, 1);
		
		System.out.println(moneyz.getAmount());
		bob.addCustomerAccount(dollabills);
		System.out.println("dollabills: " + dollabills.getCurrentAccountBalance());
		bob.addCustomerAccount(dolladollabills);
		dollabills.updateCurrentAccountBalance();
		sally.deposit(moneyz, dollabills);
		dollabills.appendTransaction(moneyz, sally);
		sally.deposit(moneyz, dollabills);
		dollabills.appendTransaction(moneyz, sally);
		System.out.println("moneyz: " + moneyz.getAmount());
		System.out.println("dollabills: " + dollabills.getCurrentAccountBalance());
		dollabills.updateCurrentAccountBalance();
		System.out.println("dollabills: " + dollabills.getCurrentAccountBalance());
		System.out.println(dollabills.getCurrentAccountBalance());
		
//		System.out.println(bob.getFirstName());
//		System.out.println(bob.getMiddleName());
//		System.out.println(bob.getLastName());
//		if(bob.getIsMale()){
//			System.out.println("Male");
//		}
//		else{
//			System.out.println("Female");
//		}
//		System.out.println(dobbob.getYear());
//		System.out.println(dobbob.getMonth());
//		System.out.println(dobbob.getDay());
//		System.out.println(bob.getCustomerSSN(Chris));
//		System.out.println(bob.getUsername());
//		System.out.println(bob.getUserID());
		
		// testing exceptions
		//Negative deposit
		Transaction moneyzdosD = new Transaction(-7000000, bob, sally, 1); // still allows a negative deposit
		sally.deposit(moneyzdosD, dollabills);
		dollabills.appendTransaction(moneyzdosD, sally);
		dollabills.updateCurrentAccountBalance();
		System.out.println("moneyzdosD: " + moneyzdosD.getAmount());
		System.out.println("dollabills: " + dollabills.getCurrentAccountBalance());
		
		//Negative withdrawal
		System.out.println("dollabills: " + dollabills.getCurrentAccountBalance());
		Transaction moneyzdosW = new Transaction(-7000000, bob, sally, 0); // still allows a negative withdrawal
		sally.withdraw(moneyzdosW, dollabills);
		dollabills.updateCurrentAccountBalance();
		System.out.println("moneyzdosW: " + moneyzdosW.getAmount());
		System.out.println("dollabills: " + dollabills.getCurrentAccountBalance());
	}

}
