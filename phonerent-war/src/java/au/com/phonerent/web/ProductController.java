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
    private final String REDIRECT = "?faces-redirect=true";
    
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
    
    
    // shopping cart operation =================================================
    
    public void ckeckout() {
        shoppingCartFacade.process(shoppingCart, purchase);
        purchase = new Purchase();
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
    
    public void removePhoneFromCart(int id) {
        Phone ph = phoneFacade.find(id);
        if (null != ph) {
            shoppingCart.getPhones().remove(ph);
            shoppingCartFacade.edit(shoppingCart);
        }
    }
    
    public void removeSimPlanFromCart(int id) {
        SimPlan sim = simplanFacade.find(id);
        if (null != sim) {
            shoppingCart.getSimPlans().remove(sim);
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
    
    public int getShoppingItemCount() {
        return shoppingCart.getPhones().size() + shoppingCart.getSimPlans().size();
    }
    
    // Load Object functions =================================================
    
    public void loadPhone(int id) {
        phone = phoneFacade.find(id);
    }
    
    public String loadSimPlan(int id, String type) {
        simplan = simplanFacade.find(id);
        if (null == simplan) {
            if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT;
            else
                return "/user/user_dashbaord" + REDIRECT;
        }
        else
            return null;
    }
    
    public String loadPhoneModel(int id, String type) {
        phoneModel = phoneModelFacade.find(id);
        if (null == phoneModel) {
            if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT;
            else
                return "/user/user_dashbaord" + REDIRECT;
        }
        else
            return null;
    }
    
    public String loadPurchase (int id, String type) {
        purchase = purchaseFacade.find(id);
        if (null == purchase) {
            if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT;
            else
                return "/user/user_dashbaord" + REDIRECT;
        }
        else
            return null;
    }
    
    public void loadShoppingCart(int id) {
        shoppingCart = shoppingCartFacade.find(id);
    }
    
    // Create functions =======================================================
    
    public String addPhone() {
        phoneFacade.create(phone);
        return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
    }
    
    public String addSimPlan() {
        simplanFacade.create(simplan);
        return "/secret/admin_dashboard" + REDIRECT + "tab=sim";
    }
    
    public String addPhoneModel() {
        phoneModelFacade.create(phoneModel);
        return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
    }
    
    // Edit functions =========================================================
    
    public String editPhone() {
        phoneFacade.edit(phone);
        return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
    }
    
    public String editSimPlan() {
        simplanFacade.edit(simplan);
        return "/secret/admin_dashboard" + REDIRECT + "tab=sim";
    }
    
    public String editPhoneModel() {
        phoneModelFacade.edit(phoneModel);
        return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
    }
    
    public String editPurchase() {
        purchaseFacade.edit(purchase);
        return "/secret/admin_dashboard" + REDIRECT + "tab=purchase";
    }
    
    public String editShoppingCart() {
        shoppingCartFacade.edit(shoppingCart);
        return "/user/user_dashboard" + REDIRECT;
    }
    
    // Delete functions =======================================================
    
    public void deletePhone() {
        phoneFacade.remove(phone);
    }
    
    public void deleteSimPlan() {
        simplanFacade.remove(simplan);
    }
    
    public void deleteSimPlan(int id) {
        simplanFacade.remove(simplanFacade.find(id));
    }
    
    public void deletePhoneModel() {
        phoneModelFacade.remove(phoneModel);
    }
    
    public String deletePoneModel(int id) {
        phoneModelFacade.remove(phoneModelFacade.find(id));
        return null;
    }
    
    public void deletePurchase() {
        purchaseFacade.remove(purchase);
    }
    
    public void deletePurchase(int id) {
        purchaseFacade.remove(purchaseFacade.find(id));
    }
    
    // initialize new Entities ================================================
    public void newPhone() {
        phone = new Phone();
    }
    
    public void newSimPlan() {
        simplan = new SimPlan();
    }
    
    public void newPhoneModel() {
        phoneModel = new PhoneModel();
    }
    
    public void newPurchase() {
        purchase = new Purchase();
    }
}
