package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.SimPlan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class SimPlanFacade extends AbstractFacade<SimPlan> implements SimPlanFacadeLocal {

    @PersistenceContext(unitName = "phonerent-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SimPlanFacade() {
        super(SimPlan.class);
    }
    
}
