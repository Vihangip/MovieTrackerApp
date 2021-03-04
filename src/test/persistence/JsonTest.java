package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Some code is taken and/or modified from JsonSerializationDemo
public class JsonTest {
    protected void checkMovie(String title,Integer year,String genre,Movie movie) {
        assertEquals(title,movie.getTitle());
        assertEquals(year,movie.getYear());
        assertEquals(genre,movie.getGenre());
    }
}
