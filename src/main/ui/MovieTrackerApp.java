package ui;

import model.Movie;
import model.MovieList;

import java.util.Scanner;

// Movie Tracker App
public class MovieTrackerApp {
    private MovieList watched;
    private MovieList toWatch;
    private Scanner input;

    // EFFECTS : Runs the Movie Tracker Application
    public MovieTrackerApp() {
        runTracker();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    public void runTracker() {
        boolean keepGoing = true;
        String command;

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
        } else if (command.equals("m")) {
            transferList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes movie
    private void init() {
        watched = new MovieList();
        toWatch = new MovieList();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a Movie");
        System.out.println("\td -> Delete a Movie");
        System.out.println("\tm -> Move to Watched list");
        System.out.println("\tv -> View Lists");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES : this
    // EFFECTS : adds a movie to the Watched list or the To-Watch list
    private void addToList() {
        MovieList selected = selectList();

        System.out.println("Enter Title");
        String name = input.next();
        name += input.nextLine();

        System.out.println("Enter Genre");
        String genre = input.next();
        genre += input.nextLine();

        System.out.println("Enter Year of Release");
        int year = input.nextInt();

        Movie newMovie = new Movie(name, year, genre);

        selected.addMovie(newMovie);

        printSelectedList(selected);


    }


    // EFFECTS : prints selected list
    private void viewList() {
        MovieList selected = selectList();

        if (selected.emptyList()) {
            System.out.println("List is empty");
        } else {
            System.out.println("The following movies are currently in your list");
            printSelectedList(selected);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from selected list
    private void deleteFromList() {
        MovieList selected = selectList();

        System.out.println("Enter Title");
        String name = input.next();
        name += input.nextLine();

        Movie selectedMovie = selected.getMovie(name);

        if (selectedMovie == null) {
            System.out.println("Movie not in list");
        } else {
            selected.removeMovie(selectedMovie);
            printSelectedList(selected);
        }


    }

    // MODIFIES: this
    // EFFECTS: moves the movie with the given title from the to-watch list to the watched list
    private void transferList() {
        System.out.println("Which movie did you watch?");
        System.out.println("Please enter title");

        String name = input.next();
        name += input.nextLine();

        Movie selectedMovie = toWatch.getMovie(name);

        if (selectedMovie == null) {
            System.out.println("Movie not in list");
        } else {
            toWatch.removeMovie(selectedMovie);
            watched.addMovie(selectedMovie);
        }

    }

    // EFFECTS: prompts user to select Watched or To-Watch list and returns it
    private MovieList selectList() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("w") || selection.equals("t"))) {
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

    // REQUIRES : list cannot be empty
    // EFFECTS : returns the titles of all the movies in the given movie list
    private void printSelectedList(MovieList selectedList) {
        for (Movie movie : selectedList.getList()) {
            System.out.println(movie.getTitle());
        }
    }

}
