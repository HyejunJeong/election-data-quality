package com.example.demo;

import com.example.demo.service.StateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;

/*
 * @created 27/04/2020 - 3:15 PM
 * @project IntelliJ IDEA
 * @author Hong Zheng, Hyejun Jeong
 */
@ComponentScan()
@EnableJpaRepositories()
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StateServiceTest {

  @Autowired
  StateService ss;

  @Test
  public void selectStateById() {
    var queryResult = ss.selectStateById("21").getId();
    System.out.println("queryResult = " + queryResult);
    Assert.assertThat(queryResult, is("21"));
  }
}
