package controllers;

import dontKnowHotToNameItXD.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private TableView<Song> songsTableView;

    @FXML
    private TableColumn<Song, String> searchCategoryColumn;

    @FXML
    private TableColumn<Song, String> searchTitleColumn;

    @FXML
    private TableView<Song> queueTableView;

    @FXML
    private TableColumn<Song, String> queueCategoryColumn;

    @FXML
    private TableColumn<Song, String> queueNameColumn;

    @FXML
    private TextField searchBar;

    @FXML
    private ToggleButton lightdark;

    @FXML
    private AnchorPane bg;

    private SlideShow slideShow = new SlideShow();
    private Project project = new Project(slideShow);
    private boolean isProjecting = false;

    private ObservableList<Song> searchList = FXCollections.observableArrayList();
    private ObservableList<Song> queueList = FXCollections.observableArrayList();
    private Search search = new Search();

    @FXML
    private void searchSong() {
        searchList.clear();

        List<Song> foundedSongs = reduceDuplicates(search.findByTitle(searchBar.getText()));

        searchList.addAll(foundedSongs);
        songsTableView.getItems().setAll(searchList);
    }

    @FXML
    private void unfocus() {
        bg.requestFocus();
    }

    @FXML
    private void addToQueue() {
        Song song = songsTableView.getSelectionModel().getSelectedItem();
        if (song == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś żadnej pieśni");
            alert.showAndWait();
        } else {
            this.queueList.add(song);
            this.queueTableView.getItems().setAll(queueList);
            this.songsTableView.getItems().setAll(reduceDuplicates(songsTableView.getItems()));

        }

    }

    @FXML
    private void clearQueue() {
        queueTableView.getItems().clear();
    }

    @FXML
    private void playSong() {
        slideShow.CurrentSlide(project);
        System.out.println("start song");
        isProjecting = true;
    }

    @FXML
    private void stopSong() {
        System.out.println("stop song");
        //  slideShow.end();
        project.loadBG();
        isProjecting = false;
    }

    @FXML
    private void nextSlide() {       //Zmien nazwę na nextSlide ---- > done!
        slideShow.nextSlide(project);
    }

    @FXML
    private void previousSlide() {   //Zmien nazwe na previousSlide ----- > done!
        slideShow.prevSlide(project);
    }

    @FXML
    private void keyListener(KeyEvent event) {
        switch (event.getCode()) {
            case NUMPAD1:
            case DIGIT1: { // Poprzedni
                previousSlide();
            }
            break;
            case NUMPAD3:
            case DIGIT3: { // Następny
                nextSlide();
            }
            break;
            case NUMPAD7:
            case DIGIT7: { // Start/Stop
                if (isProjecting) {
                    stopSong();
                } else {
                    playSong();
                }
            }
            break;
            case NUMPAD9:
            case DIGIT9: { // Następna piesn
                System.out.println("numpad9");
            }
            break;
        }
    }

    @FXML
    private void traditionalCategory() {
        songsTableView.getItems().clear();
        Filter[] filters = search.getFilters();
        search.setAllFiltersFalse(filters);
        filters[0].setState(true);
        songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle("")));
    }

    @FXML
    private void postCategory() {
        songsTableView.getItems().clear();
        Filter[] filters = search.getFilters();
        search.setAllFiltersFalse(filters);
        filters[1].setState(true);
        songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle("")));
    }

    @FXML
    private void easterCategory() {
        songsTableView.getItems().clear();
        Filter[] filters = search.getFilters();
        search.setAllFiltersFalse(filters);
        filters[2].setState(true);
        songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle("")));
    }

    @FXML
    private void adwentCategory() {
        songsTableView.getItems().clear();
        Filter[] filters = search.getFilters();
        search.setAllFiltersFalse(filters);
        filters[3].setState(true);
        songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle("")));
    }

    @FXML
    private void christmasCategory() {
        songsTableView.getItems().clear();
        Filter[] filters = search.getFilters();
        search.setAllFiltersFalse(filters);
        filters[4].setState(true);
        songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle("")));
    }


    @FXML
    private void saintCategory() {
        songsTableView.getItems().clear();
        Filter[] filters = search.getFilters();
        search.setAllFiltersFalse(filters);
        filters[6].setState(true);
        songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle("")));
    }


    @FXML
    private void changeBackground() {
        if (lightdark.isSelected())
            bg.getStylesheets().add("styles.css");
        else
            bg.getStylesheets().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        queueCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        queueNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        searchCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        searchTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        songsTableView.getItems().setAll(search.findByTitle(""));

    }

    private List<Song> reduceDuplicates(List<Song> songs) {

        for (Song queueSong : queueTableView.getItems()) {
            for (Song searchSong : songs) {
                if (queueSong.getTitle().equals(searchSong.getTitle())) {
                    songs.remove(searchSong);
                }
            }
        }
        return songs;
    }
}
