/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.CreditCard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mac
 */
@Local
public interface CreditCardFacadeLocal {

    void create(CreditCard creditCard);

    void edit(CreditCard creditCard);

    void remove(CreditCard creditCard);

    CreditCard find(Object id);

    List<CreditCard> findAll();

    List<CreditCard> findRange(int[] range);

    int count();
    
}
