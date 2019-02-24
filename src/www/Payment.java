/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

/*A payment is identical to a generic Transaction except for the report*/
public class Payment extends Transaction {
	Payment(Date dateMade, int customerNumber, int amount) {
		super(dateMade, customerNumber, amount);
	}
	public String toString() {
		String toRet = "├----------[Payment]----------┤\n";
		String stringCustomer = Support.bufferSpace(Integer.toString(customerNumber), 17);
		toRet += "　Customer ID:" + stringCustomer + "\n";
		String stringPrice = Support.bufferSpace(Integer.toString(amount), 17);
		toRet += "　Amount paid:" + stringPrice + "\n";
		String stringDate = Support.bufferSpace(dateMade.readableToString(), 13);
		toRet += "　Date of Payment:" + stringDate + "\n";
		return toRet;
	}
	public String toCommand() {
		return "addp " + Integer.toString(customerNumber) + " " + dateMade.toString() + " " + Integer.toString(amount);
		//example: payment of $25 on 2/5/16 made by customer#24 would be "addp 24 02052016 25"
	}
}