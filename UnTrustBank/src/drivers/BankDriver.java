package drivers;
import java.util.Scanner;

import bank.BankGlobal;
import bankUI.*;
import userTypes.*;
import dateTime.DateTime;
import drivers.*;
import accountStatistics.*;
import accountTypes.*;
import java.util.Scanner;

public class BankDriver {

//Hey Josh, thanks for understanding our situation. This should initialize everything you need,
// assuming everything is compiling correctly for you. All the things a user should 
// be able to do should be a method on the object (I think setting all the get/set methods for interest rate and 
//penalty fee aren't under their appropriate user. change that by using the methods in 
// UnTrustBank. Those are there for sure.
	
//Uncomment what you want to look at. Some methods require interactions with the console,	
// our half-finished UI... :/
	
//-Dania
	
	

public static void main(String[] args)
 {	
System.out.println("Hello, Joshua. Welcome to UnTrust Bank. Thank you for accepting our half finished bank. Hopefully we don't lose your money!");
System.out.println("Due to having a half finished UI, half of the methods interact with the console. Sorry for the complications");
//BankGlobal UnTrustBank = new BankGlobal();
BankGlobal.initializeBank();
System.out.println("One of each type of user will be added to the system");
AccountantUser AccountantCarl=new AccountantUser();
AuditorUser AuditorKatie = new AuditorUser();
OperationManagerUser OperationManagerDaniel = new OperationManagerUser();
TellerUser TellerSteven = new TellerUser();
AccountManagerUser AccountManagerSandy = new AccountManagerUser();
BankGlobal.appendToGlobalEmployeeList(AccountManagerSandy);
BankGlobal.appendToGlobalEmployeeList(AccountantCarl);
BankGlobal.appendToGlobalEmployeeList(TellerSteven);
BankGlobal.appendToGlobalEmployeeList(OperationManagerDaniel);
BankGlobal.appendToGlobalEmployeeList(AuditorKatie);


System.out.println("Two customers will be added to the system.");	
DateTime DaniasBirthday = new DateTime(1992, 5, 12, 1, 0, 0);
CustomerUser Dania = new CustomerUser("Dania", "Alexandra" ,"Durnas" ,false, DaniasBirthday, 123456789, new char[] {'g', 'a', 'm', 'm', 'a'}, "DANIADURNAS");
CustomerUser JohnDoe = new CustomerUser();	
BankGlobal.appendToGlobalCustomerList(Dania);
BankGlobal.appendToGlobalCustomerList(JohnDoe);

//AccountManagerSandy.enrollNewCustomer(); uncomment to add new customer

System.out.println("AccountManager Sandy will open an account for me. Please fill in the following");

AccountManagerSandy.openNewCustomerAccount(Dania); //should interact with console

System.out.println("Let's see what information the auditor has.");
System.out.println("");
AccountantCarl.DisplayGlobalStats();
//
//System.out.println("");
//System.out.println("Let's repeat the process, shall we?");
//
//System.out.println("AccountManager Sandy will open another account for me. Please fill in the following");
//
//AccountManagerSandy.openNewCustomerAccount(Dania); //should interact with console
//
//System.out.println("Let's see what information the auditor has now.");
//System.out.println("");
//AccountantCarl.DisplayGlobalStats();


System.out.println("");
System.out.println("The rest kinda depends on what you have been inputting. I've put some sample methods below");
System.out.print("Hopefully the method names and constructors will be straightforward enough");

//OperationManagerDaniel.CauseTimeChange();
//Dania.requestDeposit(amount, account)
//AuditorKatie.overturnFlag(TransactionID)


}


	
	
	
}
