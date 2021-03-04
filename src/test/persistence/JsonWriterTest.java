package persistence;

import model.Movie;
import model.MovieList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter();
            writer.open("./data/my\0illegal:fileName.json");
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMovieList() {
        try {
            JsonWriter writer = new JsonWriter();
            MovieList ml = new MovieList("Watched");
            writer.open("./data/testWriterEmptyWorkroom.json");
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader();
            ml = reader.read("./data/testWriterEmptyWorkroom.json");
            assertEquals("Watched", ml.getName());
            assertEquals(0, ml.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MovieList watched = new MovieList("Watched");
            Movie m1 = new Movie("Call me by your name",2018,"Romance/Drama" );
            Movie m2 = new Movie("Diary of wimpy kid", 2012,"Family");

            watched.addMovie(m1);
            watched.addMovie(m2);

            JsonWriter writer = new JsonWriter();
            writer.open("./data/testWriterGeneralWorkroom.json");
            writer.write(watched);
            writer.close();

            JsonReader reader = new JsonReader();
            watched = reader.read("./data/testWriterGeneralWorkroom.json");
            assertEquals("Watched", watched.getName());
            List<Movie> movies =watched.getList();
            assertEquals(2, movies.size());
            checkMovie("Call me by your name",2018,"Romance/Drama",m1);
            checkMovie("Diary of wimpy kid",2012,"Family",m2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
