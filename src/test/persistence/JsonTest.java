package persistence;

import model.Movie;
import model.MovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String title,Integer year,String genre,Movie movie) {
        assertEquals(title,movie.getTitle());
        assertEquals(year,movie.getYear());
        assertEquals(genre,movie.getGenre());
    }
}
