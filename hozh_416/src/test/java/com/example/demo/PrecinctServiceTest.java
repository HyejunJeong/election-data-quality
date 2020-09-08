package com.example.demo;

import com.example.demo.entity.Precinct;
import com.example.demo.entitymanager.PrecinctEntityManager;
import com.example.demo.service.CountyService;
import com.example.demo.service.PrecinctService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

// todo test will be independent to the current database later
/*
 * @created 27/04/2020 - 3:16 PM
 * @project IntelliJ IDEA
 * @author Hong Zheng, Hyejun Jeong
 */

@ComponentScan()
@EnableJpaRepositories()
@SpringBootTest
@RunWith(SpringRunner.class)
public class PrecinctServiceTest {

  @PersistenceContext
  public EntityManager em;
  @Autowired
  PrecinctService ps;
  @Autowired
  CountyService cs;
  @Autowired
  private PrecinctEntityManager pem;

  @Test
  public void selectPrecinctById() {

    var queryResult = ps.selectPrecinctById("21-117-B123").getId();
    System.out.println("queryResult = " + queryResult);

    List l = em.createQuery(
            "select p from precinct p where p.id LIKE :pid")
            .setParameter("pid", queryResult)
            .getResultList();
    for (Object p : l) {
      System.out.println(p);
    }

//    Assert.assertThat(queryResult, is("21-117-B123"));
  }

  @Test
  public void updatePrecinct() {
    String pid = "21-117-B123";
    Precinct current = ps.selectPrecinctById(pid);
    System.err.println(1231231223);

    System.err.println(current.getAdjPrecIds());
    System.err.println(1231231223);

    current.setGhost(true);
    ps.updatePrecinct(current);
    Assert.assertThat(ps.selectPrecinctById(pid).isGhost(), is(true));
    //System.out.println("\nAFTER: " + current);
  }
}