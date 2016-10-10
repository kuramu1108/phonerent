/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.TestEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TOSHIBA
 */
@Local
public interface TestEntityFacadeLocal {

    void create(TestEntity testEntity);

    void edit(TestEntity testEntity);

    void remove(TestEntity testEntity);

    TestEntity find(Object id);

    List<TestEntity> findAll();

    List<TestEntity> findRange(int[] range);

    int count();
    
}
