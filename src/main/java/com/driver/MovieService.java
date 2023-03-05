package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie)
    {
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director)
    {
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String movie, String diector)
    {
        movieRepository.addMovieDirectorPair(movie,diector);
    }

    public Movie getMovieByName(String movie)
    {
       return movieRepository.getMovieByName(movie);
    }

    public Director getDirectorByName(String director)
    {
        return movieRepository.getDirectorByName(director);
    }

    public List<String> getMoviesByDirectorName(String direcotr)
    {
        return movieRepository.getMoviesByDirectorName(direcotr);
    }

    public List<String> findAllMovies()
    {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String director)
    {
        movieRepository.deleteDirectorByName(director);
    }

    public void deleteAllDirectors()
    {
        movieRepository.deleteAllDirectors();
    }
}
