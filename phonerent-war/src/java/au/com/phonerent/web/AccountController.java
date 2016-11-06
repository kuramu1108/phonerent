/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
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
    
    public void addSample() {
        accountFacade.addSample();
    }
    
    public String signUp() {
        accountFacade.signUp(account);
        newRegistration = true;
        return "/login" + REDIRECT;
    }
    
    public String sendPasswordRecovery() {
        accountFacade.sendPasswordRecovery(account.getEmail());
        return "/password_confirmation" + REDIRECT;
    }
    
    public String resetPassword() {
        accountFacade.resetPassword(account, newPassword);
        return "/password_changed" + REDIRECT;
    }
    
    public String confirmRegistration(String email) {
        if (accountFacade.confirmRegistration(email))
            return null;
        else
            return "/login" + REDIRECT;
    }
    
    // Getters ================================================================
    public Account getAccount() {
        return account;
    }
    
    public CreditCard getCreditCard() {
        return creditCard;
    }
    
    public List<Account> getAllAccounts() {
        return accountFacade.findAll();
    }
    
    // Load operations ========================================================
    public String loadAccountByResetId(String resetId) {
        account = accountFacade.findByPasswordResetId(resetId);
        if (null == account)
            return "/password_recovery" + REDIRECT;
        else
            return null;
    }
    
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
    
    public void reloadAccount() {
        account = accountFacade.find(account.getId());
    }
    
    public void loadCreditCard() {
        creditCard = account.getCreditCard();
    }
    
    public String loadTempDeleteObject(int id) {
        deleteObjectId = id;
        return null;
    }
    
    // add operations =======================================================
    public String addAccount() {
        accountFacade.create(account);
        return "/secret/admin_dashboard" + REDIRECT + "tab=account";
    }
    
    // edit operations ======================================================
    public String editAccount(String type) {
        accountFacade.edit(account);
        if ("Admins".equals(type))
                return "/secret/admin_dashboard" + REDIRECT + "tab=account";
            else
                return "/user/user_dashboard" + REDIRECT + "tab=profile";
    }
    
    public String editCreditCard() {
        creditCardFacade.edit(creditCard);
        return "/user/user_dashboard" + REDIRECT + "tab=billing";
    }    
    
    // delete operaitons ======================================================
    public void deleteAccount() {
        accountFacade.remove(account);
    }
    
    public void deleteAccount(int id) {
        accountFacade.remove(accountFacade.find(id));
    }
    
    public void deleteCreditCard() {
        creditCardFacade.remove(creditCard);
    }
    
    public void deleteTempObject() {
        deleteAccount(deleteObjectId);
    }
    
    // initialize new Entities ================================================
    public void newAccount() {
        account = new Account();
    }
    
    
    // Presentation Logic functions ===========================================
    
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
    
    public boolean getLoggedIn() {
        return loggedIn;
    }
    
    public String loggedInGuard() {
        if (loggedIn)
            return "/user/user_dashboard" + REDIRECT;
        else
            return null;
    }
    
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
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
    
    private boolean test = false;
    
    // testing function
    public boolean getTest() {
        return test;
    }
    
    public String testing() {
        test = !test;
        return null;
    }
}
