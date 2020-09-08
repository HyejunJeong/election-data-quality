package com.example.demo.entity;

import com.example.demo.api.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static com.example.demo.entity.ElectionEnum.CONGRESSIONAL_16_DEM;
import static javax.persistence.CascadeType.ALL;

/**
 * @author Hong Zheng, Hyejun Jeong
 * @created 19/03/2020 - 4:14 PM
 * @project hozh-416-server
 */

@Data
@NoArgsConstructor
@Entity(name = "precinct")
@Table(name = "PRECINCTS")
public class Precinct {

  /** primary key for PRECINCT_TBL */
  @Id
  @Column(length = 128)
  @JsonView(View.PrecinctView.class)
  private String id;

  /** flag to determine whether this precinct is a ghost precinct */
  @Column(name = "is_ghost")
  @JsonView(View.PrecinctCoords.class)
  private boolean ghost;

  /** flag to determine whether this precinct has an error */
  @Column(name = "has_error")
  @JsonView(View.PrecinctCoords.class)
  private boolean error;

  /** flag to determine whether this precinct contains multiple border error */
  @Column(name = "has_multiple_border")
  @JsonView(View.PrecinctData.class)
  private boolean multipleBorder;

  /** String of coordinates -> geo data */
  @Column(columnDefinition="longtext")
  @JsonView(View.PrecinctCoords.class)
  private String coordinates;

  /** county that this precinct belongs to */
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private County county;

  /** election data of this precinct */
  @SuppressWarnings("JpaDataSourceORMInspection")
  @JsonView(View.PrecinctData.class)
  @ElementCollection
  @CollectionTable(name = "ELECTION_DATA")
  @MapKeyEnumerated(EnumType.STRING)
  @MapKeyColumn(name="election_type", length = 20)
  @Column(name="election_result")
  private Map<ElectionEnum, Integer> electionData;

  /** list of precinct's ids for which adjacent to this precinct */
  @SuppressWarnings("JpaDataSourceORMInspection")
  @JsonView(View.PrecinctData.class)
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "ADJACENT_PRECINCTS")
  @Column(name = "adjacent_precinct_id",length=128)
  private List<String> adjPrecIds;

  /** list of precinct's ids for which enclosing to this precinct -> used for determine errors */
  @SuppressWarnings("JpaDataSourceORMInspection")
  @JsonView(View.PrecinctData.class)
  @ElementCollection
  @CollectionTable(name = "ENCLOSING_PRECINCTS")
  @Column(name = "enclosing_precinct_id",length=128)
  private List<String> enclPrecIds;

  /** list of precinct's ids for which intersecting with this precinct */
  @SuppressWarnings("JpaDataSourceORMInspection")
  @JsonView(View.PrecinctData.class)
  @ElementCollection//(fetch = FetchType.EAGER)
  @CollectionTable(name = "INTERSECTING_PRECINCTS")
  @Column(name = "intersecting_precinct_id",length=128)
  private List<String> interPrecIds;

  /** map for log messages */
  @OneToMany(fetch = FetchType.LAZY, cascade = ALL, mappedBy = "precinct")
  @JsonView(View.PrecinctData.class)
  private List<Log> logBag;

  /** following are the help fields of the object which won't be persist in the database */
  @Transient
  @JsonView(View.PrecinctData.class)
  private String canonicalName;
  
  /** help field for mapping the precinct to its belonging county */
  @Transient 
  private String stateId;
  
  /** help field for mapping the precinct's belonging county to its belonging state */
  @Transient 
  private String countyId;

  /** flag to determine whether to update this precinct's belonging county's demographic data */
  @Transient
  @JsonView(View.PrecinctData.class)
  private boolean demoModified;

  /**
   * following are the demographic population help fields, can be ignore if demographicDataModified
   * is set to false
   */
  @Transient
  @JsonView(View.PrecinctData.class)
  private int white;

  @Transient
  @JsonView(View.PrecinctData.class)
  private int africanAmer;

  @Transient
  @JsonView(View.PrecinctData.class)
  private int asian;

  @Transient
  @JsonView(View.PrecinctData.class)
  private int nativeAmer;

  @Transient
  @JsonView(View.PrecinctData.class)
  private int others;

  @Transient
  @JsonView(View.PrecinctData.class)
  private int pasifika;

  //  @JsonView(View.PrecinctData.class)
  @Transient
  private int population;


  @Transient String mergeHolder;


//  public int getWhite() {
//    return county.getWhite();
//  }
//  public int getAfricanAmer() {
//    return county.getAfricanAmer();
//  }
//  public int getAsian() {
//    return county.getAsian();
//  }
//  public int getNativeAmer() {
//    return county.getNativeAmer();
//  }
//  public int getOthers() {
//    return county.getOthers();
//  }
//  public int getPasifika() {
//    return county.getPasifika();
//  }
//  public String getStateId() {
//    return county.getStateId();
//  }
//  public String getCountyId() {
//    return county.getId();
//  }
//
//  public void setCountyId(String id) {
//    county.setId(id);
//  }

//  @Override
//  public String toString() {
//    return "pid: " + id +
//            "\ncoord: " + coordinates +
//            "\nisghost: " + ghost +
//            "\nhasmultiborder: " + multipleBorder;
//  }
}
