package controllers;

import dontKnowHotToNameItXD.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    SlideShow slideShow = new SlideShow();
    Project project= new Project(slideShow);


    @FXML // Tabela wyszukanych piesni
    private TableView<Song> songsTableView;

    @FXML
    private TableColumn<Song, String> categoryColumn;

    @FXML
    private TableColumn<Song, String> songNameColumn;

//    @FXML // Tabela kolejki piesni
//    private TableView<Song> queueTableView;
//
//    @FXML
//    private TableColumn<Song, String> queueId;
//
//    @FXML
//    private TableColumn<Song, String> queueCategory;
//
//    @FXML
//    private TableColumn<Song, String> queueName;


    @FXML
    private TextField searchBar;

    @FXML
    private ToggleButton christmas;

    @FXML
    private ToggleButton easter;

    @FXML
    private ToggleButton post;

    @FXML
    private ToggleButton traditional;

    @FXML
    private ToggleButton saint;

    @FXML
    private ToggleButton adwent;

    @FXML
    private ToggleButton lightdark;

    @FXML
    private AnchorPane bg;

    private ObservableList<Song> list = FXCollections.observableArrayList();

    private Search search = new Search();

    @FXML
    private void search(KeyEvent event) {
        list.clear();
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        songNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        list.addAll(search.newSearch(searchBar.getText()));
        songsTableView.getItems().setAll(list);
    }

    @FXML
    private void addToQueue() {
        Song song = songsTableView.getSelectionModel().getSelectedItem();
        System.out.println(song.getTitle());
        System.out.println("dodanie do kolejki");


    }

    @FXML
    private void addToPreview() {

        System.out.println("dodanie do podgladu");
    }

    @FXML
    private void clearQueue() {
        System.out.println("usunieto kolejke");
    }

    @FXML
    private void playSong() {

        Song song = songsTableView.getSelectionModel().getSelectedItem();
        System.out.println(song.getTitle());
        slideShow.open(song.getPath());
        slideShow.firstSlide(project);
        System.out.println("start song");
    }

    @FXML
    private void stopSong() {
        System.out.println("stop song");
    }

    @FXML
    private void nextSong() {
        System.out.println("next Song");
    }

    @FXML
    private void previousSong() {
        System.out.println("previous Song");
    }

    @FXML
    private void keyListener(KeyEvent event) {
        switch (event.getCode()) {
            case NUMPAD1:
            case DIGIT1: { // START
                addToQueue();
                slideShow.prevSlide(project);
            }
            break;
            case NUMPAD3:
            case DIGIT3: { // STOP
                slideShow.nextSlide(project);
                System.out.println("numpad3");
            }
            break;
            case NUMPAD7:
            case DIGIT7: { // DALEJ
                System.out.println("numpad7");
                slideShow.end();
            }
            break;
            case NUMPAD9:
            case DIGIT9: { // WSTECZ
                System.out.println("numpad9");
            }
            break;
        }
    }

    @FXML
    private void christmasCategory() {
        Filter[] filters = search.getFilters();
        if (christmas.isSelected()) {
            if (search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[4].setState(true);
            }
        } else {
            search.setAllFiltersTrue(filters);
        }

    }


    @FXML
    private void easterCategory() {
        Filter[] filters = search.getFilters();
        if (easter.isSelected()) {
            if (search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[2].setState(true);
            }
        } else {
            search.setAllFiltersTrue(filters);
        }
    }

    @FXML
    private void postCategory() {
        Filter[] filters = search.getFilters();
        if (post.isSelected()) {
            if (search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[1].setState(true);
            }
        } else {
            search.setAllFiltersTrue(filters);
        }
    }

    @FXML
    private void saintCategory() {
        Filter[] filters = search.getFilters();
        if (saint.isSelected()) {
            if (search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[6].setState(true);
            }
        } else {
            search.setAllFiltersTrue(filters);
        }
    }

    @FXML
    private void traditionalCategory() {
        Filter[] filters = search.getFilters();
        if (traditional.isSelected()) {
            if (search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[0].setState(true);
            }
        } else {
            search.setAllFiltersTrue(filters);
        }
    }

    @FXML
    private void adwentCategory() {
        Filter[] filters = search.getFilters();
        if (adwent.isSelected()) {
            if (search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[3].setState(true);
            }
        } else {
            search.setAllFiltersTrue(filters);
        }
    }

    @FXML
    private void changeBackground() {
        if (lightdark.isSelected()) {
            bg.getStylesheets().add("styles.css");
        } else {
            bg.getStylesheets().clear();
            bg.setStyle(null);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
