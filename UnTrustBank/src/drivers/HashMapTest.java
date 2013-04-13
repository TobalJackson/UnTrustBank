package drivers;
import java.util.HashMap;
import bank.Transaction;
import userTypes.*;
import dateTime.DateTime;
public class HashMapTest {
	static Integer n1 = 21;
	static Integer n2 = 22;
	static Transaction t1 = new Transaction(10, null, null, 1);
	static Transaction t2 = new Transaction(20, null, null, 1);
	static String s1 = new String("john");
	static String s2 = new String("steve");
	static CustomerUser john = new CustomerUser("john", "c", "doe", true, new DateTime(), 900, new char[]{'d', 'o', 'e'}, "john");
	static CustomerUser steve = new CustomerUser("steve", "c", "doe", true, new DateTime(), 800, new char[]{'d', 'o', 'e'}, "steve");
	static HashMap<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
	static HashMap<String, CustomerUser> customers = new HashMap<String, CustomerUser>();


	public static void main(String[] args){
		transactions.put(n1, t1);
		transactions.put(n2, t2);
		int a = 21;
		int b = 22;
		System.out.println(transactions.get(a).getAmount());
		System.out.println(transactions.get(b).getAmount());
		
		String c = new String("john");
		String d = new String("steve");
		customers.put(s1, john);
		customers.put(s2, steve);
		System.out.println(customers.get("steve").getFirstName());
		System.out.println(customers.get("john").getFirstName());
		System.out.println(customers.get(c).getFirstName());
		System.out.println(customers.get(d).getFirstName());
		System.out.println(customers.keySet().contains("steve"));
		System.out.println(customers.keySet().contains("Steve"));
		
	}
}
