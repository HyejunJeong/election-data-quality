package com.example.demo;

import com.example.demo.entity.Precinct;
import com.example.demo.service.CountyService;
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

/*
 * @created 27/04/2020 - 3:15 PM
 * @project IntelliJ IDEA
 * @author Hong Zheng, Hyejun Jeong
 */


@ComponentScan()
@EnableJpaRepositories()
@SpringBootTest
@RunWith(SpringRunner.class)
public class CountyServiceTest {

  @Autowired
  CountyService cs;

  @PersistenceContext
  public EntityManager em;

  @Test
  public void selectCountyById() {
    var queryResult = cs.selectCountyById("21-117").getId();
    List l = em.createQuery(
            "select c from county c where c.id LIKE :cid")
            .setParameter("cid", queryResult)
            .getResultList();
    for(Object c:l){
      System.out.println(c);
    }
  //  Assert.assertThat(cs.selectCountyById("cid").getId(), is("cid"));
  }
}
