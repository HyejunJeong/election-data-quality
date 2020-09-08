package com.example.demo.service;

import com.example.demo.entity.Precinct;
import com.example.demo.entity.State;
import com.example.demo.entitymanager.StateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hong Zheng
 * @created 19/03/2020 - 4:14 PM
 * @project hozh-416-server
 */
@Service
public class StateService {

  @Autowired
  private StateEntityManager sem;

  /**
   * query a state by the given id
   *
   * @param id String type, using as a id to query the target state
   * @return query result by given id type State, return null if illegal arg exception raised
   * @throws IllegalArgumentException if arg of sem.findById is nullable
   */
  public State selectStateById(String id) {

    try {
      return sem.findById(id).orElse(null);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      return null;
    }
  }

  /**
   * return a collection of all the precinct records in the database
   *
   * @return query result type List<State>
   */
  public List<State> selectAllStates() {
    return sem.findAll();
  }


  /**
   * save a state object into database
   *
   * @param state State type
   * @return the saved State entity type State, return null if null pointer exception raised
   * @throws IllegalArgumentException if arg of sem.save is nullable
   */
  public State saveState(State state) {

    try {
      return sem.save(state);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      return null;
    }
  }
}
