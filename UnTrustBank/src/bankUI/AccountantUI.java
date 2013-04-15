package bankUI;

import java.util.Scanner;

import userTypes.AccountantUser;

public class AccountantUI {
	AccountantUser user;
	Scanner input = new Scanner(System.in);
	BankUI menuInstance;
	public AccountantUI(AccountantUser user, BankUI uiInstance){
		menuInstance = uiInstance;
		this.user = user;
		AccountantMenu();
	}
	public void AccountantMenu(){
	// TODO this stuff
	}
}