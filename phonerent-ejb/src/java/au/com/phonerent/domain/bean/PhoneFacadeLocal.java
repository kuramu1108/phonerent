package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Phone;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TOSHIBA
 */
@Local
public interface PhoneFacadeLocal {

    /**
     * create a new phone object
     * @param phone the phone to be create
     */
    void create(Phone phone);

    /**
     * update a phone object
     * @param phone the phone to be update
     */
    void edit(Phone phone);

    /**
     * remove a phone object
     * @param phone the phone to be removed
     */
    void remove(Phone phone);

    /**
     * find a phone object
     * @param id the id of the phone
     * @return the phone found, null if not found
     */
    Phone find(Object id);

    /**
     * find all the phones
     * @return a list of phones
     */
    List<Phone> findAll();

    List<Phone> findRange(int[] range);

    int count();
    
}
