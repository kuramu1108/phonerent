package au.com.phonerent.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;

/**
 * A class that stores the information about shopping cart
 * @author 12108573
 */
@Entity
public class ShoppingCart implements Serializable {
    private int id;
    private Account account;
    private List<Phone> phones = new ArrayList<>();
    private List<SimPlan> simPlans = new ArrayList<>();

    /**
     * Creates a new instance of shopping cart
     */
    public ShoppingCart(){
        
    }
    
    /**
     * to get or request for the id of a particular shopping cart
     * Id is being auto generated for every new instances of shopping cart
     * @return id must not be null and it is a primary key for shopping cart
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * to set new id into the shopping cart
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * to get or request for the account that is linked with the shopping cart
     * account is the owner of a particular shopping cart
     * OneToOne relationship is used because one chart can only be owned by a user
     * @return account can be null
     */
    @OneToOne(mappedBy = "shoppingCart", cascade=CascadeType.ALL)
    public Account getAccount() {
        return account;
    }

    /**
     * to set the account to a shopping cart
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * to get or request for the list of the phones that are linked with shopping cart
     * The list can be null. One phone can be selected and put in multiple shopping cart and 
     * One shopping cart can hold multiple phones.
     * Thus, many to many relationship is being used
     * @return phones can be null
     */
    @ManyToMany
    public List<Phone> getPhones() {
        return phones;
    }

    /**
     * to set the list of phone to the shopping cart
     * @param phones
     */
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    /**
     * to get or request for the list of the sim plan that are linked with shopping cart
     * The list can be null. One sim plan can be selected and put in multiple shopping cart and 
     * One shopping cart can hold multiple sim plan.
     * Thus, many to many relationship is being used
     * @return simPlans can be null
     */    
    @ManyToMany
    public List<SimPlan> getSimPlans() {
        return simPlans;
    }

    /**
     * to set the list of sim plan to the shopping cart
     * @param simPlans
     */
    public void setSimPlans(List<SimPlan> simPlans) {
        this.simPlans = simPlans;
    }
}
