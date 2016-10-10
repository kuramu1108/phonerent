/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.SimPlan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TOSHIBA
 */
@Local
public interface SimPlanFacadeLocal {

    void create(SimPlan simPlan);

    void edit(SimPlan simPlan);

    void remove(SimPlan simPlan);

    SimPlan find(Object id);

    List<SimPlan> findAll();

    List<SimPlan> findRange(int[] range);

    int count();
    
}
