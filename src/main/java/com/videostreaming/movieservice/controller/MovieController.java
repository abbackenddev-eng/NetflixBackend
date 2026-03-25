package com.videostreaming.movieservice.controller;

import com.videostreaming.movieservice.dto.ApiResponse;
import com.videostreaming.movieservice.dto.MovieRequest;
import com.videostreaming.movieservice.dto.MovieResponse;
import com.videostreaming.movieservice.dto.PagedResponse;
import com.videostreaming.movieservice.entity.Movie;
import com.videostreaming.movieservice.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PutMapping("/{id}")
    public MovieResponse updateMovie(@PathVariable Long id,@Valid @RequestBody MovieRequest request){
        return movieService.updateMovie(id, request);
    }


    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

//    @GetMapping
//    public Page<MovieResponse> getAllMovies(@RequestParam(defaultValue = "0") int page,
//                                            @RequestParam(defaultValue = "5") int size){
//        return movieService.getAllMovies(page, size);
//    }

    @GetMapping
    public ApiResponse<PagedResponse<List<MovieResponse>>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        PagedResponse<List<MovieResponse>> response = movieService.getAllMovies(page,size);

        return new ApiResponse<>(
                true,
        "Movies fetched Successfully",
                response
                );
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return "Movie deleted successfully";
    }
}
