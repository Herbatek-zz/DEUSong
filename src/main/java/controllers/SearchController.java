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
    private Project project = new Project();
    private boolean isProjecting = false;

    private ObservableList<Song> searchList = FXCollections.observableArrayList();
    private ObservableList<Song> queueList = FXCollections.observableArrayList();
    private Search search = new Search();
    private int currentPresentation = 0;

    @FXML
    private void searchSong() {
        searchList.clear();
        List<Song> foundSongs = search.findByTitle(searchBar.getText());
        foundSongs = reduceDuplicates(foundSongs);
        searchList.addAll(foundSongs);
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
            this.songsTableView.getItems().remove(song);

        }

    }

    @FXML
    private void clearQueue() {
        queueList.clear();
        queueTableView.getItems().clear();
        currentPresentation = 0;
    }

    @FXML
    private void playSong() {
        Song song;
        if (queueList.size() > 0) {
            song = queueTableView.getSelectionModel().getSelectedItem();
            if (song != null) {
                slideShow.open(song);
                project.loadImage(slideShow.currentSlide());
                project.show();
            } else {
                song = queueList.get(currentPresentation);
                slideShow.open(song);
                project.loadImage(slideShow.currentSlide());
                project.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Kolejka jest pusta");
            alert.showAndWait();
        }
    }

    @FXML
    private void stopSong() {
        project.close();
        isProjecting = false;
        currentPresentation = 0;
    }

    @FXML
    private void nextSlide() {
        try {
            project.loadImage(slideShow.nextSlide());
        } catch (RuntimeException e) {
            currentPresentation++;
            if (queueList.size() > currentPresentation) {
                playSong();
            }
        }
    }

    @FXML
    private void previousSlide() {
        try {
            project.loadImage(slideShow.prevSlide());
        } catch (RuntimeException e) {
            // nothing happend
        }
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
        setFilters(0);
    }

    @FXML
    private void postCategory() {
        setFilters(1);
    }

    @FXML
    private void easterCategory() {
        setFilters(2);
    }

    @FXML
    private void adwentCategory() {
        setFilters(3);
    }

    @FXML
    private void christmasCategory() {
        setFilters(4);
    }

    @FXML
    private void allCategories() {
        setFilters(5);
    }

    @FXML
    private void saintCategory() {
        setFilters(6);
    }

    private void setFilters(int filterId) {
        songsTableView.getItems().clear();
        Filter[] filters = search.getFilters();
        if (filterId != 5) {
            search.setAllFiltersFalse(filters);
            filters[filterId].setState(true);
        } else
            search.setAllFiltersTrue(filters);
        try {
            if (searchBar.getText().length() > 0)
                songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle(searchBar.getText())));
            else
                songsTableView.getItems().setAll(reduceDuplicates(search.findByTitle("")));
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            if (filterId == 5)
                alert.setContentText("Nie znaleziono pieśni");
            else
                alert.setContentText("Nie znaleziono pieśni w kategorii: " + filters[filterId].getCategory());
            alert.showAndWait();
        }
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

        project.show();

    }

    private List<Song> reduceDuplicates(List<Song> songs) {
        if (songs.size() > 0 && queueTableView.getItems().size() > 0)
            for (Song queueSong : queueTableView.getItems())
                for (Song searchSong : songs)
                    if (queueSong.getTitle().equals(searchSong.getTitle()))
                        songs.remove(searchSong);

            return songs;
    }
}
