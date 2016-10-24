/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author mac
 */
@Entity
public class PhoneModel implements Serializable {
    private int id;
    private String modelName;
    private String manufacturer;
    private double price;
    private List<Phone> phones = new ArrayList<>();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getModel() {
        return modelName;
    }

    public void setModel(String model) {
        this.modelName = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @OneToMany(mappedBy = "model")
    public List<Phone> getPhones() {
        return phones;
    }

    /**
     * @param simPlans the simPlans to set
     */ 
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }   
    
}
