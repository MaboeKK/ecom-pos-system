/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale.Model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author User
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sale {
    private int id;
    private int employee;
    private double amount;
    private int customer;
    private int store;
    private Timestamp saleDate;
    private int product;
    
}