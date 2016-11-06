package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.CreditCard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mac
 */
@Local
public interface CreditCardFacadeLocal {

    /**
     * create an credit card entity and persist into database
     * @param creditCard the new creditcard
     */
    void create(CreditCard creditCard);

    /**
     * update an credit card entity
     * @param creditCard the creditcard to be edit
     */
    void edit(CreditCard creditCard);

    /**
     * remove an credit card object
     * @param creditCard the creditcard to be removed
     */
    void remove(CreditCard creditCard);

    /**
     * find the credit card in the database
     * @param id the id of th credit card
     * @return the credit card found, null if not found
     */
    CreditCard find(Object id);

    /**
     * get all the credit card objects
     * @return the list of credit cards
     */
    List<CreditCard> findAll();

    List<CreditCard> findRange(int[] range);

    int count();
}
