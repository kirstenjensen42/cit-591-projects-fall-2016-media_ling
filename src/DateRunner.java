package src;

public class DateRunner {
	
	
	private String yyyymmdd ;
	private Integer date = new Integer(0);
	
	public DateRunner() {
		yyyymmdd = null;
		
	}
	
	public String getPreviousDay(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd ;
		date = Integer.decode(yyyymmdd) ;
		
		if (theMonth() == 1 && theDay() == 1) {
			 date = date - 8870 ;
		} else if (theDay() <= 1 ) {
			date = date - 101 ;
			date = date + lastDayOfMonth() ;	
		} else {
			date = date - 1 ;
		}
		
		yyyymmdd = Integer.toString(date);
		
		return yyyymmdd ;
	
	}
	
	
	public String getNextDay(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd ;
		date = Integer.decode(yyyymmdd) ;
		
		if (theMonth() == 12 && theDay() == 31) {
			date = date + 8870;
		} else if (theDay() == lastDayOfMonth() ) {
			date = date - lastDayOfMonth() + 101 ;
		} else {
			date = date + 1 ;
		}
		
		yyyymmdd = Integer.toString(date);
		
		return yyyymmdd ;
	
	}
	
	
	private int theDay() {
		int day = date%100 ;
		return day ;
	}
	
	private int theMonth() {
		int month = (date/100)%100 ;
		return month ;
	}
	
	private int theYear() {
		int year = (date/10000) ;
		return year ;
	}
	
	private int lastDayOfMonth() {
		int month = theMonth() ;
		int year = theYear() ;
		int lastDay = 31 ;
		
		if (month == 9 || month == 4 || month == 6 || month == 11 || month == 2) {
			// if February
			if (month == 2 ) {
				// if year is divisible by 0 and not divisible by 100 or if year is divisible by 400
				if (year%4 == 0 && year%100 != 0 || year%400 == 0) {
					lastDay = 29 ;
				// for all other months
				} else {
					lastDay = 28 ;
				}
			} else {
				lastDay = 30 ;
			}
		}
		
		return lastDay;
		
	}

}
