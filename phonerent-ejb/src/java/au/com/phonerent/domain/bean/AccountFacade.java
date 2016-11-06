package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Account;
import au.com.phonerent.domain.CreditCard;
import au.com.phonerent.domain.ShoppingCart;
import au.com.phonerent.domain.utility.PasswordResetIdGenerator;
import au.com.phonerent.domain.utility.Sha256;
import au.com.phonerent.jma.EmailClient;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * account DAO implementation
 * @author TOSHIBA
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal {

    @PersistenceContext(unitName = "phonerent-ejbPU")
    private EntityManager em;
    
    @EJB
    EmailClient emailClient;
    
    @EJB
    CreditCardFacadeLocal creditCardFacade;
    
    @EJB
    ShoppingCartFacadeLocal shoppingCartFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    @Override
    public void signUp(Account account) {
        account.setIsActivate(false);
        account.setAccountType("Users");
        CreditCard card = new CreditCard();
        ShoppingCart cart = new ShoppingCart();
        card.setOwner(account);
        account.setCreditCard(card);
        cart.setAccount(account);
        account.setShoppingCart(cart);
        shoppingCartFacade.create(cart);
        creditCardFacade.create(card);
        try {
            account.setPassword(Sha256.hash256(account.getPassword()));
            getEntityManager().persist(account);
            emailClient.registerationConfirmationSendTo(account.getEmail());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error encrpytion");
        }
    }
    
    @Override
    public void create(Account account) {
        account.setIsActivate(true);
        account.setAccountType("Users");
        CreditCard card = new CreditCard();
        ShoppingCart cart = new ShoppingCart();
        cart.setAccount(account);
        card.setOwner(account);
        account.setCreditCard(card);
        account.setShoppingCart(cart);
        creditCardFacade.create(card);
        shoppingCartFacade.create(cart);
        try {
            account.setPassword(Sha256.hash256(account.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error encrpytion");
        }
        getEntityManager().persist(account);
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
