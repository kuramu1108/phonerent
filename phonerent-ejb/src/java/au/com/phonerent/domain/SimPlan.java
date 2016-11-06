/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author mac
 */
@Entity
public class SimPlan implements Serializable{
    private int id;
    private String name;
    private double price;
    private double credit;
    private int bonusSMS;
    private List<Purchase> purchases = new ArrayList<>();
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public SimPlan(){
        
    }
    
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * name shows the displayed name of a particular plan to customers.
     * name helps customer to remember the SIM plan and enable them to refers it
     * by name
     * 
     * name must not be null
     * @return the name
     */
    @Column(name="simName")
    @Size(min=1, max=255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @DecimalMax("100.0") @DecimalMin("1.0") 
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * credit shows the amount of phone credit that are given to customers.
     * This credit will be used when they made a phone call or sending text.
     * 
     * credit must not be null
     * @return the credit
     */
    @NotNull
    @DecimalMax("500.0") @DecimalMin("10.0") 
    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * bonusSMS shows the incentives that will be received by customers if they 
     * choose to purchase for that SIM plan.
     * 
     * bonusSMS can be null
     * @return the endDate
     */
    @NotNull
    @Min(1)
    @Max(100)
    public int getBonusSMS() {
        return bonusSMS;
    }

    public void setBonusSMS(int bonusSMS) {
        this.bonusSMS = bonusSMS;
    }

    @ManyToMany(mappedBy = "simPlans", cascade=CascadeType.ALL)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @ManyToMany(mappedBy = "simPlans", cascade=CascadeType.ALL)
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
    
}
