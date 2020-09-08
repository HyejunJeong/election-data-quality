package com.example.demo.entity;

import com.example.demo.api.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity(name = "county")
@Table(name = "COUNTIES")
public class County {

  /** primary key for COUNTRY_TBL table */
  @Id
  @Column(length = 6)
  @JsonView(View.CountyView.class)
  private String id;

  /** String of coordinates -> geo data */
  @Column(columnDefinition="longtext")
  @JsonView(View.CountyCoords.class)
  private String coordinates;

  /** flag to determine whether this precinct is a ghost precinct */
  @Column(name = "has_error")
  @JsonView(View.CountyView.class)
  private boolean hasError;

  /** state that this county belongs to */
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnoreProperties("county")
  @JsonIgnore
  private State state;

  @Transient
  private Map<ElectionEnum, Integer> electionData;

  /** List of Precinct objects that belong to this county */
  @OneToMany(fetch = FetchType.LAZY, cascade = ALL, mappedBy = "county")
  @JsonView(View.PrecinctCoords.class)
  private List<Precinct> precincts;

  /** following are the demographic data in term of population of this precinct */
  @SuppressWarnings("JpaDataSourceORMInspection")
  @JsonView(View.CountyData.class)
  @Column(name = "african_american")
  private int africanAmer;

  @SuppressWarnings("JpaDataSourceORMInspection")
  @JsonView(View.CountyData.class)
  @Column(name = "native_american")
  private int nativeAmer;

  @SuppressWarnings("JpaDataSourceORMInspection")
  @JsonView(View.CountyData.class)
  @Column(name = "pacific_islanders")
  private int pasifika;

  @JsonView(View.CountyData.class)
  private int white;

  @JsonView(View.CountyData.class)
  private int asian;

  @JsonView(View.CountyData.class)
  private int others;

  //  @JsonView(View.CountyData.class)
  @Transient
  private int population;

  /** helper field for initialing the belonging state */
  @Transient private String stateId;
//
//  public String getStateId() {
//    return state.getId();
//  }
//  public void setStateId(String id) {
//    state.setId(id);
//  }

//  @Override
//  public String toString() {
//    return "cid: " + id +
//            "\nwhite: " + white +
//            "\nafricanAmer: " + africanAmer +
//            "\nasian: " + asian +
//            "\nnativeAmer: " + nativeAmer +
//            "\nothers: " + others +
//            "\npasifika: " + pasifika +
//            "\nstateid: " + state.getId();
//  }
}
