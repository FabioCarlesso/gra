package com.fabiocarlesso.gra.resource.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabiocarlesso.gra.domain.Movie;
import com.fabiocarlesso.gra.domain.dto.MovieDTO;
import com.fabiocarlesso.gra.domain.dto.MovieProducerMinMaxDTO;
import com.fabiocarlesso.gra.service.MovieService;

@RestController
@RequestMapping(value="/v1/movies")
public class MovieResource {
    
    @Autowired
    private MovieService service;

    @GetMapping(value="/{id}")
    public ResponseEntity<MovieDTO> find(@PathVariable Integer id){
        Movie obj = service.find(id);
        return ResponseEntity.ok().body(new MovieDTO(obj));
    }

    @GetMapping()
    public ResponseEntity<List<MovieDTO>> findAll(){
        List<Movie> list = service.findAll();
        List<MovieDTO> listDto = list.stream().map(MovieDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/winners")
    public ResponseEntity<List<MovieDTO>> findWinner(){
        List<Movie> list = service.findByWinner();
        List<MovieDTO> listDto = list.stream().map(MovieDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping(value="/interval")
    public ResponseEntity<MovieProducerMinMaxDTO> findMinMaxInterval(){
        MovieProducerMinMaxDTO objDto = service.findByMovieWinnerInterval();
        return ResponseEntity.ok().body(objDto);
    }
}
