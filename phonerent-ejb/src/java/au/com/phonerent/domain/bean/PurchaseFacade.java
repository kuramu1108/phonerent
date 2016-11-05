/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Purchase;
import au.com.phonerent.domain.SimPlan;
import au.com.phonerent.rs.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class PurchaseFacade extends AbstractFacade<Purchase> implements PurchaseFacadeLocal {

    @PersistenceContext(unitName = "phonerent-ejbPU")
    private EntityManager em;
    
    private final long ONE_DAY = 86400 * 1000;
    
    @EJB
    RestClient restClient;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseFacade() {
        super(Purchase.class);
    }

    @Override
    public List<Purchase> findExpiringPurchase(int day) {
        List<Purchase> result = new ArrayList<>();
        Date current = new Date();
        for (Purchase p: findAll()) {
            if(p.getEndDate().after(current) 
                    && p.getStartDate().before(current) 
                    && p.getEndDate().getTime() - current.getTime() <= day * ONE_DAY) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public void sendReminders(List<Purchase> purchases) {
        for (Purchase p: purchases) {
            List<SimPlan> simplans = p.getSimPlans();
            if (!simplans.isEmpty()) {
                SmsRequestBody body = new SmsRequestBody();
                //add sim card number later
                body.setTo(simplans.get(0).getName());
                String sms = "Reminder from PhoneRent\n"
                        + "your order(#" + p.getId() + ") for this sim plan is expring at\n"
                        + p.getEndDate().toString() + "\n"
                        + "Please visit our website for detail or extension";
                body.setBody(sms);
                restClient.postWithData(body);
            }
        }
    }
    
    
}
