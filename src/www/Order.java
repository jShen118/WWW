/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class Order extends Transaction {
    public String brand;
    public RepairLevel level;
    public int price;
    //public Date promiseDate() {return super.date}
    public Date completionDate = null;
    public String comment;
    
    Order(Date date, Customer customer, String brand, RepairLevel level, int price, String comment) {
		super(date, customer);
        this.brand = brand;
        this.level = level;
        this.price = price;
        this.comment = comment;
    }
    
    public void resolve(Date currentDate) {
        completionDate = currentDate;
    }
    
    public boolean isComplete() {return (completionDate != null);}
}