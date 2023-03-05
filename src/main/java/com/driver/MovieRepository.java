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

    public void addMovieDirectorPair(String movie_name, String director_name){
        List<String> moviesList = new ArrayList<>();
        if(movieDirectorHashMap.containsKey(director_name)){
            moviesList = movieDirectorHashMap.get(director_name);
            moviesList.add(movie_name);
            movieDirectorHashMap.put(director_name,moviesList);
        }
        else{
            moviesList.add(movie_name);
            movieDirectorHashMap.put(director_name,moviesList);
        }
    }

    public  Movie getMovieByName(String movie_name){
        if (movieHashMap.containsKey(movie_name)){
            return movieHashMap.get(movie_name);
        }
        else {
            return null;
        }
    }

    public  Director getDirectorByName(String directorName){
        if (directorHashMap.containsKey(directorName)){
            return directorHashMap.get(directorName);
        }
        else {
            return null;
        }
    }

    public List<String> getMoviesByDirectorName(String directorName){
        if(movieDirectorHashMap.containsKey(directorName)){
            return movieDirectorHashMap.get(directorName);
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

    public void deleteDirectorByName(String director_name) {
        if(movieDirectorHashMap.containsKey(director_name)){
            List<String> movies = movieDirectorHashMap.get(director_name);

            for(String movie: movies){
                if(movieHashMap.containsKey(movie)){
                    movieHashMap.remove(movie);
                }
            }
            movieDirectorHashMap.remove(director_name);
        }

        if(directorHashMap.containsKey(director_name)){
            directorHashMap.remove(director_name);
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