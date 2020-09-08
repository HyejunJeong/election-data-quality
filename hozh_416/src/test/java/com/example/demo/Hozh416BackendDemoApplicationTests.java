package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ComponentScan()
@EnableJpaRepositories()
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class Hozh416BackendDemoApplicationTests {

  @Test
  void contextLoads() {}
}
