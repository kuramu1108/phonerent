package au.com.phonerent.domain;

import java.io.*;
import javax.persistence.*;

/**
 *
 * @author mac
 */
@Entity
public class Phone implements Serializable {
    private int id; 
    private PhoneModel model;    
    private Purchase purchase;
    private ShoppingCart shoppingCart;

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

    @ManyToOne
    public PhoneModel getModel() {
        return model;
    }

    public void setModel(PhoneModel model) {
        this.model = model;
    }
    
    @ManyToOne
    public Purchase getPurchase() {
        return purchase;
    }
    
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @ManyToOne
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
