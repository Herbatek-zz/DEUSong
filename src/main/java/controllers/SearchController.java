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
import java.net.URL;
import java.util.ArrayList;
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
    private ImageView imagePreview;

    @FXML
    private Button loadBackground;

    @FXML
    private AnchorPane bg;

    @FXML
    private ImageView playStop;

    private SlideShow slideShow = new SlideShow();
    private Project project = new Project();
    private boolean isProjecting = false;

    private ObservableList<Song> searchList = FXCollections.observableArrayList();
    private ObservableList<Song> queueList = FXCollections.observableArrayList();
    private Search search = new Search();

    private Image background;

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
            songsTableView.getItems().setAll(songs);
        }
    }

    @FXML
    private void playSong() throws IOException {
        if (!isProjecting) {
            isProjecting = true;
            Song song;
            if (!queueList.isEmpty()) {
                song = queueList.get(0);
                slideShow.open(song);
                queueList.remove(0);
                queueTableView.getItems().setAll(queueList);
                Image currentSlide = slideShow.currentSlide();
                setPreview(currentSlide);
                project.loadImage(currentSlide);
                Image stop = new Image("buttons/stop.png");
                playStop.setScaleX(1.1);
                playStop.setScaleY(1.1);
                playStop.setImage(stop);
                project.show();
            } else {
                isProjecting = false;
                AlertFactory
                        .createError("Kolejka jest pusta")
                        .showAndWait();
            }
        }
        else stopSong();


    }

    @FXML
    private void stopSong() {
        queueTableView.getItems().setAll(queueList);
        isProjecting = false;
        project.loadImage(background);
        setPreview(background);
        Image play = new Image("buttons/play.png");
        playStop.setScaleX(0.8);
        playStop.setScaleY(0.8);
        playStop.setImage(play);

    }

    @FXML
    private void nextSlide() {
        try {
            Image currentSlide = slideShow.nextSlide();
            setPreview(currentSlide);
            project.loadImage(currentSlide);
        } catch (RuntimeException e) {
            nextPresentation();
        }
    }

    @FXML
    private void previousSlide() {
        try {
            Image currentSlide = slideShow.prevSlide();
            setPreview(currentSlide);
            project.loadImage(currentSlide);
        } catch (RuntimeException ignored) {

        }
    }

    private void nextPresentation() {
        try {
            this.queueTableView.getItems().setAll(queueList);
            if (!queueList.isEmpty())
                playSong();
            else
                stopSong();
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
                if (isProjecting)
                    stopSong();
                else
                    playSong();
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

    private void setPreview(Image currentSlide) {
        this.imagePreview.setImage(currentSlide);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        background = new Image("/obrazy/background.jpg");

        queueCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        queueNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        searchCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        searchTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        songsTableView.getItems().setAll(search.findByTitle(""));

        songsTableView.setOnMouseClicked((MouseEvent event) -> {
            Song song = songsTableView.getSelectionModel().getSelectedItem();
            Image currentSlide;
            if (!isProjecting && !songsTableView.getItems().isEmpty() && song != null) {
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
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/desktop"));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File file = fileChooser.showOpenDialog(null);
            if(file != null) {
                String path = file.toURI().toString();
                background = new Image(path);
                setPreview(background);
                project.loadImage(background);
                try {
                    if(path.endsWith(".jpg")) {
                        saveImage("jpg");
                    } else if(path.endsWith(".png"))
                        saveImage("png");

                } catch (IOException e) {
                    AlertFactory
                            .createError("Wystąpił błąd podczas utawiania obrazu")
                            .showAndWait();
                }
            }
        });

        project.show();

        setPreview(background);
    }

    private void saveImage(String jpg) throws IOException {
        ImageIO.write(SwingFXUtils.fromFXImage(background, null), jpg, new File("src\\main\\resources\\obrazy\\background.jpg"));
    }
}
