package au.com.phonerent.domain;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author mac
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Account.findByEmail",
            query="select a from Account a where a.email = :email")
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
    
    private List<Purchase> purchases = new ArrayList<>();
    
    private boolean isActivate;
    private boolean isPasswordReset;

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
    @OneToMany(mappedBy = "account")
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

    public boolean isIsPasswordReset() {
        return isPasswordReset;
    }

    public void setIsPasswordReset(boolean isPasswordReset) {
        this.isPasswordReset = isPasswordReset;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @OneToOne
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
