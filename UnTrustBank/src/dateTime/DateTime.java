package dateTime;
//written by Dennis James, version 1.00
import java.util.Calendar;
import java.util.GregorianCalendar;
public class DateTime {
	private Calendar nowDayAndTime;
	private int currentTimeYear;
	private int currentTimeMonth;
	private int currentTimeDay;
	private int currentTimeHour;
	private long currentTimeMinute;
	private long currentTimeSecond;
	//an array which holds (initially) a non-leap year representation of the total days in each month.
	private int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private long newTimeSecond;
	private long newTimeMinute;
	private int newTimeHour;
	private int newTimeDay;
	private int newTimeMonth;
	private int newTimeYear;
	
	public DateTime(){
		nowDayAndTime = new GregorianCalendar();
		currentTimeYear = nowDayAndTime.get(Calendar.YEAR);
		//Add 1 to the 0-base count of month for a human-readable month.
		currentTimeMonth = nowDayAndTime.get(Calendar.MONTH) + 1;
		currentTimeDay = nowDayAndTime.get(Calendar.DAY_OF_MONTH);
		currentTimeHour = nowDayAndTime.get(Calendar.HOUR_OF_DAY);
		currentTimeMinute = nowDayAndTime.get(Calendar.MINUTE);
		currentTimeSecond = nowDayAndTime.get(Calendar.SECOND);
		
		newTimeYear = currentTimeYear;
		newTimeMonth = currentTimeMonth;
		newTimeDay = currentTimeDay;
		newTimeHour = currentTimeHour;
		newTimeMinute = currentTimeMinute;
		newTimeSecond = currentTimeSecond;
		//check whether this year is a leap year or not, and change the days in February (in daysInMonth) accordingly.
		checkNewLeapYear();
	}
	
	public DateTime(int year, int month, int day, int hour, int min, int sec){
		
		currentTimeYear = year;
		currentTimeMonth = month;
		currentTimeDay = day;
		currentTimeHour = hour;
		currentTimeMinute = min;
		currentTimeSecond = sec;
	
		newTimeYear = currentTimeYear;
		newTimeMonth = currentTimeMonth;
		newTimeDay = currentTimeDay;
		newTimeHour = currentTimeHour;
		newTimeMinute = currentTimeMinute;
		newTimeSecond = currentTimeSecond;
		checkNewLeapYear();
		reduceNewUnits();
		
	}
	
	/**
	 * checkNewLeapYear will alter the number of days in February based on the newTimeYear of the instance, 28 for non-leap
	 * years, and 29 days for leap years.  The general formula for leap years is any year divisible by 4 AND NOT 100, or years
	 * divisible by 400.
	 */
	private void checkNewLeapYear(){
		if ((this.newTimeYear % 4 == 0 && this.newTimeYear % 100 != 0) || this.newTimeYear % 400 == 0){
            this.daysInMonth[1]= 29;
		}
		else{
			this.daysInMonth[1]= 28;
		}
	}
	
	/**
	 * this method is just like checkNewLeapYear, except it accepts an arbitrary year as input instead of the object's current
	 * year.
	 * @param year - the year that will be checked for leap characteristic.
	 * @see #checkNewLeapYear()
	 */
	private void checkUpLeapYear(int year){
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            this.daysInMonth[1]= 29;
		}
		else{
			this.daysInMonth[1]= 28;
		}
	}

	/**
	 * Method will automatically reduce time values for hh:mm:ss to fall within their respective ranges, rolling up
	 * the calculated time to a limit of days. Called in the constructor that accepts a specific date and time.
	 */

	private void reduceNewUnits(){
		newTimeSecond = (newTimeSecond + (newTimeMinute * 60) + (newTimeHour * 3600) + (newTimeDay * 86400));
		
		if ((newTimeSecond <= 60) || (newTimeSecond >= 60)){
			newTimeMinute = newTimeSecond / 60;
			newTimeSecond = newTimeSecond % 60;
		}
		if ((newTimeMinute <= 60) || (newTimeMinute >= 60)){
			newTimeHour = (int)newTimeMinute / 60;
			newTimeMinute = newTimeMinute % 60;
		}
		if ((newTimeHour <= 24) || (newTimeHour >= 24)){
			newTimeDay = newTimeHour / 24;
			newTimeHour = newTimeHour % 24;
		}
		//rolls over any month-excessive days to increasing month/year.
		resolveNewMonthYear();
	}
	
	/**
	 * This method returns a new DateTime object after having resolved any month-excessive days into month and year.  This method
	 * is used exclusively in the <b>DateTime.add(Time)</b> and <b>DateTime.subtract(Time)</b> methods and returns the final dateTime returned
	 * by each of those methods.
	 * @param dateTime - The DateTime passed to this method holds only the year, month, and day from the original DateTime.
	 * @param time - The Time Parameter holds the total days to be added (or subtracted) from the DateTime object.
	 * @return <b>DateTime</b> - returns a DateTime object with the year, month, day, hour, minute, and second resolved.
	 * @see #add(Time)
	 * @see #subtract(Time)
	 */
	private DateTime resolveMonthYear(DateTime dateTime, Time time){
		int currentYear = dateTime.getYear();
		int currentMonth = dateTime.getMonth();
		//adds the total days from the added Time object and the current day of the month.
		int currentDay = dateTime.getDay() + time.getDays(); 
		//before the month calculation is performed, the number of days in the current month is taken account of.
		int daysInCurrentMonth = daysInMonth[currentMonth - 1];
		//if the currentDay exceeds the number of days in the current month, reduce currentDay by the number of
		//days in the month and increment the month to the next, also changing to the next year after month 12.
		while (currentDay > daysInCurrentMonth){
			currentDay = currentDay - daysInCurrentMonth;
			currentMonth++;
			if (currentMonth == 13){
				currentMonth = 1;
				currentYear++;
				//checkUpLeapYear will add a day (Feb 29th) to the daysInMonth[] array depending on leap year rules.
				checkUpLeapYear(currentYear);				
			}
			daysInCurrentMonth = daysInMonth[currentMonth - 1];
		}
		//this while() will take into account a total number of days to be added that is negative.
		while (currentDay < 1){
			currentMonth--;
			if (currentMonth == 0){
				currentMonth = 12;
				currentYear--;
				checkUpLeapYear(currentYear);
			}
			currentDay += daysInMonth[currentMonth - 1];
		}
		//returns a new DateTime with the year, month, and day resolved, while the HMS Time remains unchanged (and already
		//resolved by the Time Constructor)
		DateTime finalDate = new DateTime(currentYear, currentMonth, currentDay, time.getHours(), time.getMinutes(), time.getSeconds());
		return finalDate;
	}
		
	/**
	 * This method is nearly identical to the one above except it is called only as a subroutine of reduceNewUnits() to alter the
	 * global private variables immediately after they've been used in the object's construction. It will reduce the objects
	 * date and time to valid entries.
	 */
	private void resolveNewMonthYear(){
		int currentMonth = newTimeMonth;
		int currentDay = newTimeDay; 
		int daysInCurrentMonth = daysInMonth[currentMonth - 1];
		while (currentDay > daysInCurrentMonth){
			currentDay = currentDay - daysInCurrentMonth;
			currentMonth++;
			if (currentMonth == 13){
				currentMonth = 1;
				newTimeYear++;
				checkNewLeapYear();				
			}
			daysInCurrentMonth = daysInMonth[currentMonth - 1];
		}
		while (currentDay < 1){
			currentMonth--;
			if (currentMonth == 0){
				currentMonth = 12;
				newTimeYear--;
				checkNewLeapYear();
			}
			currentDay += daysInMonth[currentMonth - 1];
		}
		newTimeDay = currentDay;
		newTimeMonth = currentMonth;
	}
	
	/**
	 * This private method will check if two DateTime objects are equal.  It will consider the two object equal if
	 * their year, month, day, hour, minute, and second are equal. This method is used in the DateTime.subtract(DateTime) method
	 * for the stopping criteria.
	 * @param instance - expects type of object DateTime to be compared to.
	 * @return <b>boolean</b> - returns true if the objects' values match, false otherwise
	 * @see #subtract(DateTime)
	 */
	private boolean isEqual(Object instance){
	    if (instance instanceof DateTime){
	    	DateTime dateTime = (DateTime)instance;
	    	if ((dateTime.getYear() == this.getYear()) 
	    			&& (dateTime.getMonth() == (this.getMonth()) 
	    			&& (dateTime.getDay() == this.getDay()))
	    			&& (dateTime.getHour() == this.getHour())
	    			&& (dateTime.getMinute() == this.getMinute())
	    			&& (dateTime.getSecond() == this.getSecond())){
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}
	    }
	    else{
	    	return false;
	    }
	}
	
	/**
	 * This private method is used specifically by DateTime.subtract(DateTime) in order to determine the direction of the date 
	 * incrementing algorithm.  Since the DateTime.subtract(DateTime) method separates the YMD from HMS, this method only 
	 * returns true if the YMD of the Subtractee DateTime is earlier (less) than the Subtractor DateTime's YMD.
	 * @param subtractor the second (subtract<b>ed</b>) term in a subtraction argument
	 * @return boolean true if the subtractee is less than the subtractor, otherwise false.
	 * @see #subtract(DateTime)
	 */
	private boolean isDaysNegative(DateTime subtractor){
		if (this.getYear() > subtractor.getYear()){
			return false;
		}
		else if (this.getYear() == subtractor.getYear()){
			if (this.getMonth() > subtractor.getMonth()){
				return false;
			}
			else if (this.getMonth() == subtractor.getMonth()){
				if (this.getDay() >= subtractor.getDay()){
					return false;
				}
				else return true;
			}
			else return true;
		}
		else return true;
	}
	
	/**
	 * Public method of DateTime which will return a String with the values from a DateTime object in the form "MM/DD/YYYY, 
	 * HH:MM:SS."  Overrides Java's built-in toString() method.
	 * 
	 */
	public String toString(){
		String dateTimeString = newTimeMonth + "/" + newTimeDay + "/" + newTimeYear + ", " + newTimeHour + ":" + newTimeMinute + ":" + newTimeSecond;
		return dateTimeString;
	}
	public int getYear(){
		return newTimeYear;
	}
	public int getMonth(){
		return newTimeMonth;
	}
	public int getDay(){
		return newTimeDay;
	}
	public int getHour(){
		return newTimeHour;
	}
	public int getMinute(){
		return (int)newTimeMinute;
	}
	public int getSecond(){
		return (int)newTimeSecond;
	}	
	
	/**
	 * Directly adds the seconds, minutes, hours, and days from the Time object to the DateTime objects matching fields.
	 * Note that the resulting values can exceed the maximum normal values for their types (hours, minutes, seconds).
	 * <p><p>
	 * The reduceUnits() method resolves excessive seconds, minutes, and hours to a number of total days.  This number
	 * of days is then passed to the resolveMonthYear(DateTime, Time) method in order to reduce the days to the bounds of the month's
	 * maximum number of days (28, 29, 30, or 31), and increments or decrements the year when necessary.
	 * @param time - The Time object (days, hours, minutes, seconds) to be added to the DateTime.
	 * @return <b>DateTime</b> - Returns a new DateTime representing the addition of the original DateTime and Time time.
	 * @see #reduceNewUnits()
	 * @see #resolveMonthYear()
	 */
	public DateTime add(Time time){	
		//construct a new Time object that adds and resolves the hours, minutes, seconds between the two objects, and holds the 
		//Time objects days separately from the DateTime's days.
		Time addedTime = new Time(time.getDays(), time.getHours() + this.getHour(), time.getMinutes() + 
				this.getMinute(), time.getSeconds() + this.getSecond());
		DateTime addedDate = new DateTime(this.getYear(), this.getMonth(), this.getDay(), 0, 0, 0);
		//The resolveMonthYear method will add the days passed from the new Time and the days from the passed DateTime,
		//and resolve the new day, month, and year based on those.
		DateTime newCurrentDateTime = resolveMonthYear(addedDate, addedTime);
		return newCurrentDateTime;
	}
	public DateTime subtract(Time time){	
		Time subtractedTime = new Time(0 - time.getDays(), this.getHour() - time.getHours(), this.getMinute() - time.getMinutes(),
				this.getSecond() - time.getSeconds());
		DateTime subtractedDate = new DateTime(this.getYear(), this.getMonth(), this.getDay(), 0, 0, 0);
		DateTime newCurrentDateTime = resolveMonthYear(subtractedDate, subtractedTime);
		return newCurrentDateTime;
	}
	
	/**
	 * This public method will subtract DateTime dateTime from (DateTime)this.  It will return a time object representing the number of
	 * days, minutes, hours, and seconds difference between the two DateTime objects.
	 * @param dateTime  - The DateTime to be subtracted <b>from</b> (DateTime)this.
	 * @return <b>Time</b> - Returns a Time object that is the differencing time in days, hours, minutes, and seconds between the
	 * two DateTime objects.
	 */
	public Time subtract(DateTime dateTime){
		//Determines which DateTime is further in the past and subsequently which DateTime to start counting from.
		boolean isDaysNegative = this.isDaysNegative(dateTime);
		//Define a 1 day Time object which will be recursively added to the earlier DateTime until the later DateTime is 
		//reached.  The Time object is used because DateTime will automatically resolve a valid DateTime format when adding
		//Time objects. 
		Time oneDay = new Time(1, 0, 0, 0);
		//The dayCount field keeps track of the total number of days added (or subtracted) to/from the start date,
		//separate from the number of days that resulted from the adding or subtracting of the Hours, Minutes, and Seconds.  
		//dayCount is then added to the number days resulting from the HMS calculation for the construction of the new Time object
		int dayCount = 0;
		//if the DateTime object calling the subtraction (this) describes a date prior to the DateTime argument (DateTime dateTime)
		//start counting from this.
		if (isDaysNegative){
			//construct a new DateTime without the hours, minutes, seconds components for Day, Month, Year calculation
			DateTime startDate = new DateTime(this.getYear(), this.getMonth(), this.getDay(), 0, 0, 0);
			DateTime endDate = new DateTime(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), 0, 0, 0);
			//pass the DateTime objects' times to Time objects for (day), hour, minute, second calculation.
			Time startTime = new Time(0, this.getHour(), (int)this.getMinute(), (int)this.getSecond());
			Time endTime = new Time(0, dateTime.getHour(), (int)dateTime.getMinute(), (int)dateTime.getSecond());
			//utilize the Time.subtract(Time) method.
			Time finalTime = startTime.subtract(endTime);
			//Start adding days to startDate until endDate is reached, keeping track of how many days are added.
			while (!startDate.isEqual(endDate)){
				//the DateTime.add(Time) method was used since it takes care of the leap year rules and month rules already.
				startDate = startDate.add(oneDay);
				//dayCount is decremented here since this case is tracking an overall negative span of time.
				dayCount--;
			}
			//Construct a new Time object taking into account the number of days counted (dayCount) and adding to the number of 
			//days resultant of the finalTime calculation
			finalTime = new Time((dayCount + finalTime.getDays()), finalTime.getHours(), finalTime.getMinutes(), finalTime.getSeconds());
			return finalTime;
		}
		//case for when the span between the DateTimes is positive.
		else{
			DateTime startDate = new DateTime(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), 0, 0, 0);
			DateTime endDate = new DateTime(this.getYear(), this.getMonth(), this.getDay(), 0, 0, 0);
			Time startTime = new Time(0, dateTime.getHour(), (int)dateTime.getMinute(), (int)dateTime.getSecond());
			Time endTime = new Time(0, this.getHour(), (int)this.getMinute(), (int)this.getSecond());
			Time finalTime = endTime.subtract(startTime);
			while (!startDate.isEqual(endDate)){
				startDate = startDate.add(oneDay);
				dayCount++;
			}
			finalTime = new Time((dayCount + finalTime.getDays()), finalTime.getHours(), finalTime.getMinutes(), finalTime.getSeconds());
			return finalTime;
		}
	}
}