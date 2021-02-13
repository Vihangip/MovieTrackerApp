package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Unit test for Movies Lists
class MovieTest {
    private MovieList watched;
    private MovieList toWatch;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;


    @BeforeEach
    void runBefore() {
        watched = new MovieList();
        toWatch = new MovieList();
        movie1 = new Movie("The Notebook", 2004, "Romance");
        movie2 = new Movie ("Parasite", 2019, "Drama");
        movie3 = new Movie(null,0,null);
    }

    @Test
    public void testInitialSizeWatched() {
        assertEquals(0,watched.size());
    }

    @Test
    public void testInitialSizeToWatch(){
        assertEquals(0,toWatch.size());
    }

    @Test
    public void testCreateMovie1() {
        movie3.createMovie("Avengers",2012,"Action");

        assertEquals("Avengers",movie3.getTitle());
        assertEquals(2012,movie3.getYear());
        assertEquals("Action",movie3.getGenre());

    }

    @Test
    public void testCreateMovie2() {
        movie3.createMovie("Grown Ups",2010,"Comedy");

        assertEquals("Grown Ups",movie3.getTitle());
        assertEquals(2010,movie3.getYear());
        assertEquals("Comedy",movie3.getGenre());

    }

    @Test
    public void testAddMovieToToWatch(){
        toWatch.addMovie(movie1);

        assertTrue(toWatch.contains(movie1));
        assertEquals(1,toWatch.size());
    }

    @Test
    public void testAddMovieToWatched(){
        watched.addMovie(movie1);

        assertTrue(watched.contains(movie1));
        assertEquals(1,watched.size());
    }

    @Test
    public void testAddMultipleMoviesToToWatch() {
        toWatch.addMovie(movie1);
        toWatch.addMovie(movie2);

        assertTrue(toWatch.contains(movie1));
        assertTrue(toWatch.contains(movie2));
        assertEquals(2,toWatch.size());
    }

    @Test
    public void testAddMultipleMoviesToWatched() {
        watched.addMovie(movie1);
        watched.addMovie(movie2);

        assertTrue(watched.contains(movie1));
        assertTrue(watched.contains(movie2));
        assertEquals(2,watched.size());
    }

    @Test
    public void testDuplicateToWatch() {
        toWatch.addMovie(movie2);
        toWatch.addMovie(movie2);

        assertTrue(toWatch.contains(movie2));
        assertEquals(1,toWatch.size());
    }

    @Test
    public void testDuplicateWatched() {
        watched.addMovie(movie2);
        watched.addMovie(movie2);

        assertTrue(watched.contains(movie2));
        assertEquals(1,watched.size());
    }

    @Test
    public void testRemoveSize1FromToWatch() {
        toWatch.addMovie(movie1);
        toWatch.removeMovie(movie1);

        assertFalse(toWatch.contains(movie1));
        assertEquals(0,toWatch.size());
    }

    @Test
    public void testRemoveSize1FromWatched() {
        watched.addMovie(movie1);
        watched.removeMovie(movie1);

        assertFalse(watched.contains(movie1));
        assertEquals(0,watched.size());
    }

    @Test
    public void testRemoveSize2FromToWatch() {
        toWatch.addMovie(movie1);
        toWatch.addMovie(movie2);
        toWatch.removeMovie(movie1);

        assertFalse(toWatch.contains(movie1));
        assertTrue(toWatch.contains(movie2));
        assertEquals(1,toWatch.size());
    }

    @Test
    public void testRemoveSize2FromWatched() {
        watched.addMovie(movie1);
        watched.addMovie(movie2);
        watched.removeMovie(movie1);

        assertFalse(watched.contains(movie1));
        assertTrue(watched.contains(movie2));
        assertEquals(1,watched.size());
    }

    @Test
    public void testViewMovieList() {
        List<Movie> list1 = Arrays.asList(movie1,movie2);

        watched.addMovie(movie1);
        watched.addMovie(movie2);

        assertEquals(list1,watched.getList());
    }

    @Test
    public void testCheckEmptyList(){
        assertTrue(watched.emptyList());
    }

    @Test
    public void testGetExistingMovie(){
        watched.addMovie(movie1);

        assertEquals(movie1,watched.getMovie("The Notebook"));
    }

    @Test
    public void testGetMovieFromEmptyList(){
        assertEquals(null,watched.getMovie("wonder"));
    }

    @Test
    public void testGetNonExistingMovie(){
        watched.addMovie(movie2);
        assertEquals(null,watched.getMovie("Avengers"));
    }





}