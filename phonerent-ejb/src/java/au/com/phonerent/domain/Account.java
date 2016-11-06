package au.com.phonerent.domain;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A class that stores the information about account
 * @author mac
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Account.findByEmail",
            query="select a from Account a where a.email = :email"),
    @NamedQuery(name="Account.findByPasswordResetId",
            query="select a from Account a where a.passwordResetId = :resetId")
})
public class Account implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
    private String accountType;
    private String deliveryAddress;
    private CreditCard creditCard;
    private ShoppingCart shoppingCart;
    
    private List<Purchase> purchases = new ArrayList<>();
    
    private boolean isActivate;
    private String passwordResetId;

    /**
     * Creates a new instance of account
     */
    public Account(){
        
    }
    
    /**
     * to get or request for the id of a particular account
     * Id is being auto generated for every new instances of account
     * @return id must not be null and it is a primary key for this class
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * to set new id into the account
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * to get or request for the first name of the account
     * first name must be between 1 and 50 characters
     * @return the firstName must not be null
     */
    @Size(min=1, max=50)
    public String getFirstName() {
        return firstName;
    }

    /**
     * to set the first name into a particular account
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    @Size(min=1, max=20)
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    @NotNull
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    @Pattern(regexp = "[a-zA-Z0-9\\.]+@[a-z]+(\\.[a-z]+)+")
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * to get or request for the list of orders made by an account
     * The list can be null. One account can issues several order at the same time
     * @return the orders
     */
    @OneToMany(mappedBy = "account", cascade=CascadeType.ALL)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * @param purchases the purchases to set
     */
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    /**
     * to get the accountType of a particular account
     * accountType must not be null.
     * There are two type of account 'Admins' or 'User'
     * @return accountType must not be null
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * to get the accountType of a particular account
     * accountType must not be null.
     * @return the orders
     */
    @Pattern(regexp = "[0-9]{8,14}")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    public boolean isIsActivate() {
        return isActivate;
    }

    public void setIsActivate(boolean isActivate) {
        this.isActivate = isActivate;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    public String getPasswordResetId() {
        return passwordResetId;
    }

    public void setPasswordResetId(String passwordResetId) {
        this.passwordResetId = passwordResetId;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    @OneToOne(cascade=CascadeType.ALL)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * to get or request for the the card number of a credit card
     * card number must be exactly 16 characters 
     * @return cardNumber must not be null 
     */
    @OneToOne(cascade=CascadeType.ALL)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
