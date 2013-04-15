package bankUI;

import java.util.Scanner;

import userTypes.OperationManagerUser;

public class OperationManagerUI {
	OperationManagerUser user;
	Scanner input = new Scanner(System.in);
	BankUI menuInstance;
	public OperationManagerUI(OperationManagerUser user, BankUI uiInstance){
		menuInstance = uiInstance;
		this.user = user;
		OperationManagerMenu();
	}
	public void OperationManagerMenu(){
	// TODO this stuff
	}
}
