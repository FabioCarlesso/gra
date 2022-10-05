package com.fabiocarlesso.gra.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fabiocarlesso.gra.domain.Movie;
import com.fabiocarlesso.gra.domain.Producer;
import com.fabiocarlesso.gra.domain.Studio;

import lombok.Data;

@Data
public class MovieDTO {
    
    private Integer id;
    private Integer movieYear;
    private String title;
    private Boolean isWinner;
    private List<Producer> producers = new ArrayList<>();
    private List<Studio> studios = new ArrayList<>();

    public MovieDTO(Movie obj){
        this.id = obj.getId();
        this.movieYear = obj.getMovieYear();
        this.title = obj.getTitle();
        this.isWinner = obj.getIsWinner();
        this.getProducers().addAll(obj.getProducers());
        this.getStudios().addAll(obj.getStudios());
    }
}
