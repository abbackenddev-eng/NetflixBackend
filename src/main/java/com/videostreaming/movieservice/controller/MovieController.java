package com.videostreaming.movieservice.controller;

import com.videostreaming.movieservice.dto.MovieRequest;
import com.videostreaming.movieservice.dto.MovieResponse;
import com.videostreaming.movieservice.entity.Movie;
import com.videostreaming.movieservice.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;


    @PostMapping
    public MovieResponse createMovie(@Valid @RequestBody MovieRequest request){
        return movieService.createMovie(request);
    }

    @GetMapping
    public List<MovieResponse> getAllMovies(){
        return movieService.getAllMovies();
    }


    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }
}
