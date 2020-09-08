package com.example.demo.entity;

import com.example.demo.api.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Hong Zheng, Hyejun Jeong
 * @created 19/03/2020 - 4:14 PM
 * @project hozh-416-server
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "state")
@Table(name = "STATES")
public class State {
  /** primary key for STATE_TBL table */
  @Id
  @Column(length = 2)
  @JsonView(View.StateView.class)
  private String id;
  
  /** String of coordinates -> geo data */
  @Column(columnDefinition="longtext")
  @JsonView(View.StateView.class)
  private String coordinates;

  /** List of County objects that belong to this state */
  @JsonView(View.CountyCoords.class)
  @OneToMany(fetch = FetchType.LAZY, cascade = ALL, mappedBy = "state")
  private List<County> counties;

  @Transient
  private Map<ElectionEnum, Integer> electionData;

  @Transient
  private int white;

  @Transient
  private int africanAmer;

  @Transient
  private int asian;

  @Transient
  private int nativeAmer;

  @Transient
  private int others;

  @Transient
  private int pasifika;

  // @JsonView(View.StateData.class)
  @Transient
  private int population;

}
