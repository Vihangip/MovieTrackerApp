package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class GUI {
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


    JTextField title;
    JTextField year;
    JTextField genre;
    JTextField deleteMovieTitle;

    JFrame frame;


    public GUI() {
        createLabels();
        createWatchedPanel();
        createToWatchPanel();
        createAddPanel();
        createDeletePanel();
        createMainPanel();
        createTextFields();
        createFrame();
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
        mainPanel.add(load);
        mainPanel.add(bgPic);
        mainPanel.validate();

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
        watchedPanel.add(saveWatched);
    }

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
        addPanel.add(addToWatched);

        addToToWatch = new JButton();
        addToToWatch.setBounds(250,250,200,20);
        addToToWatch.setText("Add to Movies To Watch >>>");
        addToToWatch.setFocusable(false);
        addPanel.add(addToToWatch);

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
        deletePanel.add(deleteWatched);

        deleteToWatch = new JButton();
        deleteToWatch.setText("Delete from Movies to Watch   >>>");
        deleteToWatch.setFocusable(false);
        deleteToWatch.setBounds(220,190,250,20);
        deletePanel.add(deleteToWatch);



    }

    public void setTextFeatures(JTextField field) {

        field.setFont(new Font("Century Gothic",Font.PLAIN,14));
        field.setForeground(Color.WHITE);
        field.setBackground(Color.BLACK);
        field.setCaretColor(Color.WHITE);


    }



    public void createTextFields() {
        title = new JTextField();
        title.setBounds(600, 270, 250, 25);
        setTextFeatures(title);

        year = new JTextField();
        year.setBounds(600,320,250,25);
        setTextFeatures(year);

        genre = new JTextField();
        genre.setBounds(600,370,250,25);
        setTextFeatures(genre);

        deleteMovieTitle = new JTextField();
        deleteMovieTitle.setBounds(600,570,250,25);
        setTextFeatures(deleteMovieTitle);
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


        frame.add(watchedPanel);
        frame.add(toWatchPanel);
        frame.add(addPanel);
        frame.add(deletePanel);
        frame.add(mainPanel);

        frame.add(title);
        frame.add(year);
        frame.add(genre);
        frame.add(deleteMovieTitle);


        frame.revalidate();
        frame.repaint();

    }
}
