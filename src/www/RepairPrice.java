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
}