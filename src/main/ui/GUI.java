package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GUI {
    MovieList watchedMovies;
    MovieList toWatchMovies;

    Border border = BorderFactory.createLineBorder(Color.WHITE,3);

    JLabel watched;
    JLabel toWatch;
    JLabel addM;
    JLabel deleteM;
    JLabel enterTitle;
    JLabel enterYear;
    JLabel enterGenre;
    JLabel bgPic;

    JTable watchedTable;
    JTable toWatchTable;
    JScrollPane watchedPane;
    JScrollPane toWatchPane;


    JPanel watchedPanel;
    JPanel toWatchPanel;
    JPanel addPanel;
    JPanel deletePanel;
    JPanel mainPanel;

    JButton addToWatched;
    JButton addToToWatch;
    JButton save;
    JButton load;
    JButton deleteWatched;
    JButton deleteToWatch;

    String[] header = {"Movie Title", "Year of release", "Genre"};
    int watchedRow;
    int toWatchRow;

    DefaultTableModel wtm;
    DefaultTableModel twtm;

    JTextField titleField;
    JTextField yearField;
    JTextField genreField;

    JFrame frame;

    JsonWriter jsonWriter = new JsonWriter();
    JsonReader jsonReader = new JsonReader();

    private static final String JSON_STORE_WATCHED = "./data/watched.json";
    private static final String JSON_STORE_TOWATCH = "./data/towatch.json";


    public GUI() {
        createWatchedTable();
        createToWatchTable();

        createLabels();

        createWatchedPanel();
        createToWatchPanel();

        createAddPanel();
        createDeletePanel();
        createMainPanel();

        createTextFields();
        createFrame();
    }

    // MODIFIES : this
    //EFFECTS : creates a JTable and Jpane for the list of Watched movies
    public void createWatchedTable() {
        watchedMovies = new MovieList("Watched");
        wtm = new DefaultTableModel(header,0);

        watchedTable = new JTable(wtm);
        watchedTable.setFillsViewportHeight(true);

        watchedPane = new JScrollPane(watchedTable);
        watchedPane.setBounds(20,60,350,650);;

        watchedTable.setBackground(Color.BLACK);
        watchedTable.setForeground(Color.WHITE);
    }

    // MODIFIES : this
    //EFFECTS : creates a JTable and Jpane for the list of Watched movies
    public void createToWatchTable() {
        toWatchMovies = new MovieList("To Watch");
        twtm = new DefaultTableModel(header,0);

        toWatchTable = new JTable(twtm);
        toWatchTable.setFillsViewportHeight(true);

        toWatchPane = new JScrollPane(toWatchTable);
        toWatchPane.setBounds(920,60,350,650);

        toWatchTable.setBackground(Color.BLACK);
        toWatchTable.setForeground(Color.WHITE);
    }

    // MODIFIES : this
    // EFFECTS : creates all labels seen in the Add Panel and Delete Panel
    public void createLabels() {
        addM = new JLabel();
        addM.setText("Add a movie to your lists!");
        addM.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        addM.setForeground(Color.GREEN);

        deleteM = new JLabel();
        deleteM.setText("Select a movie from a list and click respective button below!");
        deleteM.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        deleteM.setForeground(Color.MAGENTA);

        enterTitle = new JLabel();
        enterTitle.setText("Enter Title :");
        enterTitle.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 17));
        enterTitle.setForeground(Color.GREEN);

        enterYear = new JLabel();
        enterYear.setText("Enter Year of Release :");
        enterYear.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 17));
        enterYear.setForeground(Color.GREEN);

        enterGenre = new JLabel();
        enterGenre.setText("Enter Genre :");
        enterGenre.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 17));
        enterGenre.setForeground(Color.GREEN);

    }

    // EFFECTS : sets the font, font size and colors for the JLables
    public void setTextFeatures(JTextField field) {

        field.setFont(new Font("Century Gothic",Font.PLAIN,14));
        field.setForeground(Color.WHITE);
        field.setBackground(Color.BLACK);
        field.setCaretColor(Color.WHITE);


    }

    // MODIFIES : this
    // EFFECTS : creates the JTextFields seen in the add panel
    public void createTextFields() {
        this.titleField = new JTextField();
        titleField.setBounds(600, 270, 250, 25);
        setTextFeatures(titleField);

        titleField.validate();
        titleField.repaint();

        yearField = new JTextField();
        yearField.setBounds(600,320,250,25);
        setTextFeatures(yearField);

        genreField = new JTextField();
        genreField.setBounds(600,370,250,25);
        setTextFeatures(genreField);
    }


    // MODIFIES : this
    // EFFECTS : creates a panel with an image
    public void createMainPanel() {

        mainPanel = new JPanel();
        mainPanel.setBounds(400,0,500,300);
        mainPanel.setLayout(null);

        bgPic = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("pics/background.jpg")
                .getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
        bgPic.setIcon((imageIcon));
        bgPic.setBounds(0,0,500,300);


        mainPanel.add(bgPic);
        mainPanel.validate();

    }


    // MODIFIES : this
    // EFFECTS : creates a panel to display watched movies
    public void createWatchedPanel() {

        watchedPanel = new JPanel();
        watchedPanel.setBackground(Color.BLACK);
        watchedPanel.setBounds(0, 0, 400, 800);
        watchedPanel.setBorder(border);
        watchedPanel.setLayout(null);

        watched = new JLabel();
        watched.setText("Watched Movies");
        watched.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 35));
        watched.setForeground(Color.CYAN);

        watched.setBounds(90,25,250,30);
        watchedPanel.add(watched);
        watchedPanel.revalidate();
        watchedPanel.repaint();
    }


    // MODIFIES : this
    // EFFECTS : creates a panel to display to watch movies
    public void createToWatchPanel() {
        toWatchPanel = new JPanel();
        toWatchPanel.setBackground(Color.BLACK);
        toWatchPanel.setBounds(900, 0, 400, 800);
        toWatchPanel.setBorder(border);
        toWatchPanel.setLayout(null);


        toWatch = new JLabel();
        toWatch.setText("Movies To Watch");
        toWatch.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 35));
        toWatch.setForeground(Color.YELLOW);

        toWatch.setBounds(90,25,250,30);
        toWatchPanel.add(toWatch);

        toWatchPanel.revalidate();
        toWatchPanel.repaint();

    }


    // MODIFIES : this
    // EFFECTS : creates a panel with the elements needed to add a movie to any list
    public void createAddPanel() {

        addPanel = new JPanel();
        addPanel.setBackground(Color.BLACK);
        addPanel.setBounds(400,200,500,300);
        addPanel.setBorder(border);
        addPanel.setLayout(null);

        addM.setBounds(150,20,300,25);
        addPanel.add(addM);

        enterTitle.setBounds(20,70,100,25);
        enterYear.setBounds(20,120,200,25);
        enterGenre.setBounds(20,170,150,25);
        addPanel.add(enterTitle);
        addPanel.add(enterYear);
        addPanel.add(enterGenre);

        addToWatched = new JButton();
        addToWatched.setBounds(20,240,200,25);
        addToWatched.setText(" <<< Add to Watched Movies");
        addToWatched.addActionListener(new AddWatchedButton());
        addPanel.add(addToWatched);

        addToToWatch = new JButton();
        addToToWatch.setBounds(250,240,200,25);
        addToToWatch.setText("Add to Movies To Watch >>>");
        addToToWatch.addActionListener(new AddToWatchButton());
        addPanel.add(addToToWatch);

    }


    // represents a button that adds a movie to the watched movies list
    class AddWatchedButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            String genre = genreField.getText();

            watchedMovies.addMovie(new Movie(title, year, genre));
            refreshWatched();
        }
    }

    // represents a button that adds a movie to the to watch movies button
    class AddToWatchButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            String genre = genreField.getText();

            toWatchMovies.addMovie(new Movie(title,year,genre));
            refreshToWatch();
        }
    }


    // MODIFIES : this
    // EFFECTS : repopulates the Watched Table with the updated Watched movies list
    private void refreshWatched() {

        wtm.setRowCount(0);
        for (Movie movie : watchedMovies) {
            Object[] obj = {movie.getTitle(), movie.getYear(), movie.getGenre()};
            wtm.addRow(obj);
        }
        clearField();
    }


    // MODIFIES : this
    // EFFECTS : repopulates the ToWatch Table with the updated To Watch movies list
    private void refreshToWatch() {
        twtm.setRowCount(0);
        for (Movie movie : toWatchMovies) {
            Object[] obj = {movie.getTitle(), movie.getYear(), movie.getGenre()};
            twtm.addRow(obj);
        }
        clearField();
    }


    // MODIFIES : this
    // EFFECTS : clears all JTextFields and set focus to the title field
    private void clearField() {
        titleField.requestFocus();
        titleField.setText("");
        yearField.setText("");
        genreField.setText("");
    }


    // MODIFIES : this
    // EFFECTS : created a JPanel with elements necessary to delete a movie from any list and contains save
    //           and load buttons
    public void createDeletePanel() {
        deletePanel = new JPanel();
        deletePanel.setBackground(Color.BLACK);
        deletePanel.setBounds(400,500,500,300);
        deletePanel.setBorder(border);
        deletePanel.setLayout(null);

        deleteM.setBounds(40,20,450,25);
        deletePanel.add(deleteM);

        deleteWatched = new JButton();
        deleteWatched.setText("<<<   Delete from Watched Movies");
        deleteWatched.setFocusable(false);
        deleteWatched.setBounds(40,60,250,25);
        deleteWatched.addMouseListener(new DeleteWatchedButton());
        deletePanel.add(deleteWatched);

        deleteToWatch = new JButton();
        deleteToWatch.setText("Delete from Movies to Watch   >>>");
        deleteToWatch.setFocusable(false);
        deleteToWatch.setBounds(220,100,250,25);
        deleteToWatch.addMouseListener(new DeleteToWatchButton());
        deletePanel.add(deleteToWatch);

        createSaveLoadButtons();



    }


    // MODIFIES : this
    // EFFECTS : creates save and load JButtons
    public void createSaveLoadButtons() {
        save = new JButton();
        save.setBounds(50,180,150,25);
        save.setText("Save Movie Lists");
        save.setFocusable(false);
        save.addActionListener(new SaveButton());
        deletePanel.add(save);

        load = new JButton();
        load.setBounds(250,180,150,25);
        load.setText("Load Movie Lists");
        load.setFocusable(false);
        load.addActionListener(new LoadButton());
        deletePanel.add(load);
    }

    // represents a JButton that saves movie lists to file
    class SaveButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open(JSON_STORE_TOWATCH);
                jsonWriter.write(toWatchMovies);
                jsonWriter.close();

                jsonWriter.open(JSON_STORE_WATCHED);
                jsonWriter.write(watchedMovies);
                jsonWriter.close();
            } catch (FileNotFoundException fileNotFoundException) {
                // System.out.println("Unable to write to file");
            }
        }
    }

    // represents a JButton that loads movie lists from file
    class LoadButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                toWatchMovies = combineListsToWatch(jsonReader.read(JSON_STORE_TOWATCH));
                watchedMovies = combineListsWatched(jsonReader.read(JSON_STORE_WATCHED));

            } catch (IOException exception) {
                // System.out.println("Unable to read from file ");
            }
            refreshWatched();
            refreshToWatch();

        }

        // MODIFIES : this
        // EFFECTS : combines current to-watch list to already existing to-watch list
        public MovieList combineListsToWatch(MovieList ml) {
            for (Movie m : ml.getList()) {
                toWatchMovies.addMovie(m);
            }
            return toWatchMovies;
        }

        // MODIFIES : this
        // EFFECTS : combines current watched list to already existing watched list
        public MovieList combineListsWatched(MovieList ml) {
            for (Movie m : ml.getList()) {
                watchedMovies.addMovie(m);
            }
            return watchedMovies;
        }
    }

    // represents a JButton that deletes a movie from the Watched movies list
    class DeleteWatchedButton extends MouseAdapter  {
        @Override
        public void mouseClicked(MouseEvent e) {
            watchedRow = watchedTable.getSelectedRow();
            wtm.removeRow((watchedRow + 1));
            String movieTitle = wtm.getValueAt(watchedRow,0).toString();
            watchedMovies.removeMovie(watchedMovies.getMovie(movieTitle));
            refreshWatched();
        }

    }

    // represents a JButton that deletes a movie from the To Watch movies list
    class DeleteToWatchButton extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            toWatchRow = toWatchTable.getSelectedRow();
            twtm.removeRow((toWatchRow + 1));
            String movieTitle = twtm.getValueAt(toWatchRow,0).toString();
            toWatchMovies.removeMovie(toWatchMovies.getMovie(movieTitle));
            refreshToWatch();
        }
    }


    // MODIFIES : this
    // EFFECTS : creates a JFrame that holds all elements required for the Movie Tracker App
    public void createFrame() {
        frame = new JFrame();
        frame.setTitle("Movie Tracker App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        ImageIcon icon = new ImageIcon("pics/Logo.jpg");
        frame.setIconImage(icon.getImage());

        frame.add(watchedPane);
        frame.add(toWatchPane);

        frame.add(watchedPanel);
        frame.add(toWatchPanel);
        frame.add(addPanel);
        frame.add(deletePanel);
        frame.add(mainPanel);

        frame.add(titleField);
        frame.add(yearField);
        frame.add(genreField);

        frame.revalidate();
        frame.repaint();

    }

}
