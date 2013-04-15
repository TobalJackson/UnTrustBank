package bankUI;

import java.util.Scanner;

import userTypes.AuditorUser;

//aaaa
public class AuditorUI {
	AuditorUser user;
	Scanner input = new Scanner(System.in);
	BankUI menuInstance;
	public AuditorUI(AuditorUser user, BankUI uiInstance){
		menuInstance = uiInstance;
		this.user = user;
		AuditorMenu();
	}
	public void AuditorMenu(){
	// TODO this stuff
	}
}