/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.utility;

import au.com.phonerent.domain.Purchase;
import au.com.phonerent.domain.bean.PurchaseFacadeLocal;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author mac
 */
@Singleton
public class ExpiryTimer extends TimerTask {
//    private final static long CHECK_PERIOD = 86400 * 1000;
    private final long CHECK_PERIOD = 5 * 1000;
    private final long NOTIFY_PERIOD = 86400 * 1000 * 7;
    
    @EJB
    private PurchaseFacadeLocal purchaseFacade;
    
    @Override
    public void run() {
//        List<Purchase> purchases = purchaseFacade.findAll();
//        Date current = new Date();
//        for (Purchase p : purchases) {
//            if(p.getEndDate().after(current) && p.getStartDate().before(current) && p.getEndDate().getTime() - current.getTime() > NOTIFY_PERIOD) {
//                Logger.getAnonymousLogger().log(Level.SEVERE, "have expired");
//            } else
//                Logger.getAnonymousLogger().log(Level.SEVERE, "not expired");
//        }
        Logger.getLogger(ExpiryTimer.class.getName()).log(Level.SEVERE, "working");
    }
    
    @PostConstruct
    public void init(){
        Timer timer = new Timer();
        timer.schedule(this, new Date(), CHECK_PERIOD);
        Logger.getLogger(ExpiryTimer.class.getName()).log(Level.SEVERE, "init");
    }
}
