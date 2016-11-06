package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Phone;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Phone DAO implementation
 * @author TOSHIBA
 */
@Stateless
public class PhoneFacade extends AbstractFacade<Phone> implements PhoneFacadeLocal {

    @PersistenceContext(unitName = "phonerent-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhoneFacade() {
        super(Phone.class);
    }
    
}
