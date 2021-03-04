package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Unit test for Movies
public class MovieListTest {
    private MovieList movieList;
    private MovieList emptyList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    void runBefore() {
        emptyList = new MovieList("To-Watch");
        movieList = new MovieList("Watched");

        movie1 = new Movie("The Notebook", 2004, "Romance");
        movie2 = new Movie ("Parasite", 2019, "Drama");
        movie3 = new Movie(null,0,null);

        movieList.addMovie(movie1);
        movieList.addMovie(movie2);
        movieList.addMovie(movie3);

    }

    @Test
    public void testInitialSize() {
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testGetName(){
        assertEquals("Watched",movieList.getName());
    }

    @Test
    public void testAddMovieToList(){
        emptyList.addMovie(movie1);

        assertTrue(emptyList.contains(movie1));
        assertEquals(1,emptyList.size());
    }


    @Test
    public void testAddMultipleMoviesToList() {
        emptyList.addMovie(movie1);
        emptyList.addMovie(movie2);

        assertTrue(emptyList.contains(movie1));
        assertTrue(emptyList.contains(movie2));
        assertEquals(2,emptyList.size());
    }


    @Test
    public void testDuplicateToList() {
        emptyList.addMovie(movie2);
        emptyList.addMovie(movie2);

        assertTrue(emptyList.contains(movie2));
        assertEquals(1,emptyList.size());
    }


    @Test
    public void testRemoveSize1FromList() {
        emptyList.addMovie(movie1);
        emptyList.removeMovie(movie1);

        assertFalse(emptyList.contains(movie1));
        assertEquals(0,emptyList.size());
    }


    @Test
    public void testRemoveSize2FromList() {
        emptyList.addMovie(movie1);
        emptyList.addMovie(movie2);
        emptyList.removeMovie(movie1);

        assertFalse(emptyList.contains(movie1));
        assertTrue(emptyList.contains(movie2));
        assertEquals(1,emptyList.size());
    }


    @Test
    public void testViewMovieList() {
        List<Movie> list = new ArrayList<>();
        list.add(movie1);
        list.add(movie2);
        list.add(movie3);

        assertEquals(list, movieList.getList());
    }

    @Test
    public void testCheckEmptyList(){
        assertTrue(emptyList.emptyList());
    }

    @Test
    public void testGetExistingMovie(){
        emptyList.addMovie(movie1);

        assertEquals(movie1, emptyList.getMovie("The Notebook"));
    }

    @Test
    public void testGetMovieFromEmptyList(){
        assertEquals(null, emptyList.getMovie("wonder"));
    }

    @Test
    public void testGetNonExistingMovie(){
        emptyList.addMovie(movie2);
        assertEquals(null, emptyList.getMovie("Avengers"));
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
