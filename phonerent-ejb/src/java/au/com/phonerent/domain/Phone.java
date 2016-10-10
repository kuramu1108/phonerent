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
    private double price;
    private Purchase purchase;

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

    @Enumerated(EnumType.STRING)
    public PhoneModel getModel() {
        return model;
    }

    public void setModel(PhoneModel model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @ManyToOne
    public Purchase getPurchase() {
        return purchase;
    }
    
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
