/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author mac
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

    public Purchase(){
        
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
     * 
     * must not be null
     * @return the total
     */
    @DecimalMin("1.0")
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * status displays the condition of the order whether the purchases have been fulfilled
     * @return the status
     */
    @Size(min=3)
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the phones
     */
    @ManyToMany(cascade=CascadeType.ALL)
    public List<Phone> getPhones() {
        return phones;
    }

    /**
     * @param phones the phones to set
     */
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    /**
     * @return the simPlans
     */
    @ManyToMany(cascade=CascadeType.ALL)
    public List<SimPlan> getSimPlans() {
        return simPlans;
    }

    /**
     * @param simPlans the simPlans to set
     */ 
    public void setSimPlans(List<SimPlan> simPlans) {
        this.simPlans = simPlans;
    }   
    
    @ManyToOne
    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
}
