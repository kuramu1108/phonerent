/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.web;

import au.com.phonerent.domain.utility.ExpiryTimer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author mac
 */
@Named
@ApplicationScoped
public class DailyCheckController {
    @EJB
    ExpiryTimer timer;

    private int test = 1;
    public int getTest(){
        return test;
    }
    
    public void starTimer() {
        
    }
}