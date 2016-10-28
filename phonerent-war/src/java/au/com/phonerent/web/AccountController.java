/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.web;

import au.com.phonerent.domain.*;
import au.com.phonerent.domain.bean.*;
import au.com.phonerent.domain.utility.ExpiryTimer;
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
    
    // just for testing
    @EJB
    private PurchaseFacadeLocal purchaseFacade;
    
    private Account account = new Account();
    private CreditCard creditCard = new CreditCard();  
    
    private String newPassword;
    private boolean newRegisteration = false;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            req.login(account.getEmail(), account.getPassword());
            account = accountFacade.findByEmail(account.getEmail());
        } catch (ServletException e) {
            context.addMessage("loginresult", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!",e.getMessage()));
            return null;
        }
        if ("Admins".equals(account.getAccountType()))
            return "secret/admin_dashboard.xhtml" + REDIRECT;
        else
            return "user_dashboard.xhtml" + REDIRECT;
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
        return "login" + REDIRECT;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public CreditCard getCreditCard() {
        return creditCard;
    }
    
    public boolean getNewRegisteration () {
        return newRegisteration;
    }
    
    public void addSample() {
        accountFacade.addSample();
        purchaseFacade.addSample();
    }
    
    public String signUp() {
        accountFacade.create(account);
        newRegisteration = true;
        return "login" + REDIRECT;
    }
    
    public List<Account> getAllAccounts() {
        return accountFacade.findAll();
    }
    
    public String sendPasswordRecovery() {
        accountFacade.sendPasswordRecovery(account.getEmail());
        return null;
    }
    
    public String loadAccountByResetId(String resetId) {
        account = accountFacade.findByPasswordResetId(resetId);
        if (null == account)
            return "password_recovery.xhtml" + REDIRECT;
        else
            return null;
    }
    
    public String resetPassword() {
        accountFacade.resetPassword(account, newPassword);
        return "login.xhtml" + REDIRECT;
    }
    
    public String confirmRegistration(String email) {
        if (accountFacade.confirmRegistration(email))
            return null;
        else
            return "login.xhtml" + REDIRECT;
    }
    
    public void editAccount() {
        accountFacade.edit(account);
    }
    
    public void editCreditCard() {
        creditCardFacade.edit(creditCard);
    }
    
    public void deleteAccount() {
        accountFacade.remove(account);
    }
    
    public void deleteCreditCard() {
        creditCardFacade.remove(creditCard);
    }
}
