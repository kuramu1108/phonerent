package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Purchase;
import java.util.List;
import javax.ejb.Local;

/**
 * purhcase dao local interface
 * @author TOSHIBA
 */
@Local
public interface PurchaseFacadeLocal {

    /**
     * create a new entity of purhcase
     * @param purchase the new purchase object
     */
    void create(Purchase purchase);

    /**
     * update a purchase object
     * @param purchase the purchase object to be updated
     */
    void edit(Purchase purchase);

    /**
     * remove a purchase object
     * @param purchase the purchase object to be removed
     */
    void remove(Purchase purchase);

    /**
     * find a purhcase object
     * @param id the id of the purchase
     * @return the purchase object found, null if not found
     */
    Purchase find(Object id);

    /**
     * find all the purchases
     * 
     * @return a list of purhcases
     */
    List<Purchase> findAll();

    List<Purchase> findRange(int[] range);

    int count();
    
    /**
     * update the status of a purchase and sent out the sms notification to the corresponding account
     * @param p the purchase to be updated
     */    
    void updateStatus(Purchase p);
}
