package accountTypes;
import bank.Transaction;
public interface CustomerTransferSource {
//	public void transferBetweenOwnAccounts(double amount, BasicAccount source, BasicAccount destination);
	public Transaction customerTransferWithdrawal(double amount);
}
