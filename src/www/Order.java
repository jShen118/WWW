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
	
	public String report() {
		return "not yet";
	}
}