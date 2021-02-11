package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of movies to watch
public class ToWatchList {
    private List<Movie> toWatch;

    // EFFECTS : List is empty
    public ToWatchList() {
        toWatch = new ArrayList<>();
    }

    public int size() {
        return toWatch.size();
    }

    public boolean contains(Movie m) {
        return toWatch.contains(m);
    }

    public void addMovie(Movie m) {
        if (!toWatch.contains(m)) {
            toWatch.add(m);
        }
    }

    public void removeMovie(Movie m) {
        toWatch.remove(m);

    }

}

