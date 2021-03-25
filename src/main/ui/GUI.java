package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GUI {
    MovieList movies;
    Border border = BorderFactory.createLineBorder(Color.WHITE,3);

    JLabel watched;
    JLabel toWatch;
    JLabel addM;
    JLabel deleteM;
    JLabel enterTitle;
    JLabel enterYear;
    JLabel enterGenre;
    JLabel deleteTitle;
    JLabel bgPic;

    JTable watchedTable;
    JScrollPane watchedPane;


    JPanel watchedPanel;
    JPanel toWatchPanel;
    JPanel addPanel;
    JPanel deletePanel;
    JPanel mainPanel;

    JButton addToWatched;
    JButton addToToWatch;
    JButton saveWatched;
    JButton saveToWatch;
    JButton load;
    JButton deleteWatched;
    JButton deleteToWatch;

    DefaultTableModel dtm;

    String[] header = {"Movie Title", "Year of release", "Genre"};
    int row;
    int col;

    JTextField titleField;
    JTextField yearField;
    JTextField genreField;
    JTextField deleteTitleField;

    JFrame frame;

    JsonWriter jsonWriter = new JsonWriter();
    JsonReader jsonReader = new JsonReader();

    private static final String JSON_STORE_WATCHED = "./data/watched.json";
    private static final String JSON_STORE_TOWATCH = "./data/towatch.json";


    public GUI() {
        createTable();
        createLabels();
        createWatchedPanel();
        createToWatchPanel();
        createAddPanel();
        createDeletePanel();
        createMainPanel();
        createTextFields();
        createFrame();
    }

    public void createTable() {
        movies = new MovieList("watched");
        dtm = new DefaultTableModel(header,0);
        watchedTable = new JTable(dtm);

        watchedTable.setBounds(20,40,200,300);
        watchedTable.setPreferredScrollableViewportSize(new Dimension(200,350));
        watchedTable.setFillsViewportHeight(true);

        watchedPane = new JScrollPane(watchedTable);
        watchedPane.setBounds(20,60,350,550);
        watchedPane.setVisible(true);
        watchedPane.revalidate();
        watchedPane.repaint();

    }

    public void createLabels() {

        addM = new JLabel();
        addM.setText("Add a movie to your lists!");
        addM.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        addM.setForeground(Color.GREEN);

        deleteM = new JLabel();
        deleteM.setText("Delete any movie from your lists!");
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

    public void setTextFeatures(JTextField field) {

        field.setFont(new Font("Century Gothic",Font.PLAIN,14));
        field.setForeground(Color.WHITE);
        field.setBackground(Color.BLACK);
        field.setCaretColor(Color.WHITE);


    }

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

        deleteTitleField = new JTextField();
        deleteTitleField.setBounds(600,570,250,25);
        setTextFeatures(deleteTitleField);


    }

    public void createMainPanel() {

        mainPanel = new JPanel();
        mainPanel.setBounds(400,0,500,300);
        mainPanel.setLayout(null);

        bgPic = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("pics/background.jpg")
                .getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
        bgPic.setIcon((imageIcon));
        bgPic.setBounds(0,0,500,300);

        load = new JButton();
        load.setBounds(150,30,200,40);
        load.setText("Load saved Movie Lists");
        load.setFocusable(false);
        load.addActionListener(new LoadButton());
        mainPanel.add(load);
        mainPanel.add(bgPic);
        mainPanel.validate();

    }

    class LoadButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
//                toWatch = combineListsToWatch(jsonReader.read(JSON_STORE_TOWATCH));
//                System.out.println("Loaded from " + JSON_STORE_TOWATCH);

                movies = combineListsWatched(jsonReader.read(JSON_STORE_WATCHED));
                //System.out.println("Loaded from " + JSON_STORE_TOWATCH);


            } catch (IOException exception) {
                System.out.println("Unable to read from file ");
            }
            refreshListOfWatchedMovies();
        }

        // MODIFIES : this
        // EFFECTS : combines current watched list to already existing watched list
        public MovieList combineListsWatched(MovieList ml) {
            for (Movie m : ml.getList()) {
                movies.addMovie(m);
            }
            return movies;
        }
    }

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

        saveWatched = new JButton();
        saveWatched.setBounds(150,650,100,20);
        saveWatched.setText("Save");
        saveWatched.setFocusable(false);
        saveWatched.addActionListener(new SaveWatchedButton());
        watchedPanel.add(saveWatched);
        watchedPanel.revalidate();
        watchedPanel.repaint();
    }

    class SaveWatchedButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
//                jsonWriter.open(JSON_STORE_TOWATCH);
//                jsonWriter.write(toWatch);
//                jsonWriter.close();
//                System.out.println("Saved to " + JSON_STORE_TOWATCH);

                jsonWriter.open(JSON_STORE_WATCHED);
                jsonWriter.write(movies);
                jsonWriter.close();
                //System.out.println("Saved to " + JSON_STORE_WATCHED);
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Unable to write to file");
            }
        }
    }

    public void createToWatchPanel() {
        toWatchPanel = new JPanel();
        toWatchPanel.setBackground(Color.WHITE);
        toWatchPanel.setBounds(900, 0, 400, 800);
        toWatchPanel.setBorder(border);
        toWatchPanel.setLayout(null);


        toWatch = new JLabel();
        toWatch.setText("Movies To Watch");
        toWatch.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 35));
        toWatch.setForeground(Color.YELLOW);

        toWatch.setBounds(90,25,250,30);
        toWatchPanel.add(toWatch);

        saveToWatch = new JButton();
        saveToWatch.setBounds(150,650,100,20);
        saveToWatch.setText("Save");
        saveToWatch.setFocusable(false);
        toWatchPanel.add(saveToWatch);

    }

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
        addToWatched.setBounds(20,250,200,20);
        addToWatched.setText(" <<< Add to Watched Movies");
        addToWatched.setFocusable(false);
        addToWatched.addActionListener(new AddMovieButton());
        addPanel.add(addToWatched);

        addToToWatch = new JButton();
        addToToWatch.setBounds(250,250,200,20);
        addToToWatch.setText("Add to Movies To Watch >>>");
        addToToWatch.setFocusable(false);
        addPanel.add(addToToWatch);

    }

    class AddMovieButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            String genre = genreField.getText();

            movies.addMovie(new Movie(title, year, genre));
            refreshListOfWatchedMovies();


        }
    }

    private void refreshListOfWatchedMovies() {

        dtm.setRowCount(0);
        for (Movie movie : movies) {
            Object[] objs = {movie.getTitle(), movie.getYear(), movie.getGenre()};
            dtm.addRow(objs);
        }

        clearField();
    }



    private void clearField() {
        titleField.requestFocus();
        titleField.setText("");
        yearField.setText("");
        genreField.setText("");
    }



    public void createDeletePanel() {
        deletePanel = new JPanel();
        deletePanel.setBackground(Color.BLACK);
        deletePanel.setBounds(400,500,500,300);
        deletePanel.setBorder(border);
        deletePanel.setLayout(null);

        deleteM.setBounds(150,20,300,25);
        deletePanel.add(deleteM);

        deleteTitle = new JLabel();
        deleteTitle.setText("Enter Title :");
        deleteTitle.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 17));
        deleteTitle.setForeground(Color.MAGENTA);
        deleteTitle.setBounds(40,70,100,25);
        deletePanel.add(deleteTitle);

        deleteWatched = new JButton();
        deleteWatched.setText("<<<   Delete from Watched Movies");
        deleteWatched.setFocusable(false);
        deleteWatched.setBounds(40,150,250,20);
        deleteWatched.addMouseListener(new DeleteButtonMouseListener());
        deletePanel.add(deleteWatched);

        deleteToWatch = new JButton();
        deleteToWatch.setText("Delete from Movies to Watch   >>>");
        deleteToWatch.setFocusable(false);
        deleteToWatch.setBounds(220,190,250,20);
        deletePanel.add(deleteToWatch);



    }
    class DeleteButtonMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            row = watchedTable.getSelectedRow();
            col = watchedTable.getColumnCount();

//        titleField.setText(dtm.getValueAt(row,0).toString());
//        yearField.setText(dtm.getValueAt(row,1).toString());
//        genreField.setText(dtm.getValueAt(row,2).toString());
            dtm.removeRow(row);

            movies.removeMovie(movies.getMovie(dtm.getValueAt(row,0).toString()));
            dtm.setRowCount(0);
            refreshListOfWatchedMovies();

//            for (Movie) {
//                Object[] objs = {movies.get(i).getTitle(), movies.get(i).getYear(), movies.get(i).getGenre()};
//                dtm.addRow(objs);
//            }
//
//            clearField();


        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


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
        frame.add(watchedPanel);
        frame.add(toWatchPanel);
        frame.add(addPanel);
        frame.add(deletePanel);
        frame.add(mainPanel);

        frame.add(titleField);
        frame.add(yearField);
        frame.add(genreField);
        frame.add(deleteTitleField);

        frame.revalidate();
        frame.repaint();

    }



}
