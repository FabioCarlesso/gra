package com.fabiocarlesso.gra.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiocarlesso.gra.domain.Movie;
import com.fabiocarlesso.gra.repository.MovieRepository;
import com.fabiocarlesso.gra.service.exception.ObjectNotFoundException;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository repo;

    public List<Movie> findByWinner(){
        return repo.findByIsWinnerTrueOrderByMovieYearAsc();
    }

    public Movie find(Integer id) {
        Optional<Movie> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Movie.class.getName()));
    }

    public List<Movie> findAll(){
        return repo.findAll();
    }

    public Movie insert (Movie obj){
        obj.setId(null);
        return repo.save(obj);
    }

}