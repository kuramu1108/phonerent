/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Account;
import au.com.phonerent.domain.ShoppingCart;
import au.com.phonerent.domain.utility.PasswordResetIdGenerator;
import au.com.phonerent.domain.utility.Sha256;
import au.com.phonerent.jma.EmailClient;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal {

    @PersistenceContext(unitName = "phonerent-ejbPU")
    private EntityManager em;
    
    @EJB
    EmailClient emailClient;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    @Override
    public void create(Account account) {
        account.setIsActivate(false);
        account.setAccountType("Users");
        ShoppingCart cart = new ShoppingCart();
        account.setShoppingCart(cart);
        try {
            account.setPassword(Sha256.hash256(account.getPassword()));
            emailClient.registerationConfirmationSendTo(account.getEmail());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error encrpytion");
        }
        getEntityManager().persist(account);
    }
    
    @Override
    public void addSample() {
        try {
            Account admin = new Account();
            admin.setAccountType("Admins");
            admin.setEmail("colagarychen@gmail.com");
            admin.setFirstName("Gary");
            admin.setLastName("Chen");
            admin.setPassword(Sha256.hash256("841108"));
            admin.setPhoneNumber("0431911088");
            admin.setDob(new GregorianCalendar(1995, 11, 8).getTime());
            admin.setIsActivate(true);
            em.persist(admin);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Account findByEmail(String email) {
        TypedQuery<Account> query = em.createNamedQuery("Account.findByEmail", Account.class);
        query.setParameter("email", email);
        List result = query.getResultList();
        if (result.isEmpty())
            return null;
        else
            return (Account) result.get(0);
    }
    
    @Override
    public Account findByPasswordResetId(String resetId) {
        TypedQuery<Account> query = em.createNamedQuery("Account.findByPasswordResetId", Account.class);
        query.setParameter("resetId", resetId);
        List result = query.getResultList();
        if (result.isEmpty())
            return null;
        else
            return (Account) result.get(0);
    }
    
    @Override
    public boolean isActivate(String email) {
        TypedQuery<Account> query = em.createNamedQuery("Account.findByEmail", Account.class);
        return true;
    }
    
    @Override
    public void sendPasswordRecovery(String email) {
        Account account = findByEmail(email);
        String resetId = PasswordResetIdGenerator.generateId(account.getEmail());
        account.setPasswordResetId(resetId);
        edit(account);
        emailClient.passwordRecoverySendTo(account.getEmail(), resetId);
    }
    
    @Override
    public void resetPassword(Account account, String newPassword) {
        try {
            account.setPasswordResetId(null);
            account.setPassword(Sha256.hash256(newPassword));
            edit(account);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean confirmRegistration(String email) {
        Account account = findByEmail(email);
        
        if (null == account)
            return false;
        else {
            account.setIsActivate(true);
            edit(account);
            return true;
        }
    }
}
