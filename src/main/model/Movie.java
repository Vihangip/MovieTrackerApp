package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Movie with a Title,Year and Genre
// Some code is taken and/or modified from JsonSerializationDemo
public class Movie implements Writable {
    private String title;                        // Title of the movie
    private int year;                            // Release year of movie
    private String genre;                        // Genre of Movie


    //    REQUIRES : movieName and movieGenre has non zero length, movieYear > 1800
    //    EFFECTS  : title of movie is set to movieName, year is set to movieYear
    //               and genre is set to movieGenre
    public Movie(String movieName, int movieYear, String movieGenre) {
        title = movieName;
        year = movieYear;
        genre = movieGenre;
    }

    // EFFECTS : returns movie title of given movie
    public String getTitle() {
        return title;
    }

    // EFFECTS : returns released year of given movie
    public int getYear() {
        return year;
    }

    // EFFECTS : returns genre of given movie
    public String getGenre() {
        return genre;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", title);
        json.put("Year", year);
        json.put("Genre", genre);
        return json;
    }

}
