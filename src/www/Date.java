/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;
public class Date implements Comparable<Date> {
	public int day;
	public int month;
	public int year;
	
	Date(int month, int day, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Date future(int days) {	//returns a date "days" days in the future
		//does not take leap years into account
		switch(month) {
			//February; 28 days in month
			case 2:
				return addDays(days, 28);
			//April, June, September, November; 30 days in month
			case 4: case 6: case 9: case 11:
				return addDays(days, 30);
			//January, March, May, July, August, October, December; 31 days in month
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return addDays(days, 31);
			default:
				return null;
		}
	}
	
	public String toString() {	//converts the date to string MMDDYYYY
		String MM = Support.bufferZero(Integer.toString(month), 2);
		String DD = Support.bufferZero(Integer.toString(day), 2);
		String YYYY = Support.bufferZero(Integer.toString(year), 4);
		return MM + DD + YYYY;
	}
	
	public String readableToString() {
		String MM = Support.bufferZero(Integer.toString(month), 2);
		String DD = Support.bufferZero(Integer.toString(day), 2);
		String YYYY = Support.bufferZero(Integer.toString(year), 4);
                return MM + "/" + DD + "/" + YYYY;
	}
	
	private Date addDays(int daysLater, int daysInMonth) {
		int newMonth;
		int newDay;
		int newYear;
		if (this.day + daysLater > daysInMonth) {
			newMonth = this.month + 1;
			newDay = this.day + daysLater - daysInMonth;
			if (newMonth > 12) {newYear = this.year + 1; newMonth = newMonth % 12;} else {newYear = this.year;}
			return new Date(newMonth, newDay, newYear);
		}
		//executes if no month change
		newDay = this.day + daysLater;
		newMonth = this.month;
		newYear = this.year;
		return new Date(newMonth, newDay, newYear);
	}

    @Override
    public int compareTo(Date date) {//-1 is this date less than parameter, 0 is they are equal, 1 is this date is greater than parameter
        if(year < date.year) {return -1;}
        if(year > date.year) {return 1;}
        //passes these two if statements if years are the same, month comparisons next
        if(month < date.month) {return -1;}
        if(month > date.month) {return 1;}
        //passes these two if statements if months are the same, day comparisons next
        if(day < date.day) {return -1;}
        if(day > date.day) {return 1;}
        //passes these two if statements if the two dates are the same, return 0
        return 0;
    }
	
	public boolean isGreaterThan(Date date) {
        return (compareTo(date) == 1);
    }
    
    public boolean equals(Date date) {
        return(compareTo(date) == 0);
    }
}
