/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class Customer {
    String firstName;
    public static int ID = 0;
    Transaction[] transactions;
    
    Customer(String name) {
        this.firstName = firstName;
        ID++;
    }
    
    public int debt() {
        int debt = 0;
        for(int i = 0; i < transactions.length; i++) {
            //ERROR HERE: I couldn't figure out how to parse a transaction into Payment or Order
            if (transactions[i] instanceof Payment) {
                debt = debt - ((Payment) transactions[i]).amount;
            }
            if (transactions[i] instanceof Order) {
                debt = debt + ((Order) transactions[i]).price;
            }
        }
        return debt;
    }
}