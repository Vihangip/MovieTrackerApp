package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Movie Tracker App
public class MovieTrackerApp {
    private MovieList watched;
    private MovieList toWatch;

    private static final String JSON_STORE_WATCHED = "./data/watched.json";
    private static final String JSON_STORE_TOWATCH = "./data/towatch.json";
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS : constructs movie library and runs the Movie Tracker Application
    public MovieTrackerApp() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter();
        jsonReader = new JsonReader();
        runTracker();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    public void runTracker() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

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

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a Movie");
        System.out.println("\td -> Delete a Movie");
        System.out.println("\tm -> Move to Watched list");
        System.out.println("\tv -> View Lists");
        System.out.println("\ts -> Save lists to file");
        System.out.println("\tl -> Load lists from file");
        System.out.println("\tq -> Quit");
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
        } else if (command.equals("s")) {
            saveLists();
        } else if (command.equals("l")) {
            loadLists();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes movie
    private void init() {
        watched = new MovieList("Watched");
        toWatch = new MovieList("To-Watch");
        input = new Scanner(System.in);
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

    // EFFECTS: saves the the watched list and the to-watch list to file
    private void saveLists() {
        try {
            jsonWriter.open(JSON_STORE_TOWATCH);
            jsonWriter.write(toWatch);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE_TOWATCH);

            jsonWriter.open(JSON_STORE_WATCHED);
            jsonWriter.write(watched);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE_WATCHED);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads watched list and to-watch list from file
    private void loadLists() {
        try {
            toWatch = combineListsToWatch(jsonReader.read(JSON_STORE_TOWATCH));
            System.out.println("Loaded from " + JSON_STORE_TOWATCH);

            watched = combineListsWatched(jsonReader.read(JSON_STORE_WATCHED));
            System.out.println("Loaded from " + JSON_STORE_TOWATCH);


        } catch (IOException e) {
            System.out.println("Unable to read from file ");
        }
    }

    // MODIFIES : this
    // EFFECTS : combines current to-watch list to already existing to-watch list
    public MovieList combineListsToWatch(MovieList ml) {
        for (Movie m : ml.getList()) {
            toWatch.addMovie(m);
        }
        return toWatch;
    }

    // MODIFIES : this
    // EFFECTS : combines current watched list to already existing watched list
    public MovieList combineListsWatched(MovieList ml) {
        for (Movie m : ml.getList()) {
            watched.addMovie(m);
        }
        return watched;
    }

}
