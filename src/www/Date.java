/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class Date {
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
		//for months, days, and years, it converts the value to a string
		//and then checks if it is of the correct length. If it isn't, it
		//pads it with "0" (for example, a month of "3" will become "03")
		String MM = Integer.toString(month);
		if (MM.length() == 1) {
			MM = "0" + MM;
		}
		String DD = Integer.toString(day);
		if (DD.length() == 1) {
			DD = "0" + DD;
		}
		String YYYY = Integer.toString(year);
		while (YYYY.length() != 4) {
			YYYY = "0" + YYYY;
		}
		return MM + DD + YYYY;
	}
	
	private Date addDays(int daysLater, int daysInMonth) {
		int newMonth;
		int newDay;
		int newYear;
		if (this.day + daysLater > daysInMonth) {
			newMonth = this.month + 1;
			newDay = this.day + daysLater - daysInMonth;
			if (newMonth == 1) {newYear = this.year + 1;} else {newYear = this.year + 1;}
			return new Date(newMonth, newDay, newYear);
		}
		//executes if no month change
		newDay = this.day + daysLater;
		newMonth = this.month;
		newYear = this.year;
		return new Date(newMonth, newDay, newYear);
	}
}