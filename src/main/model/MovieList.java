package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of movies
public class MovieList {
    private List<Movie> movieList;       // a movie list

    // EFFECTS : list is empty
    public MovieList() {
        movieList = new ArrayList<>();
    }

    // EFFECTS : returns the number of items in the list
    public int size() {
        return movieList.size();
    }

    // EFFECTS: Returns true if Movie m is in the MovieList
    // and false otherwise
    public boolean contains(Movie m) {
        return movieList.contains(m);
    }

    // MODIFIES: this
    // EFFECTS: Movie m is added to the MovieList if it's not
    // already in the list
    public void addMovie(Movie m) {
        if (!movieList.contains(m)) {
            movieList.add(m);
        }
    }

    // REQUIRES: Movie m is an element of the IntegerSet
    // MODIFIES: this
    // EFFECTS: Movie m is removed from the MovieList
    public void removeMovie(Movie m) {
        movieList.remove(m);

    }

    // EFFECTS : returns the MovieList
    public List<Movie> getList() {
        return movieList;
    }

    public Movie getMovie(String name) {
        for (Movie m : movieList) {
            if (m.getTitle().equals(name)) {
                return m;
            }
            return null;
        }
        return null;
    }


    public boolean emptyList() {
        return movieList.isEmpty();
    }


}

