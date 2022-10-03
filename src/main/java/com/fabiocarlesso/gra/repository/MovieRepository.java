package com.fabiocarlesso.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiocarlesso.gra.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
}
