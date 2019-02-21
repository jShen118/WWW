/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

/*A payment is identical to a generic Transaction except for the report*/
public class Payment extends Transaction {
	public Payment(Date dateMade, int customerNumber, int amount) {
		super(dateMade, customerNumber, amount);
	}
	public String report() {
		return "not yet";
	}
}