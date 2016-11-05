package au.com.phonerent.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
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

    public Phone(){
        
    }
    
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

   
}
