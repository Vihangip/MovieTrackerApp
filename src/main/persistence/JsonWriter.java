package persistence;

import model.MovieList;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of a movie list to file
// Some code is taken and/or modified from JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter() {  }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open(String destination) throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of a movie list to file
    public void write(MovieList ml) {
        JSONObject json = ml.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
