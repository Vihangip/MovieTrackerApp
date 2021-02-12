package model;

// Represents a Movie with a Title,Year and Genre
public class Movie {
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

    // EFFECTS : creates a new movie with given title,year and genre
    public Movie createMovie(String name, int year, String genre) {
        this.title = name;
        this.year = year;
        this.genre = genre;
        return new Movie(this.title, this.year, this.genre);
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

}
