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
	public void addo(int customerNumber, Date date, String brand, RepairLevel level, String comment) {
		RepairPrice p = priceTable.getRepairPrice(brand, level);
		Order o = new Order(date, customerNumber, p, comment);	//Date date, int customerNumber, RepairPrice repairPrice, String comment)
		transactions.add(o);
	};	//add order
	public void addp(int customerNumber, Date date, int amount) {
		Payment p = new Payment(date, customerNumber, amount);
		transactions.add(p);
	}
		public void comp(int orderNumber, Date completionDate) {};	//mark order completed
		//"complete next" would be useful extra feature
	public void printrp() {
		System.out.println(priceTable);
	};	//print repair prices
	
	public void printcnum() { //prints customers by number
            String toPrint = "├-[Customers by number]--------------------------------------------------------------┤\n";
            for(Customer c: customers) { //no ordering for customerNumber needed because ArrayList customers' first element is customer with number 1, second element is customer with number 2, so on and so forth
                toPrint += customerInfo(c);
            } 
            toPrint += "├------------------------------------------------------------------------------------┤";
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
            toPrint += "├------------------------------------------------------------------------------------┤";
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
            toReturn += "┌[Date]--┬[Type]-----┬[Amount]┬[Status]---┐";
            for(Transaction t: customerTransactions) { //this for loop is for producing the table
                Date date = t.dateMade;
                String type;
                int amount = t.amount;
                String status;
                if(t instanceof Order) {
                    type = "Order";
                    if(((Order) t).isComplete()) {
                        status = "  complete ";
                    } else {status = "incomplete ";}
                } else {
                    type = "Payment";
                    status = "     -     ";
                }
                    
                String typeWithBuffer = Support.bufferSpace(type, 11);
                String amountWithBuffer = Support.bufferSpace(Integer.toString(amount), 8);
                toReturn += "\n│" + date + "--│" + typeWithBuffer + "│" + amountWithBuffer + "│" + status + "│";
            }
            toReturn += "\n└--------┴-----------┴--------┘\n\n\n";
            return toReturn;
        }
        
        public void printo() { //prints table of all orders, past and pending; does not order by date yet because date is not comparable
            String toPrint = "├[Orders]----------------------------------------------------------------------------┤";
            ArrayList<Order> orders = Support.orders((ArrayList<Transaction>) transactions);
            toPrint += "\n# of all orders: " + orders.size();
            toPrint += "\n# of incomplete orders: " + (orders.size() - Support.numComplete(orders));
            toPrint += "\nValue of all orders: $" + Support.ordersSum(orders);
            toPrint += "\n┌[Date Made]┬[Amount]┬[Status]---┬[Completion Date]┐";
            for(Order o: orders) { //fills in table with all orders
                Date dateMade = o.dateMade;
                int amount = o.amount;
                String status;
                Date completionDate = o.completionDate;
                if(o.isComplete()) {
                    status = "  complete ";
                } else {status = "incomplete ";}
                
                String amountWithBuffer = Support.bufferSpace(Integer.toString(amount), 8);
                toPrint += "\n│" + dateMade + "│" + amountWithBuffer + "│" + status + "│" + "    " + completionDate + "    │";
            }
            toPrint += "\n└-----------┴--------┴-----------┴----------------┘";
            toPrint += "├------------------------------------------------------------------------------------┤";
            System.out.println(toPrint);
        }
        public void printp() { //prints table of payments
            String toPrint = "├[Payments]--------------------------------------------------------------------------┤";
            ArrayList<Payment> payments = Support.payments((ArrayList<Transaction>) transactions);
            toPrint += "\n# of all payments: " + payments.size();
            toPrint += "\nValue of all payments: $" + Support.paymentsSum(payments);
            toPrint += "\n┌[Date]--┬[Amount]┬[Customer ID]┐";
            for(Payment p: payments) {
                Date date = p.dateMade;
                int amount = p.amount;
                int customerNumber = p.customerNumber;
                
                String amountWithBuffer = Support.bufferSpace(Integer.toString(amount), 8);
                String customerNumberWithBuffer = Support.bufferSpace(Integer.toString(customerNumber), 13);
                toPrint += "\n│" + date + "│" + amountWithBuffer + "│" + customerNumberWithBuffer + "│";
            }
            toPrint += "\n└--------┴--------┴-------------┘";
            toPrint += "├------------------------------------------------------------------------------------┤";
            System.out.println(toPrint);
        }
		public void printt() {};	//print transactions
		//receivables and statements can be calculated as they need to be printed, no need to store them
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
