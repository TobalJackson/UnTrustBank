package accountStatistics;

//-interestRateHistory:ArrayList<DateTime, double>
//-serviceFeeHistory:ArrayList<DateTime, double>
//
//+getInterestRateHistory():ArrayList<DateTime, double>
//+getServiceFeeHistory():ArrayList<DateTime, double>
//+addInterestRateChange(double):void
//+addServiceFeeChange(double):void
import accountTypes.SavingsAccount;
public class SavingsAccountStats extends AccountStats {
	
	public SavingsAccountStats(SavingsAccount account){
		super(account);
	}
}
