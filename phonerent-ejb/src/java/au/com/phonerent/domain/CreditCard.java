/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain;

import java.io.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author TOSHIBA
 */
@Entity
public class CreditCard implements Serializable {
    private int id;
    private String cardNumber;
    private String nameOnCard;
    private int cvv;
    private int expiryMonth;
    private int expiryYear;
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

    @Size(min=16, max=16)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Size(min=1, max=70)
    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }    
    
    @Min(1)
    @Max(999)
    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    
    @Min(1)
    @Max(12)
    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    @Min(2016)
    @Size(min=4, max=4)
    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    @OneToOne(mappedBy = "creditCard", cascade=CascadeType.ALL)
    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    } 
}
