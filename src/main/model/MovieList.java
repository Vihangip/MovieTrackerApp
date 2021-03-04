package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of movies
public class MovieList implements Writable {
    private List<Movie> movieList;       // a movie list
    private String name;

    // EFFECTS : list is empty
    public MovieList(String name) {
        this.name = name;
        movieList = new ArrayList<>();
    }

    public String getName() {
        return name;
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

    public void setList(List<Movie> list) {
        movieList = list;

    }

    // EFFECTS : If the given string matches a movie title in the Movie List, returns the movie;
    // else returns null
    public Movie getMovie(String name) {
        for (Movie m : movieList) {
            if (m.getTitle().equals(name)) {
                return m;
            }
        }
        return null;
    }

    // EFFECTS : returns true if the movie list is empty, false otherwise
    public boolean emptyList() {
        return movieList.isEmpty();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Lists", movieListsToJson());
        json.put("Type", name);
        return json;
    }

    // EFFECTS: returns movie lists in this movie library as a JSON array
    public JSONArray movieListsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie movie : movieList) {
            jsonArray.put(movie.toJson());
        }

        return jsonArray;
    }
}

