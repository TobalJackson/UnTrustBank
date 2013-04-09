package accountTypes;

import java.util.Iterator;

import dateTime.DateTime;
import dateTime.Time;
import userTypes.CustomerUser;

public class LoanAccount extends BasicAccount{
double interestrate;
double minloanmonthlyloanpayment;

	public LoanAccount(CustomerUser owner, int accountID, double maxallowedtospend, double myinterestrate, double myminmontlyloanpayment) {
		super(owner, accountID);
		setMaximumAccountBalance(0);
		if(maxallowedtospend>0){
			throw new IllegalArgumentException("cap must be set as negative");
		}
		setMinimumAccountBalance(maxallowedtospend);
		if(myinterestrate>1 || myinterestrate <= 0){
			throw new IllegalArgumentException("interest percentage must be between 0 and 1");
		}
		interestrate=myinterestrate;
		if(myminmontlyloanpayment<=0){
			throw new IllegalArgumentException("loan payments have to be positive unfortunately");
		}
		minloanmonthlyloanpayment=myminmontlyloanpayment;
		
		
		
		
		
		// TODO Auto-generated constructor stub
	}
//must have one negative transaction in constructor 

	@Override
	public Iterator<BasicAccount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//ok so I need help here - Dania <3
	public void respondToTimeChange(DateTime originalTime, DateTime newTime,
			Time timeDifference) {
		DateTime tempDate = new DateTime(originalTime.getYear(), originalTime.getMonth(), originalTime.getDay(), originalTime.getHour(), originalTime.getMinute(), originalTime.getSecond());
		
		Time oneDay = new Time(1,0, 0,0);
		int i=0; int month1=tempDate.getMonth(); int month2=tempDate.getMonth();
		int monthcounter=0;
		
		for(i=0; i< timeDifference.getDays(); i++){
			month1=tempDate.getMonth();
			tempDate=tempDate.add(oneDay);
			month2=tempDate.getMonth();	
			if(!(month1==month2)){
				monthcounter+=1;
				// ??????
			}
		}
		
		//Interest accrues once per month
		// TODO Auto-generated method stub
		
	}
}
