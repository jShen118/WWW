/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

import java.util.*;

public class Support {
	//these three functions take a string and buffer it with a character until it is of length "length"
	//ie bufferSpace("test", 10) returns "	  test"
	static public String bufferSpace(String toBuffer, int length) {
		while (toBuffer.length() < length) {
			toBuffer = " " + toBuffer;
		}
		return toBuffer;
	}
	static public String bufferSpaceCentered(String toBuffer, int length) {
			double bufferLength = ((double) (length - toBuffer.length())) / 2;
			String buffer = "";
			if(bufferLength % 1 == 0) { //executes inside if bufferLength is not an integer
				return spaces((int) bufferLength) + toBuffer + spaces((int) bufferLength);
			}
			return spaces((int) bufferLength) + toBuffer + spaces(((int) bufferLength) + 1);
                        //returns (bufferLength - "1/2 space") + toBuffer + (bufferLength + "1/2 space")
        }
	static public String spaces(int length) {
		String toReturn = "";
		for(int n = 1; n <= length; n++) {
			toReturn = toReturn + " ";
		}
		return toReturn;
	}
	static public String bufferZero(String toBuffer, int length) {
		while (toBuffer.length() < length) {
			toBuffer = "0" + toBuffer;
		}
		return toBuffer;
	}
	static public String bufferCustom(String toBuffer, int length, String bufferWith) {
		while (toBuffer.length() < length) {
			toBuffer = bufferWith + toBuffer;
		}
		return toBuffer;
	}
	static public String[] splitStringIntoParts(String s) {	//splits string by space
		return s.split("\\s+");
	}
	static public String multilineComment(String comment, int buffer, int cutoffLength) {	//turns "Lorem ipsum dolor sit amet, consectetur adipiscing elit." into
		String mc = "";																//"Lorem ipsum dolor sit amet,
		String[] splitComment = Support.splitStringIntoParts(comment);				//consectetur adipiscing elit"
		int rowLength = 0;
		String stringBuffer = "\n　";
		for (int i = 0; i < buffer; ++i) {
			stringBuffer += " ";
		}
		for (int i = 0; i < splitComment.length; ++i) {
			rowLength += splitComment[i].length() + 1;
			mc += splitComment[i] + " ";
			if (rowLength >= cutoffLength) {
				rowLength = 0;
				mc += stringBuffer;
			}
		}
		return mc;
	}
	static public int min3(int a, int b, int c) {	//returns the smallest of 3 values
		return Math.min(a, Math.min(b, c));
	}
	//levenshtein distance calculates the distance between two strings. 
	//"kitten" and "mitten" would have a distance of 1 because of the 1 char difference.
	//I would love to say I wrote this myself based on the mathematical definition, but
	//this is just a direct translation of the C code on wikipedia
	static private int lev(String a, int sizea, String b, int sizeb) {
		int cost;
		if (sizea == 0) {
			return sizeb;
		}
		if (sizeb == 0) {
			return sizea;
		}
		if (a.charAt(sizea - 1) == b.charAt(sizeb - 1)) {
			cost = 0;
		} else {
			cost = 1;
		}
		return min3(
				lev(a, sizea - 1, b, sizeb		) + 1,
				lev(a, sizea	, b, sizeb - 1	) + 1,
				lev(a, sizea - 1, b, sizeb - 1	) + cost
			);
	}
	static public int levenshtein(String a, String b) {
		return lev(a, a.length(), b, b.length());
	}
	
	static public ArrayList<Transaction> customerTransactions(Customer c, ArrayList<Transaction> transactions) {
		ArrayList<Transaction> toReturn = new ArrayList<>();
		int number = c.customerNumber;
		for(Transaction t: transactions) {
			if(t.customerNumber == number) {
				toReturn.add(t);
			}
		}
		return toReturn;
	} //returns ArrayList of Transactions of one specific customer
	static public ArrayList<Order> orders(ArrayList<Transaction> transactions) {
		ArrayList<Order> toReturn = new ArrayList<>();
		for (Transaction t: transactions) {
			if(t instanceof Order) {
				Order o = (Order) t;
				toReturn.add(o);
			}
		}
		return toReturn;
	} // returns ArrayList of any orders found in ArrayList of Transaction
	static public ArrayList<Payment> payments(ArrayList<Transaction> transactions) {
		ArrayList<Payment> toReturn = new ArrayList<>();
		for (Transaction t: transactions) {
			if(t instanceof Payment) {
				Payment o = (Payment) t;
				toReturn.add(o);
			}
		}
		return toReturn;
	} // returns ArrayList of any payments found in ArrayList of Transaction
		
	static public int numComplete(ArrayList<Order> orders) {
		int toReturn = 0;
		for(Order o: orders) {
			if(o.isComplete()) {
				toReturn++;
			}
		}
		return toReturn;
	}
	static public int transactionsSum(ArrayList<Transaction> transactions) {
		int sum = 0;
		for(Transaction t: transactions) {
			if(t instanceof Order) {
				sum += t.amount;
			} else {
				sum -= t.amount;
			}
		}
		return sum;
	} //returns the sum (indicated debt) of transactions 
	
	static public int ordersSum(ArrayList<Order> orders) {
		int sum = 0;
		for(Order o: orders) {
			sum += o.amount;
		}
		return sum;
	} //returns the sum of amounts of orders
	static public int paymentsSum(ArrayList<Payment> payments) {
		int sum = 0;
		for(Payment p: payments) {
			sum += p.amount;
		}
		return sum;
	} //returns the sum of amounts of payments

	static public ArrayList<Transaction> tSortedByDate(ArrayList<Transaction> transactions) {
		ArrayList<Transaction> toReturn = new ArrayList<>();
                ArrayList<Date> dates = new ArrayList<>();
		HashMap<Date, Transaction> map = new HashMap<>();
                for(Transaction t: transactions) {
                    map.put(t.dateMade, t);
                    dates.add(t.dateMade);
                }
                Collections.sort(dates);
                Collections.reverse(dates);
                for(Date d: dates) {
                    toReturn.add(map.get(d));
                }
		return toReturn;
	} //returns transactions from latest to oldest
	static public ArrayList<Order> oSortedByDate(ArrayList<Order> orders) {
                ArrayList<Order> toReturn = new ArrayList<>();
                ArrayList<Date> dates = new ArrayList<>();
		HashMap<Date, Order> map = new HashMap<>();
                for(Order o: orders) {
                    map.put(o.dateMade, o);
                    dates.add(o.dateMade);
                }
                Collections.sort(dates);
                Collections.reverse(dates);
                for(Date d: dates) {
                    toReturn.add(map.get(d));
                }
		return toReturn;
	} //returns orders from latest to oldest
	static public ArrayList<Payment> pSortedByDate(ArrayList<Payment> payments) {
		ArrayList<Payment> toReturn = new ArrayList<>();
                ArrayList<Date> dates = new ArrayList<>();
		HashMap<Date, Payment> map = new HashMap<>();
                for(Payment p: payments) {
                    map.put(p.dateMade, p);
                    dates.add(p.dateMade);
                }
                Collections.sort(dates);
                Collections.reverse(dates);
                for(Date d: dates) {
                    toReturn.add(map.get(d));
                }
		return toReturn;
	} //returns payments from latest to oldest
}
