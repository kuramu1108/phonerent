package au.com.phonerent.web;

import au.com.phonerent.domain.*;
import au.com.phonerent.domain.bean.PurchaseFacade;
import au.com.phonerent.domain.bean.ShoppingCartFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.*;

/**
 *
 * @author mac
 */
@Named
@SessionScoped
public class PurchaseController implements Serializable{
    @EJB
    PurchaseFacade purchaseFacade;
    
    @EJB
    ShoppingCartFacade shoppingcardFacade;
    
    private Purchase purchase = new Purchase();
    private ShoppingCart shoppingCart = new ShoppingCart();
    
    public List<Purchase> getAllPurchases() {
        return purchaseFacade.findAll();
    }
    
    public void loadPurchase (int id) {
        
    }
    
    public void loadShoppingCart(int id) {
        
    }
    
    
    
    public Purchase getPurchase() {
        return purchase;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
