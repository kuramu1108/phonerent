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
public class ShoppingCart implements Serializable {
    private int id;
    private Account account;
    private List<Phone> phones = new ArrayList<>();
    private List<SimPlan> simPlans = new ArrayList<>();

    public ShoppingCart(){
        
    }
    
    /**
     * @return the id
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "shoppingCart")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToMany(mappedBy = "shoppingCarts")
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @ManyToMany(mappedBy = "shoppingCarts")
    public List<SimPlan> getSimPlans() {
        return simPlans;
    }

    public void setSimPlans(List<SimPlan> simPlans) {
        this.simPlans = simPlans;
    }
}
