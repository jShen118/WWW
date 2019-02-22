/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class RepairPrice {
    private String brand;
    private RepairLevel level;
    public int price;
    public int daysToRepair;
    
    RepairPrice(String brand, RepairLevel level, int price, int daysToRepair) {
        this.brand = brand;
        this.level = level;
        this.price = price;
        this.daysToRepair = daysToRepair;     
    }
    
    public void newPrice(int price) {
        this.price = price;
    }
    
    public void newRepairTime(int daysToRepair) {
        this.daysToRepair = daysToRepair;
    }
	
	public String toString() {
		//values are padded to ensure each repairPrice is the same size (19 characters)
		//this allows it to be nicely tiled in a grid
		String stringPrice = Integer.toString(price);
		while (stringPrice.length() < 3) {
			stringPrice = " " + stringPrice;
		}
		String stringDays = Integer.toString(daysToRepair);
		while (stringDays.length() < 3) {
			stringDays = " " + stringDays;
		}
		return "Price:" + stringPrice + ", Days:" + stringDays;
		//example: "Price: 50, Days:  3"
	}
}