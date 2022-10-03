package com.fabiocarlesso.gra.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer movieYear;
    private String title;

    @ManyToMany
    @JoinTable(name = "MOVIE_STUDIO")
    private List<Studio> studios = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "MOVIE_PRODUCER")
    private List<Producer> producers = new ArrayList<>();

}
