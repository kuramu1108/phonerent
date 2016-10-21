/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author TOSHIBA
 */
@Entity
public class CreditCard implements Serializable {
    private int id;
    private String cardNumber;
    private int cvv;
    private int ExpiryMonth;
    private int ExpiryYear;
    private Account owner;

    public CreditCard(){
        
    }
    
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    
        public int getExpiryMonth() {
        return ExpiryMonth;
    }

    public void setExpiryMonth(int ExpiryMonth) {
        this.ExpiryMonth = ExpiryMonth;
    }

    public int getExpiryYear() {
        return ExpiryYear;
    }

    public void setExpiryYear(int ExpiryYear) {
        this.ExpiryYear = ExpiryYear;
    }

    @OneToOne(mappedBy = "creditCard")
    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }


    
     
            
}
