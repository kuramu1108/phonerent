package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Purchase;
import au.com.phonerent.domain.ShoppingCart;
import java.util.List;
import javax.ejb.Local;

/**
 * shopping cart DAO local interface
 * @author mac
 */
@Local
public interface ShoppingCartFacadeLocal {

    /**
     * create a new cart
     * @param shoppingCart a new cart
     */
    void create(ShoppingCart shoppingCart);

    /**
     * update a cart
     * @param shoppingCart the cart to be updated
     */
    void edit(ShoppingCart shoppingCart);

    /**
     * remove a cart
     * @param shoppingCart the cart to be removed
     */
    void remove(ShoppingCart shoppingCart);

    /**
     * find a cart
     * @param id the id of the cart
     * @return the cart found, null if not found
     */
    ShoppingCart find(Object id);

    /**
     * find all the shopping cart
     * @return the list of all the cart
     */
    List<ShoppingCart> findAll();

    List<ShoppingCart> findRange(int[] range);

    int count();
    
    /**
     * process the cart and create a purchase record
     * @param id the id of the shopping cart
     * @param purchase the purhcase object to be create
     */
    void process(int id, Purchase purchase);
    
    /**
     * clean up the shopping cart for next buy
     * @param shoppingCart the cart to be cleaned
     */
    void empty(ShoppingCart shoppingCart);
}
