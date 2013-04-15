package bankUI;
import java.util.Scanner;
import userTypes.AccountManagerUser;

public class AccountManagerUI {
	AccountManagerUser user;
	Scanner input = new Scanner(System.in);
	BankUI menuInstance;
	public AccountManagerUI(AccountManagerUser user, BankUI uiInstance){
		menuInstance = uiInstance;
		this.user = user;
		AccountManagerMenu();
	}
	public void AccountManagerMenu(){
	// TODO this stuff
	}
}
