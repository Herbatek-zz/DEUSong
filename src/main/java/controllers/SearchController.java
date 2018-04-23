package controllers;

import dontKnowHotToNameItXD.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

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

    private ObservableList<Song> list = FXCollections.observableArrayList();

    @FXML
    private void search(KeyEvent event) {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        songNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        songsTableView.getItems().setAll(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
