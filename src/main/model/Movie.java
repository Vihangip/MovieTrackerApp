package model;

// Represents a Movie with a Title,Production Year and Genre
public class Movie {
    private String title;                        // Title of the movie
    private int proYear;                         // Production year of movie
    private String genre;                        // Genre of Movie



//    REQUIRES : movieName and movieGenre has non zero length, productionYear > 1800
//    EFFECTS  : title of movie is set to movieName, proYear is set to productionYear
//               and genre is set to movieGenre

    public Movie(String movieName, int productionYear, String movieGenre) {
        title = movieName;
        proYear = productionYear;
        genre = movieGenre;


    }

    public Movie createMovie(String name,int year, String genre) {
        this.title = name;
        this.proYear = year;
        this.genre = genre;
        return new Movie(this.title, this.proYear,this.genre);
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return proYear;
    }

    public String getGenre() {
        return genre;
    }

}
