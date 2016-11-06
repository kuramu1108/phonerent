package au.com.phonerent.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A class that stores the information about purchase
 * @author 12108573
 */
@Entity
public class Purchase implements Serializable {
    private int id;
    private Date startDate;
    private Date endDate;
    private double total;
    private String status;
    private Account account;
    private List<Phone> phones = new ArrayList<>();
    private List<SimPlan> simPlans = new ArrayList<>();

    /**
     * Creates a new instance of purchase
     */
    public Purchase(){
        
    }
    
    /**
     * to get or request for the id of a particular purchase
     * Id is being auto generated for every new instances of purchase
     * @return id must not be null and it is a primary key for purchase
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * to set new id into the purchase
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * startDate shows the date when account issued the purchase.
     * must not be null
     * @return the startDate
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the startDate for a particular purchase.
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * endDate shows the expired date of the renting period.
     * customer/account can choose to extends their contract. 
     * 
     * 3 days before the endDate, system will send SMS to customers in order
     * to remind about the expired date
     * 
     * endDate must not be null
     * @return the endDate
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the endDate for a particular purchase
     * @param endDate 
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * total shows the total amount of money that need to be paid for a
     * particular purchase session.
     * the price must be at least more than 1 dollar
     * must not be null
     * @return the total
     */
    @DecimalMin("1.0")
    public double getTotal() {
        return total;
    }

    /**
     * to set the total price of a purchase
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * status displays the condition of the order whether the purchases have been fulfilled
     * status must be at least 3 characters long
     * @return the status
     */
    @Size(min=3)
    public String getStatus() {
        return status;
    }

    /**
     * to set the status of the purchase
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * to get or request for the list of the phones that are linked with purchase
     * The list can be null. One phone can be bought in multiple purchases. 
     * One purchase can include with multiple phones.
     * Thus, many to many relationship is being used
     * @return phones can be null
     */
    @ManyToMany
    public List<Phone> getPhones() {
        return phones;
    }

    /**
     * to set the list of phone to a particular purchase
     * @param phones 
     */
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    /**
     * to get or request for the list of the sim plan that are linked with purchase
     * The list can be null. One sim plan can be bought in multiple purchases. 
     * One purchase can include with multiple sim plans.
     * Thus, many to many relationship is being used
     * @return phones can be null
     */
    @ManyToMany
    public List<SimPlan> getSimPlans() {
        return simPlans;
    }

    /**
     * to set the list of sim plan to the purchase
     * @param simPlans 
     */ 
    public void setSimPlans(List<SimPlan> simPlans) {
        this.simPlans = simPlans;
    }   
    
    /**
     * to get or request for the account of the purchase
     * account in here symbolize as the customer who made the purchase
     * Many purchase can be own by one account
     * @return account 
     */
    @ManyToOne
    public Account getAccount() {
        return account;
    }
    
    /**
     * to set the account for the purchase
     * @param account 
     */ 
    public void setAccount(Account account) {
        this.account = account;
    }
}
