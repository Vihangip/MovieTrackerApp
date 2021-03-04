package persistence;

import model.Movie;
import model.MovieList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader();
        try {
            MovieList movieList = reader.read("./data/noSuchFile.json");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader();
        try {
            MovieList movieList = reader.read("./data/testReaderEmptyWorkRoom.json");
            assertEquals("Watched", movieList.getName());
            assertEquals(0, movieList.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader();
        try {
            MovieList movieList = reader.read("./data/testReaderGeneralWorkRoom.json");
            assertEquals("Watched", movieList.getName());
            List<Movie> movies = movieList.getList();
            assertEquals(2, movieList.size());
            checkMovie("Call me by your name",2018,"Romance/Drama",movies.get(0));
            checkMovie("Diary of wimpy kid", 2012,"Family",movies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
