/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class Order extends Transaction {
	static private int orderNumberDispenser	= 0;	//this is used to give orders unique orderNumbers
	public int orderNumber;	//the order's unique ID
	public RepairPrice repairPrice;
	public String comment;
	public Date completionDate;	//completion date is set to null until it is marked as completed
	
	Order(Date date, int customerNumber, RepairPrice repairPrice, String comment) {
		super(date, customerNumber, repairPrice.price);	//price is retrieved from repairPrice
		this.repairPrice = repairPrice;
		this.comment = comment;
		this.orderNumber = orderNumberDispenser++;	//set orderNumber to the next ID, then inrement the ID dispenser
		this.completionDate = null;
	}
	
	public Date promisedDate() {	//derive date when it will be ready from repairPrice.daysToRepair and dateMade
		return dateMade.future(repairPrice.daysToRepair);
	}
	public void complete(Date currentDate) {
		completionDate = currentDate;
	}
	public boolean isComplete() {
		return (completionDate != null);
	}
	
	private String multilineComment(String comment, int buffer, int cutoffLength) {	//turns "Lorem ipsum dolor sit amet, consectetur adipiscing elit." into
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
	public String toString() {	//display information by order of relevancy
		String toRet = "├-----------[Order]-----------┤\n";
		String stringOrder = Support.bufferSpace(Integer.toString(orderNumber), 20);
		toRet += "　Order ID:" + stringOrder + "\n";

		String stringDate3 = (completionDate == null ? "-" : completionDate.readableToString());
		stringDate3 = Support.bufferSpace(stringDate3, 19);
		toRet += "　Completed:" + stringDate3 + "\n";
		
		String stringDate = Support.bufferSpace(dateMade.readableToString(), 17);
		toRet += "　Date Placed:" + stringDate + "\n";
		
		String stringDate2 = Support.bufferSpace(promisedDate().readableToString(), 15);
		toRet += "　Date Promised:" + stringDate2 + "\n";
		
		String stringCustomer = Support.bufferSpace(Integer.toString(customerNumber), 17);
		toRet += "　Customer ID:" + stringCustomer + "\n";
		
		String stringBrand = Support.bufferSpace(repairPrice.brand, 18);
		toRet += "　Bike Brand:" + stringBrand + "\n";
		
		String stringLevel = Support.bufferSpace(repairPrice.level.toString(), 16);
		toRet += "　Repair Level:" + stringLevel + "\n";
		
		String stringPrice = Support.bufferSpace("$" + Integer.toString(amount), 18);
		toRet += "　Amount due:" + stringPrice + "\n";
		
		String stringComment = multilineComment(comment, 9, 15);
		toRet += "　Comment: " + stringComment + "\n";
		return toRet;
	}
}