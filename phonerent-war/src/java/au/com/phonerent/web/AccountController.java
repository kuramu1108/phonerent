package au.com.phonerent.web;

import au.com.phonerent.domain.*;
import au.com.phonerent.domain.bean.*;
import java.io.*;
import java.util.List;
import javax.ejb.*;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * A controller that define the action of account which will take place in a view
 * @author garysnmb
 */
@Named
@SessionScoped
public class AccountController implements Serializable {
    private final String REDIRECT = "?faces-redirect=true";
    
    @EJB
    private AccountFacadeLocal accountFacade;
    
    @EJB
    private CreditCardFacadeLocal creditCardFacade;
        
    private Account account = new Account();
    private CreditCard creditCard = new CreditCard();  
    
    private int deleteObjectId;
    
    // Presentation Logic Supporting variables =========================================
    private String newPassword;
    private boolean newRegistration = false;
    private boolean registrationChecked = false;

    private boolean loggedIn = false;
    
    // business logic processing ==============================================
     /**
     * To allow users to login by comparing user input against database information
     * @return page redirection to either admin or user depending on the type of account
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            req.login(account.getEmail(), account.getPassword());
            account = accountFacade.findByEmail(account.getEmail());
        } catch (ServletException e) {
            context.addMessage("loginresult", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The credentials you supplied were not correct",e.getMessage()));
            return null;
        }
        loggedIn = true;
        if ("Admins".equals(account.getAccountType())) {
            return "/secret/admin_dashboard" + REDIRECT;
        }
        else
            return "/user/user_dashboard" + REDIRECT;
    }
    
    /**
     * To allow users to end their session securely by logging out
     * @return page redirection to home page
     */
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try {
            request.logout();
            request.getSession().invalidate();
        } catch (ServletException e) {
            // (you could also log the exception to the server log)
            context.addMessage(null, new FacesMessage(e.getMessage()));
        }
        return "/login" + REDIRECT;
    }
    
    /**
     * To allow to add sample account for testing
     */
    public void addSample() {
        accountFacade.addSample();
    }
    
    /**
     * To allow new user to sign up and saving their details to the database
     * @return page redirection to login page
     */
    public String signUp() {
        accountFacade.signUp(account);
        newRegistration = true;
        return "/login" + REDIRECT;
    }
    
     /**
     * To send link of password recovery to user through email
     * @return page redirection to another page where prompt is shown to user
     */
    public String sendPasswordRecovery() {
        accountFacade.sendPasswordRecovery(account.getEmail());
        return "/password_confirmation" + REDIRECT;
    }
    
    /**
     * To allow user to reset their password after obtaining link to it
     * @return page redirection
     */
    public String resetPassword() {
        accountFacade.resetPassword(account, newPassword);
        return "/password_changed" + REDIRECT;
    }
    
    /**
     * To confirm registration of the account
     * @param email is the email of the account to be confirmed?
     * @return null or page redirection to login page
     */
    public String confirmRegistration(String email) {
        if (accountFacade.confirmRegistration(email))
            return null;
        else
            return "/login" + REDIRECT;
    }
    
    // Getters ================================================================
     
    /**
     * @return account
     */
    public Account getAccount() {
        return account;
    }
    
    /**
     * @return credit card
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }
    
     /**
     * @return all accounts in database
     */
    public List<Account> getAllAccounts() {
        return accountFacade.findAll();
    }
    
    // Load operations ========================================================
    
    /**
     * To load account by the reset ID
     * @param resetId is the reset ID of the account 
     * @return page redirection 
     */
    public String loadAccountByResetId(String resetId) {
        account = accountFacade.findByPasswordResetId(resetId);
        if (null == account)
            return "/password_recovery" + REDIRECT;
        else
            return null;
    }
    
    /**
     * To load account in accordance to its type
     * @param id is the unique Id of the account
     * @param type is the account type
     * @return appropriate page redirection based on the type
     */
    public String loadAccount(int id, String type) {
        account = accountFacade.find(id);
        if (null == account) {
            if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT;
            else
                return "/user/user_dashbaord" + REDIRECT;
        }
        else
            return null;        
    }
    
     /**
     * to reload account by finding ID
     */
    public void reloadAccount() {
        account = accountFacade.find(account.getId());
    }
    
     /**
     * To reload credit card information
     */
    public void loadCreditCard() {
        creditCard = account.getCreditCard();
    }
    
     /**
     * To load temporary object
     * @param id is the unique ID of the object
     * @return 
     */
    public String loadTempDeleteObject(int id) {
        deleteObjectId = id;
        return null;
    }
    
    // add operations =======================================================
    
    /**
     * To add an account to the database
     * @return page redirection after account creation
     */
    public String addAccount() {
        accountFacade.create(account);
        return "/secret/admin_dashboard" + REDIRECT + "tab=account";
    }
    
    // edit operations ======================================================
    
    /**
     * To edit account that has been registered
     * @param type is the type of the account
     * @return page redirection
     */
    public String editAccount(String type) {
        accountFacade.edit(account);
        if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT + "tab=account";
            else
                return "/user/user_dashboard" + REDIRECT + "tab=profile";
    }
    
    /**
     * To edit credit card information
     * @return page redirection
     */
    public String editCreditCard() {
        creditCardFacade.edit(creditCard);
        return "/user/user_dashboard" + REDIRECT + "tab=billing";
    }    
    
    // delete operaitons ======================================================
    
    /**
     * To remove account from the database
     */
    public void deleteAccount() {
        accountFacade.remove(account);
    }
    
    /**
     * To remove account from the database based on ID
     * @param id is the unique ID of the account
     */
    public void deleteAccount(int id) {
        accountFacade.remove(accountFacade.find(id));
    }
    
    /**
     * To remove credit card from the database
     */
    public void deleteCreditCard() {
        creditCardFacade.remove(creditCard);
    }
    
    /**
     * To remove temporary object
     */
    public void deleteTempObject() {
        deleteAccount(deleteObjectId);
    }
    
    // initialize new Entities ================================================
    
    /**
     * To initialize new account
     */
    public void newAccount() {
        account = new Account();
    }
    
    
    // Presentation Logic functions ===========================================
    
    /**
     * To display alert in front end after registration
     * @return boolean on whether the alert should be shown
     */
    public boolean displayRegistrationAlert() {
        if (newRegistration) {
            if (!registrationChecked) {
                registrationChecked = true;
                return true;
            } else
                return false;
        } else
            return false;
    }
    
    /**
     * To find the session status of the account
     * @return loggedIn or not
     */
    public boolean getLoggedIn() {
        return loggedIn;
    }
    
    /**
     * To constrain user from going to pages if not logged in
     * @return page redirection
     */
    public String loggedInGuard() {
        if (loggedIn)
            return "/user/user_dashboard" + REDIRECT;
        else
            return null;
    }
    
    /**
     * To get new password for an account
     * @return new password for the account?
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * To set new password for an account
     * @param newPassword is the new password for the account
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    /**
     * To find out whether the account has payment method stored in the account
     * @return boolean whether there are payment information stored
     */
    public boolean isPaymentConfirmed() {
        if (creditCard.getCardNumber() == null)
            return false;
        else if (creditCard.getCvv() == null)
            return false;
        else if (creditCard.getExpiryMonth() == 0)
            return false;
        else if (creditCard.getExpiryYear() == 0)
            return false;
        else if (creditCard.getNameOnCard() == null)
            return false;
        else if (account.getDeliveryAddress() == null)
            return false;
        else
            return true;
    }
}
