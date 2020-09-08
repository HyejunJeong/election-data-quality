package com.example.demo.service;

import com.example.demo.entity.County;
import com.example.demo.entity.State;
import com.example.demo.entitymanager.CountyEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hong Zheng
 * @created 19/03/2020 - 4:14 PM
 * @project hozh-416-server
 */
@Service
public class CountyService {

  @Autowired
  private CountyEntityManager cem;
  @Autowired
  private StateService ss;

  /**
   * query a county by the given id
   *
   * @param id String type, using as a id to query the target county
   * @return query result by given id -> type county, return null if illegal arg exception raised
   * @throws IllegalArgumentException if arg of cem.findById is nullable
   */
  public County selectCountyById(String id) {

    try {
      return cem.findById(id).orElse(null);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      return null;
    }
  }

  /**
   * save a state object into database
   *
   * @param county County type
   * @return the saved County entity type County, return null if null pointer exception raised
   * @throws IllegalArgumentException if arg of ss.selectStateById is nullable
   */
  public County saveCounty(County county) {

//    try {
      // if the target county is not yet assigned to a state (case newly created county)
      // create a state by the stateId field in county object
      if (county.getState() == null) {
        var targetState = ss.selectStateById(county.getStateId());

        if (targetState == null) {
          targetState = new State();
          targetState.setId(county.getStateId());
          ss.saveState(targetState);
        }
        county.setState(targetState);
      }
      return cem.save(county);
//    } catch (Exception ex) {
//      System.err.println(ex.getMessage());
//      return null;
//    }
  }
}
