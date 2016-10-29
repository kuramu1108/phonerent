/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Purchase;
import au.com.phonerent.domain.ShoppingCart;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mac
 */
@Local
public interface ShoppingCartFacadeLocal {

    void create(ShoppingCart shoppingCart);

    void edit(ShoppingCart shoppingCart);

    void remove(ShoppingCart shoppingCart);

    ShoppingCart find(Object id);

    List<ShoppingCart> findAll();

    List<ShoppingCart> findRange(int[] range);

    int count();
    
    void process(ShoppingCart shoppingCart, Purchase purchase);
    
    void empty(ShoppingCart shoppingCart);
}
