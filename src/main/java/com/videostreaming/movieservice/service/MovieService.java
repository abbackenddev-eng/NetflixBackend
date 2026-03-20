package com.videostreaming.movieservice.service;

import com.videostreaming.movieservice.dto.MovieRequest;
import com.videostreaming.movieservice.dto.MovieResponse;
import com.videostreaming.movieservice.entity.Movie;
import com.videostreaming.movieservice.exception.ResourceNotFoundException;
import com.videostreaming.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieResponse createMovie(MovieRequest request){

        Movie movie = new Movie();

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setGenre(request.getGenre());
        movie.setDuration(request.getDuration());
        movie.setVideoUrl(request.getVideoUrl());

        Movie savedMovie = movieRepository.save(movie);

        return mapToResponse(savedMovie);
    }

    private MovieResponse mapToResponse(Movie movie) {

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getGenre(),
                movie.getDuration(),
                movie.getVideoUrl()
        );
    }

    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MovieResponse updateMovie(Long id, MovieRequest request){

        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found with id" + id));

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setGenre(request.getGenre());
        movie.setDuration(request.getDuration());
        movie.setVideoUrl(request.getVideoUrl());

        Movie updatedMovie = movieRepository.save(movie);

        return mapToResponse(updatedMovie);
    }

    public MovieResponse getMovieById(Long id){

        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found with id:" + id));

        return mapToResponse(movie);

    }

    public Page<MovieResponse> getAllMovies(int page, int size){

        Pageable pageable= PageRequest.of(page, size);

        Page<Movie> moviePage = movieRepository.findAll(pageable);

        return moviePage.map(this::mapToResponse);
    }

    public void deleteMovie(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not exist with id" + id));

        movieRepository.delete(movie);
    }


}
