package com.fabiocarlesso.gra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiocarlesso.gra.domain.Movie;
import com.fabiocarlesso.gra.domain.dto.MovieProducerIntervalDTO;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
    List<Movie> findByIsWinnerTrueOrderByMovieYearAsc();

    @Query("SELECT new com.fabiocarlesso.gra.domain.dto.MovieProducerIntervalDTO(p.producerName, "
                + "MAX(m.movieYear), "
                + "MIN(m.movieYear), "
                + "(MAX(m.movieYear) - MIN(m.movieYear))) "
            + "FROM Movie as m "
            + "INNER JOIN m.producers p "
            + "WHERE m.isWinner = TRUE "
            + "GROUP BY p.producerName "
            + "HAVING COUNT (m.title) > 1 "
            + "ORDER BY (MAX(m.movieYear) - MIN(m.movieYear))")
    List<MovieProducerIntervalDTO> findProducersInterval();
    
}

// SQL QUERY
// SELECT p.PRODUCER_NAME, MAX(m.MOVIE_YEAR), MIN(m.MOVIE_YEAR), (MAX(m.MOVIE_YEAR) - MIN(m.MOVIE_YEAR))
// FROM MOVIE as m
// INNER JOIN MOVIE_PRODUCER as mp ON m.ID = mp.MOVIES_ID
// INNER JOIN PRODUCER as p ON mp.PRODUCERS_ID = p.ID
// WHERE m.IS_WINNER = TRUE
// GROUP BY p.PRODUCER_NAME
// HAVING COUNT (m.title) > 1
// ORDER BY (MAX(m.MOVIE_YEAR) - MIN(m.MOVIE_YEAR))