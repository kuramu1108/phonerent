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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A controller that define the action of product which will take place in a view
 * @author mac
 */
@Named
@SessionScoped
public class ProductController implements Serializable {
    private final String REDIRECT = "?faces-redirect=true";
    @Inject
    AccountController accountController;
    
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
    
    private String startDateString;
    private String endDateString;
    
    // presentation logic support =============================================
    private boolean dateConfirmed = false;
    
    // shopping cart operation =================================================
     /**
     * To write user purchase into database tables
     * @return page redirection
     */
    public String ckeckout() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = formatter.parse(startDateString);
            Date endDate = formatter.parse(endDateString);
            purchase.setStartDate(startDate);
            purchase.setEndDate(endDate);
            shoppingCartFacade.process(shoppingCart.getId(), purchase);
            purchase = new Purchase();
            accountController.reloadAccount();
        return "/user/user_dashboard" + REDIRECT;
        } catch (ParseException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginresult",new FacesMessage(FacesMessage.SEVERITY_ERROR, "the date shouldn't be empty",""));
            return null;
        }
    }
    
     /**
     * To add phone(s) into the user cart
     * @param id is the unique ID of the phone
     * @return null as the user will still be at the same page but the phone adding is processed
     */
    public String addPhoneToCart(int id) {
        Phone ph = phoneFacade.find(id);
        if (null != ph) {
            shoppingCart.getPhones().add(ph);
            shoppingCartFacade.edit(shoppingCart);
        }
        return null;
    }
    
    /**
     * To add simPlan(s) into the user cart
     * @param id is the unique ID of the simPlan
     * @return null as the user will still be at the same page but the simPlan adding is processed
     */
    public String addSimPlanToCart(int id) {
        SimPlan sim = simplanFacade.find(id);
        if (null != sim) {
            shoppingCart.getSimPlans().add(sim);
            shoppingCartFacade.edit(shoppingCart);
        }
        return null;
    }
    
    /**
     * To remove phone from the cart
     * @param id is the unique ID of the phone
     * @return null as the user will still be at the same page but the phone order deletion is processed 
     */
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
    
    /**
     * To remove simPlan from the cart
     * @param id is the unique ID of the simPlan
     * @return null as the user will still be at the same page but the simPlan order deletion is processed 
     */
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
    
    /**
     * To check whether a particular phone is in the cart 
     * @param id is the id of the phone
     * @return boolean for the view to grey out a button if item is in cart
     */
    public boolean isPhoneInCart(int id) {
        for (Phone p: shoppingCart.getPhones()) {
            if (p.getId() == id)
                return true;
        }
        return false;
    }
    
    /**
     * To check whether a particular simPlan is in the cart 
     * @param id is the id of the simPlan
     * @return boolean for the view to grey out a button if item is in cart
     */
    public boolean isSimPlanInCart(int id) {
        for (SimPlan sp: shoppingCart.getSimPlans()) {
            if (sp.getId() == id)
                return true;
        }
        return false;
    }
    // Getters =================================================================
    
    /**
     * To get all phones in database
     * @return all phones in the database
     */
    public List<Phone> getAllPhones() {
        return phoneFacade.findAll();
    }
    
    /**
     * To get phone object
     * @return phone object
     */
    public Phone getPhone() {
        return phone;
    }
   
    /**
     * To get all simPlans in database
     * @return all simPlan in the database
     */ 
    public List<SimPlan> getAllSimPlans() {
        return simplanFacade.findAll();
    }
    
    /**
     * To get a simPlan
     * @return simPlan object 
     */
    public SimPlan getSimPlan() {
        return simplan;
    }
    
    /**
     * To get all purchases in database
     * @return all purchases 
     */
    public List<Purchase> getAllPurchases() {
        return purchaseFacade.findAll();
    }
    
    /**
     * To get a purchase detail
     * @return purchase object
     */
     public Purchase getPurchase() {
        return purchase;
    }
    
    /**
     * To get shopping cart
     * @return shopping cart object
     */
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    /**
     * To count items in the cart
     * @return number of the item in the cart
     */
    public int getShoppingItemCount() {
        return shoppingCart.getPhones().size() + shoppingCart.getSimPlans().size();
    }

    /**
     * To get start date of the renting
     * @return start date
     */
    public String getStartDateString() {
        return startDateString;
    }

    /**
     * To set start date of the renting
     * @param startDateString is the start date of the renting
     */
    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    /**
     * To get end date of the renting
     * @return end date
     */
    public String getEndDateString() {
        return endDateString;
    }

    /**
     * To set end date of the renting
     * @param endDateString is the end date of the renting
     */
    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }
    

    
    // Load Object functions =================================================
    
    /**
     * To load phone based on user
     * @param id is the ID of the phone
     * @param type is the type of the user
     * @return page redirection
     */
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
    
    /**
     * To load simPlan based on user
     * @param id is the ID of the simPlan
     * @param type is the type of the user
     * @return page redirection
     */
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
    
    /**
     * To load purchase based on user
     * @param id is the ID of the purchase
     * @param type is the type of the user
     * @return page redirection
     */
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
    
    /**
     * To load shopping cart based on ID
     * @param id is the ID of the shopping cart
     */
    public void loadShoppingCart(int id) {
        shoppingCart = shoppingCartFacade.find(id);
    }
    
    /**
     * To load temporary object
     * @param id is the object ID
     * @param type is the type for temporary object
     * @return null
     */
    public String loadTempDeleteObject(int id, String type) {
        deleteObjectId = id;
        deleteObjectType = type;
        return null;
    }
    // Create functions =======================================================
    
    /**
     * To add phone to the database
     * @return page redirection
     */
    public String addPhone() {
        phoneFacade.create(phone);
        return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
    }
    
    /**
     * To add simPlan to the database
     * @return page redirection
     */
    public String addSimPlan() {
        simplanFacade.create(simplan);
        return "/secret/admin_dashboard" + REDIRECT + "tab=sim";
    }
    
    // Edit functions =========================================================
    
     /**
     * To edit phone in the database
     * @return page redirection
     */
    public String editPhone() {
        phoneFacade.edit(phone);
        return "/secret/admin_dashboard" + REDIRECT + "tab=phone";
    }
    
    /**
     * To edit simPlan in the database
     * @return page redirection
     */
    public String editSimPlan() {
        simplanFacade.edit(simplan);
        return "/secret/admin_dashboard" + REDIRECT + "tab=sim";
    }
    
    /**
     * To edit purchase in the database
     * @return page redirection
     */
    public String editPurchase() {
        purchaseFacade.edit(purchase);
        return "/secret/admin_dashboard" + REDIRECT + "tab=purchase";
    }
    
    /**
     * To update purchase status
     * @return page redirection
     */
    public String updatePurchaseStatus() {
        purchaseFacade.updateStatus(purchase);
        return "/secret/admin_dashboard" + REDIRECT + "tab=purchase";
    }
    
     /**
     * To edit shopping cart object
     * @return page redirection
     */
    public String editShoppingCart() {
        shoppingCartFacade.edit(shoppingCart);
        return "/user/user_dashboard" + REDIRECT;
    }
    
    // Delete functions =======================================================
    
    /**
     * To delete phone from database
     */
    public void deletePhone() {
        phoneFacade.remove(phone);
    }
    
    /**
     * To delete phone by ID
     * @param id is the ID of the phone
     * @return null
     */
    public String deletePhone(int id) {
        phoneFacade.remove(phoneFacade.find(id));
        return null;
    }
    
    /**
     * To delete simPlan from database
     */
    public void deleteSimPlan() {
        simplanFacade.remove(simplan);
    }
    
    /**
     * To delete simPlan by ID
     * @param id is the ID of the simPlan
     */
    public void deleteSimPlan(int id) {
        simplanFacade.remove(simplanFacade.find(id));
    }
    
    /**
     * To remove purchase from database
     */
    public void deletePurchase() {
        purchaseFacade.remove(purchase);
    }
    
    /**
     * To remove purchase by ID
     * @param id is the ID of purchase
     */
    public void deletePurchase(int id) {
        purchaseFacade.remove(purchaseFacade.find(id));
    }
    
    /**
     * To delete temporary object
     * @return page redirection
     */
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
    
    /**
     * initialize a phone object
     */
    public void newPhone() {
        phone = new Phone();
    }
    
    /**
     * Initialize a simPlan object
     */
    public void newSimPlan() {
        simplan = new SimPlan();
    }
    
     /**
     * Initialize a purchase object
     */
    public void newPurchase() {
        purchase = new Purchase();
    }
    
    // presentation logic supporting methods =================================

    /**
     * To check whether date is confirmed
     * @return boolean if date is confirmed
     */
    public boolean isDateConfirmed() {
        return dateConfirmed;
    }

    /**
     * To check if date is entered
     */
    public void dateEntered() {
        this.dateConfirmed = true;
    }    
}
