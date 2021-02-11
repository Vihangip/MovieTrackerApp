package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of movies already watched
public class WatchedList {
    private List<Movie> watched;

    public WatchedList() {
        watched = new ArrayList<>();
    }

    public int size() {
        return watched.size();
    }

    public boolean contains(Movie m) {
        return watched.contains(m);
    }

    public void addMovie(Movie m) {
        if (!watched.contains(m)) {
            watched.add(m);
        }
    }

    public void removeMovie(Movie m) {
        watched.remove(m);

    }

}
