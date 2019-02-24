/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;
import java.util.*;
/*
 this class contains the data structures abd implements the functions that correspond to the commands
 */
public class Shop {
	private List<Transaction> transactions;
	private List<Customer> customers;
	private PriceTable priceTable;
	
	Shop() {
		transactions = new ArrayList<>();
		customers = new ArrayList<>();
		priceTable = new PriceTable();
	}
	
	/*------------------commands-------------------------*/
	//indented functions are not yet implemented
	public void help() {
		String message = "├-[help]-----------------------------------------------------------------------------┤\n" +	
				"　Commands:\n" +
				"　　help" + Support.bufferCustom("display help\n", 82 - "help".length(), ".") +
				"　　quit" + Support.bufferCustom("quit the system\n", 82 - "quit".length(), ".") +
				"　　addrp <brand> <level> <price> <days>" + Support.bufferCustom("add a repair price\n", 82 - "addrp <brand> <level> <price> <days>".length(), ".") +
				"　　addc <first name> <last name>" + Support.bufferCustom("add a customer\n", 82 - "addc <first name> <last name>".length(), ".") +
				"　　addo <customer number> <date> <brand> <level> <comment>" + Support.bufferCustom("add an order\n", 82 - "addo <customer number> <date> <brand> <level> <comment>".length(), ".") +
				"　　addp <customer number> <date> <amount>" + Support.bufferCustom("add a payment\n", 82 - "addp <customer number> <date> <amount>".length(), ".") +
				"　　comp <order number> <completion date>" + Support.bufferCustom("mark order <order number> as completed\n", 82 - "comp <order number> <completion date>".length(), ".") +
				"　　printrp" + Support.bufferCustom("print repair price table\n", 82 - "printrp".length(), ".") +
				"　　printcnum" + Support.bufferCustom("print customers by customer name\n", 82 - "printcnum".length(), ".") +
				"　　printcname" + Support.bufferCustom("print customers by customer number\n", 82 - "printcname".length(), ".") +
				"　　printo" + Support.bufferCustom("print orders\n", 82 - "printo".length(), ".") +
				"　　printp" + Support.bufferCustom("print payments\n", 82 - "printp".length(), ".") +
				"　　printt" + Support.bufferCustom("print all transactions\n", 82 - "printt".length(), ".") +
				"　　printr" + Support.bufferCustom("print receivables\n", 82 - "printr".length(), ".") +
				"　　prints" + Support.bufferCustom("print statements\n", 82 - "prints".length(), ".") +
				"　　readc <filename>" + Support.bufferCustom("read commands from file with name <filename>\n", 82 - "readc <filename>".length(), ".") +
				"　　savebs <filename>" + Support.bufferCustom("save bike shop to file <filename>\n", 82 - "savebs <filename>".length(), ".") +
				"　　restorebs <filename>" + Support.bufferCustom("restore bike shop from save file <filename>\n", 82 - "restorebs <filename>".length(), ".") +
				"\n　*All dates must be entered in the format of MMDDYYYY (2/5/19 would become 02052019)\n" +
				"├------------------------------------------------------------------------------------┤";
		System.out.println(message);
	};	//print help
	public void addrp(String brand, RepairLevel level, int price, int days) {
		priceTable.addRepairPrice(brand, level, price, days);
	};	//add repair price
	public void addc(String firstName, String lastName) {
		Customer c = new Customer(firstName, lastName);
		customers.add(c);
	};	//add customer
		public void addo(int customerNumber, Date date, String brand, RepairLevel level, String comment) {};	//add order
		public void comp(int orderNumber, Date completionDate) {};	//mark order completed
		//comp next would be useful extra feature
	public void printrp() {
		System.out.println(priceTable);
	};	//print repair prices
		public void printcnum() {};	//print customers by number
		public void printcname() {};	//print customers by name
		public void printo() {};	//print orders
		public void printp() {};	//print payments
		public void printt() {};	//print transactions
		public void printr() {};	//print receivables
		public void prints() {};	//print statements
		/**
		 * the actual save/load/read should be handled by IOHandler
		 * instead, getSavefile() passes to IOHandler the commands needed to restore the system
		 * in the form of a big string. The IOHandler takes that string and saves,
		 * and handles loading/reading like it would handle any input
		 */
	public String getSavefile() {
		String savefile = "";
		//customers first
		for (int i = 0; i < customers.size(); ++i) {
			savefile += customers.get(i).toCommand() + "\n";
		}
		//then pricetable
		savefile += priceTable.toCommand();
		//then transactions
		for (int i = 0; i < transactions.size(); ++i) {
			savefile += transactions.get(i).toCommand() + "\n";
		}
		return savefile;
	}
//	public void readc(String filename) {};	//read commands from file
//	public void savebs(String filename) {};	//save bike shop to file
//	public void restorebs(String filename) {};	//restore a previously saved bike shop
}
