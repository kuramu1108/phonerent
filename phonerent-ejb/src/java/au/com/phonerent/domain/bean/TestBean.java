/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author mac
 */
@Stateless
public class TestBean {
    @PersistenceContext
    EntityManager em;
}
