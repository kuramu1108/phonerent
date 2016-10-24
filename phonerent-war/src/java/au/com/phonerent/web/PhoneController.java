/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.web;

import au.com.phonerent.domain.Phone;
import au.com.phonerent.domain.bean.PhoneFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author mac
 */
@Named
@ApplicationScoped
public class PhoneController {
    @EJB
    PhoneFacade phoneFacade;
    
    public List<Phone> getAllPhones() {
        return phoneFacade.findAll();
    }
}
