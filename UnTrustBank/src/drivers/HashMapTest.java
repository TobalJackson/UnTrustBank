package drivers;
import java.util.HashMap;
import bank.Transaction;
public class HashMapTest {
	static Integer n1 = 21;
	static Integer n2 = 22;
	static Transaction t1 = new Transaction(10, null, null, 1);
	static Transaction t2 = new Transaction(20, null, null, 1);
	static HashMap<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
	public static void main(String[] args){
		transactions.put(n1, t1);
		transactions.put(n2, t2);
		int a = 21;
		int b = 22;
		System.out.println(transactions.get(a).getAmount());
		System.out.println(transactions.get(b).getAmount());
	}
}
