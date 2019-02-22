/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class RepairPrice {
    public String brand;
    public RepairLevel level;
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
		String stringPrice = Support.bufferSpace(Integer.toString(price), 3);
		String stringDays = Support.bufferSpace(Integer.toString(daysToRepair), 3);
		return "Price:" + stringPrice + ", Days:" + stringDays;
		//example: "Price: 50, Days:  3"
	}
}