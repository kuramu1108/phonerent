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
    @EJB
    private AccountFacadeLocal accountFacade;
    
    private Account account = new Account();
    
    private String confirmedPassword;

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
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
        return "test?faces-redirect=true";
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
        return "login?faces-redirect=true";
    }
    
    public Account getAccount() {
        return account;
    }
    
    public void addSample() {
        accountFacade.addSample();
    }
    
    public String signUp() {
        accountFacade.create(account);
        return "login?faces-redirect=true";
    }
    
    public List<Account> getAllAccounts() {
        return accountFacade.findAll();
    }
    
    public String sendPasswordRecovery() {
        accountFacade.sendPasswordRecovery(account.getEmail());
        return null;
    }
    
    public void loadAccountByResetId(String resetId) {
        account = accountFacade.findByPasswordResetId(resetId);
    }
}
