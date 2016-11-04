package au.com.phonerent.domain;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
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
    private Date dob;
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

    public Account(){
        
    }
    
    /**
     * @return the id
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dob
     */
    
    @Temporal(TemporalType.DATE)
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the firstName
     */
    @Size(min=1)
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    @Size(min=1)
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
     * @return the password
     */
    @Size(min=6)
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
     * @return the email
     */
    @Pattern(regexp = "[a-z0-9\\.]+@[a-z]+(\\.[a-z]+)+")
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
     * @return the orders
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
    @Size(min=4, max=12)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIsActivate() {
        return isActivate;
    }

    public void setIsActivate(boolean isActivate) {
        this.isActivate = isActivate;
    }

    public String getPasswordResetId() {
        return passwordResetId;
    }

    public void setPasswordResetId(String passwordResetId) {
        this.passwordResetId = passwordResetId;
    }

    @Size(min=3)
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @OneToOne(cascade=CascadeType.ALL)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @OneToOne(cascade=CascadeType.ALL)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
