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
	
	public boolean doesTableContainBrand(String brand) {
		return priceTable.containsBrand(brand);
	}
	public boolean doesBrandHaveLevel(String brand, RepairLevel level) {
		return priceTable.containsBrandLevelPair(brand, level);
	}
	public Customer getCustomer(int customerNumber) {
		for (int i = 0; i < customers.size(); ++i) {
			if (customers.get(i).customerNumber == customerNumber) {
				return customers.get(i);
			}
		}
		return null;
	}
	//this returns all brands
	public ArrayList<String> getAllBrands() {
		return priceTable.getAllBrands();
	}
	
	/*------------------commands-------------------------*/
	//indented functions are not yet implemented
	//checking that input is valid (for example, brand exists when making order) should be the job of IOHandler
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
				"　　prints <statement>" + Support.bufferCustom("print statements with optional message of <statement>\n", 82 - "prints <statement>".length(), ".") +
				"　　printworr" + Support.bufferCustom("prints weekly order revenue report\n", 82 - "printworr".length(), ".") +
				"　　printmorr" + Support.bufferCustom("print monthly order revenue report\n", 82 - "printmorr".length(), ".") +
				"　　readc <filename>" + Support.bufferCustom("read commands from file with name <filename>\n", 82 - "readc <filename>".length(), ".") +
				"　　savebs <filename>" + Support.bufferCustom("save bike shop to file <filename>\n", 82 - "savebs <filename>".length(), ".") +
				"　　restorebs <filename>" + Support.bufferCustom("restore bike shop from save file <filename>\n", 82 - "restorebs <filename>".length(), ".") +
				"　　restorebss <filename>" + Support.bufferCustom("silently restore bike shop from save file <filename>\n", 82 - "restorebss <filename>".length(), ".") +
				"\n　*All dates must be entered in the format of MMDDYYYY (2/5/19 would become 02052019)\n" +
				"├------------------------------------------------------------------------------------┤";
		System.out.println(message);
	};	//print help
	public void addrp(String brand, RepairLevel level, int price, int days, boolean silent) {
		priceTable.addRepairPrice(brand, level, price, days);
		if (!silent) {
			System.out.println(priceTable);
		}
	};	//add repair price
	public void addc(String firstName, String lastName) {
		Customer c = new Customer(firstName, lastName);
		System.out.println("→added customer " + c);
		customers.add(c);
	};	//add customer
	public void addo(int customerNumber, Date date, String brand, RepairLevel level, String comment, boolean silent) {
		RepairPrice p = priceTable.getRepairPrice(brand, level);
		Order o = new Order(date, customerNumber, p, comment);	//Date date, int customerNumber, RepairPrice repairPrice, String comment)
		if (!silent) {
			System.out.println(o);
		}
		transactions.add(o);
	};	//add order
	public void addp(int customerNumber, Date date, int amount) {
		Payment p = new Payment(date, customerNumber, amount);
		transactions.add(p);
	}
	public void comp(int orderNumber, Date completionDate) {
		for (Transaction t: transactions) {
			if (t instanceof Order) {
				Order o = (Order)t;
				if (o.orderNumber == orderNumber) {
					o.complete(completionDate);
					System.out.println("→Completed order " + Integer.toString(orderNumber));
					return;
				}
			}
		}
		System.out.println("»No order of number " + Integer.toString(orderNumber));
	};	//mark order completed
	//"complete next" would be useful extra feature
	public void printrp() {
		System.out.println(priceTable);
	};	//print repair prices
	
	public void printcnum() { //prints customers by number
		String toPrint = "├-[Customers by number]--------------------------------------------------------------┤\n";
		for(Customer c: customers) { //no ordering for customerNumber needed because ArrayList customers' first element is customer with number 1, second element is customer with number 2, so on and so forth
			toPrint += customerInfo(c);
		} 
		toPrint += "\n├------------------------------------------------------------------------------------┤";
		System.out.println(toPrint);
	}
	public void printcname() {//print customers by name
		String toPrint = "├-[Customers by alphabetical order of last names]------------------------------------┤\n";
		ArrayList<String> lastNames = new ArrayList<>();
		HashMap<String, Customer> map = new HashMap<>();
		for(Customer c: customers) {
			lastNames.add(c.lastName);
			map.put(c.lastName, c);
		}
		Collections.sort(lastNames);
		
		ArrayList<Customer> customersByLastName = new ArrayList<>();
		for(String ln: lastNames) { //fills new Customer Array List but in order by last name
			customersByLastName.add(map.get(ln));
		}
		
		for(Customer c: customersByLastName) {
			toPrint += customerInfo(c);
		}
		toPrint += "\n├------------------------------------------------------------------------------------┤";
		System.out.println(toPrint);
	};
	
	public String customerInfo(Customer c) { //helper for cnum and cname, returns all relevant info about a customer including number of orders, total value of orders, debt to the company, and a table of all their transactions
		ArrayList<Transaction> customerTransactions;
		ArrayList<Order> customerOrders;
		ArrayList<Payment> customerPayments;
		String toReturn = c.toString() + ":\n";
		customerTransactions = Support.customerTransactions(c, (ArrayList<Transaction>) transactions);
		customerOrders = Support.orders(customerTransactions);
		customerPayments = Support.payments(customerTransactions);
		toReturn += "# of orders: " +  customerOrders.size();
		toReturn += "\ntotal value of orders: $" + Support.ordersSum(customerOrders);
		toReturn += "\ntotal value of payments: $" + Support.paymentsSum(customerPayments);
		toReturn += "\ndebt to company: $" + Support.transactionsSum(customerTransactions) + "\n\n";
		toReturn += c.firstName + " " + c.lastName + "'s Transactions:\n";
		toReturn += "┌[Date]----┬[Type]-----┬[Amount]┬[Status]---┐"; // │8│11│8│11│
		customerTransactions = Support.tSortedByDate(customerTransactions);
		for(Transaction t: customerTransactions) { //this for loop is for producing the table
			Date date = t.dateMade;
			String type;
			int amount = t.amount;
			String status;
			if(t instanceof Order) { //initializes type and status
				type = "Order";
				if(((Order) t).isComplete()) {
					status = "complete ";
				} else {status = "incomplete";}
			} 
			else {
				type = "Payment";
				status = "-";
			}
			
			//date does not need buffer since it is always 10 characters long, the width of the column
			String typeWithBuffer = Support.bufferSpaceCentered(type, 11);
			String amountWithBuffer = Support.bufferSpaceCentered(Integer.toString(amount), 8);
			String statusWithBuffer = Support.bufferSpaceCentered("-", 11);
			toReturn += "\n│" + date.readableToString() + "│" + typeWithBuffer + "│" + amountWithBuffer + "│" + statusWithBuffer + "│";
		}
		toReturn += "\n└----------┴-----------┴--------┴-----------┘\n\n\n";
		return toReturn;
	}
		
	public void printo() { //prints table of all orders (and individual reports), past and pending; does not order by date yet because date is not comparable
		String toPrint = "├[Orders]-----------------------------------------------------------------------------┤";
		ArrayList<Order> orders = Support.orders((ArrayList<Transaction>) transactions);
		toPrint += "\n# of all orders: " + orders.size();
		toPrint += "\n# of incomplete orders: " + (orders.size() - Support.numComplete(orders));
		toPrint += "\nValue of all orders: $" + Support.ordersSum(orders);
		toPrint += "\n\nTable of all orders:";
		toPrint += "\n*to see more specifics about an order scroll down to individual order reports";
		toPrint += "\n┌[Order ID]┬[Date Made]┬[Amount]┬[Customer ID]┬[Status]---┬[Completion Date]┐"; // │10│11│8│13│11│17│
		orders = Support.oSortedByDate(orders);
		for(Order o: orders) { //this for loop is for producing the table
			int orderID = o.orderNumber;
			Date dateMade = o.dateMade;
			int amount = o.amount;
			int customerID = o.customerNumber;
			String status;
			String completionDate = "";
			if(o.isComplete()) {
				status = "complete";
				completionDate = o.completionDate.toString();
			} else {
				status = "incomplete";
				completionDate = "-";
			}
			
			String orderIDWithBuffer = Support.bufferSpaceCentered(Integer.toString(orderID), 10);
			String dateMadeWithBuffer = Support.bufferSpaceCentered(dateMade.readableToString(), 11);
			String amountWithBuffer = Support.bufferSpaceCentered(Integer.toString(amount), 8);
			String customerIDWithBuffer = Support.bufferSpaceCentered(Integer.toString(customerID), 13);
			String statusWithBuffer = Support.bufferSpaceCentered(status, 11);
			String completionDateWithBuffer = Support.bufferSpaceCentered(completionDate.toString(), 17);
			toPrint += "\n│" + orderIDWithBuffer + "│" + dateMadeWithBuffer + "│" + amountWithBuffer + "│" + customerIDWithBuffer + "│" + statusWithBuffer + "│" + completionDateWithBuffer + "│";
		}
		toPrint += "\n└----------┴-----------┴--------┴-------------┴-----------┴-----------------┘";//end of table construction
		toPrint += "\n\nIndividual order Reports:";
		for(Order o: orders) { //this for loop simply adds all order toString()s to toPrint. Expands on information in the table
			toPrint += "\n" + o.toString();
		}
		toPrint += "\n\n├------------------------------------------------------------------------------------┤";
		System.out.println(toPrint);
	}
	public void printp() { //prints table of payments and each individual report
		String toPrint = "├[Payments]--------------------------------------------------------------------------┤";
		ArrayList<Payment> payments = Support.payments((ArrayList<Transaction>) transactions);
		toPrint += "\n# of all payments: " + payments.size();
		toPrint += "\nValue of all payments: $" + Support.paymentsSum(payments);
		toPrint += "\n\nTable of all payments:";
		toPrint += "\n┌[Date]----┬[Amount]┬[Customer ID]┐"; // │8│8│13│
		payments = Support.pSortedByDate(payments); //this for loop is for producing the table
		for(Payment p: payments) { 
			Date date = p.dateMade;
			int amount = p.amount;
			int customerNumber = p.customerNumber;
			
			//date does not need buffer since the date column is 10 spaces wide and date is always 8 digits
			String amountWithBuffer = Support.bufferSpaceCentered(Integer.toString(amount), 8);
			String customerNumberWithBuffer = Support.bufferSpaceCentered(Integer.toString(customerNumber), 13);
			toPrint += "\n│" + date.readableToString() + "│" + amountWithBuffer + "│" + customerNumberWithBuffer + "│";
		}
		toPrint += "\n└----------┴--------┴-------------┘";//end of producing table
		toPrint += "\n\nIndividual payment reports:";
		for(Payment p: payments) { //this for loop simply adds all payment toString()s to toPrint. Kind of irrelevent because of table
			toPrint += "\n" + p.toString();
		}
		toPrint += "\n\n\n├------------------------------------------------------------------------------------┤";
		System.out.println(toPrint);
	}
	public void printt() {
		String toPrint = "├[Transactions]----------------------------------------------------------------------┤";
		toPrint += "\n# of all transactions: " + transactions.size();
		toPrint += "\nTotal debt to the tune-up business: $" + Support.transactionsSum((ArrayList<Transaction>) transactions);
		toPrint += "\n\nTable of all transactions:";
		toPrint += "\n┌[Date Made]┬[Type]-----┬[Amount]┬[Status]--┬[Customer ID]┐";// │11│11│8│11│13│
		for(Transaction t: transactions) { //this for loop is for producing the table
			Date date = t.dateMade;
			String type;
			int amount = t.amount;
			String status;
			int customerID = t.customerNumber;
			if(t instanceof Order) { //initializes type and status
				type = "Order";
				if(((Order) t).isComplete()) {
					status = " complete ";
				} else {status = "incomplete";}
			}
			else {
				type = "Payment";
				status = "    -     ";
			}
			
			String dateWithBuffer = Support.bufferSpaceCentered(date.readableToString(), 11);
			String typeWithBuffer = Support.bufferSpaceCentered(type, 11);
			String amountWithBuffer = Support.bufferSpaceCentered(Integer.toString(amount), 8);
			String statusWithBuffer = Support.bufferSpaceCentered(status, 12);
			String customerIDWithBuffer = Support.bufferSpaceCentered(Integer.toString(customerID), 13);
			toPrint += "\n│" + dateWithBuffer + "│" + typeWithBuffer + "│" + amountWithBuffer + "│" + status + "│" + customerIDWithBuffer + "│";
		}
		toPrint += "\n└-----------┴-----------┴--------┴----------┴-------------┘";//end of table construction
		toPrint += "\n\n\n├------------------------------------------------------------------------------------┤";
		System.out.println(toPrint);
	};	//print transactions
	public void printr() {
		int longestName = 6;
	   	String toPrint = "├[Accounts Receivables]--------------------------------------------------------------┤";
		ArrayList<Customer> customersWithDebt = new ArrayList<>();
		HashMap<Customer, Integer> map = new HashMap<>(); //keeps track of customer and corresponding debt
		for(Customer c: customers) { // this for loop finds customers with debt
			ArrayList<Transaction> customerTransactions = Support.customerTransactions(c, (ArrayList<Transaction>) transactions);
			int customerDebt = Support.transactionsSum(customerTransactions);
			if(customerDebt > 0) {
				customersWithDebt.add(c);
				if ((" " + c.firstName + " " + c.lastName + " ").length() > longestName) {
					longestName = (" " + c.firstName + " " + c.lastName + " ").length();
				}
				map.put(c, customerDebt);
			}
		}
		
		String nameHeader = "┌[Name]";
		for (int i = 7; i <= longestName; ++i) {
			nameHeader+="-";
		}
		toPrint += "\n" + nameHeader + "┬-[ID]-┬[Debt to Company]┐";// │18│6│17│
		for(Customer c: customersWithDebt) { //start of table filling
			String name = " " + c.firstName + " " + c.lastName + " ";
			int customerID = c.customerNumber;
			int debt = map.get(c);
			
			String nameWithBuffer = Support.bufferSpaceCentered(name, longestName);
			String customerIDWithBuffer = Support.bufferSpaceCentered(Integer.toString(customerID), 6);
			String debtWithBuffer = Support.bufferSpaceCentered(Integer.toString(debt), 17);
			toPrint += "\n│" + nameWithBuffer + "│" + customerIDWithBuffer + "│" + debtWithBuffer + "│";
		}
		toPrint += "\n└" + Support.bufferCustom("", longestName, "-") + "┴------┴-----------------┘";// end of table construction
		toPrint += "\n├------------------------------------------------------------------------------------┤";
		System.out.println(toPrint);
	};
	public void prints(String message) {
		ArrayList<Customer> customersWithDebt = new ArrayList<>();
		for(Customer c: customers) { // this for loop finds customers with debt
			ArrayList<Transaction> customerTransactions = Support.customerTransactions(c, (ArrayList<Transaction>) transactions);
			int customerDebt = Support.transactionsSum(customerTransactions);
			if(customerDebt > 0) {
				customersWithDebt.add(c);
			}
		}
		//loop through customers with debt and total up their debt
		for (Customer c: customersWithDebt) {
			String capitalizedLN = c.lastName.substring(0, 1).toUpperCase() + c.lastName.substring(1);
			String statement = "Dear Mr./Ms. " + capitalizedLN + ",\n" + message + "\n\n";
			ArrayList<Transaction> customerTransactions = Support.customerTransactions(c, (ArrayList<Transaction>) transactions);
			int grandTotal = 0;
			for (Transaction t: customerTransactions) {
				String transactionDesc = "";
				if (t instanceof Order) {
					Order o = (Order)t;
					transactionDesc = "[" + o.dateMade.readableToString() + "] Order of " + o.repairPrice.brand + " brand bike (service level " + o.repairPrice.level.toString() + ")";
					transactionDesc = transactionDesc + Support.bufferCustom(Support.bufferZero(Integer.toString(o.amount), 5), 86 - transactionDesc.length(), ".") + "\n";
					grandTotal += o.amount;
				} else {
					Payment p = (Payment) t;
					transactionDesc = "[" + p.dateMade.readableToString() + "] Payment";
					transactionDesc = transactionDesc + Support.bufferCustom("-" + Support.bufferZero(Integer.toString(p.amount), 5), 66, ".") + "\n";
					grandTotal -= p.amount;
				}
				statement+=transactionDesc;
			}
			statement+=Support.bufferCustom("", 86, "-") + "\n";
			statement+="GRAND TOTAL:" + Support.bufferSpace(Support.bufferZero(Integer.toString(grandTotal), 5), 74) + "\n\n";
			System.out.println(statement);
		}
	};	//print statements
	
	public void printworr() {
            String toPrint = Support.weeklyOrderRevenueReport(Support.orders((ArrayList<Transaction>) transactions));
            System.out.println(toPrint);
        }
	
	public void printmorr() {
            String toPrint = Support.monthlyOrderRevenueReport(Support.orders((ArrayList<Transaction>) transactions));
            System.out.println(toPrint);
        }
	
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
