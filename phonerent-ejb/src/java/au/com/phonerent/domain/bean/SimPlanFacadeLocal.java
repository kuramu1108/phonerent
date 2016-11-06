package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.SimPlan;
import java.util.List;
import javax.ejb.Local;

/**
 * Sim plan DAO local interface
 * @author TOSHIBA
 */
@Local
public interface SimPlanFacadeLocal {

    /**
     * create a new sim plan
     * @param simPlan the new simplan
     */
    void create(SimPlan simPlan);

    /**
     * update a sim plan
     * @param simPlan the sim plan to be update
     */
    void edit(SimPlan simPlan);

    /**
     * remove the sim object
     * @param simPlan the sim to be removed
     */
    void remove(SimPlan simPlan);

    /**
     * find a sim object
     * @param id the id of the sim
     * @return the sim object found, null if not found
     */
    SimPlan find(Object id);

    /**
     * find all the sims
     * @return a list of all the sims
     */
    List<SimPlan> findAll();

    List<SimPlan> findRange(int[] range);

    int count();
    
}
