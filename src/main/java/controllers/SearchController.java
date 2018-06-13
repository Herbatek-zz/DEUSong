package controllers;

import dontKnowHotToNameItXD.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.poi.EmptyFileException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private TableView<Song> searchTableView;

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
    private ImageView imagePreview;

    @FXML
    private Button loadBackground;

    @FXML
    private Button defaultBackground;

    @FXML
    private AnchorPane bg;

    @FXML
    private ImageView playStop;

    private final Image STOP_BUTTON = new Image("buttons/stop.png");
    private final Image PLAY_BUTTON = new Image("buttons/play.png");

    private final SlideShow slideShow = new SlideShow();
    private final Project project = new Project();
    private final Search search = new Search();
    private boolean isProjecting = false;

    private ObservableList<Song> searchList = FXCollections.observableArrayList();
    private ObservableList<Song> queueList = FXCollections.observableArrayList();

    private Image background = new Image("/obrazy/background.jpg");

    @FXML
    private void searchSong() {
        searchList.clear();
        List<Song> foundSongs = search.findByTitle(searchBar.getText());
        foundSongs = reduceDuplicates(foundSongs);
        searchList.addAll(foundSongs);
        searchTableView.getItems().setAll(searchList);
    }

    @FXML
    private void unfocus() {
        bg.requestFocus();
    }

    @FXML
    private void addToQueue() {
        Song song = searchTableView.getSelectionModel().getSelectedItem();
        if (song == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś żadnej pieśni");
            alert.showAndWait();
        } else {
            this.queueList.add(song);
            this.queueTableView.getItems().setAll(queueList);
            this.searchTableView.getItems().remove(song);
        }
    }

    @FXML
    private void clearQueue() {
        if (queueTableView.getItems().isEmpty())
            AlertFactory
                    .createError("Nie możesz wyczyścić pustej listy")
                    .showAndWait();
        else if (isProjecting)
            AlertFactory
                    .createError("Nie możesz wyczyścić kolejki podczas projekcji")
                    .showAndWait();
        else {
            queueList.clear();
            queueTableView.getItems().clear();
            List<Song> songs = search.findByTitle("");
            searchTableView.getItems().setAll(songs);
        }
    }

    @FXML
    private void playButton() throws IOException {
        if (!isProjecting)
            playSong();
        else
            stopSong();
    }

    private void playSong() throws IOException {
        isProjecting = true;
        loadBackground.setDisable(true);
        defaultBackground.setDisable(true);
        Song song;
        if (!queueList.isEmpty()) {
            song = queueList.get(0);
            slideShow.open(song);

            Image currentSlide = slideShow.currentSlide();
            Image previewSlide = slideShow.previewNextSlide();
            setPreview(previewSlide);

            project.loadImage(currentSlide);
            playStop.setImage(STOP_BUTTON);
            project.show();
        } else {
            isProjecting = false;
            loadBackground.setDisable(false);
            defaultBackground.setDisable(false);
            AlertFactory
                    .createError("Kolejka jest pusta")
                    .showAndWait();
        }
    }

    @FXML
    private void stopSong() {
        if (!queueList.isEmpty())
            queueList.remove(0);
        queueTableView.getItems().setAll(queueList);
        searchTableView.getItems().setAll(reduceDuplicates(search.findByTitle(searchBar.getText())));
        isProjecting = false;
        loadBackground.setDisable(false);
        defaultBackground.setDisable(false);
        project.loadImage(background);
        setPreview(background);
        playStop.setImage(PLAY_BUTTON);
        slideShow.clearSlides();

    }

    @FXML
    private void nextSlide() {
        loadBackground.setDisable(true);
        defaultBackground.setDisable(true);
        playStop.setImage(STOP_BUTTON);
        try {
            Image currentSlide = slideShow.nextSlide();
            project.loadImage(currentSlide);
        } catch (RuntimeException e) {
            nextPresentation();
        }
        try {
            Image previewSlide = slideShow.previewNextSlide();
            setPreview(previewSlide);
        } catch (RuntimeException ignored) {
           playStop.setImage(PLAY_BUTTON);
            loadBackground.setDisable(false);
            defaultBackground.setDisable(false);
        }
    }

    @FXML
    private void previousSlide() {
        try {
            Image currentSlide = slideShow.prevSlide();
            project.loadImage(currentSlide);
            try {
                Image previewSlide = slideShow.previewNextSlide();
                setPreview(previewSlide);
            } catch (RuntimeException ignored) {
                //xD
            }
        } catch (RuntimeException ignored) {

        }
    }

    @FXML
    private void nextPresentation() {
        try {
            this.queueTableView.getItems().setAll(queueList);
            queueList.remove(0);
            if (!queueList.isEmpty()) {
                queueTableView.getItems().setAll(queueList);
                playSong();
            } else {

                stopSong();
            }
        } catch (RuntimeException e) {
            // nothing here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void keyListener(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case NUMPAD1:
            case DIGIT1:
                previousSlide();
                break;
            case NUMPAD3:
            case DIGIT3:
                nextSlide();
                break;
            case NUMPAD7:
            case DIGIT7:
                playButton();
                break;
            case NUMPAD9:
            case DIGIT9:
                nextPresentation();
                break;
        }
    }

    @FXML
    private void traditionalCategory() {
        setFilters(0);
        searchSong();
    }

    @FXML
    private void postCategory() {
        setFilters(1);
        searchSong();
    }

    @FXML
    private void easterCategory() {
        setFilters(2);
        searchSong();
    }

    @FXML
    private void adwentCategory() {
        setFilters(3);
        searchSong();
    }

    @FXML
    private void christmasCategory() {
        setFilters(4);
        searchSong();
    }

    @FXML
    private void allCategories() {
        setFilters(5);
        searchSong();
    }

    @FXML
    private void saintCategory() {
        setFilters(6);
        searchSong();
    }

    private void setFilters(int filterId) {
        Filter[] filters = search.getFilters();
        if (filterId != 5) {
            search.setAllFiltersFalse(filters);
            filters[filterId].setState(true);
        } else
            search.setAllFiltersTrue(filters);
    }

    @FXML
    private void changeBackground() {
        if (lightdark.isSelected())
            bg.getStylesheets().add("styles.css");
        else
            bg.getStylesheets().clear();
    }

    private List<Song> reduceDuplicates(List<Song> songs) {
        List<Song> toRemoveList = new ArrayList<>();
        if (songs.size() > 0 && queueTableView.getItems().size() > 0)
            for (Song queueSong : queueTableView.getItems())
                for (Song searchSong : songs)
                    if (queueSong.getTitle().equals(searchSong.getTitle()))
                        toRemoveList.add(searchSong);
        songs.removeAll(toRemoveList);
        return songs;
    }

    private void setPreview(Image nextSlide) {
        this.imagePreview.setImage(nextSlide);
    }

    private void saveBackground(String extension) {
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(background, null), extension, new File("src\\main\\resources\\obrazy\\background.jpg"));
        } catch (IOException e) {
            AlertFactory
                    .createError("Wystąpił błąd podczas utawiania obrazu")
                    .showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        queueCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        queueNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        searchCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        searchTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        searchTableView.getItems().setAll(search.findByTitle(""));

        searchTableView.setOnMouseClicked((MouseEvent event) -> {
            Song song = searchTableView.getSelectionModel().getSelectedItem();
            Image currentSlide;
            if (!isProjecting && !searchTableView.getItems().isEmpty() && song != null) {
                try {
                    slideShow.open(song);
                    currentSlide = slideShow.currentSlide();
                    setPreview(currentSlide);
                } catch (IOException e) {
                    AlertFactory
                            .createError("Błąd podczas otwierania pliku")
                            .showAndWait();
                    setPreview(background);
                } catch (EmptyFileException | NullPointerException ex) {
                    AlertFactory
                            .createError("Plik jest pusty")
                            .showAndWait();
                    setPreview(background);
                }
            }
        });

        loadBackground.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Pliki graficzne", "*.jpg", "*.png"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String path = file.toURI().toString();
                background = new Image(path);
                setPreview(background);
                project.loadImage(background);
                if (path.endsWith(".jpg")) {
                    saveBackground("jpg");
                } else if (path.endsWith(".png"))
                    saveBackground("png");
            }
        });

        defaultBackground.setOnAction(event -> {
            background = new Image("/obrazy/default.jpg");
            saveBackground("jpg");
            setPreview(background);
            project.loadImage(background);
        });

        setPreview(background);
        project.show();
    }
}
