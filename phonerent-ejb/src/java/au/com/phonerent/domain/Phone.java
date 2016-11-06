package au.com.phonerent.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A class that stores the information about account
 * @author mac
 */
@Entity
public class Phone implements Serializable {
    private int id; 
    private String modelName;
    private String manufacturer;
    private double price;   
    private List<Purchase> purchases = new ArrayList<>();
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    /**
     * Creates a new instance of phone
     */
    public Phone(){
        
    }
    
    /**
     * to get or request for the id of a particular phone
     * Id is being auto generated for every new instances of phone
     * @return id must not be null and it is a primary key for phone
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * to set new id into the phone
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * to get or request for the list of the purchases that are linked with phone
     * The list can be null. One phone can be bought in multiple purchases. 
     * One purchase can include with multiple phones.
     * Thus, many to many relationship is being used
     * @return purchases can be null
     */
    @ManyToMany(mappedBy = "phones", cascade=CascadeType.ALL)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * to set new purchases into the phone
     * @param id 
     */
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    /**
     * to get or request for the list of the shopping Cart that are linked with phone
     * One phone can be put in multiple shopping carts and
     * One shopping cart can has multiple phones.
     * many to many relationship is being used because of this
     * @return shoppingCarts can be null
     */
    @ManyToMany(mappedBy = "phones", cascade=CascadeType.ALL)
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Size(min=1, max=20)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Size(min=1, max=20)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NotNull
    @DecimalMax("100.0") @DecimalMin("1.0")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
}
