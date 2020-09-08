package com.example.demo.entitymanager;

import com.example.demo.entity.County;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hong Zheng, Hyejun Jeong
 * @created 19/03/2020 - 4:14 PM
 * @project hozh-416-server
 */

@Repository("cem")
public interface CountyEntityManager extends JpaRepository<County, String> {}
