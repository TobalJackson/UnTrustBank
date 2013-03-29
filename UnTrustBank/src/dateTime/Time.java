package dateTime;
//written by Dennis James, version 1.00
public class Time {
	private long currentTimeDay, currentTimeHour, currentTimeMinute, currentTimeSecond;
	private long newTimeDay, newTimeHour, newTimeMinute, newTimeSecond, totalSeconds;
	public Time(int day, int hour, int min, int sec){
		currentTimeDay = day;
		currentTimeHour = hour;
		currentTimeMinute = min;
		currentTimeSecond = sec;
		
		newTimeDay = currentTimeDay;
		newTimeHour = currentTimeHour;
		newTimeMinute = currentTimeMinute;
		newTimeSecond = currentTimeSecond;
		
		//totalSeconds holds the current number of total seconds represented from days, hours, minutes, and seconds provided
		//by the object's user. Using a long to hold total seconds restricts the number of days that can be held by this variable
		//to approximately 292 billion years.
		totalSeconds = (newTimeSecond + (newTimeMinute * 60) + (newTimeHour * 3600) + (newTimeDay * 86400));
		reduceUnits();
	}

	/**
	 * This method overrides the Java built-in toString method, and returns a human-readable representation of the total time
	 * value held by the Time object.  Since each time field of the Time object can hold negative value, this method will, if
	 * the time is overall negative, instead of returning individually negative fields, return a single negative sign (-) in front of the entire time to 
	 * indicate negativity.
	 */
	public String toString(){
		String totalTime = "";
		if (totalSeconds >= 0){
			totalTime = newTimeDay + " days, " + newTimeHour + ":" + newTimeMinute + ":" + newTimeSecond;
		}
		else{
			totalTime = "-" + Math.abs(newTimeDay) + " days, " + Math.abs(newTimeHour) + ":" + Math.abs(newTimeMinute) + ":" + Math.abs(newTimeSecond);
		}
		return totalTime;
	}
	//the variables currentTime* still hold the original (unreduced) values entered, and could be implemented if needed in the
	//future.
	public int getDays(){
		return (int)newTimeDay;
	}
	public int getHours(){
		return (int)newTimeHour;
	}
	public int getMinutes(){
		return (int)newTimeMinute;
	}
	public int getSeconds(){
		return (int)newTimeSecond;
	}
	
	/**
	 * This method will arithmetically add two time objects together and return a new Time object with the result.  Since the 
	 * reduction to lowest terms occurs at Time object instantiation, there is no need to reduce the units within this method.
	 * @param time - The Time object to add to the first.
	 * @return <b>Time</b> - Returns a new time object that is the result of the two Time objects' additions.
	 */
	public Time add(Time time){
		
		long totalSeconds = time.getSeconds() + this.getSeconds();
		long totalMinutes = time.getMinutes() + this.getMinutes();
		long totalHours = time.getHours() + this.getHours();
		long totalDays = time.getDays() + this.getDays();
								
		Time newCurrentTime = new Time((int)totalDays, (int)totalHours, (int)totalMinutes, (int)totalSeconds);
		return newCurrentTime;
	}
	
	/**
	 * This method will return the result of subtracting a Time object parameter from a base time object (this).  Since the 
	 * reduction to lowest terms occurs at Time object instantiation, there is no need to reduce the units within this method.
	 * @param time - The time object to subtract from a base Time object (this).
	 * @return <b>Time</b> - returns a new Time object that is the result of subtracting an argument time from a base time.
	 */
	public Time subtract(Time time){
		
		int totalSeconds = this.getSeconds() - time.getSeconds();
		int totalMinutes = this.getMinutes() - time.getMinutes();
		int totalHours =  this.getHours() - time.getHours();
		int totalDays = this.getDays() - time.getDays();
						
		Time newCurrentTime = new Time(totalDays, totalHours, totalMinutes, totalSeconds);
		return newCurrentTime;
	}
	
	/**
	 * This private method will take the total number of seconds and reduce each of seconds, minutes, and hours to their 
	 * respective maximum values, rolling the total time up to a total number of days. Since it totals up the time in seconds, 
	 * the method will automatically handle overall negative or positive values appropriately.
	 */
	private void reduceUnits(){
		newTimeSecond = totalSeconds;
		
		if ((newTimeSecond <= 60) || (newTimeSecond >= 60)){
			newTimeMinute = newTimeSecond / 60;
			newTimeSecond = newTimeSecond % 60;
		}
		if ((newTimeMinute <= 60) || (newTimeMinute >= 60)){
			newTimeHour = newTimeMinute / 60;
			newTimeMinute = newTimeMinute % 60;
		}
		if ((newTimeHour <= 24) || (newTimeHour >= 24)){
			newTimeDay = newTimeHour / 24;
			newTimeHour = newTimeHour % 24;
		}
	}
}