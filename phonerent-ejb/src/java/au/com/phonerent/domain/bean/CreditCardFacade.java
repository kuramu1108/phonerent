/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.CreditCard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mac
 */
@Stateless
public class CreditCardFacade extends AbstractFacade<CreditCard> implements CreditCardFacadeLocal {

    @PersistenceContext(unitName = "phonerent-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditCardFacade() {
        super(CreditCard.class);
    }
    
}