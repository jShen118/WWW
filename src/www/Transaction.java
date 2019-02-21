/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class Transaction {
    Date date;
    Customer customer;
    //int customerID;
    
    Transaction(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
        //customerID = customer.ID;
    }
}