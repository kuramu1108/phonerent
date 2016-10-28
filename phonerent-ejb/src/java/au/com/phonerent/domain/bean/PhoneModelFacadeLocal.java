/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.PhoneModel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mac
 */
@Local
public interface PhoneModelFacadeLocal {

    void create(PhoneModel phoneModel);

    void edit(PhoneModel phoneModel);

    void remove(PhoneModel phoneModel);

    PhoneModel find(Object id);

    List<PhoneModel> findAll();

    List<PhoneModel> findRange(int[] range);

    int count();
    
}
