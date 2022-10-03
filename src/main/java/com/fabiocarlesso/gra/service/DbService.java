package com.fabiocarlesso.gra.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fabiocarlesso.gra.domain.Movie;
import com.fabiocarlesso.gra.domain.Producer;
import com.fabiocarlesso.gra.domain.Studio;
import com.fabiocarlesso.gra.repository.MovieRepository;
import com.fabiocarlesso.gra.repository.ProducerRepository;
import com.fabiocarlesso.gra.repository.StudioRepository;
import com.fabiocarlesso.gra.util.CsvConverter;

@Service
public class DbService {
    
    private static final Integer YEAR_INDEX = 0;
    private static final Integer TITLE_INDEX = 1;
    private static final Integer STUDIOS_INDEX = 2;
    private static final Integer PRODUCERS_INDEX = 3;
    private static final Integer WINNER_INDEX = 4;
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private StudioRepository studioRepository;

    public void instantiateDatabase() throws IOException {
        File file = ResourceUtils.getFile("classpath:movielist.csv");
        List<Producer> listProducers = new ArrayList<>();
        List<Studio> listStudios = new ArrayList<>();
        List<Movie> listMovies = new ArrayList<>();
        CsvConverter.getRecordsFromCsv(file).stream().skip(1).forEachOrdered(
            values -> {
                Boolean isWinner = this.isWinner(values);
                Movie movie = this.createMovie(values, isWinner);
                
                String[] producers = values[PRODUCERS_INDEX].split("( and |,)");
                List<Producer> listProducersMovie = new ArrayList<>();
                for (String prod : producers) {
                    if(!prod.isBlank()){
                        Producer producer = this.createProducer(prod);
                        producer.getMovies().addAll(Arrays.asList(movie));
                        Producer producerFound = this.checkProducers(listProducers, producer);
                        if(producerFound == null){
                            listProducers.add(producer);
                            listProducersMovie.add(producer);
                        } else {
                            listProducersMovie.add(producerFound);
                        }
                    }
                }

                String[] studios = values[STUDIOS_INDEX].split(",");
                List<Studio> listStudiosMovie = new ArrayList<>();
                for (String stu : studios) {
                    Studio studio = this.createStudio(stu);
                    studio.getMovies().addAll(Arrays.asList(movie));
                    Studio studioFound = this.checkStudios(listStudios, studio);
                    if(studioFound == null){
                        listStudios.add(studio);
                        listStudiosMovie.add(studio);
                    } else {
                        listStudiosMovie.add(studioFound);
                    }
                }
               
                movie.getProducers().addAll(listProducersMovie);
                movie.getStudios().addAll(listStudiosMovie);
                listMovies.add(movie);
            }
        );
        producerRepository.saveAll(listProducers);
        studioRepository.saveAll(listStudios);
        movieRepository.saveAll(listMovies);
    }

    private Boolean isWinner (String[] winnerColumn){
        Boolean isWinner = false;
        if (winnerColumn.length > 4 && winnerColumn[WINNER_INDEX] != null) {
            isWinner = true;
        }
        return isWinner;
    }

    private Movie createMovie (String[] info, Boolean isWinner){
        return new Movie(
            null,
            Integer.valueOf(info[YEAR_INDEX]),
            info[TITLE_INDEX].trim(),
            isWinner,
            new ArrayList<>(),
            new ArrayList<>());
    }

    private Producer createProducer (String info){
        return new Producer(
            null,
            info.trim(),
            new ArrayList<>()); 
    }

    private Producer checkProducers (List<Producer> listProducers, Producer producer){
        return listProducers.stream()
            .filter(element -> element.getProducerName().equals(producer.getProducerName()))
            .findAny()
            .orElse(null);
    }

    private Studio createStudio (String info){
        return new Studio(
            null,
            info.trim(),
            new ArrayList<>());
    }

    private Studio checkStudios (List<Studio> listStudios, Studio studio){
        return listStudios.stream()
            .filter(element -> element.getStudioName().equals(studio.getStudioName()))
            .findAny()
            .orElse(null);
    }
}
