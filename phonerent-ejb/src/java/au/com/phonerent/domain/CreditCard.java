package au.com.phonerent.domain;

import java.io.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A class that stores the information about credit card
 * @author 12108573
 */
@Entity
public class CreditCard implements Serializable {
    private int id;
    private String cardNumber;
    private String nameOnCard;
    private String cvv;
    private int expiryMonth;
    private int expiryYear;
    private Account owner;

    /**
     * Creates a new instance of credit card
     */
    public CreditCard(){
        
    }
    
    /**
     * to get or request for the id of the credit card
     * Id is being auto generated for every new instances of credit card
     * @return id must not be null and it is a primary key for credit card
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * to set new id into the credit card
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * to set a card number into a particular credit card
     * @param cardNumber  
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * to get or request for the name on card of a credit card
     * name on card should be between 1 to 70 characters
     * @return nameOnCard must not be null 
     */    
    public String getNameOnCard() {
        return nameOnCard;
    }

    /**
     * to set name on card into a credit card
     * @param nameOnCard  
     */
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }    
    
    /**
     * to get or request for the cvv of a credit card
     * cvv number should be between 1 to 4 characters
     * @return cvv must not be null 
     */    
    public String getCvv() {
        return cvv;
    }

    /**
     * to set name on card into a credit card
     * @param nameOnCard  
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    /**
     * to get or request for the expiryMonth of a credit card
     * expiry month should be between 1 to 12, January to December
     * @return expiryMonth must not be null 
     */ 
    public int getExpiryMonth() {
        return expiryMonth;
    }

    /**
     * to set expiry month into a credit card
     * @param expiryMonth  
     */
    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    /**
     * to get or request for the expiry year of a credit card
     * expiry year should be 4 characters
     * expiry year must not be in the past meaning the number should be higher than current year
     * @return expiryYear must not be null 
     */ 
    public int getExpiryYear() {
        return expiryYear;
    }

    /**
     * to set expiry year into a credit card
     * @param expiryYear  
     */
    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    /**
     * to get or request for the owner of the credit card
     * OneToOne relationship is being used because every user is limited with one credit card
     * @return owner must not be null 
     */ 
    @OneToOne(mappedBy = "creditCard")
    public Account getOwner() {
        return owner;
    }

    /**
     * to set the account instance into a credit card
     * @param owner  
     */
    public void setOwner(Account owner) {
        this.owner = owner;
    } 
}