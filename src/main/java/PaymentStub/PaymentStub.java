/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaymentStub;

/**
 *
 * @author Tsheno
 */
public class PaymentStub {
    
    private double pay;
    
    public PaymentStub(double pay){
        this.pay = Math.random()*(2*pay);
    }
    
    public boolean Cash(double amount){
        return pay>amount;
    }
    
    public boolean Card(double amount){
        return (pay*1.4)>amount;
    }
}
