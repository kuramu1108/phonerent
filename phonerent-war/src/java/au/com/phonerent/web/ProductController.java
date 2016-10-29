package au.com.phonerent.web;

import au.com.phonerent.domain.Phone;
import au.com.phonerent.domain.PhoneModel;
import au.com.phonerent.domain.Purchase;
import au.com.phonerent.domain.ShoppingCart;
import au.com.phonerent.domain.SimPlan;
import au.com.phonerent.domain.bean.*;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author mac
 */
@Named
@SessionScoped
public class ProductController implements Serializable {
    @EJB
    private PhoneFacadeLocal phoneFacade;
    @EJB
    private SimPlanFacadeLocal simplanFacade;
    @EJB
    private PhoneModelFacadeLocal phoneModelFacade;
    @EJB
    private PurchaseFacadeLocal purchaseFacade;
    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;
    
    private Phone phone = new Phone();
    private SimPlan simplan = new SimPlan();
    private PhoneModel phoneModel = new PhoneModel();
    private Purchase purchase = new Purchase();
    private ShoppingCart shoppingCart = new ShoppingCart();
    
    public void ckeckout() {
        shoppingCartFacade.process(shoppingCart, purchase);
    }
    
    public void addPhoneToCart(int id) {
        Phone ph = phoneFacade.find(id);
        if (null != ph) {
            shoppingCart.getPhones().add(ph);
            shoppingCartFacade.edit(shoppingCart);
        }
    }
    
    public void addSimPlanToCart(int id) {
        SimPlan sim = simplanFacade.find(id);
        if (null != sim) {
            shoppingCart.getSimPlans().add(sim);
            shoppingCartFacade.edit(shoppingCart);
        }
    }
    
    // Getters =================================================================
    
    public List<Phone> getAllPhones() {
        return phoneFacade.findAll();
    }
    
    public Phone getPhone() {
        return phone;
    }
    
    public List<SimPlan> getAllSimPlans() {
        return simplanFacade.findAll();
    }
    
    public SimPlan getSimPlan() {
        return simplan;
    }
    
    public List<PhoneModel> getAllPhoneModels() {
        return phoneModelFacade.findAll();
    }
    
    public PhoneModel getPhoneModel () {
        return phoneModel;
    }
    
    public List<Purchase> getAllPurchases() {
        return purchaseFacade.findAll();
    }
    
     public Purchase getPurchase() {
        return purchase;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    // Load Object functions =================================================
    
    public void loadPhone(int id) {
        phone = phoneFacade.find(id);
    }
    
    public void loadSimPlan(int id) {
        simplan = simplanFacade.find(id);
    }
    
    public void loadPhoneModel(int id) {
        phoneModel = phoneModelFacade.find(id);
    }
    
    public void loadPurchase (int id) {
        purchase = purchaseFacade.find(id);
    }
    
    public void loadShoppingCart(int id) {
        shoppingCart = shoppingCartFacade.find(id);
    }
    
    // Create functions =======================================================
    
    public void addPhone() {
        phoneFacade.create(phone);
    }
    
    public void addSimPlan() {
        simplanFacade.create(simplan);
    }
    
    public void addPhoneModel() {
        phoneModelFacade.create(phoneModel);
    }
    
    // Edit functions =========================================================
    
    public void editPhone() {
        phoneFacade.edit(phone);
    }
    
    public void editSimPlan() {
        simplanFacade.edit(simplan);
    }
    
    public void editPhoneModel() {
        phoneModelFacade.edit(phoneModel);
    }
    
    public void editPurchase() {
        purchaseFacade.edit(purchase);
    }
    
    public void editShoppingCart() {
        shoppingCartFacade.edit(shoppingCart);
    }
    
    // Delete functions =======================================================
    
    public void deletePhone() {
        phoneFacade.remove(phone);
    }
    
    public void deleteSimPlan() {
        simplanFacade.remove(simplan);
    }
    
    public void deletePhoneModel() {
        phoneModelFacade.remove(phoneModel);
    }
    
    public void deletePurchase() {
        purchaseFacade.remove(purchase);
    }
}
