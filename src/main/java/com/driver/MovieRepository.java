package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    HashMap<String,Movie> movieHashMap = new HashMap<>();
    HashMap<String, Director> directorHashMap = new HashMap<>();

    HashMap<String, List<String>> movieDirectorHashMap = new HashMap<>();

    public void addMovie(Movie movie){
        movieHashMap.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        directorHashMap.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName){
        List<String> moviesList = new ArrayList<>();
        if(movieDirectorHashMap.containsKey(directorName)){
            moviesList = movieDirectorHashMap.get(directorName);
            moviesList.add(movieName);
            movieDirectorHashMap.put(directorName,moviesList);
        }
        else{
            moviesList.add(movieName);
            movieDirectorHashMap.put(directorName,moviesList);
        }
    }

    public  Movie getMovieByName(String movieName){
        if (movieHashMap.containsKey(movieName)){
            return movieHashMap.get(movieName);
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


    public void deleteDirectorByName(String directorName) {
        if(movieDirectorHashMap.containsKey(directorName)){
            List<String> movies = movieDirectorHashMap.get(directorName);

            for(String movie: movies){
                if(movieHashMap.containsKey(movie)){
                    movieHashMap.remove(movie);
                }
            }
            movieDirectorHashMap.remove(directorName);
        }

        if(directorHashMap.containsKey(directorName)){
            directorHashMap.remove(directorName);
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