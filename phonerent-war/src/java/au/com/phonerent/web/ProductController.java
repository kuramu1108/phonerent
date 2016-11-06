package au.com.phonerent.web;

import au.com.phonerent.domain.Phone;
import au.com.phonerent.domain.Purchase;
import au.com.phonerent.domain.ShoppingCart;
import au.com.phonerent.domain.SimPlan;
import au.com.phonerent.domain.bean.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private String startDateString = "";
    private String endDateString = "";
    
    // presentation logic support =============================================
    private boolean dateConfirmed = false;
    
    // shopping cart operation =================================================
    
    public String ckeckout() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = formatter.parse(startDateString);
            Date endDate = formatter.parse(endDateString);
            purchase.setStartDate(startDate);
            purchase.setEndDate(endDate);
            shoppingCartFacade.process(shoppingCart.getId(), purchase);
            purchase = new Purchase();
        } catch (ParseException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/user/user_dashboard" + REDIRECT;
    }
    
    public String addPhoneToCart(int id) {
        Phone ph = phoneFacade.find(id);
        if (null != ph) {
            shoppingCart.getPhones().add(ph);
            shoppingCartFacade.edit(shoppingCart);
        }
        return null;
    }
    
    public String addSimPlanToCart(int id) {
        SimPlan sim = simplanFacade.find(id);
        if (null != sim) {
            shoppingCart.getSimPlans().add(sim);
            shoppingCartFacade.edit(shoppingCart);
        }
        return null;
    }
    
    public String removePhoneFromCart(int id) {
        Phone ph = phoneFacade.find(id);
        int indexToRemove = -1;
        if (null != ph) {
            for (int i = 0; i < shoppingCart.getPhones().size(); i++) {
                if (shoppingCart.getPhones().get(i).getId() == id) {
                    indexToRemove = i;
                    break;
                }
            }
            if (indexToRemove != -1) {
                shoppingCart.getPhones().remove(indexToRemove);
                shoppingCartFacade.edit(shoppingCart);
            }
        }
        return null;
    }
    
    public String removeSimPlanFromCart(int id) {
        SimPlan sim = simplanFacade.find(id);
        int indexToRemove = -1;
        if (null != sim) {
            for (int i = 0; i < shoppingCart.getSimPlans().size(); i++) {
                if (shoppingCart.getSimPlans().get(i).getId() == id) {
                    indexToRemove = i;
                    break;
                }
            }
            if (indexToRemove != -1) {
                shoppingCart.getSimPlans().remove(indexToRemove);
                shoppingCartFacade.edit(shoppingCart);
            }
        }
        return null;
    }
    
    public boolean isPhoneInCart(int id) {
        for (Phone p: shoppingCart.getPhones()) {
            if (p.getId() == id)
                return true;
        }
        return false;
    }
    
    public boolean isSimPlanInCart(int id) {
        for (SimPlan sp: shoppingCart.getSimPlans()) {
            if (sp.getId() == id)
                return true;
        }
        return false;
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

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }
    

    
    // Load Object functions =================================================
    
    public String loadPhone(int id, String type) {
        phone = phoneFacade.find(id);
        if (null == phone) {
            if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT;
            else
                return "/user/user_dashboard" + REDIRECT;
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
                return "/user/user_dashboard" + REDIRECT;
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
                return "/user/user_dashboard" + REDIRECT;
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
    
    public String deleteTempObject() {
        if (null != deleteObjectType)
            switch (deleteObjectType) {
            case "Phone":
                this.deletePhone(deleteObjectId);
                return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
            case "SimPlan":
                this.deleteSimPlan(deleteObjectId);
                return "/secret/admin_dashboard" + REDIRECT + "tab=sim";
            case "Purchase":
                this.deletePurchase(deleteObjectId);
                return "/secret/admin_dashboard" + REDIRECT + "tab=purchase";
            default:
                break;
        }
        return null;
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
    
    // presentation logic supporting methods =================================

    public boolean isDateConfirmed() {
        return dateConfirmed;
    }

    public void dateEntered() {
        this.dateConfirmed = true;
    }    
}
