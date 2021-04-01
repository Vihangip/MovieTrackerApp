package ui;

import model.exceptions.MovieNotInListException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws MovieNotInListException {

        new GUI();
        new MovieTrackerApp();

    }
}
