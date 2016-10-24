package au.com.phonerent.web;

import au.com.phonerent.domain.*;
import au.com.phonerent.domain.bean.PurchaseFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.*;

/**
 *
 * @author mac
 */
@Named
@SessionScoped
public class PurchaseController implements Serializable{
    @EJB
    PurchaseFacade purchaseFacade;
    
    public List<Purchase> getAllPurchases() {
        return purchaseFacade.findAll();
    }
    
    
}
