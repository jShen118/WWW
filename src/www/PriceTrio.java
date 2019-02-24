/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

//a wrapper class for three repair prices
public class PriceTrio {
	private RepairPrice prices[];
	PriceTrio() {
		prices = new RepairPrice[3];
	}
	public void setRepairPrice(RepairLevel level, RepairPrice price) {
		switch (level) {
			case silver:
				prices[0] = price;
				break;
			case gold:
				prices[1] = price;
				break;
			case platinum:
				prices[2] = price;
				break;
		}
	}
	public RepairPrice getRepairPrice(RepairLevel level) {
		switch (level) {
			case silver:
				return prices[0];
			case gold:
				return prices[1];
			case platinum:
				return prices[2];
		}
		return null;	//if there's some weird error
	}
	public String toString() {
		String string0 = "         -         ";
		if (prices[0] != null) {
			string0 = prices[0].toString();
		}
		String string1 = "         -         ";
		if (prices[1] != null) {
			string1 = prices[1].toString();
		}
		String string2 = "         -         ";
		if (prices[2] != null) {
			string2 = prices[2].toString();
		}
		return " " + string0 + " │ " + string1 + " │ " + string2 + " │";
	}
	public String toCommand() {
		//returns a multiline command for its RepairPrices
		String command = "";
		if (prices[0] != null) {
			command += prices[0].toCommand() + "\n";
		}
		if (prices[1] != null) {
			command += prices[1].toCommand() + "\n";
		}
		if (prices[2] != null) {
			command += prices[2].toCommand() + "\n";
		}
		return command;
	}
}
