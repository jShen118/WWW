/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class Customer {
	static public int customerNumberDispenser = 0;	//used to give unique customer IDs
	public int customerNumber;	//the ID of this specific customer
	public String firstName;
	public String lastName;
	
	Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		customerNumber = customerNumberDispenser++;	//set customer number to next ID, then increment the dispenser
	}
	
	public String toString() {
		return firstName + " " + lastName + " (customer #" + Integer.toString(customerNumber) + ")";
		//example: "John Smith (customer #193)"
	}
	public String toCommand() {
		return "addc " + firstName + " " + lastName;
		//example: John Smith (customer #193) would become "addc john smith"
	}
}