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
     * @return id must not be null and it is a primary key for account
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
     * first name must be between 1 and 20 characters
     * @return the firstName must not be null
     */
    @Size(min=1, max=20)
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
     * to get or request for the last name of the account
     * last name must be between 1 and 20 characters
     * @return lastName must not be null
     */
    @Size(min=1, max=20)
    public String getLastName() {
        return lastName;
    }

    /**
     * to set the last name into a particular account
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * to get or request for the password of an account
     * password must not be null
     * @return password
     */
    @NotNull
    public String getPassword() {
        return password;
    }

    /**
     * to set the password into an account
     * @param password 
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * to get the email of an account
     * email must has one @ character inside it 
     * Regex pattern has been applied in order to ensure that the entered email
     * has at least a proper format
     * @return email must not be null 
     */
    @Pattern(regexp = "[a-zA-Z0-9\\.]+@[a-z]+(\\.[a-z]+)+")
    public String getEmail() {
        return email;
    }

    /**
     * to set the email into a particular account
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * to get or request for the list of purchases made by this account
     * The list can be null. One account can issues several purchase at the same time
     * Hence, it is a one to many relationship between those two variables
     * @return purchases can be null
     */
    @OneToMany(mappedBy = "account", cascade=CascadeType.ALL)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * to set a list of purchases into this account
     * @param purchases
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
     * To set an account type into the account
     * @param accountType 
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * to get the phone number from an account
     * phone number must contain from 8 to 14 digits
     * @return phoneNumber must not be null
     */
    @Pattern(regexp = "[0-9]{8,14}")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * to set phone number to the account
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * to get or request for the status of an account
     * whether it is activate or not activate yet
     * @return isActivate can be null
    */
    public boolean isIsActivate() {
        return isActivate;
    }

    /**
     * to set the status to an account
     * @param isActivate
     */
    public void setIsActivate(boolean isActivate) {
        this.isActivate = isActivate;
    }

    /**
     * to get or request for the passwordResetID of an account
     * passwordResetID is being used in order to allow user to reset their password
     * in case they forget their password
     * @return passwordResetId can be null 
     */
    public String getPasswordResetId() {
        return passwordResetId;
    }

    /**
     * to set the passwordResetId to an account
     * @param passwordResetId
     */
    public void setPasswordResetId(String passwordResetId) {
        this.passwordResetId = passwordResetId;
    }

    /**
     * to get or request for the delivery address of an account
     * This is the address where the product is being sent to
     * deliveryAddress must not be null when purchase is going to be made
     * @return deliveryAddress 
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * to set the deliveryAddress to an account
     * @param deliveryAddress
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    /**
     * to get or request for the credit card that is linked with the account
     * OneToOne relationship is being used because one user can only have one credit card details
     * @return creditCard 
     */
    @OneToOne
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * to set the creditCard to an account
     * @param creditCard
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }


    /**
     * to get or request for the shopping cart that is linked with the account
     * OneToOne relationship is used because one user can only have one cart
     * @return shoppingCart can be null
     */
    @OneToOne
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /**
     * to set the shoppingCart to an account
     * @param shoppingCart
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
