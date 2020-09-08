package com.example.demo.entity;

import com.example.demo.api.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "log")
@Table(name = "LOGS")
public class Log {
    /** primary key for ERRORS table */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment id
    @JsonView(View.PrecinctData.class)
    private int id;

    @JsonView(View.PrecinctData.class)
    @Enumerated(EnumType.STRING)
    private ErrorEnum type;

    @JsonView(View.PrecinctData.class)
    private Timestamp time;

    @JsonView(View.PrecinctData.class)
    private String comment;

    @Column(columnDefinition="longtext")
    @JsonView(View.PrecinctData.class)
    private String oldval;

    @Column(columnDefinition="longtext")
    @JsonView(View.PrecinctData.class)
    private String newval;

    @ManyToOne
    @JoinColumn(name="precinct_id", nullable=false)
    private Precinct precinct;
}
