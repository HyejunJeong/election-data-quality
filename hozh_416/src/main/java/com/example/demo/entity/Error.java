package com.example.demo.entity;

import com.example.demo.api.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "error")
@Table(name = "ERRORS")
public class Error {

    /** primary key for ERRORS table */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment id
    @JsonView(View.ErrorView.class)
    private int id;

    /** following are the demographic data in term of population of this precinct */
    @SuppressWarnings("JpaDataSourceORMInspection")
    @JsonView(View.ErrorView.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "error_type", length = 10)
    private ErrorEnum errorType;

    @ManyToOne
    @JoinColumn(name="pid1", nullable = false)
    private Precinct precinct1;

    @ManyToOne
    @JoinColumn(name="pid2")
    private Precinct precinct2;

    @Override
    public String toString() {
        return "pid: " + id +
                "\nerr_type: " + errorType +
                "\npid1: " + precinct1.getId() +
                "\npid2: " + precinct2.getId();
    }
}
