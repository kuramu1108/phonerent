package au.com.phonerent.web;

import au.com.phonerent.domain.Phone;
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
    private PurchaseFacadeLocal purchaseFacade;
    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;
    
    private Phone phone = new Phone();
    private SimPlan simplan = new SimPlan();
    private Purchase purchase = new Purchase();
    private ShoppingCart shoppingCart = new ShoppingCart();
    
    private String deleteObjectType = "";
    private int deleteObjectId;
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
    
    public String loadPhone(int id, String type) {
        phone = phoneFacade.find(id);
        if (null == phone) {
            if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT;
            else
                return "/user/user_dashbaord" + REDIRECT;
        }
        else
            return null;
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
    
    public String loadTempDeleteObject(int id, String type) {
        deleteObjectId = id;
        deleteObjectType = type;
        return null;
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
    
    // Edit functions =========================================================
    
    public String editPhone() {
        phoneFacade.edit(phone);
        return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
    }
    
    public String editSimPlan() {
        simplanFacade.edit(simplan);
        return "/secret/admin_dashboard" + REDIRECT + "tab=sim";
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
    
    public String deletePhone(int id) {
        phoneFacade.remove(phoneFacade.find(id));
        return null;
    }
    
    public void deleteSimPlan() {
        simplanFacade.remove(simplan);
    }
    
    public void deleteSimPlan(int id) {
        simplanFacade.remove(simplanFacade.find(id));
    }
    
    public void deletePurchase() {
        purchaseFacade.remove(purchase);
    }
    
    public void deletePurchase(int id) {
        purchaseFacade.remove(purchaseFacade.find(id));
    }
    
    public void deleteTempObject() {
        if (null != deleteObjectType)
            switch (deleteObjectType) {
            case "Phone":
                this.deletePhone(deleteObjectId);
                break;
            case "SimPlan":
                this.deleteSimPlan(deleteObjectId);
                break;
            case "Purchase":
                this.deletePurchase(deleteObjectId);
                break;
            default:
                break;
        }
    }
    // initialize new Entities ================================================
    public void newPhone() {
        phone = new Phone();
    }
    
    public void newSimPlan() {
        simplan = new SimPlan();
    }
    
    public void newPurchase() {
        purchase = new Purchase();
    }
}
