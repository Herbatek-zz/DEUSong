package controllers;

import dontKnowHotToNameItXD.Filter;
import dontKnowHotToNameItXD.Search;
import dontKnowHotToNameItXD.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private TableView<Song> songsTableView;

    @FXML
    private TableColumn<Song, String> categoryColumn;

    @FXML
    private TableColumn<Song, String> songNameColumn;

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
        System.out.println("klik");
    }

    @FXML
    private void preview() {

    }

    @FXML
    private void clearQueue() {

    }

    @FXML
    private void christmasCategory() {
        Filter[] filters = search.getFilters();
        if(christmas.isSelected()) {
            if(search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[4].setState(true);
            }
        }
        else {
            search.setAllFiltersTrue(filters);
        }

    }


    @FXML
    private void easterCategory() {
        Filter[] filters = search.getFilters();
        if (easter.isSelected()) {
            if(search.isAllFiltersTrue(filters)) {
                search.setAllFiltersFalse(filters);
                filters[2].setState(true);
            }
        }
        else {
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
        }
        else {
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
        }
        else {
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
        }
        else {
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
        }
        else {
            search.setAllFiltersTrue(filters);
        }
    }

    @FXML
    private void changeBackground() {
        if (lightdark.isSelected()) {
            bg.setStyle("-fx-background-color: #6C7A89");
        }
        else {
            bg.setStyle("-fx-background-color: #FFFFFF");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
