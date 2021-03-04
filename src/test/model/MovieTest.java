package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Unit test for Movies
class MovieTest {
    private Movie movie;
    private JSONObject json;

    private String title;
    private int year;
    private String genre;


    @BeforeEach
    void runBefore() {

        movie = new Movie("Avengers",2012,"Action");
    }


    @Test
    public void testGetFields() {
        assertEquals("Avengers",movie.getTitle());
        assertEquals(2012,movie.getYear());
        assertEquals("Action",movie.getGenre());

    }

    @Test
    public void testToJson(){

        JSONObject avengers = movie.toJson();

        assertEquals("Avengers",avengers.getString("Title"));
        assertEquals(2012,avengers.getInt("Year"));
        assertEquals("Action",avengers.getString("Genre"));

    }

}