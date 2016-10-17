/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Account;
import java.util.GregorianCalendar;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    @Override
    public void addSample() {
        Account admin = new Account();
        admin.setAccountType("Admins");
        admin.setEmail("colagarychen@gmail.com");
        admin.setFirstName("Gary");
        admin.setLastName("Chen");
        admin.setPassword("4ac1a11e2411b1ad6361357c564ffd5a3df39fc9950abc9059e1d7afcf0bcd02");
        admin.setPhoneNumber("0431911088");
        admin.setDob(new GregorianCalendar(1995, 11, 8).getTime());
        
        em.persist(admin);
    }
    
    @Override
    public Account findByEmail(String email) {
        TypedQuery<Account> query = em.createNamedQuery("Account.findByEmail", Account.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
