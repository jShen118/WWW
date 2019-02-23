/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

/*
 this class contains the data structures abd implements the functions that correspond to the commands
 */
public class Shop {
	public void help() {};	//print help
	public void addrp(String brand, RepairLevel level, int price, int days) {};	//add repair price
	public void addc(String firstName, String lastName) {};	//add customer
	public void addo(int customerNumber, Date date, String brand, RepairLevel level, String comment) {};	//add order
	public void comp(int orderNumber, Date completionDate) {};	//mark order completed
	//comp next would be useful extra feature
	public void printrp() {};	//print repair prices
	public void printcnum() {};	//print customers by number
	public void printcname() {};	//print customers by name
	public void printo() {};	//print orders
	public void printp() {};	//print payments
	public void printt() {};	//print transactions
	public void printr() {};	//print receivables
	public void prints() {};	//print statements
	public void readc(String filename) {};	//read commands from file
	public void savebs(String filename) {};	//save bike shop to file
	public void restorebs(String filename) {};	//restore a previously saved bike shop
}
