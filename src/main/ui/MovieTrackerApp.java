package ui;

import model.Movie;
import model.MovieList;

import java.util.ArrayList;
import java.util.Scanner;

// Movie Tracker App
public class MovieTrackerApp {
    private Movie movie;
    private MovieList watched;
    private MovieList toWatch;
    private Scanner input;
    private MovieTrackerApp mapp;

    // EFFECTS : Runs the Movie Tracker Application
    public MovieTrackerApp() {
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addToList();
        } else if (command.equals("v")) {
            viewList();
        } else if (command.equals("d")) {
            deleteFromList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes movie
    private void init() {
        movie = new Movie(null, 0, null);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a Movie");
        System.out.println("\tv -> View Lists");
        System.out.println("\td -> Delete a Movie");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES : this
    // EFFECTS : adds a movie to Watched list or To Watch list
    private void addToList() {
        MovieList selected = selectList();

        System.out.println("Enter Title");
        String name = input.next();

        System.out.println("Enter Genre");
        String genre = input.next();

        System.out.println("Enter Production Year");
        int year = input.nextInt();

        Movie newMovie = new Movie(name, year, genre);

        selected.addMovie(newMovie);

        printList(selected);


    }

    //


    // EFFECTS : prints selected list
    private void viewList() {
        MovieList selected = selectList();

        if (selected == null) {
            System.out.println("List is empty");
        } else {
            printList(selected);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from selected list
    private void deleteFromList() {
        MovieList selected = selectList();

        System.out.println("Enter Title");
        String name = input.nextLine();

        System.out.println("Enter Production Year");
        int year = input.nextInt();

        System.out.println("Enter Genre");
        String genre = input.nextLine();

        Movie newMovie = new Movie(name, year, genre);

        selected.removeMovie(newMovie);


        printList(selected);
    }

    // EFFECTS: prompts user to select Watched or ToWatch account and returns it
    private MovieList selectList() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("w") || selection.equals("tw"))) {
            System.out.println("Choose a list");
            System.out.println("w for Watched");
            System.out.println("t for To Watch");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("w")) {
            return watched;
        } else {
            return toWatch;
        }
    }

    // EFFECTS: prints list of movies in selected list
    private void printList(MovieList selected) {
        System.out.printf(" $%.2f\n", selected.getList());
    }
}
