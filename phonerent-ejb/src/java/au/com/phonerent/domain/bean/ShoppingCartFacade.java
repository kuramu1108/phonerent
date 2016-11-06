package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Phone;
import au.com.phonerent.domain.Purchase;
import au.com.phonerent.domain.ShoppingCart;
import au.com.phonerent.domain.SimPlan;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * shopping cart DAO implementation
 * @author mac
 */
@Stateless
public class ShoppingCartFacade extends AbstractFacade<ShoppingCart> implements ShoppingCartFacadeLocal {

    @PersistenceContext(unitName = "phonerent-ejbPU")
    private EntityManager em;

    @EJB
    private PurchaseFacadeLocal purchaseFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoppingCartFacade() {
        super(ShoppingCart.class);
    }

    @Override
    public void process(int id, Purchase purchase) {
        ShoppingCart shoppingCart = this.find(id);
        purchase.setAccount(shoppingCart.getAccount());
        shoppingCart.getAccount().getPurchases().add(purchase);
        purchase.setPhones(shoppingCart.getPhones());
        purchase.setSimPlans(shoppingCart.getSimPlans());
        purchase.setStatus("Recieved");
        double total = 0;
        for (Phone p : purchase.getPhones()) {
            total += p.getPrice();
        }
        for (SimPlan sm : purchase.getSimPlans()) {
            total += sm.getPrice();
        }
        purchase.setTotal(total);
        purchaseFacade.create(purchase);
        this.empty(shoppingCart);
    }

    @Override
    public void empty(ShoppingCart shoppingCart) {
        shoppingCart.setPhones(new ArrayList<>());
        shoppingCart.setSimPlans(new ArrayList<>());
        this.edit(shoppingCart);
    }
    
}
