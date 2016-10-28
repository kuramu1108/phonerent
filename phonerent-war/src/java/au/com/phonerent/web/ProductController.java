/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.web;

import au.com.phonerent.domain.Phone;
import au.com.phonerent.domain.PhoneModel;
import au.com.phonerent.domain.SimPlan;
import au.com.phonerent.domain.bean.PhoneFacade;
import au.com.phonerent.domain.bean.PhoneModelFacade;
import au.com.phonerent.domain.bean.SimPlanFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author mac
 */
@Named
@SessionScoped
public class ProductController implements Serializable {
    @EJB
    PhoneFacade phoneFacade;
    
    @EJB
    SimPlanFacade simplanFacade;
    
    @EJB
    PhoneModelFacade phoneModelFacade;
    
    private Phone phone = new Phone();
    private SimPlan simplan = new SimPlan();
    private PhoneModel phoneModel = new PhoneModel();
    
    public List<Phone> getAllPhones() {
        return phoneFacade.findAll();
    }
    
    public Phone getPhone() {
        return phone;
    }
    
    public List<SimPlan> getAllSimPlans() {
        return simplanFacade.findAll();
    }
    
    public SimPlan getSimPlan() {
        return simplan;
    }
    
    public List<PhoneModel> getAllPhoneModels() {
        return phoneModelFacade.findAll();
    }
    
    public PhoneModel getPhoneModel () {
        return phoneModel;
    }
    
    public void loadPhone(int id) {
        phone = phoneFacade.find(id);
    }
    
    public void loadSimPlan(int id) {
        simplan = simplanFacade.find(id);
    }
    
    public void loadPhoneModel(int id) {
        phoneModel = phoneModelFacade.find(id);
    }
    
    public void addPhone() {
        phoneFacade.create(phone);
    }
    
    public void addSimPlan() {
        simplanFacade.create(simplan);
    }
    
    public void addPhoneModel() {
        phoneModelFacade.create(phoneModel);
    }
    
    public void editPhone() {
        phoneFacade.edit(phone);
    }
    
    public void editSimPlan() {
        simplanFacade.edit(simplan);
    }
    
    public void editPhoneModel() {
        phoneModelFacade.edit(phoneModel);
    }
    
    public void deletePhone() {
        phoneFacade.remove(phone);
    }
    
    public void deleteSimPlan() {
        simplanFacade.remove(simplan);
    }
    
    public void deletePhoneModel() {
        phoneModelFacade.remove(phoneModel);
    }
    
    
}
