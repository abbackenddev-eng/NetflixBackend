package com.videostreaming.movieservice.service;

import com.videostreaming.movieservice.entity.Movie;
import com.videostreaming.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie createMovie(Movie movie){
        log.info("Creating movie: title={}, genre={}, duration={}",
                movie.getTitle(),
                movie.getGenre(),
                movie.getDuration());
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        log.info("Fetching all Movies");
        return movieRepository.findAll();
    }


}
