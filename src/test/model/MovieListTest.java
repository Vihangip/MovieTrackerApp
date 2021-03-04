package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Unit test for Movies
public class MovieListTest {
    private MovieList movieList;

    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    void runBefore() {
        movieList = new MovieList("Watched");

        movie1 = new Movie("The Notebook", 2004, "Romance");
        movie2 = new Movie ("Parasite", 2019, "Drama");
        movie3 = new Movie(null,0,null);


    }

    @Test
    public void testInitialSize() {
        assertEquals(0, movieList.size());
    }

    @Test
    public void testGetName(){
        assertEquals("Watched",movieList.getName());
    }

    @Test
    public void testAddMovieToList(){
        movieList.addMovie(movie1);

        assertTrue(movieList.contains(movie1));
        assertEquals(1,movieList.size());
    }


    @Test
    public void testAddMultipleMoviesToList() {
        movieList.addMovie(movie1);
        movieList.addMovie(movie2);

        assertTrue(movieList.contains(movie1));
        assertTrue(movieList.contains(movie2));
        assertEquals(2,movieList.size());
    }


    @Test
    public void testDuplicateToList() {
        movieList.addMovie(movie2);
        movieList.addMovie(movie2);

        assertTrue(movieList.contains(movie2));
        assertEquals(1,movieList.size());
    }


    @Test
    public void testRemoveSize1FromList() {
        movieList.addMovie(movie1);
        movieList.removeMovie(movie1);

        assertFalse(movieList.contains(movie1));
        assertEquals(0,movieList.size());
    }


    @Test
    public void testRemoveSize2FromList() {
        movieList.addMovie(movie1);
        movieList.addMovie(movie2);
        movieList.removeMovie(movie1);

        assertFalse(movieList.contains(movie1));
        assertTrue(movieList.contains(movie2));
        assertEquals(1,movieList.size());
    }


    @Test
    public void testViewMovieList() {
        List<Movie> list1 = Arrays.asList(movie1,movie2);

        movieList.addMovie(movie1);
        movieList.addMovie(movie2);

        assertEquals(list1, movieList.getList());
    }

    @Test
    public void testCheckEmptyList(){
        assertTrue(movieList.emptyList());
    }

    @Test
    public void testGetExistingMovie(){
        movieList.addMovie(movie1);

        assertEquals(movie1, movieList.getMovie("The Notebook"));
    }

    @Test
    public void testGetMovieFromEmptyList(){
        assertEquals(null, movieList.getMovie("wonder"));
    }

    @Test
    public void testGetNonExistingMovie(){
        movieList.addMovie(movie2);
        assertEquals(null, movieList.getMovie("Avengers"));
    }

    @Test
    public void testSetList(){

         List<Movie> movies = new ArrayList<>();
         movies.add(movie1);
         movies.add(movie2);
         movies.add(movie3);

         movieList.setList(movies);

         assertEquals(movies,movieList.getList());
    }

    @Test
    public void testMovieListsToJson(){

        movieList.addMovie(movie1);
        movieList.addMovie(movie2);
        movieList.addMovie(movie3);

        JSONObject jsonObj = movieList.toJson();
        JSONArray jsonList = movieList.movieListsToJson();

        JSONArray objToList = jsonObj.getJSONArray("Lists");

        JSONObject movie = movie1.toJson();
        JSONObject sameMovie = jsonList.getJSONObject(0);
        JSONObject alsoSameMovie = objToList.getJSONObject(0);

        assertEquals(movie.getString("Title"),sameMovie.getString("Title"));
        assertEquals(movie.getString("Title"),alsoSameMovie.getString("Title"));
        assertEquals(sameMovie.getString("Title"),alsoSameMovie.getString("Title"));

    }

}
