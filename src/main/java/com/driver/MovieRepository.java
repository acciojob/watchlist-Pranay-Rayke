package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String,Movie> movieHashMap = new HashMap<>();
    HashMap<String, Director> directorHashMap = new HashMap<>();

    HashMap<String, List<String>> movieDirectorHashMap = new HashMap<>();

    public void addMovie(Movie movie){
        String name = movie.getName();
        movieHashMap.put(name,movie);
    }

    public void addDirector(Director director){
        String name = director.getName();
        directorHashMap.put(name, director);
    }

    public void addMovieDirectorPair(String movie, String director){
        List<String> moviesList = new ArrayList<>();
        if(movieDirectorHashMap.containsKey(director)){
            moviesList = movieDirectorHashMap.get(director);
            moviesList.add(movie);
            movieDirectorHashMap.put(director,moviesList);
        }
        else{
            moviesList.add(movie);
            movieDirectorHashMap.put(director,moviesList);
        }
    }

    public  Movie getMovieByName(String movie){
        if (movieHashMap.containsKey(movie)){
            return movieHashMap.get(movie);
        }
        else {
            return null;
        }
    }

    public  Director getDirectorByName(String director){
        if (directorHashMap.containsKey(director)){
            return directorHashMap.get(director);
        }
        else {
            return null;
        }
    }

    public List<String> getMoviesByDirectorName(String director){
        if(movieDirectorHashMap.containsKey(director)){
            return movieDirectorHashMap.get(director);
        }
        else{
            return null;
        }
    }

    public List<String> findAllMovies(){
        List<String> moviesList = new ArrayList<>();

        for (Map.Entry<String,Movie> movieEntry : movieHashMap.entrySet()){
            Movie movie = (movieEntry.getValue());
            moviesList.add(movie.getName());
        }
        return moviesList;
    }

    public void deleteDirectorByName(String director) {
        if(movieDirectorHashMap.containsKey(director)){
            List<String> movies = movieDirectorHashMap.get(director);

            for(String movie: movies){
                if(movieHashMap.containsKey(movie)){
                    movieHashMap.remove(movie);
                }
            }
            movieDirectorHashMap.remove(director);
        }

        if(directorHashMap.containsKey(director)){
            directorHashMap.remove(director);
        }
    }

    public void deleteAllDirectors(){
        for(String director: directorHashMap.keySet()){
            List<String> movies = movieDirectorHashMap.get(director);

            for(String movie: movies){
                if(movieHashMap.containsKey(movie)){
                    movieHashMap.remove(movie);
                }
            }
            if (movieDirectorHashMap.containsKey(director)){
                movieDirectorHashMap.remove(director);
            }
            directorHashMap.remove(director);
        }
    }
}