/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

/*
A Transaction contains a customer, a date, and the amount of money transacted.
It is specialized as a Payment (which doesn't add anything) or an Order (which adds quite a lot)
*/
public abstract class Transaction {
	public Date dateMade;	//the date that the transaction was made on
	public int customerNumber;	//who made the transaction
	public int amount;	//how much money was exchanged
	
	Transaction(Date date, int customerNumber, int amount) {
		this.dateMade = date;
		this.customerNumber = customerNumber;
		this.amount = amount;
	}
	
	abstract public String toCommand();
}