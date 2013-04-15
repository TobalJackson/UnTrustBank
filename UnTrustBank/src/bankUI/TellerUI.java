package bankUI;

import java.util.Scanner;

import userTypes.TellerUser;

public class TellerUI {
	TellerUser user;
	Scanner input = new Scanner(System.in);
	BankUI menuInstance;
	public TellerUI(TellerUser user, BankUI uiInstance){
		menuInstance = uiInstance;
		this.user = user;
		TellerMenu();
	}
	public void TellerMenu(){
	// TODO this stuff
	}
}