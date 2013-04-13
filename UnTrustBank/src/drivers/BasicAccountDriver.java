package drivers;
import bank.*;
import accountTypes.*;
import userTypes.*;
import dateTime.*;

public class BasicAccountDriver {
	public static CustomerUser bob = new CustomerUser("bob", "c", "jones", true, new DateTime(), 555999, new char[]{'b', 'o', 'b'}, "bob");
	public static CheckingAccount chk1 = new CheckingAccount(bob);
	public static Transaction t1 = new Transaction(10, bob, bob, Transaction.DEPOSIT);
	public static Transaction t2 = new Transaction(20, bob, bob, Transaction.DEPOSIT);
	public static SavingsAccount svg1 = new SavingsAccount(bob, t2);
	public static void main(String[] args){
		chk1.appendTransaction(t1, bob);
		bob.printAccountSummary();
	}
}
