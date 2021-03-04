package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Movie;
import model.MovieList;
import org.json.*;

// Represents a reader that reads movielibrary from JSON data stored in file
public class JsonReader {


    // EFFECTS: constructs reader to read from source file
    public JsonReader() {

    }

    // EFFECTS: reads movielibrary from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieList read(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return  parseMovieList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses movielibrary from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        String name = jsonObject.getString("Type");
        MovieList ml = new MovieList(name);
        ml.setList(addLists(jsonObject));
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses lists from JSON object and adds them to movielibrary
    private List<Movie> addLists(JSONObject jsonObject) {
        List<Movie> movies = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("Lists");
        for (Object json : jsonArray) {
            JSONObject list = (JSONObject) json;
            movies.add(parseMovie(list));
        }
        return movies;
    }

    private Movie parseMovie(JSONObject jsonObject) {
        Movie m = new Movie(jsonObject.getString("Title"),
                            jsonObject.getInt("Year"),
                            jsonObject.getString("Genre"));
        return m;


    }


}
