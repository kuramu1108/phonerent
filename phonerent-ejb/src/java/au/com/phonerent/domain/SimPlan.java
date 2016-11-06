package au.com.phonerent.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A class that stores the information about sim plan
 * @author mac
 */
@Entity
public class SimPlan implements Serializable{
    private int id;
    private String name;
    private double price;
    private double credit;
    private int bonusSMS;
    private String phoneNumber;
    private List<Purchase> purchases = new ArrayList<>();
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    /**
     * Creates a new instance of sim plan
     */
    public SimPlan(){
        
    }
    
    /**
     * to get or request for the id of a particular sim plan
     * Id is being auto generated for every new instances of sim plan
     * @return id must not be null and it is a primary key for sim plan
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * to set new id into the sim plan
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * name shows the sim provider name for a particular plan.
     * name must not be null
     * name should be at least one characters and 
     * should not be more than 255 characters
     * @return the name
     */
    @Column(name="simName")
    @Size(min=1, max=255)
    public String getName() {
        return name;
    }

    /**
     * to set name to the sim plan
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * to get the price of a particular sim plan.
     * price must not be null and should be between 1 to 100
     * @return the price
     */
    @NotNull
    @DecimalMax("100.0") @DecimalMin("1.0") 
    public double getPrice() {
        return price;
    }

    /**
     * to set price for a sim plan
     * @param price 
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * credit shows the amount of phone credit that are given to customers.
     * This credit will be used when they made a phone call or sending text.
     * 
     * credit must not be null
     * credit must be between 10 to 500
     * @return the credit
     */
    @NotNull
    @DecimalMax("500.0") @DecimalMin("10.0") 
    public double getCredit() {
        return credit;
    }

    /**
     * to set credit for a sim plan
     * @param credit 
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * bonusSMS shows the incentives that will be received by customers if they 
     * choose to purchase for that SIM plan.
     * 
     * bonusSMS can be null
     * bonusSMS must be between 1 to 100
     * @return the endDate
     */
    @NotNull
    @Min(1)
    @Max(100)
    public int getBonusSMS() {
        return bonusSMS;
    }

    /**
     * to set bonus sms for a sim plan
     * @param bonusSMS 
     */
    public void setBonusSMS(int bonusSMS) {
        this.bonusSMS = bonusSMS;
    }

    /**
     * to get or request for the list of the purchases that are linked with sim plan
     * The list can be null. One sim plan can be bought in multiple purchases. 
     * One purchase can include with sim plan.
     * Thus, many to many relationship is being used
     * @return purchases can be null
     */
    @ManyToMany(mappedBy = "simPlans")
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * to set new a list of purchase into the sim plan
     * @param purchases 
     */
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    /**
     * to get or request for the list of the shopping Cart that are linked with the sim plan
     * One sim plan can be put in multiple shopping carts and
     * One shopping cart can hold multiple sim plan.
     * many to many relationship is being used because of this
     * @return shoppingCarts can be null
     */
    @ManyToMany(mappedBy = "simPlans")
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    /**
     * to set new a list of shopping chart into the sim plan
     * @param shoppingCarts 
     */
    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    /**
     * to get the phone number from the sim plan
     * phone number must contain from 8 to 14 digits
     * @return phoneNumber must not be null
     */
    @NotNull
    @Pattern(regexp = "[0-9]{8,14}")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * to set phone number for a particular sim plan
     * @param phoneNumber 
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
