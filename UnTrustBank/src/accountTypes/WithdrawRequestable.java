package accountTypes;

public interface WithdrawRequestable { //implemented by COD and Savings account only
	public void requestWithdrawal(double amount);
}
